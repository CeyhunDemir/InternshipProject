package com.sd.stockmanagementsystem.domain.service.implementation;

import com.sd.stockmanagementsystem.application.dto.core.LocationKey;
import com.sd.stockmanagementsystem.application.dto.core.ProductKey;
import com.sd.stockmanagementsystem.application.dto.core.StockKey;
import com.sd.stockmanagementsystem.application.dto.request.AddStockRequestDTO;
import com.sd.stockmanagementsystem.application.dto.request.MoveMultipleProductsInStockRequestDTO;
import com.sd.stockmanagementsystem.application.dto.request.MoveProductInStockRequestDTO;
import com.sd.stockmanagementsystem.domain.model.Location;
import com.sd.stockmanagementsystem.domain.model.Product;
import com.sd.stockmanagementsystem.domain.model.Stock;
import com.sd.stockmanagementsystem.domain.service.ILocationService;
import com.sd.stockmanagementsystem.domain.service.IProductService;
import com.sd.stockmanagementsystem.domain.service.IStockService;
import com.sd.stockmanagementsystem.infrastructure.adapter.out.persistence.repository.StockRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.LockModeType;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.modelmapper.internal.Pair;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class StockServiceImpl implements IStockService {
    private final StockRepository stockRepository;
    private final IProductService productService;
    private final ILocationService locationService;
    private final EntityManager entityManager;

    @Override
    public Double findQuantityInStockByStockKey(StockKey stockKey) {
        Optional<Stock> stock = stockRepository.findByProduct_NameOrProduct_BarcodeAndLocation_Name(stockKey.getProductKey().getName(), stockKey.getProductKey().getBarcode(), stockKey.getLocationKey().getName());
        if (stock.isPresent()) {
            return stock.get().getQuantity();
        } else {
            throw new EntityNotFoundException("No Stock found for StockKey: " + stockKey);
        }
    }

    @Override
    public Stock findStockByStockKey(StockKey stockKey) {
        Optional<Stock> stock = stockRepository.findByProduct_NameOrProduct_BarcodeAndLocation_Name(stockKey.getProductKey().getName(), stockKey.getProductKey().getBarcode(), stockKey.getLocationKey().getName());
        if (stock.isPresent()) {
            return stock.get();
        } else {
            throw new EntityNotFoundException("No Stock found for StockKey: " + stockKey);
        }
    }

    @Override
    public Map<StockKey, Stock> findStocksByStockKeys(Set<StockKey> stockKeys) {
        if (stockKeys == null || stockKeys.isEmpty()) {
            return Collections.emptyMap();
        }
        Set<String> productNames = new HashSet<>();
        Set<String> barcodes = new HashSet<>();
        Set<String> locationNames = new HashSet<>();

// Process all sets in one pass
        stockKeys.forEach(stockKey -> {
            if (stockKey.getProductKey().getName() != null) {
                productNames.add(stockKey.getProductKey().getName());
            }
            if (stockKey.getProductKey().getBarcode() != null) {
                barcodes.add(stockKey.getProductKey().getBarcode());
            }
            if (stockKey.getLocationKey().getName() != null) {
                locationNames.add(stockKey.getLocationKey().getName());
            }
        });

        List<Stock> stocks = stockRepository.findStocksByProductNamesOrBarcodesAndLocations(productNames, barcodes, locationNames);
        return stocks.stream().collect(Collectors.toMap(entry ->
                        StockKey.builder()
                                .locationKey(LocationKey.builder()
                                        .name(entry.getLocation().getName())
                                        .build())
                                .productKey(ProductKey.builder()
                                        .barcode(entry.getProduct().getBarcode())
                                        .name(entry.getProduct().getName())
                                        .build())
                                .build()
                ,
                entry -> entry
        ));
    }

    @Override
    public Stock findStockByIdForUpdate(long id) {
        Optional<Stock> stock = stockRepository.findByIdForUpdate(id);
        if (stock.isPresent()) {
            return stock.get();
        }
        throw new EntityNotFoundException("No Stock found with id: " + id);
    }

    @Override
    @Transactional
    public void updateStockQuantityInBulk(Map<Long, Double> stockQuantityMap) {
        // Step 1: Select rows with a pessimistic lock
        List<Long> ids = new ArrayList<>(stockQuantityMap.keySet());
        entityManager.createQuery("SELECT s FROM Stock s WHERE s.id IN (:ids)")
                .setParameter("ids", ids)
                .setLockMode(LockModeType.PESSIMISTIC_WRITE) // Lock rows for update
                .getResultList();

        // Step 2: Perform the update
        StringBuilder query = new StringBuilder("UPDATE Stock s SET s.quantity = CASE ");

        for (Map.Entry<Long, Double> entry : stockQuantityMap.entrySet()) {
            query.append("WHEN s.id = ")
                    .append(entry.getKey())
                    .append(" THEN s.quantity + ")
                    .append(entry.getValue())
                    .append(" ");
        }

        query.append("END WHERE s.id IN (:ids)");

        entityManager.createQuery(query.toString())
                .setParameter("ids", ids)
                .executeUpdate();
    }

    @Override
    public Stock addStock(AddStockRequestDTO addStockRequestDTO) {
        if (addStockRequestDTO.getProduct() != null && addStockRequestDTO.getLocation() != null) {
            Stock stock = new Stock();
            stock.setProduct(addStockRequestDTO.getProduct());
            stock.setLocation(addStockRequestDTO.getLocation());
            stock.setQuantity(0);
            stockRepository.save(stock);
            return stock;
        } else if ((addStockRequestDTO.getProductName() != null || addStockRequestDTO.getBarcode() != null) && addStockRequestDTO.getLocationName() != null) {
            Stock stock = new Stock();
            stock.setProduct(productService.findProductByProductKey(ProductKey.builder().name(addStockRequestDTO.getProductName()).barcode(addStockRequestDTO.getBarcode()).build()));
            stock.setLocation(locationService.findByLocationKey(LocationKey.builder().name(addStockRequestDTO.getLocationName()).build()));
            stock.setQuantity(0);
            stockRepository.save(stock);
            return stock;
        } else {
            throw new EntityNotFoundException("bla bla");
        }
    }

    @Override
    public void MoveProductInStock(MoveProductInStockRequestDTO moveProductInStockRequestDTO) {


    }

    @Override
    @Transactional
    public void moveMultipleProductsInStock(MoveMultipleProductsInStockRequestDTO moveMultipleProductsInStockRequestDTO) {
        List<MoveProductInStockRequestDTO> moveProductInStockRequestDTOS = moveMultipleProductsInStockRequestDTO.getMoveRequests();

        //Re arranging the requests so that if a specific stock is being sent to the same place in different requests it sums their quantities and makes it only one request
        List<MoveProductInStockRequestDTO> uniqueMoveProductInStockRequestDTOS = moveProductInStockRequestDTOS.stream()
                .collect(Collectors.toMap(
                        dto -> dto.getStockId() + "|" + dto.getDestinationId(),
                        dto -> dto,
                        (dto1, dto2) -> {
                            dto1.setQuantity(dto1.getQuantity() + dto2.getQuantity());
                            return dto1;
                        }
                ))
                .values()
                .stream().toList();

        Map<Long, MoveProductInStockRequestDTO> moveProductInStockRequestDTOSGroupedByOrigin = moveProductInStockRequestDTOS.stream()
                .collect(Collectors.toMap(
                        dto -> dto.getStockId(),
                        dto -> dto,
                        (dto1, dto2) -> {
                            dto1.setQuantity(dto1.getQuantity() + dto2.getQuantity());
                            return dto1;
                        }
                ))
                .values()
                .stream()
                .collect(Collectors.toMap(
                        dto -> dto.getStockId(),
                        dto -> dto
                ));


        //Forming the request map for eliminating further for loops, separating stock id list to use as an input
        Map<Long, MoveProductInStockRequestDTO> moveProductRequests = new HashMap<>();
        List<Long> ids = new ArrayList<>();
        List<Long> destinationIds = new ArrayList<>();

        uniqueMoveProductInStockRequestDTOS.forEach(moveProductInStockRequestDTO -> {
            if (moveProductInStockRequestDTO.getStockId() != null) {
                ids.add(moveProductInStockRequestDTO.getStockId());
            }
            if (moveProductInStockRequestDTO.getDestinationId() != null) {
                destinationIds.add(moveProductInStockRequestDTO.getDestinationId());
            }
            moveProductRequests.put(moveProductInStockRequestDTO.getStockId(), moveProductInStockRequestDTO);
        });

        //Creating the origin stock map for later use
        List<Stock> originStocks = stockRepository.findByIdIn(ids);
        Map<Long, Stock> idOriginStockMap = originStocks.stream().collect(Collectors.toMap(
                stock -> {
                    Double moveQuantity = moveProductInStockRequestDTOSGroupedByOrigin.get(stock.getId()).getQuantity();
                    if (stock.getQuantity() < moveQuantity) {
                        throw new IllegalArgumentException("Stock of " + stock.getProduct().getName() + " is " + stock.getQuantity() + " but you are attempting to move " + moveQuantity);
                    } else return stock.getId();
                },
                stock -> stock
        ));

        //Forming a set of pairs that lists all the destinations needed before the transfer operation.
        Map<Pair<Long, Long>, MoveProductInStockRequestDTO> possibleDestinations = uniqueMoveProductInStockRequestDTOS.stream()
                .collect(Collectors.toMap(
                        entry -> Pair.of(idOriginStockMap.get(entry.getStockId()).getProduct().getId(), entry.getDestinationId()),
                        entry -> entry
                ));

        //Finding and listing the destinations that does not exist yet.
        Set<Pair<Long, Long>> missingDestinations = new HashSet<>();
        StringBuilder query = new StringBuilder(
                "WITH pairs AS ( " +
                        "    SELECT CAST(product_id AS BIGINT) AS product_id, CAST(location_id AS BIGINT) AS location_id " +
                        "    FROM (VALUES ");

        for (Pair<Long, Long> possibleDestination : possibleDestinations.keySet()) {
            query.append("(")
                    .append(possibleDestination.getLeft()).append(", ")
                    .append(possibleDestination.getRight()).append("),");
        }

// Remove the trailing comma
        query.deleteCharAt(query.length() - 1);

        query.append(") AS temp(product_id, location_id) " +
                ") " +
                "SELECT pairs.product_id, pairs.location_id, " +
                "       CASE WHEN EXISTS ( " +
                "           SELECT 1 FROM Stock s " +
                "           WHERE s.product_id = pairs.product_id AND s.location_id = pairs.location_id " +
                "       ) THEN 1 ELSE 0 END AS exists_flag " +
                "FROM pairs");

// Execute the query
        List<Object[]> results = entityManager.createNativeQuery(query.toString(), Object[].class).getResultList();

        for (Object[] result : results) {
            Long productId = ((Number) result[0]).longValue(); // Cast to Long
            Long locationId = ((Number) result[1]).longValue(); // Cast to Long
            Long matchFlag = ((Number) result[2]).longValue(); // Cast to Long

            // Use the pair as desired
            Pair<Long, Long> searchedPair = Pair.of(productId, locationId);
            if (matchFlag == 0) {
                missingDestinations.add(searchedPair);
            }
        }

        //Get the location and product entities in bulk to create new stocks.
        Set<Long> productIds = new HashSet<>();
        Set<Long> locationIds = new HashSet<>();

        missingDestinations.forEach(entry -> {
            productIds.add(entry.getLeft());
            locationIds.add(entry.getRight());
        });

        Map<Long, Product> products = productService.findProductsByIds(productIds);
        Map<Long, Location> locations = locationService.findLocationsByIds(locationIds);

        //Creating the new stocks list then saving them to the stocks
        List<Stock> newStocks = missingDestinations.stream()
                .map(entry -> {
                    Product product = products.get(entry.getLeft());
                    Location location = locations.get(entry.getRight());
                    Stock stock = new Stock();
                    stock.setProduct(product);
                    stock.setLocation(location);
                    stock.setQuantity(0.0);
                    return stock;
                })
                .collect(Collectors.toList());
        stockRepository.saveAll(newStocks);
        entityManager.flush();
        entityManager.clear();

        //Selecting all the origin and destination stock entries and LOCKING them.
        List<Stock> lockedStocks = entityManager.createQuery(
                        "SELECT s FROM Stock s WHERE s.id IN (:ids) " +
                                "OR (s.product.id IN :productIds AND s.location.id IN (:locationIds))",
                        Stock.class)
                .setParameter("ids", ids)
                .setParameter("locationIds", destinationIds)
                .setParameter("productIds", possibleDestinations.keySet().stream().map(Pair::getLeft).collect(Collectors.toSet()))
                .setLockMode(LockModeType.PESSIMISTIC_WRITE)
                .getResultList(); // Lock rows for update

        entityManager.flush();
        entityManager.clear();


        //Updating origin stocks

        StringBuilder updateQuery2 = new StringBuilder("UPDATE Stock s SET s.quantity = CASE ");

        for (Stock stock : originStocks) {
            updateQuery2.append("WHEN s.id = ")
                    .append(stock.getId())
                    .append(" THEN s.quantity-")
                    .append(moveProductRequests.get(stock.getId()).getQuantity())
                    .append(" ");
        }
        updateQuery2.append("END WHERE s.id IN (:ids)");

        entityManager.createQuery(updateQuery2.toString())
                .setParameter("ids", ids)
                .executeUpdate();
        entityManager.flush();
        entityManager.clear();


        //Updating destination stocks
        StringBuilder updateQuery = new StringBuilder("UPDATE Stock s SET s.quantity = CASE ");

        for (Pair<Long, Long> destination : possibleDestinations.keySet()) {
            updateQuery.append("WHEN s.product.id = ")
                    .append(destination.getLeft())
                    .append(" AND s.location.id = ")
                    .append(destination.getRight())
                    .append(" THEN s.quantity+")
                    .append(possibleDestinations.get(destination).getQuantity())
                    .append(" ");
        }
        updateQuery.append("END WHERE ");
        for (Pair<Long, Long> destination : possibleDestinations.keySet()) {
            updateQuery.append("(s.product.id = ")
                    .append(destination.getLeft())
                    .append(" AND s.location.id = ")
                    .append(destination.getRight())
                    .append(") OR ");
        }
        updateQuery.delete(updateQuery.length() - 4, updateQuery.length());

        entityManager.createQuery(updateQuery.toString())
/*                .setParameter("productIds", possibleDestinations.keySet().stream().map(Pair::getLeft).collect(Collectors.toSet()))
                .setParameter("locationIds", possibleDestinations.keySet().stream().map(Pair::getRight).collect(Collectors.toSet()))*/
                .executeUpdate();
        entityManager.flush();
        entityManager.clear();
    }
}

package com.sd.stockmanagementsystem.infrastructure.adapter.out.persistence.repository;

import com.sd.stockmanagementsystem.application.port.output.StockRepositoryPort;
import com.sd.stockmanagementsystem.domain.model.Stock;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface StockRepository extends JpaRepository<Stock, Long>, StockRepositoryPort {

    @Query("SELECT s FROM Stock s WHERE " +
            "(s.product.name IN :productNames OR s.product.barcode IN :productBarcodes) " +
            "AND s.location.name IN :locationNames")
    List<Stock> findStocksByProductNamesOrBarcodesAndLocations(
            @Param("productNames") Set<String> productNames,
            @Param("productBarcodes") Set<String> productBarcodes,
            @Param("locationNames") Set<String> locationNames);

    @Query("SELECT s FROM Stock s WHERE " +
            "(s.product.name = :productName OR s.product.barcode = :productBarcode) " +
            "AND s.location.name = :locationName")
    Optional<Stock> findByProduct_NameOrProduct_BarcodeAndLocation_Name(
            @Param("productName") String product_Name,
            @Param("productBarcode") String product_Barcode,
            @Param("locationName") String location_Name);

    List<Stock> findByIdIn(List<Long> ids);

    List<Stock> findByIdInAndLocation_IdIn(Set<Long> ids, Set<Long> locationId);


    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("select s from Stock s where s.id = :id")
    Optional<Stock> findByIdForUpdate(@Param("id") long id);

}

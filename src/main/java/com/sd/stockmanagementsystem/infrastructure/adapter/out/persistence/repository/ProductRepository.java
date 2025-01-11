package com.sd.stockmanagementsystem.infrastructure.adapter.out.persistence.repository;

import com.sd.stockmanagementsystem.application.port.output.ProductRepositoryPort;
import com.sd.stockmanagementsystem.domain.model.Product;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface ProductRepository extends JpaRepository<Product, Long>, ProductRepositoryPort {

    Optional<Product> findById(long id);

    Optional<Product> findByName(String name);

    Optional<Product> findByBarcode(String barcode);

    List<Product> findAllByBarcodeIn(Set<String> barcodes);

    List<Product> findAllByNameIn(Set<String> names);

    List<Product> findAllByIdIn(Set<Long> ids);

    List<Product> findAll();

    @Query(value = "SELECT product FROM Product product WHERE LOWER(product.name) LIKE LOWER(CONCAT('%', :subString, '%')) ORDER BY product.name ASC LIMIT 5")
    List<Product> findBySubstring(@Param("subString") String substring);


    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("select p from Product p where p.id = :id")
    Optional<Product> findByIdForUpdate(@Param("id") long id);

    /*@Modifying
    @Query(value = "UPDATE product p SET c.quantity = :status WHERE c.city = :city FOR UPDATE", nativeQuery = true)
    List<Product> updateByIds(@Param("ids") Map<Long, Double> ids);*/
}

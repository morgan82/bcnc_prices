package com.bcnc.price.adapter.out.pricerepository;

import com.bcnc.price.adapter.out.pricerepository.model.PriceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface PriceEntityRepository extends JpaRepository<PriceEntity, Long> {
    @Query(value = """
            SELECT * FROM prices pr
            WHERE pr.brand_uuid = :brandId
              AND pr.product_uuid = :productId
              AND pr.start_date <= :applicationDate
              AND pr.end_date > :applicationDate
            ORDER BY pr.priority DESC
            LIMIT 1
            """, nativeQuery = true)
    Optional<PriceEntity> findApplicablePrice(
            @Param("productId") UUID productId,
            @Param("brandId") UUID brandId,
            @Param("applicationDate") Instant applicationDate);
}

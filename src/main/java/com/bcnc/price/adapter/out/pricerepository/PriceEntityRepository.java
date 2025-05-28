package com.bcnc.price.adapter.out.pricerepository;

import com.bcnc.price.adapter.out.pricerepository.model.PriceEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.UUID;

@Repository
public interface PriceEntityRepository extends JpaRepository<PriceEntity, Long> {
    @Query("""
            SELECT p FROM PriceEntity p
            WHERE :applicationDate BETWEEN p.startDate AND p.endDate
              AND p.product.uuid = :productUuid
              AND p.brand.uuid= :brandUuid
            ORDER BY p.priority DESC
            """)
    Page<PriceEntity> findApplicablePrice(
            @Param("applicationDate") Instant applicationDate,
            @Param("productUuid") UUID productId,
            @Param("brandUuid") UUID brandId,
            Pageable pageable);
}

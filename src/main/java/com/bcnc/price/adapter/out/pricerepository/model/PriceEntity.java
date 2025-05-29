package com.bcnc.price.adapter.out.pricerepository.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

@Table(name = "prices",
        indexes = @Index(columnList = "product_uuid, brand_uuid, start_date, end_date, priority DESC"))
@Entity
@Getter
@Setter
public class PriceEntity extends AuditableEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @JdbcTypeCode(SqlTypes.VARCHAR)
    @Column(nullable = false, unique = true, length = 36)
    private UUID uuid;
    @JdbcTypeCode(SqlTypes.VARCHAR)
    private UUID brandUuid;
    @JdbcTypeCode(SqlTypes.VARCHAR)
    private UUID productUuid;
    @Column(name = "start_date", nullable = false)
    private Instant startDate;
    @Column(name = "end_date", nullable = false)
    private Instant endDate;
    @Column(name = "amount", nullable = false)
    private BigDecimal amount;
    @Column(name = "priority", nullable = false)
    private int priority;
    @Column(name = "currency", nullable = false, length = 3)
    private String currency;
}

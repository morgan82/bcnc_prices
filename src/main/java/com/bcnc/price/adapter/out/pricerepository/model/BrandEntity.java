package com.bcnc.price.adapter.out.pricerepository.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.UUID;

@Table(name = "brands")
@Entity
@Getter
@Setter
public class BrandEntity extends AuditableEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @JdbcTypeCode(SqlTypes.VARCHAR)
    @Column(nullable = false, unique = true, length = 36)
    private UUID uuid;
    @Column(nullable = false, length = 20)
    private String name;
}

package com.bcnc.price.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

@AllArgsConstructor
@Getter
@Setter
public class Price {
    private UUID id;
    private UUID brandId;
    private UUID productId;
    private Instant startDate;
    private Instant endDate;
    private BigDecimal amount;
    private int priority;
    private String currency;
}

package com.bcnc.price.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.Instant;

@AllArgsConstructor
@Getter
@Setter
public class Price {
    private Long id;
    private Brand brand;
    private Product product;
    private Instant startDate;
    private Instant endDate;
    private BigDecimal amount;
    private int priority;
    private String currency;
}

package com.bcnc.price.adapter.in.api.model;

import java.math.BigDecimal;

public record PriceRsDTO(
        String currency,
        BigDecimal amount,
        String productName,
        String productCode) {
}

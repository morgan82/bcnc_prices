package com.bcnc.price.adapter.in.api.model;

import java.math.BigDecimal;
import java.time.Instant;

public record PriceRsDTO(
        String productId,
        String brandId,
        String priceId,
        BigDecimal amount,
        Instant applicationDate) {
}

package com.bcnc.price.application.port.out;

import com.bcnc.price.domain.model.Price;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

public interface PriceRepository {
    Optional<Price> findApplicablePrice(Instant applicationDate, UUID productId, UUID brandId);
}

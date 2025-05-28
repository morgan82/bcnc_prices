package com.bcnc.price.application.port.in;

import com.bcnc.price.domain.model.Price;

import java.time.Instant;
import java.util.UUID;

public interface GetPriceUseCase {
    Price getPrice(Instant applicationDate, UUID productId, UUID brandId);
}

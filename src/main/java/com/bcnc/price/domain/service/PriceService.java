package com.bcnc.price.domain.service;

import com.bcnc.price.application.port.in.GetPriceUseCase;
import com.bcnc.price.domain.model.Price;

import java.time.Instant;

public class PriceService implements GetPriceUseCase {
    @Override
    public Price getPrice(Instant applicationDate, Long priceId, Long brandId) {
        return null;
    }
}

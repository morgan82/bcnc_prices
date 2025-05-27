package com.bcnc.price.application.port.in;

import com.bcnc.price.domain.model.Price;

import java.time.Instant;

public interface GetPriceUseCase {
    //TODO use UUID for business key?
    Price getPrice(Instant applicationDate, Long priceId, Long brandId);
}

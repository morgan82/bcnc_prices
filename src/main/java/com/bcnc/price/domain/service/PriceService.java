package com.bcnc.price.domain.service;

import com.bcnc.price.application.port.in.GetPriceUseCase;
import com.bcnc.price.application.port.out.PriceRepository;
import com.bcnc.price.domain.exception.PriceNotFoundException;
import com.bcnc.price.domain.model.Price;
import lombok.AllArgsConstructor;

import java.time.Instant;
import java.util.UUID;

@AllArgsConstructor
public class PriceService implements GetPriceUseCase {
    private final PriceRepository repository;

    @Override
    public Price getPrice(Instant applicationDate, UUID productId, UUID brandId) {
        return repository.findApplicablePrice(applicationDate, productId, brandId)
                .orElseThrow(() -> new PriceNotFoundException("Price not found for product_id: %s and brand_id: %s"
                        .formatted(productId, brandId)));
    }
}

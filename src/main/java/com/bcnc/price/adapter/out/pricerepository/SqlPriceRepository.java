package com.bcnc.price.adapter.out.pricerepository;

import com.bcnc.price.adapter.out.pricerepository.mapper.PriceSqlMapper;
import com.bcnc.price.application.port.out.PriceRepository;
import com.bcnc.price.domain.model.Price;
import lombok.AllArgsConstructor;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

@AllArgsConstructor
public class SqlPriceRepository implements PriceRepository {
    private final PriceEntityRepository repository;
    private final PriceSqlMapper mapper;

    @Override
    public Optional<Price> findApplicablePrice(Instant applicationDate, UUID productId, UUID brandId) {
        return repository.findApplicablePrice(productId, brandId, applicationDate).map(mapper::toPrice);
    }
}

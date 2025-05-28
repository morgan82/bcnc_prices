package com.bcnc.price.adapter.out.pricerepository;

import com.bcnc.price.adapter.out.pricerepository.mapper.PriceSqlMapper;
import com.bcnc.price.application.port.out.PriceRepository;
import com.bcnc.price.domain.model.Price;
import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.data.domain.PageRequest;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

import static org.springframework.util.CollectionUtils.isEmpty;

@AllArgsConstructor
public class SqlPriceRepository implements PriceRepository {
    private final PriceEntityRepository repository;
    private final PriceSqlMapper mapper;

    @Override
    public Optional<Price> findApplicablePrice(Instant applicationDate, UUID productId, UUID brandId) {
        val pricePage = repository.findApplicablePrice(applicationDate, productId, brandId, PageRequest.of(0, 1));
        val content = pricePage.map(mapper::toPrice).getContent();
        return isEmpty(content) ? Optional.empty() : Optional.ofNullable(content.getFirst());
    }
}

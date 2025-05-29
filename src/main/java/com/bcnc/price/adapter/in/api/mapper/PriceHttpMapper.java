package com.bcnc.price.adapter.in.api.mapper;

import com.bcnc.price.adapter.in.api.model.PriceRsDTO;
import com.bcnc.price.domain.model.Price;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.time.Instant;

@Mapper(unmappedTargetPolicy = ReportingPolicy.ERROR, componentModel = "spring")
public interface PriceHttpMapper {
    @Mapping(target = "productId", source = "price.productId")
    @Mapping(target = "priceId", source = "price.id")
    @Mapping(target = "brandId", source = "price.brandId")
    @Mapping(target = "applicationDate", source = "applicationDate")
    PriceRsDTO toResponse(Price price, Instant applicationDate);
}

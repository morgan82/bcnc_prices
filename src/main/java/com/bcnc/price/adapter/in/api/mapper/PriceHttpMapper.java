package com.bcnc.price.adapter.in.api.mapper;

import com.bcnc.price.adapter.in.api.model.PriceRsDTO;
import com.bcnc.price.domain.model.Price;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.ERROR, componentModel = "spring")
public interface PriceHttpMapper {
    @Mapping(target = "productCode", source = "product.code")
    @Mapping(target = "productName", source = "product.name")
    PriceRsDTO toResponse(Price price);
}

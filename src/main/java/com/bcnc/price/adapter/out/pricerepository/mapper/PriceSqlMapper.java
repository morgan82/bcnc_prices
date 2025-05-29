package com.bcnc.price.adapter.out.pricerepository.mapper;

import com.bcnc.price.adapter.out.pricerepository.model.PriceEntity;
import com.bcnc.price.domain.model.Price;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.ERROR, componentModel = "spring")
public interface PriceSqlMapper {

    @Mapping(target = "productId", source = "productUuid")
    @Mapping(target = "brandId", source = "brandUuid")
    @Mapping(target = "id", source = "uuid")
    Price toPrice(PriceEntity priceEntity);

}

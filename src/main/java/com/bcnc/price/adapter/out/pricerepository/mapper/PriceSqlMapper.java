package com.bcnc.price.adapter.out.pricerepository.mapper;

import com.bcnc.price.adapter.out.pricerepository.model.BrandEntity;
import com.bcnc.price.adapter.out.pricerepository.model.PriceEntity;
import com.bcnc.price.adapter.out.pricerepository.model.ProductEntity;
import com.bcnc.price.domain.model.Brand;
import com.bcnc.price.domain.model.Price;
import com.bcnc.price.domain.model.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.ERROR, componentModel = "spring")
public interface PriceSqlMapper {

    @Mapping(target = "id", source = "uuid")
    Price toPrice(PriceEntity priceEntity);

    @Mapping(target = "id", source = "uuid")
    Brand toBrand(BrandEntity brandEntity);

    @Mapping(target = "id", source = "uuid")
    Product toProduct(ProductEntity productEntity);
}

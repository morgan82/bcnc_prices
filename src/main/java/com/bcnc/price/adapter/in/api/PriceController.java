package com.bcnc.price.adapter.in.api;

import com.bcnc.price.adapter.in.api.mapper.PriceHttpMapper;
import com.bcnc.price.adapter.in.api.model.PriceRsDTO;
import com.bcnc.price.application.port.in.GetPriceUseCase;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.util.UUID;

@RestController
@RequestMapping("v1/prices")
@AllArgsConstructor
public class PriceController {
    private final GetPriceUseCase getPriceUseCase;
    private final PriceHttpMapper mapper;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public PriceRsDTO getPrice(
            @Schema(example = "2020-06-14T10:00:00.0Z") @RequestParam Instant applicationDate,
            @Schema(example = "18c5948a-b52c-425f-a854-e0b6efee84f5") @RequestParam UUID productId,
            @Schema(example = "9d02c1b7-56a9-4049-bc87-518d49a0eb78") @RequestParam UUID brandId) {
        val price = getPriceUseCase.getPrice(applicationDate, productId, brandId);
        return mapper.toResponse(price);
    }
}

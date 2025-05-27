package com.bcnc.price.adapter.in.api;

import com.bcnc.price.application.port.in.GetPriceUseCase;
import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;

@RestController
@RequestMapping("v1/prices")
@AllArgsConstructor
public class PriceController {
    private final GetPriceUseCase getPriceUseCase;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public void getPrice(
            @RequestParam Instant applicationDate,
            @RequestParam Long priceId,
            @RequestParam Long brandId) {
        val price = getPriceUseCase.getPrice(applicationDate, priceId, brandId);
        System.out.println(price);
    }
}

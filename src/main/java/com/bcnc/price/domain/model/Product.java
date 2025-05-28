package com.bcnc.price.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@AllArgsConstructor
@Getter
@Setter
public class Product {
    private UUID id;
    private String name;
    private String code;
    private String description;
}

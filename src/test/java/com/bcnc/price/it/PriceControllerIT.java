package com.bcnc.price.it;

import com.bcnc.price.adapter.in.api.model.PriceRsDTO;
import lombok.val;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;

import java.math.BigDecimal;
import java.net.URI;
import java.time.Instant;
import java.util.UUID;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PriceControllerIT {
    @LocalServerPort
    private int port;
    @Autowired
    private TestRestTemplate restTemplate;

    static Stream<Arguments> priceRequests() {
        return Stream.of(
                //test 1
                Arguments.of(Instant.parse("2020-06-14T10:00:00.00Z"), UUID.fromString("18c5948a-b52c-425f-a854-e0b6efee84f5"), UUID.fromString("9d02c1b7-56a9-4049-bc87-518d49a0eb78"), "35.50"),
                //test 2
                Arguments.of(Instant.parse("2020-06-14T16:00:00.00Z"), UUID.fromString("18c5948a-b52c-425f-a854-e0b6efee84f5"), UUID.fromString("9d02c1b7-56a9-4049-bc87-518d49a0eb78"), "25.45"),
                //test 3
                Arguments.of(Instant.parse("2020-06-14T21:00:00.00Z"), UUID.fromString("18c5948a-b52c-425f-a854-e0b6efee84f5"), UUID.fromString("9d02c1b7-56a9-4049-bc87-518d49a0eb78"), "35.50"),
                //test 4
                Arguments.of(Instant.parse("2020-06-15T10:00:00.00Z"), UUID.fromString("18c5948a-b52c-425f-a854-e0b6efee84f5"), UUID.fromString("9d02c1b7-56a9-4049-bc87-518d49a0eb78"), "30.50"),
                //test 5
                Arguments.of(Instant.parse("2020-06-16T21:00:00.00Z"), UUID.fromString("18c5948a-b52c-425f-a854-e0b6efee84f5"), UUID.fromString("9d02c1b7-56a9-4049-bc87-518d49a0eb78"), "38.95")
        );
    }


    @ParameterizedTest(name = "Should return {3} for date={0}, product={1}, brand={2}")
    @MethodSource("priceRequests")
    void shouldReturnPriceForValidRequests(Instant applicationDate, UUID productId, UUID brandId, String expectedAmount) {

        // Given:
        val url = String.format("http://localhost:%d/v1/prices?applicationDate=%s&productId=%s&brandId=%s",
                port, applicationDate, productId, brandId);

        // When
        val response = restTemplate.exchange(URI.create(url), HttpMethod.GET, null, PriceRsDTO.class);

        // Then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        val priceRsDTO = response.getBody();
        assertThat(priceRsDTO).isNotNull();
        assertThat(priceRsDTO.amount()).isEqualTo(new BigDecimal(expectedAmount));
        assertThat(priceRsDTO.priceId()).isNotNull();
        assertThat(priceRsDTO.productId()).isEqualTo(productId.toString());
        assertThat(priceRsDTO.brandId()).isEqualTo(brandId.toString());
        assertThat(priceRsDTO.applicationDate()).isEqualTo(applicationDate);

    }

    @Test
    void shouldReturnPriceForInvalidRequests() {

        // Given:
        val applicationDate = Instant.parse("1900-06-14T10:00:00.00Z");
        val productId = UUID.fromString("18c5948a-b52c-425f-a854-e0b6efee84f5");
        val brandId = UUID.fromString("9d02c1b7-56a9-4049-bc87-518d49a0eb78");
        val url = String.format("http://localhost:%d/v1/prices?applicationDate=%s&productId=%s&brandId=%s",
                port, applicationDate, productId, brandId);

        // When
        val response = restTemplate.exchange(URI.create(url), HttpMethod.GET, null, PriceRsDTO.class);

        // Then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }

}

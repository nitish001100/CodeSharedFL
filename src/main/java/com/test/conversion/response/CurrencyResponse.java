package com.test.conversion.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CurrencyResponse<T> {
    private String from;
    private String to;
    private Integer to_amount;
    private BigDecimal exchange_rate;
    private BigDecimal result;
    private boolean repute;
    private int status;
    private String message;
    private String description;
    private T data;
}

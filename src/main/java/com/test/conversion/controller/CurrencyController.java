package com.test.conversion.controller;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import com.test.conversion.model.Currency;
import com.test.conversion.model.CurrencyRepository;
import com.test.conversion.response.CurrencyResponse;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class CurrencyController {
    @Autowired
    private CurrencyRepository currencyRepository;

    @GetMapping("/currencies")
    public List<Currency> getAllCurrencies() {
        return currencyRepository.findAll();
    }

    @ApiOperation(value = "Exchange Amount")
    @GetMapping(path = "/exchange_amount/from/{from}/to/{to}/amount/{from_amount}")
    @ApiImplicitParam(paramType = "header", dataTypeClass = String.class)
    public ResponseEntity<CurrencyResponse> exchange_amount(
            @PathVariable String from,
            @PathVariable String to,
            @PathVariable String from_amount) throws Exception {

        List<Currency> all_currencies = currencyRepository.findAll();

        Map<String, String> all_currencies_mapping = all_currencies.stream().collect(
                Collectors.toMap(x -> x.getCurrency(), x -> x.getAmount()));

        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url("https://api.apilayer.com/exchangerates_data/convert?to=GBP&from=JPY&amount=25")
                .addHeader("apikey", "LQmLPYJti28DIQ6CvE0fnLvera9wtuY1")
                .method("GET", null)
                .build();

        Response response = client.newCall(request).execute();
        String res = response.body().string();
        JSONObject obj = new JSONObject(res);
        String f = (String) ((JSONObject) obj.get("query")).get("from");
        String t = (String) ((JSONObject) obj.get("query")).get("to");
        BigDecimal rate = (BigDecimal) ((JSONObject) obj.get("info")).get("rate");
        BigDecimal result = (BigDecimal) obj.get("result");
        Integer amount = (Integer) ((JSONObject) obj.get("query")).get("amount");

        return new ResponseEntity<>(CurrencyResponse.builder()
                .from(f)
                .to(t)
                .to_amount(amount)
                .exchange_rate(rate)
                .result(result)
                .repute(true)
                .status(HttpStatus.CREATED.value())
                .message("Conversion Done")
                .description("Conversion done")
                .build(), HttpStatus.CREATED);
    }
}
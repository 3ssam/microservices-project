package com.microservices.currencyconversionservice.controllers;

import com.microservices.currencyconversionservice.models.CurrencyConversionObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@RestController
public class CurrencyConversionController {

    @GetMapping("/currency-converter/from/{from}/to/{to}/quantity/{quantity}")
    public CurrencyConversionObject convertCurrency(@PathVariable String from,
                                                    @PathVariable String to,
                                                    @PathVariable BigDecimal quantity){

        Map<String, String> uriVarible = new HashMap<>();
        uriVarible.put("from",from);
        uriVarible.put("to",to);

        ResponseEntity<? extends CurrencyConversionObject> entity = new RestTemplate()
                .getForEntity("http://localhost:8000/currency-exchange/from/{from}/to/{to}",
                        CurrencyConversionObject.class,
                        uriVarible);

        CurrencyConversionObject object = entity.getBody();
        return new CurrencyConversionObject(object.getId(),
                from,
                to,
                object.getConversionMultiple(),
                quantity,
                (quantity.multiply(object.getConversionMultiple())),
                object.getPort());
    }

}

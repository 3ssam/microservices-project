package com.microservices.currencyexchangeservice.Controllers;

import com.microservices.currencyexchangeservice.Repositories.ExchangeValueRepository;
import com.microservices.currencyexchangeservice.models.ExchangeValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
public class CurrencyExchangeController {

    @Autowired
    Environment environment;

    @Autowired
    ExchangeValueRepository exchangeValueRepository;


    @GetMapping("/currency-exchange/from/{from}/to/{to}")
    public ExchangeValue retrieveExchangeValue(@PathVariable String from, @PathVariable String to){
        ExchangeValue exchangeValue ;//= new ExchangeValue((long) 1000, from, to, BigDecimal.valueOf(65));
        exchangeValue = exchangeValueRepository.findByFromAndTo(from,to);
        exchangeValue.setPort(Integer.parseInt(environment.getProperty("server.port")));
        return exchangeValue;
    }
}

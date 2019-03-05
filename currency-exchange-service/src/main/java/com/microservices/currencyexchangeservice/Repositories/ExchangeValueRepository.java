package com.microservices.currencyexchangeservice.Repositories;

import com.microservices.currencyexchangeservice.models.ExchangeValue;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExchangeValueRepository extends JpaRepository<ExchangeValue,Long> {

    ExchangeValue findByFromAndTo(String from,String to);
}

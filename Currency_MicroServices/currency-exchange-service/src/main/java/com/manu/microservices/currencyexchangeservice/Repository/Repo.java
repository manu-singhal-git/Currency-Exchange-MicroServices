package com.manu.microservices.currencyexchangeservice.Repository;

import com.manu.microservices.currencyexchangeservice.Model.CurrencyExchange;
import org.springframework.boot.json.JacksonJsonParser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Repo extends JpaRepository<CurrencyExchange,Long> {
     CurrencyExchange findByFromAndTo(String From,String To);

}

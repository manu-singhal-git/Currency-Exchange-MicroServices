package com.manu.microservices.currencyconversionservice.proxy;

import com.manu.microservices.currencyconversionservice.Model.CurrencyConversion;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="currency-exchange")
public interface CurrencyExchangeProxy {
    @GetMapping("currency-exchange/from/{from}/to/{to}")
    public CurrencyConversion retreiveExchangeValue(@PathVariable String from, @PathVariable String to);

}

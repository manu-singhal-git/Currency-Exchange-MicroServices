package com.manu.microservices.currencyconversionservice.Controllers;

import com.manu.microservices.currencyconversionservice.Model.CurrencyConversion;
import com.manu.microservices.currencyconversionservice.proxy.CurrencyExchangeProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.couchbase.CouchbaseProperties;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.HashMap;

@RestController
public class CurrencyConversionController {
    @Autowired
    private CurrencyExchangeProxy exchangeProxy;
    @Autowired
    private Environment environment;
    @GetMapping("/currency-conversion/from/{from}/to/{to}/quantity/{quantity}")
    public CurrencyConversion conversion(@PathVariable String from,@PathVariable String to,@PathVariable BigDecimal quantity){
        HashMap<String,String> arr=new HashMap<>();
        arr.put("from",from);
        arr.put("to",to);
        ResponseEntity<CurrencyConversion> currencyConversionResponseEntity=new RestTemplate().getForEntity("http://localhost:8000/currency-exchange/from/{from}/to/{to}", CurrencyConversion.class,arr);
        CurrencyConversion body=currencyConversionResponseEntity.getBody();
        return new CurrencyConversion(body.getId(), body.getFrom(), body.getTo(),body.getConversionMultiple(), body.getEnvironment()+"From Rest Template",quantity,quantity.multiply(body.getConversionMultiple()));
       //return new CurrencyConversion(1001L,from,to, BigDecimal.valueOf(65), environment.getProperty("local.server.port"),BigDecimal.valueOf(10));
    }
    @GetMapping("/currency-conversion-feign/from/{from}/to/{to}/quantity/{quantity}")
    public CurrencyConversion conversionfeign(@PathVariable String from,@PathVariable String to,@PathVariable BigDecimal quantity){
      CurrencyConversion body=exchangeProxy.retreiveExchangeValue(from,to);
        return new CurrencyConversion(body.getId(), body.getFrom(), body.getTo(),body.getConversionMultiple(), body.getEnvironment()+"From feign",quantity,quantity.multiply(body.getConversionMultiple()));
    }
}

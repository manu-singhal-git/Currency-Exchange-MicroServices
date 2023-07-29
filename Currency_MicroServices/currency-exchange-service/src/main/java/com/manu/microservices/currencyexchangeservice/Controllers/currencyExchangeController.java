package com.manu.microservices.currencyexchangeservice.Controllers;

import com.manu.microservices.currencyexchangeservice.Repository.Repo;
import com.manu.microservices.currencyexchangeservice.Model.CurrencyExchange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class currencyExchangeController {
    @Autowired
    private Environment environment;
    @Autowired
    private Repo exchangerepo;
    @GetMapping("/currency-exchange/from/{from}/to/{to}")
    public CurrencyExchange retriveExchangeValue(@PathVariable String from, @PathVariable String to){
        CurrencyExchange currencyExchange=exchangerepo.findByFromAndTo(from,to);
        if(currencyExchange==null){
            throw new RuntimeException("Unable to find Data");
        }
        String port= environment.getProperty("local.server.port");
        currencyExchange.setEnvironment(port);
        return currencyExchange;
        // port=environment.getProperty("local.server.port");
        //return new CurrencyExchange(10001L,from,to, BigDecimal.valueOf(65),port);
    }
}

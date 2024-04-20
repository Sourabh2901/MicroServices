package com.sourabh.currencyexchange.Controller;

import com.sourabh.currencyexchange.Entity.CurrencyExchange;
import com.sourabh.currencyexchange.Repository.CurrencyExchangeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
public class CurrencyExchangeController {

    @Autowired
    private Environment env;

    @Autowired
    private CurrencyExchangeRepo repo;

    @GetMapping("/currency-exchange/from/{from}/to/{to}")
    public CurrencyExchange retrieveExchangeValue(@PathVariable String from , @PathVariable String to){
        CurrencyExchange obj = repo.findByFromAndTo(from ,to);
        if(obj == null) throw new RuntimeException("Unable to find requested data");
        obj.setEnvironment(env.getProperty("local.server.port"));
        return obj;

//        return  CurrencyExchange.builder()
//                .id(1000L)
//                .from(from)
//                .to(to)
//                .conversionMultiple(BigDecimal.valueOf(50))
//                .environment(env.getProperty("local.server.port"))
//                .build();
    }

}

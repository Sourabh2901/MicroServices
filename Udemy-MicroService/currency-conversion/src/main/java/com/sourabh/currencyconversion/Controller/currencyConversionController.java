package com.sourabh.currencyconversion.Controller;

import com.sourabh.currencyconversion.Entity.CurrencyConversion;
import com.sourabh.currencyconversion.ProxyEntity.CurrencyExchangeProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.HashMap;

@RestController
public class currencyConversionController {
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private CurrencyExchangeProxy proxy;

//    Communicating MicroService from RestTemplate
    @GetMapping("/currency-conversion/from/{from}/to/{to}/quantity/{quantity}")
    public CurrencyConversion calculateCurrencyConversion(@PathVariable String from ,
                                                          @PathVariable String to ,
                                                          @PathVariable BigDecimal quantity
                                                          ){
        HashMap<String ,String> map = new HashMap<>();
        map.put("from" ,from);
        map.put("to" ,to);
        ResponseEntity<CurrencyConversion> response = restTemplate.
                getForEntity("http://localhost:8000/currency-exchange/from/{from}/to/{to}",
                CurrencyConversion.class,
                map);

        CurrencyConversion body = response.getBody();
        body.setQuantity(quantity);
        body.setTotalCalculatedAmount(body.getConversionMultiple().multiply(quantity));
        return body;
    }

//    Communicating MicroService with Feign Client
    @GetMapping("/currency-conversion-feign/from/{from}/to/{to}/quantity/{quantity}")
    public CurrencyConversion calculateCurrencyConversionFeign(@PathVariable String from ,
                                                          @PathVariable String to ,
                                                          @PathVariable BigDecimal quantity){
        CurrencyConversion currencyConversion = proxy.retrieveExchangeValue(from, to);
        currencyConversion.setQuantity(quantity);
        currencyConversion.setTotalCalculatedAmount(quantity.multiply(currencyConversion.getConversionMultiple()));
        return currencyConversion;
    }


}

package com.sourabh.currencyexchange.Controller;

import io.github.resilience4j.retry.annotation.Retry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class CircuitBreakerController {

    private Logger logger = LoggerFactory.getLogger(CircuitBreakerController.class);
    @GetMapping("/sample-api")
    @Retry(name = "sample-api" ,fallbackMethod = "hardCodedFallBackMethod")
    public String sampleAPI(){
        logger.info("Sample Api Call received");
        ResponseEntity<String> response = new RestTemplate().getForEntity("http://localhost:8080/dummyUrl", String.class);
        return response.getBody();
    }

    public String hardCodedFallBackMethod(Exception ex){
        return "Fallback-response";
    }
}

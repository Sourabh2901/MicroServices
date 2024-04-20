package com.sourabh.limitsservice.Controllers;

import com.sourabh.limitsservice.Config.LimitConfig;
import com.sourabh.limitsservice.Entity.Limits;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LimitController {

    @Autowired
    private LimitConfig limitConfig;

    @GetMapping("/limits")
    public Limits retriveLimits(){
//        return new Limits(1,1000);
        return new Limits(limitConfig.getMinimum() , limitConfig.getMaximum());
    }
}

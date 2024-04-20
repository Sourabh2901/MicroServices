package com.sourabh.limitsservice.Config;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties("limits-service")
@Data
public class LimitConfig {

    private int minimum;
    private int maximum;
}

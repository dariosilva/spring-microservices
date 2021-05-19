package com.dams.microservices.currencyexchangeservice.controller;

import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class CircuitBreakerController {

    private final Logger log = LoggerFactory.getLogger(CircuitBreakerController.class);

    @GetMapping("/sample-api")
    //@Retry(name = "sample-api", fallbackMethod = "hardcodedResponse")
    //@CircuitBreaker(name = "sample-api", fallbackMethod = "hardcodedResponse")
    //@RateLimiter(name = "default") // 10s => 10000 calls to the sample api
    @Bulkhead(name = "sample-api") // concurrent calls
    public String sampleApi() {
        log.info("Sample API call received");
        //new RestTemplate().getForEntity("http:/localhost:8080/some-dummy-url", String.class);
        return "Sample API";
    }

    public String hardcodedResponse(Exception exception) {
        return "Fallback response";
    }
}

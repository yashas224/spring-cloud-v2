package com.microservices.currencyexchangeservice.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;

@RestController
public class CircuitBreakerController {

	private Logger logger = LoggerFactory.getLogger(CircuitBreakerController.class);

	@GetMapping("/sample-api")
	// @Retry(name = "sample-api", fallbackMethod = "fallBackMethod")
	// @CircuitBreaker(name = "default", fallbackMethod = "fallBackMethod")
	@RateLimiter(name = "default")
	public String sampleApi() {
		logger.info("Sample api call received");
		String forObject = "sample API returned";
		// forObject= new RestTemplate().getForObject("http://localhost:8080/some-url",
		// String.class);
		return forObject;
	}

	public String fallBackMethod(Exception ex) {
		return "fallBack response";
	}
}
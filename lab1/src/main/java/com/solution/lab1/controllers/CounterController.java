package com.solution.lab1.controllers;

import counter.RequestCounter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CounterController {
    Logger logger = LogManager.getLogger(CounterController.class);

    @GetMapping(value = "/counter")
    public String getCounter() {
        logger.info("Visited RequestCounterController");
        return RequestCounter.getRequestsCount() + " запросов было выполнено";
    }
}

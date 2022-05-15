package com.solution.lab1.controllers;
import com.solution.lab1.exceptions.IntervalsExceptions;
import com.solution.lab1.ResponseHandler;
import com.solution.lab1.Services.SolutionService;
import com.solution.lab1.Cache.SolutionHash;
import com.solution.lab1.entity.SolutionParameters;
import counter.RequestCounter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Semaphore;

@RestController("/")
public class SolutionControllerRest {
    private RequestCounter count;
    private final SolutionService solutionService;
    private final SolutionHash cache;
    private static Logger logger = LoggerFactory.getLogger(SolutionControllerRest.class);
    @Autowired
    public SolutionControllerRest(SolutionService solutionService, SolutionHash cache) {
        this.solutionService = solutionService;
        this.cache = cache;
    }

    @GetMapping("/sol")
    public ResponseEntity<Object> sol(@RequestParam("value1") int value1, @RequestParam("value2") int value2,
                                      @RequestParam("interval1") int interval1,
                                      @RequestParam("interval2") int interval2)  throws IntervalsExceptions,InterruptedException {
        SolutionParameters params = new SolutionParameters(value1, value2, interval1, interval2);
        logger.info("Getting params...");
        int result = 0;

        Semaphore semaphore = new Semaphore(1, true);
        if (cache.isContain(params)) {
            result = cache.getParameters(params);
            logger.info("get hashMap");
        }else {
            result = solutionService.performcalculation(params);
            logger.info("adding to HashMap");
            cache.addToMap(params, result);
        }
        try {
            semaphore.acquire();
            RequestCounter.inc();
            semaphore.release();
        } catch (InterruptedException e) {
            logger.warn(Thread.currentThread().getName() + "was interrupted");
        }

        logger.info("Forming response in json format");
        return ResponseHandler.generateResponse(HttpStatus.OK,value1, value2,interval1,interval2,result);
    }

    @PostMapping("/bulk")
    public ResponseEntity<?> calculateBulkParams(@Valid @RequestBody List<SolutionParameters> bodyList) {

        List<Integer> resultList = new LinkedList<>();
        bodyList.forEach((currentElement) -> {

            resultList.add(solutionService.performcalculation(currentElement));

        });

        logger.info("Successfully postMapping");
        int sumResult = solutionService.calculateSumOfResult(resultList);
        int maxResult = solutionService.findMaxOfResult(resultList);
        int minResult = solutionService.findMinOfResult(resultList);

        return new ResponseEntity<>(resultList + "\nSum: " + sumResult + "\nMax result: " + maxResult + "\nMin result: " + minResult, HttpStatus.OK);
    }
}


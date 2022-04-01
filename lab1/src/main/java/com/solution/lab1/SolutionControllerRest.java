package com.solution.lab1;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController("/")
public class SolutionControllerRest {
    private static Logger logger = LoggerFactory.getLogger(SolutionControllerRest.class);
    @GetMapping("/sol")
    public ResponseEntity<Object> sol(@RequestParam("value1") int value1, @RequestParam("value2") int value2,
                                      @RequestParam("interval1") int interval1,
                                      @RequestParam("interval2") int interval2)  throws IntervalsExceptions{
        logger.info("Getting params...");
        if (interval1 == interval2 || interval1 > interval2) {
            logger.error("WrongIntervals");
            throw new IntervalsExceptions("The beginning of the interval cannot be greater than the end");
        }
        int result=value2-value1;
        if(result <= interval2 && result>=interval1){
            logger.info("Forming response in json format");
            return ResponseHandler.generateResponse(HttpStatus.OK,value1, value2,interval1,interval2,result);
        }
        else
            logger.error("The result is not included in the interval");
            throw new IntervalsExceptions("The result is not included in the interval");
    }
}


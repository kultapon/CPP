package com.solution.lab1.Services;
import com.solution.lab1.Cache.SolutionHash;
import com.solution.lab1.controllers.SolutionControllerRest;
import com.solution.lab1.entity.SolutionParameters;
import com.solution.lab1.exceptions.IntervalsExceptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class SolutionService {
    private static Logger logger = LoggerFactory.getLogger(SolutionControllerRest.class);

    public int performcalculation(SolutionParameters params) {
        int res;

        res = params.getValue2() - params.getValue1();
        if (params.getInterval1() == params.getInterval2() || params.getInterval1() > params.getInterval2()) {
            logger.error("WrongIntervals");
            throw new IntervalsExceptions("The beginning of the interval cannot be greater or equal than the end");
        }
        else if (res <= params.getInterval2() && res >= params.getInterval1()) {
            logger.info("returning result");
            return res;

        } else {
            logger.error("The result is not included in the interval");
            throw new IntervalsExceptions("The result is not included in the interval");
        }
    }
    public int calculateSumOfResult(List<Integer> resultList) {
        int sum = 0;
        if (!resultList.isEmpty()) {
            sum = resultList.stream().mapToInt(Integer::intValue).sum();
        }
        return sum;
    }

    public int findMinOfResult(List<Integer> resultList) {
        int min = 0;
        if (!resultList.isEmpty()) {
            min = resultList.stream().mapToInt(Integer::intValue).min().getAsInt();
        }
        return min;
    }

    public int findMaxOfResult(List<Integer> resultList) {
        int max = 0;
        if (!resultList.isEmpty()) {
            max = resultList.stream().mapToInt(Integer::intValue).max().getAsInt();
        }
        return max;
    }
}

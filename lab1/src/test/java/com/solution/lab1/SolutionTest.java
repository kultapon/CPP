package com.solution.lab1;

import com.solution.lab1.Services.SolutionService;
import com.solution.lab1.entity.SolutionParameters;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class SolutionTest {
    private final SolutionService TestSol = new SolutionService();
    @Test
    void PerformSolution() {
        int value1 = -75800;
        int value2 = 65536;
        int interval1 = 100000;
        int interval2 = 200000;
        int exceptedResult = 141336;
        SolutionParameters params = new SolutionParameters(value1, value2, interval1, interval2);
        assertEquals(exceptedResult,TestSol.performcalculation(params));
    }
}

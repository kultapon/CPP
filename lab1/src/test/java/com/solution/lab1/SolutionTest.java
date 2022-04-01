package com.solution.lab1;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class SolutionTest {

    @Test
    void PerformSolution() {
        int value1= -75800;
        int value2= 65536;
        int exceptedResult=141336;
        assertEquals(exceptedResult,value2-value1);
    }
}

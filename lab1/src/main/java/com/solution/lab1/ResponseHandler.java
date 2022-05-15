package com.solution.lab1;

import org.springframework.core.style.ToStringCreator;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.util.HashMap;
import java.util.Map;

public class ResponseHandler {
    public static ResponseEntity<Object> generateResponse(HttpStatus status,int value1, int value2,int interval1,int interval2,int result) {
        Map<String,Integer> map = new HashMap<String,Integer>();
        map.put("FirstValue", value1);
        map.put("SecondValue", value2);
        map.put("BeginOfInterval", interval1);
        map.put("EndOfInterval", interval2);
        map.put("Result", result);
        return new ResponseEntity<Object>(map,status);
    }
}

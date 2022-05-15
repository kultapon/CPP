package com.solution.lab1.Cache;

import com.solution.lab1.entity.SolutionParameters;
import org.springframework.stereotype.Component;
import java.util.HashMap;

@Component
public class SolutionHash {
    private final HashMap<SolutionParameters, Integer> hashMap = new HashMap<>();
    public boolean isContain(SolutionParameters key) {
        return hashMap.containsKey(key);
    }
    public void addToMap(SolutionParameters key, Integer result){
        hashMap.put(key, result);
    }
    public Integer getParameters(SolutionParameters key) {
        return hashMap.get(key);
    }
}

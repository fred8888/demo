package com.example.demo.bean;

import java.util.List;
import java.util.Map;

public class CountResult {

    private List<Map<String, Integer>> counts;

    public CountResult(List<Map<String, Integer>> counts){
        this.counts = counts;
    }

    public static CountResult build(List<Map<String, Integer>> counts) {
        return new CountResult(counts);
    }

    public void setCounts(List<Map<String, Integer>> counts) {
        this.counts = counts;
    }

    public List<Map<String, Integer>> getCounts() {
        return counts;
    }
}

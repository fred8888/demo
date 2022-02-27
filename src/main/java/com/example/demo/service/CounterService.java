package com.example.demo.service;

import java.util.List;
import java.util.Map;

/**
 * Counter service
 */
public interface CounterService {

    /**
     * Count the number of the text
     * @param searchText
     * @return
     */
    public abstract int search(String searchText);

    /**
     * get the list of top number of text
     * @param top
     * @return
     */
    public abstract List<Map.Entry<String, Integer>> searchTop(int top);

}

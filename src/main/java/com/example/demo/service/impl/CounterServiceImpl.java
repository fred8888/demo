package com.example.demo.service.impl;

import com.example.demo.service.CounterService;
import com.example.demo.util.ArrayUtil;
import com.example.demo.util.CommonUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;


import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class CounterServiceImpl implements CounterService {
    private static final Logger logger = LoggerFactory.getLogger(CounterServiceImpl.class);

    /**
     * Count the number of the text
     * @param searchText
     * @return
     */
    public int search(String searchText){
        String txt = CommonUtil.readTxtFile().toLowerCase();
        int count = StringUtils.countMatches(txt, searchText.toLowerCase());
        return count;
    }

    /**
     * get the list of top number of text
     * @param top
     * @return
     */
    public List<Map.Entry<String, Integer>> searchTop(int top){
        List<Map.Entry<String, Integer>> subList = null;

        //read text file
        String text = CommonUtil.readTxtFile();
        if(StringUtils.isEmpty(text)){
            return subList;
        }

        //count each word
        Map<String, Integer> map = CommonUtil.countWords(text);
        if(ArrayUtil.isEmpty(map)){
            return subList;
        }

        //sort
        List<Map.Entry<String, Integer>> listMap = map
                .entrySet()
                .stream()
                .sorted(Map.Entry.<String, Integer> comparingByValue().reversed())
                .collect(Collectors.toList());


        int size = listMap.size();
        if(top > size){
            return listMap;
        }

        return listMap.subList(0,top);
    }




}

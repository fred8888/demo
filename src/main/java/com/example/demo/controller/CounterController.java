package com.example.demo.controller;

import com.example.demo.bean.CountResult;
import com.example.demo.bean.SearchText;
import com.example.demo.service.CounterService;
import com.example.demo.util.ArrayUtil;
import com.example.demo.util.CSVUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Controller
@RequestMapping(path = "/counter-api")
public class CounterController {

    @Autowired
    CounterService counterService;

    @RequestMapping(path = "/search", method = {RequestMethod.POST})
    @ResponseBody
    public CountResult search(@RequestBody SearchText searchText){
        List<Map<String, Integer>> counts = null;
        if(searchText == null || ArrayUtil.isEmpty(searchText.getSearchText())){
            return  CountResult.build(counts);
        }

        String[] textArray = searchText.getSearchText();
        List<String> list = Stream.of(textArray).collect(Collectors.toList());
        counts = list.stream().map(keyWord -> {
            int count = counterService.search(keyWord);
            Map<String, Integer> map = new HashMap<String, Integer>();
            map.put(keyWord, count);
            return map;
        }).collect(Collectors.toList());

        return  CountResult.build(counts);
    }

    @RequestMapping(path = "top/{number}", method = {RequestMethod.GET})
    public ResponseEntity<byte[]> top(@PathVariable Integer number) {
        List<Map.Entry<String, Integer>>  list = counterService.searchTop(number);
        if(ArrayUtil.isEmpty(list)){
            return null;
        }
        List<List<Object>> newList = CSVUtil.convertList(list);
        byte[]  csvByte = CSVUtil.writeCSV(newList);

        HttpHeaders httpHeaders = new HttpHeaders();

        httpHeaders.setContentDispositionFormData("attachment", "hello.csv");
        httpHeaders.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        ResponseEntity<byte[]> fileByte = new ResponseEntity<byte[]>(csvByte, httpHeaders, HttpStatus.OK);
        return fileByte;
    }

}
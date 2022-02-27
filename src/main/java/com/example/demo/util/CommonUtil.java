package com.example.demo.util;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;

import java.io.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Common  Utility class
 */
public class CommonUtil {
    private static final Logger logger = LoggerFactory.getLogger(CommonUtil.class);

    /**
     * Read file
     * @return file content
     */
    public static String readTxtFile(){
        try {
            byte[] bytes;
            ClassPathResource classPathResource = new ClassPathResource("file.txt");
            InputStream keyStream = classPathResource.getInputStream();
            bytes = IOUtils.toByteArray(keyStream);
            keyStream.read(bytes);
            keyStream.close();

            ByteArrayInputStream certBis = new ByteArrayInputStream(bytes);
            InputStreamReader input = new InputStreamReader(certBis);
            BufferedReader bf = new BufferedReader(input);
            String line = null;
            StringBuilder sb = new StringBuilder();
            while((line=bf.readLine()) != null){
                sb.append(line);
            }
            String text = sb.toString();
            return sb.toString();
        } catch (IOException e) {
            logger.error("Reading file error",e);
        }
        return "";
    }

    /**
     * count words
     * @param text
     * @return
     */
    public static Map<String, Integer> countWords(String text){
        Map<String, Integer> map = new HashMap<String, Integer>();
        text = text.replace(".",",");
        text = text.replace(" ",",");
        String[] textArray = text.split(",");
        for(String word : textArray){
            String key = word.toLowerCase();
            if(!"".equals(key)){
                Integer num = map.get(key);
                if(num == null || num == 0){
                    map.put(key, 1);
                }else if(num > 0){
                    map.put(key, num+1);
                }
            }
        }
        return map;
    }
}

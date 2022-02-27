package com.example.demo.util;

import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

/**
 * Write CSV file
 */
public class CSVUtil {

    /**
     * Write CSV file
     * @param cellList
     * @return
     */
    public static byte[] writeCSV(List<List<Object>> cellList) {
        byte[] bytes = new byte[0];
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(byteArrayOutputStream, StandardCharsets.UTF_8);
        BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter);
        CSVPrinter  csvPrinter = null;
        try {
            CSVFormat csvFormat = CSVFormat.DEFAULT.withIgnoreHeaderCase();
            csvPrinter = new CSVPrinter(bufferedWriter, csvFormat);

            // fill data
            csvPrinter.printRecords(cellList);
            csvPrinter.flush();
            bytes = byteArrayOutputStream.toString(StandardCharsets.UTF_8.name()).getBytes();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (csvPrinter != null) {
                    csvPrinter.close();
                }
                if (bufferedWriter != null) {
                    bufferedWriter.close();
                }
                if (outputStreamWriter != null) {
                    outputStreamWriter.close();
                }
                if (byteArrayOutputStream != null) {
                    byteArrayOutputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return bytes;
    }

    /**
     * convert data type
     * @param list
     * @return
     */
    public static List<List<Object>> convertList(List<Map.Entry<String, Integer>> list) {
        List<List<Object>> all = new ArrayList<List<Object>>();
        list.forEach(e -> {
            ArrayList<Object> objList = new ArrayList<>();
            objList.add(e.getKey());
            objList.add(e.getValue());
            all.add(objList);
        });

        return all;
    }
}
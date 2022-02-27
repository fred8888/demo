package com.example.demo.util;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * Utility class for working with arrays
 */
public class ArrayUtil {
    //check if collection is empty
    public static boolean isEmpty(Collection<?> collection) {
        return collection == null || collection.isEmpty();
    }

    //check if map is empty
    public static boolean isEmpty(Map<?, ?> map) {
        return map == null || map.isEmpty();
    }

    //check if array is empty
    public static boolean isEmpty(Object[] array) {
        return array == null || array.length == 0;
    }

    ////check if list is empty
    public static boolean isEmpty(List<Object> list) {
        return list == null || list.size() == 0;
    }
}

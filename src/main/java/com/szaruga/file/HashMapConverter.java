package com.szaruga.file;

import java.util.*;

public class HashMapConverter {

    public List<String> hMapConvertToList(Map<String, String> mapProperties) {
        List<String> stringList = new ArrayList<>();

        Set<Map.Entry<String, String>> set = mapProperties.entrySet();
        for (Map.Entry<String, String> entry : set) {
            String key = entry.getKey();
            String value = entry.getValue();
            String line = key + value;
            stringList.add(line);
        }
        return stringList;
    }
}
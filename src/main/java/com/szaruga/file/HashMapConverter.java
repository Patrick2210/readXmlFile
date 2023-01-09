package com.szaruga.file;

import java.util.*;

public class HashMapConverter {
    private Map<String, String> mapProperties;

    public List<String> hMapConvertToList(Map<String, String> mapProperties) {
        this.mapProperties = mapProperties;

        List<String> stringList = new ArrayList<>();

        Set<Map.Entry<String, String>> set = mapProperties.entrySet();
        for (Map.Entry<String, String> entry : set) {
            String key = entry.getKey();
            String value = entry.getValue();
            String line = key + ": " + value + "\n";

            stringList.add(line);
        }
        return stringList;
    }
}
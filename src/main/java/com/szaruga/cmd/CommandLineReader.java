package com.szaruga.cmd;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class CommandLineReader {
    private final String[] args;
    private static final String txt = ".txt";
    private static final String xml = ".xml";
    private static final String csv = ".csv";

    public CommandLineReader(String[] args) {
        this.args = args;
    }

    public List<String> listFilePath() {
        List<String> stringList = new ArrayList<>();
        for (String str : args) {
            if (str.contains(xml)) {
                stringList.add(str);
            }

            //
        }
        return stringList;
    }

    private String getFileName() {
        String fileName = "";
        for (String str : args) {

            File file = new File(str);
            fileName = file.getName();
        }
        return fileName;
    }

    public String fileResult() {
        String fileName = getFileName();
        int lastIndex = fileName.lastIndexOf(".");

        if (lastIndex == -1) {
            return fileName + txt;
        } else if (fileName.endsWith(txt)) {
            return fileName;
        }
        return fileName;
    }
}
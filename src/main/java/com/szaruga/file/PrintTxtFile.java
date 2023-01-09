package com.szaruga.file;

import java.io.*;
import java.util.*;

public class PrintTxtFile {
    private String path;

    public PrintTxtFile(String path) {
        this.path = path;
    }

    public void write(List<String> inputList) {
        File file = new File(path);
        try {
            FileWriter fileWriter = new FileWriter(file, true);
            PrintWriter printWriter = new PrintWriter(fileWriter);

            for (String str : inputList) {
                printWriter.write(str);
            }
            printWriter.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
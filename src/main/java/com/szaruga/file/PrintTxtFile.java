package com.szaruga.file;

import java.io.*;
import java.util.*;

public class PrintTxtFile {
    private final List<String> path;

    public PrintTxtFile(List<String> path) {

        this.path = path;
    }

    public void write(List<String> inputList) {

        try {
            for (int i = 0; i < path.size(); i++) {
                File file = new File(path.get(i));

                FileWriter fileWriter = new FileWriter(file, true);
                PrintWriter printWriter = new PrintWriter(fileWriter);

                for (String str : inputList) {
                    printWriter.write(str);
                    printWriter.write("\n");
                }
                printWriter.close();

            }
        } catch (IOException | RuntimeException e) {
            throw new RuntimeException(e);
        }
    }
}
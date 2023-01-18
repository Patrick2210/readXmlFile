package com.szaruga.cmd;

import com.szaruga.EnumStrings;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class CommandLineReader {
    private final String[] args;

    public CommandLineReader(String[] args) {
        this.args = args;
    }

    public List<String> listFilePath() {
        List<String> stringListFilePath = new ArrayList<>();
        EnumStrings xml = EnumStrings.XML;

        for (int i = 0; i < args.length - 2; i++) {
            String listExceptLastTwo = args[i];
            if (listExceptLastTwo.contains(xml.extension)) {
                stringListFilePath.add(listExceptLastTwo);
            }
        }

        return stringListFilePath;
    }

    private List<String> getFileName() {
        List<String> stringList = new ArrayList<>();
        EnumStrings xml = EnumStrings.XML;
        String fileName;

        for (String str : args) {

            if (!str.endsWith(xml.extension)) {
                File file = new File(str);
                fileName = file.getName();
                stringList.add(fileName);
            }
        }
        return stringList;
    }

    private List<String> addExtensionTxt() {

        List<String> listFile = getFileName();
        List<String> extensionList = new ArrayList<>();
        EnumStrings txt = EnumStrings.TXT;

        for (String fileName : listFile) {

            if (fileName.contains(txt.extension)) {
                extensionList.add(fileName);
            } else if (!fileName.endsWith(txt.extension)) {
                System.out.println("Error...");
            }
        }
        return extensionList;
    }

    private List<String> addExtensionCsv() {

        List<String> listFile = getFileName();
        List<String> extensionList = new ArrayList<>();
        EnumStrings csv = EnumStrings.CSV;

        for (String fileName : listFile) {
            int lastIndex = fileName.lastIndexOf(fileName.length());

            if (fileName.contains(csv.extension)) {
                extensionList.add(fileName);
            }
            if (lastIndex == -1) {
                extensionList.add(fileName + csv.extension);
            } else if (!fileName.endsWith(csv.extension)) {
                System.out.println("Error...");
            }
        }
        return extensionList;
    }

    public String fileResult() {
        List<String> extensionTxt = addExtensionTxt();
        List<String> extensionCsv = addExtensionCsv();

        String fileResultTxt = " ";

        for (String str : extensionCsv) {
            fileResultTxt = str;
        }
        return fileResultTxt;
    }
}
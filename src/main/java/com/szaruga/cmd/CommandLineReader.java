package com.szaruga.cmd;

import com.szaruga.EnumStrings;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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

    private List<String> addExtension(String extension) {
        List<String> listFile = getFileName();
        List<String> extensionList = new ArrayList<>();

        for (String fileName : listFile) {

            if (fileName.endsWith(extension)) {
                extensionList.add(fileName);
            } else if (fileName.indexOf('.') == -1) {
                fileName += extension;
                extensionList.add(fileName);
            } else {
                System.err.println(EnumStrings.ADD_EXTENSION_ERR + extension + "\n" + EnumStrings.FILE_NAME + fileName);
            }
        }
        return extensionList;
    }

    public List<String> fileResult() throws Error {
        Scanner scanner = new Scanner(System.in);

        System.out.println(EnumStrings.WELCOME_ONE.extension);
        System.out.println(EnumStrings.WELCOME_TWO.extension);
        System.out.println(EnumStrings.WELCOME_THREE.extension);

        int number;

        do {
            number = scanner.nextInt();
            if (number == 1) {
                System.out.println(EnumStrings.IF_ONE.extension);
                return addExtension(EnumStrings.TXT.extension);
            } else if (number == 2) {
                System.out.println(EnumStrings.IF_TWO.extension);
                return addExtension(EnumStrings.CSV.extension);
            } else {
                System.out.println(EnumStrings.ELSE.extension);
            }
        } while (number != 1 && number != 2);

        return new ArrayList<>();
    }
}
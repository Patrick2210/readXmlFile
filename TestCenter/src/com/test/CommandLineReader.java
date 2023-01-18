package com.test;

import java.io.File;
import java.util.*;

// TODO zczytac wszystkie plki od 0 do -2(przedostatniego)
// TODO sprawdzic extension czy file zawiera czy nie
// TODO dodoac jezeli nie zawiera extension, format chciany np. csv
// TODO zrobic metode ktora zwroci format
// TODO enum output obiekt - enum TXT = zawiera .txt
// TODO "dzieciczenie" + "interface" -> poczytac
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
            } else if (fileName.endsWith(" ")) {
                extensionList.add(fileName + txt.extension);
            } else {

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

        /*Scanner inputUser = new Scanner(System.in);
        System.out.println("Please tell us what format would you like to receive?");
        System.out.println("Press 1 for txt format ");
        System.out.println("Press 2 for csv format ");

        try {
            int number = inputUser.nextInt();

            if (number == 0){
                System.out.println("Enter 1 or 2");
            }
            if (number == 1) {
                extensionTxt.listIterator();
                System.out.println("output format --> txt");
            }
            if (number == 2 ){
                extensionCsv.listIterator();
                System.out.println("output format --> csv");
            }
            else {
                System.out.println("Please enter correct number...");
            }
        }catch (InputMismatchException e){
            e.printStackTrace();
        }

         */

        return fileResultTxt;
    }
}
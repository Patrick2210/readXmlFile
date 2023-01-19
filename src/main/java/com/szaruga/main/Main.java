package com.szaruga.main;

import com.szaruga.cmd.CommandLineReader;
import com.szaruga.file.HashMapConverter;
import com.szaruga.file.PrintTxtFile;
import com.szaruga.file.XmlReader;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class Main {
    public static void main(String[] args) {

        CommandLineReader commandLineReader = new CommandLineReader(args);
        List<String> listFilePath = commandLineReader.listFilePath();

        XmlReader xmlReader = new XmlReader();
        HashMapConverter hashMapReader = new HashMapConverter();

        PrintTxtFile printTxtFile = new PrintTxtFile(commandLineReader.fileResult());

        for (int i = 0; i < listFilePath.size(); i++) {
            String path = listFilePath.get(i);

            Map<String, String> properties = xmlReader.read(path);
            printTxtFile.write(hashMapReader.hMapConvertToList(properties));
        }
    }
}
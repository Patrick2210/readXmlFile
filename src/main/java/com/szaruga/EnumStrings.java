package com.szaruga;

public enum EnumStrings {
    TXT(".txt"),
    XML(".xml"),
    CSV(".csv"),
    STRING("string"),
    PLURALS("plurals"),
    ITEM("item"),
    NAME("name"),
    QUANTITY("quantity"),
    ADD_EXTENSION_ERR("Error in addExtension"),
    FILE_NAME("File has name: "),
    WELCOME_ONE("Welcome in ReaderXml\n" + "Press number for desired output: \n"),
    WELCOME_TWO("Press number 1 for --> txt file"),
    WELCOME_THREE("Press number 2 for --> csv file"),
    ELSE("Press correct number from 1 to 2..."),
    IF_ONE("Select format --> txt\n" + "Thank you for using ReaderXml"),
    IF_TWO("Select format --> csv\n" + "Thank you for using ReaderXml");


    public String extension;

    EnumStrings(String extension) {
        this.extension = extension;
    }
}

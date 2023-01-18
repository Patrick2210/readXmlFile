package com.test;

public enum EnumStrings {
    TXT(".txt"),
    XML(".xml"),
    CSV(".csv"),
    NAME_CHILD_ELEMENT_ONE("string"),
    NAME_CHILD_ELEMENT_TWO("plurals"),
    NAME_SUB_CHILD_ELEMENT("item"),
    NAME_CHILD_ATTRIBUTE_ONE("name"),
    NAME_CHILD_ATTRIBUTE_TWO("quantity");


    String extension;

    EnumStrings(String extension) {
        this.extension = extension;
    }
}

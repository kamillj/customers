package pl.jurczak.kamil.customers.enumeration;

import java.util.ArrayList;
import java.util.List;

public enum FileExtention {
    CSV,
    TXT,
    XML;

    public static List<String> getValues() {
        List<String> values = new ArrayList<>();
        for (int i = 0; i < FileExtention.values().length; i++) {
            values.add(FileExtention.values()[i].toString());
        }
        return values;
    }
}

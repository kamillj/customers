package pl.jurczak.kamil.customers.enumeration;

import java.util.ArrayList;
import java.util.List;

public enum FileExtentions {
    CSV,
    TXT,
    XML;

    public static List<String> getValues() {
        List<String> values = new ArrayList<>();
        for (int i = 0; i < FileExtentions.values().length; i++) {
            values.add(FileExtentions.values()[i].toString());
        }
        return values;
    }
}

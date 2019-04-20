package pl.jurczak.kamil.customers.enumeration;

import lombok.AllArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
public enum FileExtension {
    CSV("csv"),
    TXT("txt"),
    XML("xml");

    private String value;

    public static List<String> getValues() {
        List<String> values = new ArrayList<>();
        for (int i = 0; i < FileExtension.values().length; i++) {
            values.add(FileExtension.values()[i].toString());
        }
        return values;
    }

    public String getValue() {
        return value;
    }
}

package pl.jurczak.kamil.customers.model;

import lombok.Data;

@Data
public class Customer {

    private long id;
    private String name;
    private String surname;
    private int age;

    public Customer(String name, String surname) {
        this.name = name;
        this.surname = surname;
        this.age = -1;
    }

    public Customer(String name, String surname, int age) {
        this.name = name;
        this.surname = surname;
        this.age = age;
    }
}

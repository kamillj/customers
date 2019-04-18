package pl.jurczak.kamil.customers.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Customer {

    private long id;
    private String name;
    private String surname;
    private int age = -1;
    private String city;
    private Contact contact;

    public Customer(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }

    public Customer(String name, String surname, int age) {
        this.name = name;
        this.surname = surname;
        this.age = age;
    }
}

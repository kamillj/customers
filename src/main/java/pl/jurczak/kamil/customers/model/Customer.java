package pl.jurczak.kamil.customers.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@Data
@NoArgsConstructor
@XmlRootElement(name = "person")
@XmlAccessorType(XmlAccessType.FIELD)
public class Customer {

    private long id;
    private String name;
    private String surname;
    private int age = -1;
    private String city;
    private Contacts contacts;

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

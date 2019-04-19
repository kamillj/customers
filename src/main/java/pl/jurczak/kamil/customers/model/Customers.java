package pl.jurczak.kamil.customers.model;

import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@Data
@XmlRootElement(name = "persons")
@XmlAccessorType(XmlAccessType.FIELD)
public class Customers {

    @XmlElement(name = "person")
    private List<Customer> customers;
}

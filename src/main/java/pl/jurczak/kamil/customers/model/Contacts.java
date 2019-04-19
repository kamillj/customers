package pl.jurczak.kamil.customers.model;

import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@Data
@XmlRootElement(name = "contacts")
@XmlAccessorType(XmlAccessType.FIELD)
public class Contacts {

    private long id;
    private long idCustomer;
    @XmlElement(name = "phone")
    private List<String> phones;
    @XmlElement(name = "email")
    private List<String> emails;
    @XmlElement(name = "icq")
    private List<String> icqs;
    @XmlElement(name = "jabber")
    private List<String> jabbers;
    private List<String> undefineds;

    public void addPhone(String phone) {
        if (phones == null) {
            phones = new ArrayList<>();
        }
        phones.add(phone);
    }

    public void addEmail(String email) {
        if (emails == null) {
            emails = new ArrayList<>();
        }
        emails.add(email);
    }

    public void addIcq(String icq) {
        if (icqs == null) {
            icqs = new ArrayList<>();
        }
        icqs.add(icq);
    }

    public void addJabber(String jabber) {
        if (jabbers == null) {
            jabbers = new ArrayList<>();
        }
        jabbers.add(jabber);
    }

    public void addUndefined(String undefined) {
        if (undefineds == null) {
            undefineds = new ArrayList<>();
        }
        undefineds.add(undefined);
    }
}

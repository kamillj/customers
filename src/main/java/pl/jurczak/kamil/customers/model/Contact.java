package pl.jurczak.kamil.customers.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Contact {

    private long id;
    private long idCustomer;
    private List<String> phones;
    private List<String> emails;
    private List<String> icqs;
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

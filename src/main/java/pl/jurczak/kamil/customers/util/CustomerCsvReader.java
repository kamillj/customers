package pl.jurczak.kamil.customers.util;

import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.jurczak.kamil.customers.model.Contacts;
import pl.jurczak.kamil.customers.model.Customer;

@NoArgsConstructor
@Setter
public class CustomerCsvReader {

    private static final String CSV_SEPARATOR = ",";

    public Customer read(String line) {
        Customer customer = new Customer();
        Contacts contacts = null;
        String[] values = line.split(CSV_SEPARATOR);
        for (int i = 0; i < values.length; i++) {
            if (!values[i].trim().isEmpty()) {
                if (i == 0) customer.setName(values[i]);
                if (i == 1) customer.setSurname(values[i]);
                if (i == 2) customer.setAge(Integer.parseInt(values[i]));
                if (i == 3) customer.setCity(values[i]);
                if (i > 3) {
                    if (contacts == null) contacts = new Contacts();

                    if (validEmail(values[i])) contacts.addEmail(values[i]);
                    else if (validPhone(values[i])) contacts.addPhone(values[i]);
                    else if (validIcq(values[i])) contacts.addIcq(values[i]);
                    else if (validJabber(values[i])) contacts.addJabber(values[i]);
                    else contacts.addUndefined(values[i]);
                }
            }
        }
        customer.setContacts(contacts);
        return customer;
    }

    private boolean validEmail(String email) {
        return email.contains("@");
    }

    private boolean validPhone(String phone) {
        phone = phone.replace(" ", "").replace("-", "").trim();
        return phone.matches("^[0-9]{9}$");
    }

    private boolean validIcq(String icq) {
        return icq.matches("^[0-9]{5}$");
    }

    private boolean validJabber(String jabber) {
        return jabber.contains("jbr");
    }
}

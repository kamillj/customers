package pl.jurczak.kamil.customers.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import pl.jurczak.kamil.customers.dao.CustomerDao;
import pl.jurczak.kamil.customers.model.Customer;

@Repository
public class CustomerDaoImpl implements CustomerDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void addCustomer(Customer customer) {
        long idCustomer = getNextCustomerSeqVal();
        String insertCutomer;
        if (customer.getAge() < 0) {
            insertCutomer = "INSERT INTO \"Customers\" (id, name, surname) VALUES (?, ?, ?)";
            jdbcTemplate.update(insertCutomer, idCustomer, customer.getName(), customer.getSurname());
        } else {
            insertCutomer = "INSERT INTO \"Customers\" (id, name, surname, age) VALUES (?, ?, ?, ?)";
            jdbcTemplate.update(insertCutomer, idCustomer, customer.getName(), customer.getSurname(), customer.getAge());
        }

        if (customer.getContacts() != null) {
            if (customer.getContacts().getUndefineds() != null && customer.getContacts().getUndefineds().size() > 0) {
                for (int i = 0; i < customer.getContacts().getUndefineds().size(); i++) {
                    String insertContact = "INSERT INTO \"Contacts\" (id, id_customer, type, contact) VALUES (?, ?, ?, ?)";
                    jdbcTemplate.update(insertContact, getNextContactsSeqVal(), idCustomer,
                            0, customer.getContacts().getUndefineds().get(i));
                }
            }
            if (customer.getContacts().getEmails() != null && customer.getContacts().getEmails().size() > 0) {
                for (int i = 0; i < customer.getContacts().getEmails().size(); i++) {
                    String insertContact = "INSERT INTO \"Contacts\" (id, id_customer, type, contact) VALUES (?, ?, ?, ?)";
                    jdbcTemplate.update(insertContact, getNextContactsSeqVal(), idCustomer,
                            1, customer.getContacts().getEmails().get(i));
                }
            }
            if (customer.getContacts().getPhones() != null && customer.getContacts().getPhones().size() > 0) {
                for (int i = 0; i < customer.getContacts().getPhones().size(); i++) {
                    String insertContact = "INSERT INTO \"Contacts\" (id, id_customer, type, contact) VALUES (?, ?, ?, ?)";
                    jdbcTemplate.update(insertContact, getNextContactsSeqVal(), idCustomer,
                            2, customer.getContacts().getPhones().get(i));
                }
            }
            if (customer.getContacts().getJabbers() != null && customer.getContacts().getJabbers().size() > 0) {
                for (int i = 0; i < customer.getContacts().getJabbers().size(); i++) {
                    String insertContact = "INSERT INTO \"Contacts\" (id, id_customer, type, contact) VALUES (?, ?, ?, ?)";
                    jdbcTemplate.update(insertContact, getNextContactsSeqVal(), idCustomer,
                            3, customer.getContacts().getJabbers().get(i));
                }
            }
            if (customer.getContacts().getIcqs() != null && customer.getContacts().getIcqs().size() > 0) {
                for (int i = 0; i < customer.getContacts().getIcqs().size(); i++) {
                    String insertContact = "INSERT INTO \"Contacts\" (id, id_customer, type, contact) VALUES (?, ?, ?, ?)";
                    jdbcTemplate.update(insertContact, getNextContactsSeqVal(), idCustomer,
                            4, customer.getContacts().getIcqs().get(i));
                }
            }
        }
    }

    private int getNextCustomerSeqVal() {
        String sql = "SELECT nextval('\"customers_seq\"')";
        return jdbcTemplate.queryForObject(sql, Integer.class);
    }

    private int getNextContactsSeqVal() {
        String sql = "SELECT nextval('\"contacts_seq\"')";
        return jdbcTemplate.queryForObject(sql, Integer.class);
    }
}
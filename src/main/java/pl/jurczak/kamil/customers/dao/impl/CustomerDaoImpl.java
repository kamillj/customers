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

        if (customer.getContact() != null) {
            if (customer.getContact().getUndefineds() != null && customer.getContact().getUndefineds().size() > 0) {
                for (int i = 0; i < customer.getContact().getUndefineds().size(); i++) {
                    String insertContact = "INSERT INTO \"Contacts\" (id, id_customer, type, contact) VALUES (?, ?, ?, ?)";
                    jdbcTemplate.update(insertContact, getNextContactsSeqVal(), idCustomer,
                            0, customer.getContact().getUndefineds().get(i));
                }
            }
            if (customer.getContact().getEmails() != null && customer.getContact().getEmails().size() > 0) {
                for (int i = 0; i < customer.getContact().getEmails().size(); i++) {
                    String insertContact = "INSERT INTO \"Contacts\" (id, id_customer, type, contact) VALUES (?, ?, ?, ?)";
                    jdbcTemplate.update(insertContact, getNextContactsSeqVal(), idCustomer,
                            1, customer.getContact().getEmails().get(i));
                }
            }
            if (customer.getContact().getPhones() != null && customer.getContact().getPhones().size() > 0) {
                for (int i = 0; i < customer.getContact().getPhones().size(); i++) {
                    String insertContact = "INSERT INTO \"Contacts\" (id, id_customer, type, contact) VALUES (?, ?, ?, ?)";
                    jdbcTemplate.update(insertContact, getNextContactsSeqVal(), idCustomer,
                            2, customer.getContact().getPhones().get(i));
                }
            }
            if (customer.getContact().getJabbers() != null && customer.getContact().getJabbers().size() > 0) {
                for (int i = 0; i < customer.getContact().getJabbers().size(); i++) {
                    String insertContact = "INSERT INTO \"Contacts\" (id, id_customer, type, contact) VALUES (?, ?, ?, ?)";
                    jdbcTemplate.update(insertContact, getNextContactsSeqVal(), idCustomer,
                            3, customer.getContact().getJabbers().get(i));
                }
            }
            if (customer.getContact().getIcqs() != null && customer.getContact().getIcqs().size() > 0) {
                for (int i = 0; i < customer.getContact().getIcqs().size(); i++) {
                    String insertContact = "INSERT INTO \"Contacts\" (id, id_customer, type, contact) VALUES (?, ?, ?, ?)";
                    jdbcTemplate.update(insertContact, getNextContactsSeqVal(), idCustomer,
                            4, customer.getContact().getIcqs().get(i));
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

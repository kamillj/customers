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
        String sql;
        if (customer.getAge() < 0) {
            sql = "INSERT INTO \"Customers\" (id, name, surname) VALUES (?, ?, ?)";
            jdbcTemplate.update(sql, getNextSeqVal(), customer.getName(), customer.getSurname());
        } else {
            sql = "INSERT INTO \"Customers\" (id, name, surname, age) VALUES (?, ?, ?, ?)";
            jdbcTemplate.update(sql, getNextSeqVal(), customer.getName(), customer.getSurname(), customer.getAge());
        }
    }

    private int getNextSeqVal() {
        String sql = "SELECT nextval('\"customers_seq\"')";
        return jdbcTemplate.queryForObject(sql, Integer.class);
    }
}

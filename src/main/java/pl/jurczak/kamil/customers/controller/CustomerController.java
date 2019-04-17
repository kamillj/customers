package pl.jurczak.kamil.customers.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import pl.jurczak.kamil.customers.dao.CustomerDao;

@Controller
public class CustomerController {

    private CustomerDao customerDao;

    @Autowired
    public void setCustomerDao(CustomerDao customerDao) {
        this.customerDao = customerDao;
    }

    @GetMapping("/")
    public String home() {
        return "home";
    }
}
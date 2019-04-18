package pl.jurczak.kamil.customers.controller;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import pl.jurczak.kamil.customers.dao.CustomerDao;
import pl.jurczak.kamil.customers.model.Customer;
import pl.jurczak.kamil.customers.util.CustomerCsvReader;

import java.io.*;

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

    @PostMapping("/")
    public String uploadFile(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            System.out.println("PLEASE SELECT A FILE");
        } else {
            String extension = FilenameUtils.getExtension(file.getOriginalFilename());

            if (extension.equalsIgnoreCase("csv") || extension.equalsIgnoreCase("txt")) {
                CustomerCsvReader customerCsvReader = new CustomerCsvReader();
                BufferedReader br;
                try {
                    String line;
                    InputStream is = file.getInputStream();
                    br = new BufferedReader(new InputStreamReader(is));
                    while ((line = br.readLine()) != null) {
                        System.out.println(line);
                        Customer customer = customerCsvReader.read(line);
                        customerDao.addCustomer(customer);
                    }
                } catch (IOException e) {
                    System.err.println(e.getMessage());
                }
            } else if (extension.equalsIgnoreCase("xml")) {

            }
        }
        return "redirect:/";
    }
}
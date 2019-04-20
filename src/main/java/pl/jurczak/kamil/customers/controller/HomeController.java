package pl.jurczak.kamil.customers.controller;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.jurczak.kamil.customers.dao.CustomerDao;
import pl.jurczak.kamil.customers.enumeration.FileExtension;
import pl.jurczak.kamil.customers.model.Customer;
import pl.jurczak.kamil.customers.model.Customers;
import pl.jurczak.kamil.customers.model.FileBucket;
import pl.jurczak.kamil.customers.util.CustomerCsvReader;

import javax.validation.Valid;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.*;

@Controller
public class HomeController {

    private CustomerDao customerDao;

    @Autowired
    public void setCustomerDao(CustomerDao customerDao) {
        this.customerDao = customerDao;
    }

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("fileBucket", new FileBucket());
        return "home";
    }

    @PostMapping("/")
    public String uploadFile(@ModelAttribute("fileBucket") @Valid FileBucket bucket, BindingResult bindingResult, RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            return "home";
        }

        MultipartFile file = bucket.getFile();
        try {
            String extension = FilenameUtils.getExtension(file.getOriginalFilename());
            if (extension.equalsIgnoreCase(FileExtension.CSV.getValue()) || extension.equalsIgnoreCase(FileExtension.TXT.getValue())) {
                CustomerCsvReader customerCsvReader = new CustomerCsvReader();
                BufferedReader br;
                String line;
                InputStream is = file.getInputStream();
                br = new BufferedReader(new InputStreamReader(is));
                int i = 0;
                while ((line = br.readLine()) != null) {
                    Customer customer = customerCsvReader.read(line);
                    customerDao.addCustomer(customer);
                    if (i > 200) {
                        System.gc();
                        i = 0;
                    }
                    i++;
                }

            } else if (extension.equalsIgnoreCase(FileExtension.XML.getValue())) {
                InputStreamReader isr = new InputStreamReader(file.getInputStream());
                XMLInputFactory xmlif = XMLInputFactory.newInstance();
                XMLStreamReader xmlr = xmlif.createXMLStreamReader(isr);

                JAXBContext jaxbContext = JAXBContext.newInstance(Customers.class);
                Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

                xmlr.nextTag();
                xmlr.require(XMLStreamConstants.START_ELEMENT, null, "persons");

                xmlr.nextTag();
                int i = 0;
                while (xmlr.getEventType() == XMLStreamConstants.START_ELEMENT) {
                    JAXBElement<Customer> customerJAXBElement = unmarshaller.unmarshal(xmlr, Customer.class);
                    Customer customer = customerJAXBElement.getValue();
                    customerDao.addCustomer(customer);

                    if (xmlr.getEventType() == XMLStreamConstants.CHARACTERS) {
                        xmlr.next();
                    }

                    if (i > 50) {
                        System.gc();
                        i = 0;
                    }
                    i++;
                }
            }

            redirectAttributes.addFlashAttribute("message", "You successfully uploaded '" + file.getOriginalFilename() + "'");
            return "redirect:/";

        } catch (JAXBException | IOException | XMLStreamException e) {
            e.printStackTrace();
        }
        return "redirect:/";
    }
}
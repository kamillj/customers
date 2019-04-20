package pl.jurczak.kamil.customers.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import pl.jurczak.kamil.customers.model.Contacts;
import pl.jurczak.kamil.customers.model.Customer;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CustomerDaoTest {

    @Mock
    private CustomerDao customerDaoMock;

    @Test
    public void shouldReturnsTheSameEmail() {
        Customer customer = new Customer("Kevin", "White", 24);
        customer.setId(1);

        Contacts contacts = new Contacts();
        contacts.addEmail("kevin.white@yahoo.com");

        customer.setContacts(contacts);

        customerDaoMock.addCustomer(customer);

        when(customerDaoMock.getCustomerById(1)).thenReturn(customer);

        assertEquals("The same address email",
                customer.getContacts().getEmails().get(0), customerDaoMock.getCustomerById(1).getContacts().getEmails().get(0));
    }
}

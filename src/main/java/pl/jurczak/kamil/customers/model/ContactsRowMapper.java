package pl.jurczak.kamil.customers.model;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.lang.Nullable;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ContactsRowMapper implements RowMapper<Contacts> {

    @Nullable
    @Override
    public Contacts mapRow(ResultSet rs, int i) throws SQLException {
        Contacts contacts = new Contacts();
        contacts.setId(rs.getLong("id"));
        contacts.setIdCustomer(rs.getLong("id_customer"));
        if (rs.getInt("type") == 1) contacts.addEmail("contact");
        else if (rs.getInt("type") == 2) contacts.addPhone("contact");
        else if (rs.getInt("type") == 3) contacts.addJabber("contact");
        else if (rs.getInt("type") == 4) contacts.addIcq("contact");
        else if (rs.getInt("type") == 0) contacts.addUndefined("contact");

        return contacts;
    }
}

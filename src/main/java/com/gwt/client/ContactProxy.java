
package com.gwt.client;

import java.util.List;

import com.google.web.bindery.requestfactory.shared.EntityProxy;
import com.google.web.bindery.requestfactory.shared.ProxyFor;
import com.google.web.bindery.requestfactory.shared.ValueProxy;
import com.gwt.server.Contact;
import com.gwt.server.Contact.Phone;

@ProxyFor(Contact.class)
public interface ContactProxy extends EntityProxy
{
    Long getId();
    void setId(Long id);

    Integer getVersion();

    String getName();
    void setName(String name);

    String getEmail();
    void setEmail(String email);

    List<PhoneProxy> getPhone();
    void setPhone(List<PhoneProxy> phone);

    String getNotes();
    void setNotes(String notes);
    
    @ProxyFor(Phone.class)
    public interface PhoneProxy extends ValueProxy
    {
        String getType();
        void setType(String type);

        String getNumber();
        void setNumber(String number);
    }

}

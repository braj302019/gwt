package com.gwt.client;

import java.util.List;

import com.google.web.bindery.requestfactory.shared.InstanceRequest;
import com.google.web.bindery.requestfactory.shared.Request;
import com.google.web.bindery.requestfactory.shared.RequestContext;
import com.google.web.bindery.requestfactory.shared.RequestFactory;
import com.google.web.bindery.requestfactory.shared.Service;
import com.gwt.server.Contact;

public interface Factory extends RequestFactory
{
    ContactRequest createContactRequest();
    
    @Service(Contact.class)
    public interface ContactRequest extends RequestContext
    {
        Request<Integer> count();
        Request<ContactProxy> findContact(Long id);
        Request<List<ContactProxy>> findAllContacts();
        
        InstanceRequest<ContactProxy, Void> persist();
        InstanceRequest<ContactProxy, Void> remove();
    }
}

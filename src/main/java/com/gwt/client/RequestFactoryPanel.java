
package com.gwt.client;

import java.util.Arrays;
import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Element;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.google.web.bindery.event.shared.SimpleEventBus;
import com.google.web.bindery.requestfactory.shared.Receiver;
import com.gwt.client.ContactProxy.PhoneProxy;
import com.gwt.client.Factory.ContactRequest;

public class RequestFactoryPanel extends Composite
{

    private static final RequestFactoryPanelUiBinder uiBinder = GWT.create(RequestFactoryPanelUiBinder.class);
    private static final Factory factory = GWT.create(Factory.class);

    interface RequestFactoryPanelUiBinder extends UiBinder<Widget, RequestFactoryPanel>
    {}

    public RequestFactoryPanel()
    {
        factory.initialize(new SimpleEventBus());
        initWidget(uiBinder.createAndBindUi(this));
    }

    @UiField
    HTML logging;

    @UiField
    TextBox input;

    @UiHandler("count")
    void count(ClickEvent e)
    {
        factory.createContactRequest().count().fire(new Receiver<Integer>()
            {

                @Override
                public void onSuccess(Integer response)
                {
                    log("Total Contacts :" + response);
                }

            });
    }

    @UiHandler("persist")
    void persist(ClickEvent e)
    {
        String rand = "" + (int) (Math.random() * 99999);

        ContactRequest context = factory.createContactRequest();

        ContactProxy contact = context.create(ContactProxy.class);
        contact.setName(rand);
        contact.setEmail(rand + "@example.com");
        contact.setNotes("Random notes for " + rand);

        PhoneProxy phone = context.create(PhoneProxy.class);
        phone.setType("Home");
        phone.setNumber("555-" + rand);
        contact.setPhone(Arrays.asList(phone));

        context.persist().using(contact).fire(new VoidReceiver("persist"));
    }

    @UiHandler("list")
    void list(ClickEvent e)
    {
        factory.createContactRequest().findAllContacts().with("phone").fire(new Receiver<List<ContactProxy>>()
            {

                @Override
                public void onSuccess(List<ContactProxy> response)
                {
                    if (response.size() > 0)
                    {
                        for (ContactProxy contact : response)
                        {
                            if (contact != null)
                            {
                                log(contact);
                            }
                        }
                    }
                    else
                    {
                        log("no contacts found");
                    }
                }

            });
    }

    @UiHandler("fetch")
    void fetch(ClickEvent e)
    {
        Long id = Long.valueOf(input.getValue());

        factory.createContactRequest().findContact(id).fire(new Receiver<ContactProxy>()
            {

                @Override
                public void onSuccess(ContactProxy response)
                {
                    if (response != null)
                    {
                        log(response);
                    }
                    else
                    {
                        log("Contact not found");
                    }
                }
            });
    }

    @UiHandler("delete")
    void delete(ClickEvent e)
    {
        Long id = Long.valueOf(input.getValue());

        ContactRequest context = factory.createContactRequest();
        ContactProxy contect = context.create(ContactProxy.class);
        contect.setId(id);

        context.remove().using(contect).fire(new VoidReceiver("delete"));
    }

    @UiHandler("clean")
    void clean(ClickEvent e)
    {
        logging.getElement().removeAllChildren();
    }

    private void log(ContactProxy contact)
    {
        log("--------------------------------------------------");
        log(contact.getId() + "-" + contact.getEmail());
        log(contact.getNotes());
        if(contact.getPhone() != null)
        {
            for(PhoneProxy phone : contact.getPhone())
            {
                log(phone.getType() + "#" + phone.getNumber());
            }
        }
        log("--------------------------------------------------");
    }

    private void log(String msg)
    {
        consoleLog(msg);
        Element log = DOM.createDiv();
        log.setInnerHTML(msg);
        logging.getElement().appendChild(log);
    }

    public static native void consoleLog(String msg) /*-{
		console.log(msg);
    }-*/;

    class VoidReceiver extends Receiver<Void>
    {
        private String operation;

        public VoidReceiver(String operation)
        {
            this.operation = operation;
        }

        @Override
        public void onSuccess(Void response)
        {
            log(operation + " operation completed successfully");
        }
    }

}

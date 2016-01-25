
package com.gwt.client;

import java.util.List;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.view.client.AsyncDataProvider;
import com.google.gwt.view.client.HasData;
import com.google.gwt.view.client.Range;
import com.google.web.bindery.event.shared.SimpleEventBus;
import com.google.web.bindery.requestfactory.shared.Receiver;
import com.gwt.client.Factory.ContactRequest;

public class ContactProvider extends AsyncDataProvider<ContactProxy>
{

    private Factory factory = GWT.create(Factory.class);

    public ContactProvider()
    {
        factory.initialize(new SimpleEventBus());
    }

    @Override
    protected void onRangeChanged(final HasData<ContactProxy> display)
    {
        ContactRequest context = factory.createContactRequest();

        context.findAllContacts().fire(new Receiver<List<ContactProxy>>()
            {

                @Override
                public void onSuccess(List<ContactProxy> response)
                {
                    if (response.size() > 0)
                    {
                        display.setRowCount(response.size());
                        Range range = display.getVisibleRange();
                        updateRowData(range.getStart(), response.subList(range.getStart(), range.getStart() + range.getLength()));
                    }
                }
            });
    }

}

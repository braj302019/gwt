
package com.gwt.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.ui.RootPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class BasicProject implements EntryPoint
{
    public void onModuleLoad()
    {
        CssResources.IMPL.css().ensureInjected();
        RootPanel.get().add(new BaseLayoutPanel());
        setupHistoryMgnt();
    }
    
    private void setupHistoryMgnt()
    {
        History.addValueChangeHandler(new ValueChangeHandler<String>()
            {
                
                @Override
                public void onValueChange(ValueChangeEvent<String> event)
                {
                    ClientFactory.getEventBus().fireEvent(new ChangeHistoryEvent(event.getValue()));
                }
            });
    }


}

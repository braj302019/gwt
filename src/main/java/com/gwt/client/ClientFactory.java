
package com.gwt.client;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.SimpleEventBus;

public class ClientFactory
{
    private static final EventBus EVENT_BUS = new SimpleEventBus();

    public static EventBus getEventBus()
    {
        return EVENT_BUS;
    }
}

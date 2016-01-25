
package com.gwt.client;

import com.google.web.bindery.event.shared.Event;
import com.google.web.bindery.event.shared.HandlerRegistration;

public class ChangeHistoryEvent extends Event<ChangeHistoryEvent.Handler>
{
    public interface Handler
    {
        void onHistoryChange(ChangeHistoryEvent event);
    }
    
    private static final Type<ChangeHistoryEvent.Handler> TYPE = new Type<>();

    private String token;

    public ChangeHistoryEvent(String token)
    {
        this.token = token;
    }

    public String getToken()
    {
        return token;
    }

    public static HandlerRegistration register(ChangeHistoryEvent.Handler handler)
    {
        return ClientFactory.getEventBus().addHandler(TYPE, handler);
    }

    @Override
    public Type<ChangeHistoryEvent.Handler> getAssociatedType()
    {
        return TYPE;
    }

    @Override
    protected void dispatch(ChangeHistoryEvent.Handler handler)
    {
        handler.onHistoryChange(this);
    }
}

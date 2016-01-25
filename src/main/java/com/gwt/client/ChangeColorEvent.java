
package com.gwt.client;

import com.google.web.bindery.event.shared.Event;
import com.google.web.bindery.event.shared.HandlerRegistration;

public class ChangeColorEvent extends Event<ChangeColorEvent.Handler>
{
    private static final Type<ChangeColorEvent.Handler> TYPE = new Type<>();

    public interface Handler
    {
        void onColorChanged(ChangeColorEvent event);
    }

    private String color;

    public ChangeColorEvent(String color)
    {
        this.color = color;
    }

    public String getColor()
    {
        return color;
    }

    public static HandlerRegistration register(ChangeColorEvent.Handler handler)
    {
        return ClientFactory.getEventBus().addHandler(TYPE, handler);
    }

    @Override
    public Type<ChangeColorEvent.Handler> getAssociatedType()
    {
        return TYPE;
    }

    @Override
    protected void dispatch(ChangeColorEvent.Handler handler)
    {
        handler.onColorChanged(this);
    }
}


package com.gwt.client.mvp.presenters;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.PlaceController;
import com.gwt.client.mvp.views.GoodbyeView;
import com.gwt.client.mvp.views.HelloView;

public interface ClientFactory
{
    EventBus getEventBus();

    PlaceController getPlaceController();

    HelloView getHelloView();

    GoodbyeView getGoodbyeView();
}
package com.gwt.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.TextResource;

public interface TextResources extends ClientBundle
{

    TextResources IMPL = GWT.create(TextResources.class);
    
    @Source("com/gwt/client/resources/about_us.txt")
    TextResource aboutUs();

}

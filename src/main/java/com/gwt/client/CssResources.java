
package com.gwt.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;

public interface CssResources extends ClientBundle
{
    CssResources IMPL = GWT.create(CssResources.class);
    
    @Source("resources/css/main.css")
    MyCssResource css();
    
}

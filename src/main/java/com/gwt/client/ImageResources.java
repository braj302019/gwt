package com.gwt.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.resources.client.ImageResource.ImageOptions;

public interface ImageResources extends ClientBundle
{
    ImageResources IMPL = GWT.create(ImageResources.class);
    
    @Source("com/gwt/client/resources/images/logo32.png")
    @ImageOptions(flipRtl = true)
    ImageResource logo();

}

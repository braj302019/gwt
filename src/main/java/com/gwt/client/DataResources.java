package com.gwt.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.DataResource;
import com.google.gwt.resources.client.DataResource.DoNotEmbed;
import com.google.gwt.resources.client.DataResource.MimeType;

public interface DataResources extends ClientBundle
{
    
    DataResources IMPL = GWT.create(DataResources.class);

    @Source("resources/images/Papership-Facebook-Cover.jpg")
    DataResource bigPhoto();
    
    @DoNotEmbed
    @Source("resources/pdf/html5_in_action.pdf")
    DataResource pdfHtml5();
    
    @MimeType("image/png")
    @Source("resources/images/logo32.png")
    DataResource logo();
}

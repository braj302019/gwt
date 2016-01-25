
package com.gwt.client;

import com.google.gwt.cell.client.AbstractCell;
import com.google.gwt.cell.client.Cell;
import com.google.gwt.core.shared.GWT;
import com.google.gwt.safehtml.client.SafeHtmlTemplates;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;

public class ContactCell extends AbstractCell<ContactProxy>
{

    private Template template = GWT.create(Template.class);
    
    interface Template extends SafeHtmlTemplates
    {
        @Template("<div style=\"border-left : 10px solid {3}; border-bottom : 1px solid #C2C2C2\"><div>{0} # {1}</div><div>{2}</div></div>")
        SafeHtml contact(Long id, String email, String notes, String color);
    }
    
    
    @Override
    public void render(Cell.Context context, ContactProxy contact, SafeHtmlBuilder sb)
    {
        if(contact == null)
        {
            return;
        }
        
        sb.append(template.contact(contact.getId(), contact.getEmail(), contact.getNotes(), ClientUtils.randomColor()));
    }

}

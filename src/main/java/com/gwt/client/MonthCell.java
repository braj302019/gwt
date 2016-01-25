
package com.gwt.client;

import com.google.gwt.cell.client.AbstractCell;
import com.google.gwt.cell.client.Cell;
import com.google.gwt.core.shared.GWT;
import com.google.gwt.safehtml.client.SafeHtmlTemplates;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;

public class MonthCell extends AbstractCell<Month>
{
    private Template template = GWT.create(Template.class);

    interface Template extends SafeHtmlTemplates
    {
        @Template("<div class=\"month\">{0}</div>")
        SafeHtml month(String month);
    }

    @Override
    public void render(Cell.Context context, Month value, SafeHtmlBuilder sb)
    {
        if (value == null)
        {
            return;
        }
        sb.append(template.month(value.getValue()));
    }

}

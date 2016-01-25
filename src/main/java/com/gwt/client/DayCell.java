
package com.gwt.client;

import com.google.gwt.cell.client.AbstractCell;
import com.google.gwt.cell.client.Cell;
import com.google.gwt.core.shared.GWT;
import com.google.gwt.safehtml.client.SafeHtmlTemplates;
import com.google.gwt.safehtml.client.SafeHtmlTemplates.Template;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;

public class DayCell extends AbstractCell<Day>
{
    private Template template = GWT.create(Template.class);

    interface Template extends SafeHtmlTemplates
    {
        @Template("<div class=\"day\">{0}</div>")
        SafeHtml day(String day);
    }

    @Override
    public void render(Cell.Context context, Day value, SafeHtmlBuilder sb)
    {
        if (value == null)
        {
            return;
        }
        sb.append(template.day(value.getValue()));
    }
}

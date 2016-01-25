
package com.gwt.client;

import com.google.gwt.cell.client.AbstractCell;
import com.google.gwt.cell.client.Cell;
import com.google.gwt.core.shared.GWT;
import com.google.gwt.safehtml.client.SafeHtmlTemplates;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;

public class YearCell extends AbstractCell<Year>
{
    private Template template = GWT.create(Template.class);

    interface Template extends SafeHtmlTemplates
    {
        @Template("<div class=\"year\">{0}</div>")
        SafeHtml year(String year);
    }

    @Override
    public void render(Cell.Context context, Year value, SafeHtmlBuilder sb)
    {
        if (value == null)
        {
            return;
        }
        sb.append(template.year(value.getValue()));
    }

}

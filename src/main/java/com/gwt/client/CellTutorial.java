
package com.gwt.client;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import com.google.gwt.cell.client.Cell;
import com.google.gwt.cell.client.DateCell;
import com.google.gwt.cell.client.DatePickerCell;
import com.google.gwt.cell.client.IconCellDecorator;
import com.google.gwt.cell.client.SafeHtmlCell;
import com.google.gwt.cell.client.SelectionCell;
import com.google.gwt.cell.client.TextCell;
import com.google.gwt.cell.client.TextInputCell;
import com.google.gwt.core.shared.GWT;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeHtmlUtils;
import com.google.gwt.user.cellview.client.CellList;
import com.google.gwt.user.cellview.client.CellWidget;
import com.google.gwt.user.cellview.client.SimplePager;
import com.google.gwt.user.client.ui.CaptionPanel;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.TabLayoutPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.view.client.AbstractDataProvider;

public class CellTutorial extends Composite
{
    private final ImageResources images = GWT.create(ImageResources.class);

    public CellTutorial()
    {
        initWidget(createTabs());
    }

    private TabLayoutPanel createTabs()
    {
        TabLayoutPanel panel = new TabLayoutPanel(2, Unit.EM);
        panel.add(createDisplayCells(), "Display Cells");
        panel.add(createEditableCells(), "Editable Cells");
        panel.add(createCellList(), "Cell List");
        return panel;
    }

    private VerticalPanel createDisplayCells()
    {
        VerticalPanel panel = new VerticalPanel();
        panel.setWidth("100%");

        CaptionPanel textCellPanel = new CaptionPanel("TextCell");
        CellWidget<String> textCellWidget = new CellWidget<String>(new TextCell());
        textCellWidget.setValue("Hello World!!!");
        textCellPanel.setContentWidget(textCellWidget);
        panel.add(textCellPanel);

        CaptionPanel dateCellPanel = new CaptionPanel("DateCell");
        CellWidget<Date> dateCellWidget = new CellWidget<Date>(new DateCell());
        dateCellWidget.setValue(new Date());
        dateCellPanel.setContentWidget(dateCellWidget);
        panel.add(dateCellPanel);

        CaptionPanel iconCellPanel = new CaptionPanel("IconCellDecorator");
        Cell<SafeHtml> iconCell = new IconCellDecorator<SafeHtml>(images.logo(), new SafeHtmlCell());
        CellWidget<SafeHtml> iconCellWidget = new CellWidget<SafeHtml>(iconCell);
        iconCellWidget.setValue(SafeHtmlUtils.fromString("Logo"));
        iconCellPanel.setContentWidget(iconCellWidget);
        panel.add(iconCellPanel);

        return panel;
    }

    private VerticalPanel createEditableCells()
    {
        VerticalPanel panel = new VerticalPanel();
        panel.setWidth("100%");

        CaptionPanel textCellPanel = new CaptionPanel("TextInputCell");
        TextInputCell textInputCell = new TextInputCell();
        CellWidget<String> textCellWidget = new CellWidget<String>(textInputCell);
        textCellPanel.setContentWidget(textCellWidget);
        panel.add(textCellPanel);

        CaptionPanel dateCellPanel = new CaptionPanel("DatePickerCell");
        CellWidget<Date> dateCellWidget = new CellWidget<Date>(new DatePickerCell());
        dateCellWidget.setValue(new Date());
        dateCellPanel.setContentWidget(dateCellWidget);
        panel.add(dateCellPanel);

        CaptionPanel selectionCellPanel = new CaptionPanel("SelectionCell");
        List<String> options = Arrays.asList(new String[] { "morning", "afternoon", "evening", "night" });
        CellWidget<String> selectionCellWidget = new CellWidget<String>(new SelectionCell(options));
        selectionCellWidget.setValue("evening");
        selectionCellPanel.setContentWidget(selectionCellWidget);
        panel.add(selectionCellPanel);

        return panel;
    }

    private VerticalPanel createCellList()
    {
        VerticalPanel panel = new VerticalPanel();
        panel.setWidth("100%");

        CellList<ContactProxy> list = new CellList<ContactProxy>(new ContactCell());
        list.setPageSize(5);
        
        AbstractDataProvider<ContactProxy> provider = new ContactProvider();
        provider.addDataDisplay(list);
        
        SimplePager pager = new SimplePager();
        pager.setDisplay(list);

        panel.add(list);
        panel.add(pager);
        return panel;
    }
}

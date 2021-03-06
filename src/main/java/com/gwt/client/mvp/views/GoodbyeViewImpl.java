
package com.gwt.client.mvp.views;

import com.google.gwt.dom.client.Element;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.SimplePanel;

public class GoodbyeViewImpl extends Composite implements GoodbyeView
{

    private SimplePanel viewPanel = new SimplePanel();
    private Element nameSpan = DOM.createSpan();

    public GoodbyeViewImpl()
    {
        viewPanel.getElement().appendChild(nameSpan);
        initWidget(viewPanel);
    }

    @Override
    public void setName(String name)
    {
        nameSpan.setInnerText("Good-bye, " + name);
    }
}

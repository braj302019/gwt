package com.gwt.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

public class LoginDialogBox extends Composite implements HasText
{

    private static LoginDialogBoxUiBinder uiBinder = GWT.create(LoginDialogBoxUiBinder.class);

    interface LoginDialogBoxUiBinder extends UiBinder<Widget, LoginDialogBox>
    {}

    public LoginDialogBox()
    {
        initWidget(uiBinder.createAndBindUi(this));
    }
    
    @UiField
    TextBox username;
    
    @UiField
    PasswordTextBox password;

    @UiField
    Button button;

    @UiHandler("button")
    void onClick(ClickEvent e)
    {
        Window.alert("Hello!");
    }

    @Override
    public void setText(String text)
    {
        button.setText(text);
    }

    @Override
    public String getText()
    {
        return button.getText();
    }

}

package com.gwt.client.mvp.presenters;

import com.google.gwt.user.client.ui.HasWidgets;

public interface Presenter
{
    void go(HasWidgets container);
    void bind();
}

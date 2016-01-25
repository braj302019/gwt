
package com.gwt.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.ScheduledCommand;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeHtmlUtils;
import com.google.gwt.safehtml.shared.SimpleHtmlSanitizer;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.Cookies;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.HasRpcToken;
import com.google.gwt.user.client.rpc.ServiceDefTarget;
import com.google.gwt.user.client.rpc.XsrfToken;
import com.google.gwt.user.client.rpc.XsrfTokenService;
import com.google.gwt.user.client.rpc.XsrfTokenServiceAsync;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.SimpleLayoutPanel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.TabLayoutPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class BaseLayoutPanel extends Composite
{

    private static BaseLayoutPanelUiBinder uiBinder = GWT.create(BaseLayoutPanelUiBinder.class);

    private XsrfTokenServiceAsync xsrfTokenService = GWT.create(XsrfTokenService.class);
    private SecureServiceAsync serviceService = GWT.create(SecureService.class);

    interface BaseLayoutPanelUiBinder extends UiBinder<Widget, BaseLayoutPanel>
    {}

    @UiField
    protected SimpleLayoutPanel container;

    public BaseLayoutPanel()
    {
        initWidget(uiBinder.createAndBindUi(this));
        subscribeChangeHistoryEvent();
    }

    private void subscribeChangeHistoryEvent()
    {
        ChangeHistoryEvent.register(new ChangeHistoryEvent.Handler()
            {

                @Override
                public void onHistoryChange(ChangeHistoryEvent event)
                {
                    String token = event.getToken();
                    GWT.log("History change event : " + token);

                    if (container.getWidget() != null)
                    {
                        container.getWidget().removeFromParent();
                    }

                    if ("tabs".equals(token))
                    {
                        container.add(getTabLayoutPanel());
                    }
                    else if ("client-bundle".equals(token))
                    {
                        container.add(getClientBundle());
                    }
                    else if ("secret-data".equals(token))
                    {
                        container.add(getSecretDataPanel());
                    }
                    else if ("ui-binder".equals(token))
                    {
                        container.add(new LoginDialogBox());
                    }
                    else if ("event-bus".equals(token))
                    {
                        container.add(getEventBusPanel());
                    }
                    else if("request-factory".equals(token))
                    {
                        container.add(new RequestFactoryPanel());
                    }
                    else if("cells".equals(token))
                    {
                        container.add(new CellTutorial());
                    }
                    else
                    {
                        container.add(getTabLayoutPanel());
                    }
                }
            });

        Scheduler.get().scheduleDeferred(new ScheduledCommand()
            {

                @Override
                public void execute()
                {
                    History.fireCurrentHistoryState();
                }
            });
    }

    private VerticalPanel getEventBusPanel()
    {
        VerticalPanel panel = new VerticalPanel();
        final HTML html = new HTML();
        html.setWidth("10em");
        html.setHeight("10em");
        html.getElement().getStyle().setBackgroundColor("red");
        final Button button = new Button("Paint me Green");
        button.addClickHandler(new ClickHandler()
            {

                @Override
                public void onClick(ClickEvent event)
                {
                    ClientFactory.getEventBus().fireEvent(new ChangeColorEvent("green"));
                }
            });
        ChangeColorEvent.register(new ChangeColorEvent.Handler()
            {

                @Override
                public void onColorChanged(ChangeColorEvent event)
                {
                    html.getElement().getStyle().setBackgroundColor(event.getColor());
                }
            });
        panel.add(button);
        panel.add(html);
        return panel;
    }

    private VerticalPanel getSecretDataPanel()
    {
        VerticalPanel panel = new VerticalPanel();
        final HTMLPanel html = new HTMLPanel("...");
        final Button button = new Button("Fetch secret data");

        button.addClickHandler(new ClickHandler()
            {

                @Override
                public void onClick(ClickEvent event)
                {
                    ((ServiceDefTarget) xsrfTokenService).setServiceEntryPoint(GWT.getModuleBaseURL() + "xsrf");

                    if (Cookies.getCookie("JSESSIONID") == null)
                    {
                        Cookies.setCookie("JSESSIONID", Double.toString(Math.random()));
                    }

                    xsrfTokenService.getNewXsrfToken(new AsyncCallback<XsrfToken>()
                        {

                            @Override
                            public void onSuccess(XsrfToken result)
                            {
                                ((HasRpcToken) serviceService).setRpcToken(result);

                                serviceService.getSecretData(new AsyncCallback<String>()
                                    {

                                        @Override
                                        public void onFailure(Throwable caught)
                                        {
                                            html.getElement().setInnerHTML("failed to get the secret data");
                                        }

                                        @Override
                                        public void onSuccess(String result)
                                        {
                                            html.getElement().setInnerHTML(result);
                                        }
                                    });
                            }

                            @Override
                            public void onFailure(Throwable caught)
                            {
                                html.getElement().setInnerHTML("failed to get the token");
                            }
                        });
                }
            });

        panel.add(button);
        panel.add(html);
        return panel;
    }

    private ScrollPanel getClientBundle()
    {
        Grid grid = new Grid(4, 3);
        grid.setBorderWidth(1);
        grid.setCellPadding(0);
        grid.setCellSpacing(0);
        grid.setStyleName("grid");
        grid.setWidget(0, 0, new Label("Name"));
        grid.setWidget(0, 1, new Label("URL"));
        grid.setWidget(0, 2, new Label("Resources"));
        grid.setWidget(1, 0, new Label(DataResources.IMPL.bigPhoto().getName()));
        grid.setWidget(1, 1, new Label(DataResources.IMPL.bigPhoto().getSafeUri().asString()));
        grid.setWidget(1, 2, new Anchor(DataResources.IMPL.bigPhoto().getName(), DataResources.IMPL.bigPhoto().getSafeUri().asString()));
        grid.setWidget(2, 0, new Label(DataResources.IMPL.logo().getName()));
        grid.setWidget(2, 2, new Image(DataResources.IMPL.logo().getSafeUri()));
        grid.setWidget(3, 0, new Label(DataResources.IMPL.pdfHtml5().getName()));
        grid.setWidget(3, 1, new Label(DataResources.IMPL.pdfHtml5().getSafeUri().asString()));
        grid.setWidget(3, 2, new Anchor(DataResources.IMPL.pdfHtml5().getName(), DataResources.IMPL.pdfHtml5().getSafeUri().asString()));

        Element pre = DOM.createElement("pre");
        pre.setInnerSafeHtml(SafeHtmlUtils.fromString(TextResources.IMPL.aboutUs().getText()));

        SimplePanel panel = new SimplePanel();
        panel.addStyleName(CssResources.IMPL.css().bold());
        panel.getElement().appendChild(pre);

        VerticalPanel verticalPanel = new VerticalPanel();
        verticalPanel.setWidth("100%");
        verticalPanel.add(grid);
        verticalPanel.add(panel);
        verticalPanel.add(new Image(ImageResources.IMPL.logo()));

        ScrollPanel scrollPanel = new ScrollPanel();
        scrollPanel.add(verticalPanel);
        return scrollPanel;
    }

    private TabLayoutPanel getTabLayoutPanel()
    {
        final TabLayoutPanel tabLayoutPanel = new TabLayoutPanel(2, Unit.EM);
        tabLayoutPanel.addStyleName("tab-container");
        tabLayoutPanel.add(getHome(), "home");
        tabLayoutPanel.add(getProducts(), "products");
        tabLayoutPanel.add(getContact(), "contect");
        return tabLayoutPanel;
    }

    private HTMLPanel getHome()
    {
        SafeHtml html = SimpleHtmlSanitizer.sanitizeHtml("<b>Welcome to GWT Basic Project</b>");
        HTMLPanel panel = new HTMLPanel(html);
        return panel;
    }

    private HTMLPanel getProducts()
    {
        HTMLPanel panel = new HTMLPanel("Products");
        return panel;
    }

    private HTMLPanel getContact()
    {
        HTMLPanel panel = new HTMLPanel("Contact");
        return panel;
    }
}

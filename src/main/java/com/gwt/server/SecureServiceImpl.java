
package com.gwt.server;

import com.google.gwt.user.server.rpc.XsrfProtectedServiceServlet;
import com.gwt.client.SecureService;

public class SecureServiceImpl extends XsrfProtectedServiceServlet implements SecureService
{

    private static final long serialVersionUID = 1L;

    @Override
    public String getSecretData()
    {
        return "Secret Data";
    }

}


package com.gwt.client;

import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.google.gwt.user.client.rpc.XsrfProtectedService;

@RemoteServiceRelativePath("secure")
public interface SecureService extends XsrfProtectedService
{
    public String getSecretData();
}

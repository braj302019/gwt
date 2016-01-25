package com.gwt.client;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface SecureServiceAsync
{

    void getSecretData(AsyncCallback<String> callback);

}

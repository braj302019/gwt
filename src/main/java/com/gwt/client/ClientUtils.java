
package com.gwt.client;

public class ClientUtils
{
    public static native void consoleLog(String msg) /*-{
		console.log(msg);
    }-*/;

    public static String randomColor()
    {
        String red = Integer.toHexString((int) Math.ceil(Math.random() * 240) + 16);
        String green = Integer.toHexString((int) Math.ceil(Math.random() * 240) + 16);
        String blue = Integer.toHexString((int) Math.ceil(Math.random() * 240) + 16);

        return "#" + red + green + blue;
    }
}

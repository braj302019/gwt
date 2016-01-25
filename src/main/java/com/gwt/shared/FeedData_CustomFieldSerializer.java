
package com.gwt.shared;

import java.util.Date;

import com.google.gwt.user.client.rpc.SerializationException;
import com.google.gwt.user.client.rpc.SerializationStreamReader;
import com.google.gwt.user.client.rpc.SerializationStreamWriter;

public class FeedData_CustomFieldSerializer
{
    public static void serialize(SerializationStreamWriter ssw, FeedData instance) throws SerializationException
    {
        ssw.writeObject(instance.getCreatedAt());
        ssw.writeString(instance.getText());
    }

    public static FeedData instantiate(SerializationStreamReader ssr) throws SerializationException
    {
        return new FeedData((Date) ssr.readObject());
    }

    public static void deserialize(SerializationStreamReader ssr, FeedData instance) throws SerializationException
    {
        instance.setText(ssr.readString());
    }
}

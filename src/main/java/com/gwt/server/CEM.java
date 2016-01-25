
package com.gwt.server;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

public class CEM
{
    private static final Map<Long, Contact> STORAGE = new ConcurrentHashMap<Long, Contact>();
    private static final AtomicLong counter = new AtomicLong(0);

    private CEM()
    {}

    public static Contact read(Long id)
    {
        return STORAGE.get(id);
    }

    public static void create(Contact contact)
    {
        contact.setId(counter.incrementAndGet());
        STORAGE.put(contact.getId(), contact);
    }

    public static void delete(Long id)
    {
        STORAGE.remove(id);
    }

    public static List<Contact> list()
    {
        return new ArrayList<Contact>(STORAGE.values());
    }
    
    public static int count()
    {
        return STORAGE.size();
    }

}

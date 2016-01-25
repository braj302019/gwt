
package com.gwt.server;

import java.util.List;

public class Contact
{
    private Long id;
    private Integer version = 1;
    private String name;
    private String email;
    private List<Phone> phone;
    private String notes;

    public static class Phone
    {
        private String type;
        private String number;
        public String getType()
        {
            return type;
        }
        public void setType(String type)
        {
            this.type = type;
        }
        public String getNumber()
        {
            return number;
        }
        public void setNumber(String number)
        {
            this.number = number;
        }

    }

    public static Integer count()
    {
        return CEM.count();
    }

    public static List<Contact> findAllContacts()
    {
        return CEM.list();
    }

    public static Contact findContact(Long id)
    {
        return CEM.read(id);
    }

    public void persist()
    {
        CEM.create(this);
    }

    public void remove()
    {
        CEM.delete(this.getId());
    }

    public Long getId()
    {
        return id;
    }
    
    public void setId(Long id)
    {
        this.id = id;
    }

    public Integer getVersion()
    {
        return version;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public List<Phone> getPhone()
    {
        return phone;
    }

    public void setPhone(List<Phone> phone)
    {
        this.phone = phone;
    }

    public String getNotes()
    {
        return notes;
    }

    public void setNotes(String notes)
    {
        this.notes = notes;
    }

}

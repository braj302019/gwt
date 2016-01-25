
package com.gwt.client;

public class Month
{

    private String year;
    private String value;

    public Month(String year, String value)
    {
        this.year = year;
        this.value = value;
    }

    public String getValue()
    {
        return value;
    }

    public String getYear()
    {
        return year;
    }

}
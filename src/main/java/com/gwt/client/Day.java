
package com.gwt.client;

public class Day
{

    private String year;
    private String month;
    private String value;

    public Day(String year, String month, String value)
    {
        this.year = year;
        this.month = month;
        this.value = value;
    }

    public String getValue()
    {
        return value;
    }

    public String getMonth()
    {
        return month;
    }

    public String getYear()
    {
        return year;
    }

}
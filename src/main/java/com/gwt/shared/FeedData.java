
package com.gwt.shared;

import java.util.Date;

public class FeedData
{
    private Date createdAt;
    private String text;

    public FeedData(Date createdAt)
    {
        this.createdAt = createdAt;
    }

    public Date getCreatedAt()
    {
        return createdAt;
    }

    public String getText()
    {
        return text;
    }

    public void setText(String text)
    {
        this.text = text;
    }
}

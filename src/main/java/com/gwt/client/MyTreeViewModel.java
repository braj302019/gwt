
package com.gwt.client;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.view.client.ListDataProvider;
import com.google.gwt.view.client.TreeViewModel;

public class MyTreeViewModel implements TreeViewModel
{

    @Override
    public <T> NodeInfo<?> getNodeInfo(T value)
    {
        if (value == null)
        {
            final List<Year> years = new ArrayList<>();
            for (int i = 2010; i <= 2015; i++)
            {
                years.add(new Year(String.valueOf(i)));
            }
            return new DefaultNodeInfo<Year>(new ListDataProvider<Year>(years), new YearCell());
        }
        else if (value instanceof Year)
        {
            String year = ((Year) value).getValue();
            final List<Month> months = new ArrayList<>();
            for (int i = 1; i <= 12; i++)
            {
                months.add(new Month(year, String.valueOf(i)));
            }

            return new DefaultNodeInfo<Month>(new ListDataProvider<Month>(months), new MonthCell());
        }
        else if (value instanceof Month)
        {
            String month = ((Month) value).getValue();
            String year = ((Month) value).getYear();

            final List<Day> days = new ArrayList<>();
            for (int i = 1; i <= 31; i++)
            {
                days.add(new Day(year, month, String.valueOf(i)));
            }

            return new DefaultNodeInfo<Day>(new ListDataProvider<Day>(days), new DayCell());
        }

        // Unhandled type.
        String type = value.getClass().getName();
        ClientUtils.consoleLog("Unsupported object type: " + type);
        return null;
    }

    @Override
    public boolean isLeaf(Object value)
    {
        return value instanceof Day;
    }

}

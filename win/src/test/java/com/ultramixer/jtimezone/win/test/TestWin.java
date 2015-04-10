package com.ultramixer.jtimezone.win.test;

import com.ultramixer.jtimezone.JTimeZoneChangeListener;
import com.ultramixer.jtimezone.JTimeZone;
import org.joda.time.DateTimeZone;
import org.junit.Test;

/**
 * Created by TB on 10.04.15.
 */
public class TestWin
{
    @Test
    public void test()
    {
        JTimeZone tz = new JTimeZone();
        tz.addTimeZoneChangeListener(new JTimeZoneChangeListener()
        {
            public void timeZoneChanged(DateTimeZone dateTimeZone)
            {
                System.out.println("dateTimeZone = " + dateTimeZone);
            }
        });
    }


}

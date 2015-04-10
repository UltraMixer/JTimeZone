package com.ultramixer.jtimezone.win.test;

import com.ultramixer.jtimezone.JTimeZoneChangeListener;
import com.ultramixer.jtimezone.JTimeZone;
import org.joda.time.DateTimeZone;
import org.junit.Test;

import javax.swing.*;
import java.util.concurrent.CountDownLatch;

/**
 * Created by TB on 10.04.15.
 */
public class TestWin
{
    public static  JFrame frame;

    @Test
    public void test()
    {
        final CountDownLatch latch = new CountDownLatch(1);

        frame = new JFrame("Test");
        frame.setSize(800,600);
        frame.setVisible(true);

        JTimeZone tz = new JTimeZone();
        tz.addTimeZoneChangeListener(new JTimeZoneChangeListener()
        {
            public void timeZoneChanged(DateTimeZone dateTimeZone)
            {
                System.out.println("dateTimeZone = " + dateTimeZone);

                latch.countDown();
            }
        });

        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}

package com.ultramixer.jtimezone.mac.test;

import com.ultramixer.jtimezone.JTimeZoneChangeListener;
import com.ultramixer.jtimezone.JTimeZone;
import org.joda.time.DateTimeZone;
import org.junit.Assert;
import org.junit.Test;

import java.util.concurrent.CountDownLatch;

/**
 * Created by TB on 10.04.15.
 */
public class TestMac
{
    @Test
    public void test()
    {
        final CountDownLatch latch = new CountDownLatch(1);

        JTimeZone timeZoneService = new JTimeZone();

        final DateTimeZone tz = timeZoneService.getDefaultTimeZone();
        System.out.println("tz = " + tz);

        Assert.assertNotNull(tz);

        timeZoneService.addTimeZoneChangeListener(new JTimeZoneChangeListener()
        {
            public void timeZoneChanged(DateTimeZone dateTimeZone)
            {
                System.out.println("new java dateTimeZone = " + dateTimeZone);
                Assert.assertNotNull(dateTimeZone);
                Assert.assertNotEquals(tz.getID(), dateTimeZone.getID());
                latch.countDown();
            }
        });

        try
        {
            latch.await();
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }

    }
}

package com.ultramixer.jtimezone.mac.test;

import com.ultramixer.jtimezone.JTimeZone;
import com.ultramixer.jtimezone.JTimeZoneChangeListener;
import org.joda.time.DateTime;
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
        final CountDownLatch latch = new CountDownLatch(2);

        JTimeZone timeZoneService = new JTimeZone();

        final DateTimeZone nativeTimeZone = timeZoneService.getDefaultTimeZone();
        System.out.println("nativeTimeZone = " + nativeTimeZone);

        DateTimeZone javaTimeZone = DateTimeZone.getDefault();


        long javaTimeOffset = javaTimeZone.toTimeZone().getOffset(DateTime.now(javaTimeZone).getMillis());
        long nativeTimeOffset = nativeTimeZone.toTimeZone().getRawOffset();

        Assert.assertEquals("Native Time Zone and Java Time Zone not equal", nativeTimeOffset, javaTimeOffset);

        long offset = timeZoneService.getDefaultTimeZoneOffsetInMillis();
        System.out.println("offset = " + offset);

        long tzOffset = nativeTimeZone.toTimeZone().getOffset(DateTime.now(nativeTimeZone).getMillis());
        Assert.assertEquals(tzOffset, offset);


        Assert.assertNotNull(nativeTimeZone);

        timeZoneService.addTimeZoneChangeListener(new JTimeZoneChangeListener()
        {
            public void timeZoneChanged(DateTimeZone dateTimeZone)
            {
                System.out.println("new java dateTimeZone = " + dateTimeZone);
                Assert.assertNotNull(dateTimeZone);
                Assert.assertNotEquals(nativeTimeZone.getID(), dateTimeZone.getID());
                System.out.println("dateTimeZone = " + dateTimeZone.toTimeZone().getDisplayName());
                System.out.println("dateTimeZone.toTimeZone().getOffset() = " + dateTimeZone.toTimeZone().getOffset(DateTime.now(dateTimeZone).getMillis()));
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

package com.ultramixer.jtimezone;

import org.joda.time.DateTimeZone;

/**
 * Created by TB on 10.04.15.
 */
public interface JTimeZoneChangeListener
{
    public void timeZoneChanged(DateTimeZone dateTimeZone);
}

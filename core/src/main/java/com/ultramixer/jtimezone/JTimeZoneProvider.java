package com.ultramixer.jtimezone;

import org.joda.time.DateTimeZone;

/**
 * Created by TB on 10.04.15.
 */
public interface JTimeZoneProvider
{
    public void addTimeZoneChangeListener(JTimeZoneChangeListener listener);

    public boolean removeTimeZoneChangeListener(JTimeZoneChangeListener listener);

    public String getDefaultTimeZoneName();

    public DateTimeZone getDefaultTimeZone();

    public Long getDefaultTimeZoneOffsetInMillis();
}

package com.ultramixer.jtimezone.win;

import com.ultramixer.jtimezone.JTimeZoneChangeListener;
import com.ultramixer.jtimezone.JTimeZoneProvider;

import java.util.logging.Logger;

/**
 * Created by TB on 10.04.15.
 */
public class WinJTimeZoneProvider implements JTimeZoneProvider
{
    private Logger logger = Logger.getLogger(getClass().getName());


    public void addTimeZoneChangeListener(JTimeZoneChangeListener listener)
    {
        logger.info("Not yet implemented on Win");
    }

    public boolean removeTimeZoneChangeListener(JTimeZoneChangeListener listener)
    {
        logger.info("Not yet implemented on Win");
        return false;
    }
}

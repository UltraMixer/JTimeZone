package com.ultramixer.jtimezone;

import org.joda.time.DateTimeZone;

import java.util.Iterator;
import java.util.ServiceLoader;
import java.util.logging.Logger;

/**
 * Created by TB on 10.04.15.
 */
public class JTimeZone implements JTimeZoneProvider
{
    private final ServiceLoader<JTimeZoneProvider> serviceLoader;
    private JTimeZoneProvider provider;

    private Logger logger = Logger.getLogger(getClass().getName());

    public JTimeZone()
    {
        serviceLoader = ServiceLoader.load(JTimeZoneProvider.class);

        Iterator<JTimeZoneProvider> iterator = serviceLoader.iterator();
        while (iterator.hasNext())
        {
            if (provider == null)
            {
                provider = iterator.next();
                logger.info(String.format("Using TimeZoneProvider: %s", provider.getClass().getName()));
            }
            else
            {
                logger.info(String.format("This TimeZoneProvider is ignored: %s", iterator.next().getClass().getName()));
            }
        }

        if (provider == null)
        {
            logger.severe("No TimeZoneProvider implementation could be found!");
        }
        else
        {
            System.out.println("provider = " + provider);
        }
    }

    public void addTimeZoneChangeListener(JTimeZoneChangeListener listener)
    {
        if (!checkProvider())
        {
            return;
        }

        provider.addTimeZoneChangeListener(listener);

    }

    public boolean removeTimeZoneChangeListener(JTimeZoneChangeListener listener)
    {
        if (!checkProvider())
        {
            return true;
        }

        return provider.removeTimeZoneChangeListener(listener);
    }

    private boolean checkProvider()
    {
        return provider != null;
    }


    public DateTimeZone getDefaultTimeZone()
    {
        if (!checkProvider())
        {
            return null;
        }

        return provider.getDefaultTimeZone();
    }

    public String getDefaultTimeZoneName()
    {
        if (!checkProvider())
        {
            return null;
        }

        return provider.getDefaultTimeZoneName();
    }

    public Long getDefaultTimeZoneOffsetInMillis()
    {
        if (!checkProvider())
        {
            return null;
        }

        return provider.getDefaultTimeZoneOffsetInMillis();
    }
}

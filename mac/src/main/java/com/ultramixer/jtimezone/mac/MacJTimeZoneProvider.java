package com.ultramixer.jtimezone.mac;

import com.ultramixer.jtimezone.JTimeZoneChangeListener;
import com.ultramixer.jtimezone.JTimeZoneProvider;

import java.util.ArrayList;
import java.util.logging.Logger;

/**
 * Created by TB on 10.04.15.
 */
public class MacJTimeZoneProvider implements JTimeZoneProvider
{
    private ArrayList<JTimeZoneChangeListener> timeZoneChangeListeners = new ArrayList<JTimeZoneChangeListener>(1);

    private Logger logger = Logger.getLogger(getClass().getName());

    public void addTimeZoneChangeListener(JTimeZoneChangeListener listener)
    {
        timeZoneChangeListeners.add(listener);

        if (timeZoneChangeListeners.size() == 1)
        {
            startTimeZoneChangeListening();
        }
    }

    private void startTimeZoneChangeListening()
    {
        NSSystemTimeZoneDidChangeNotificationManager.getInstance().setListeners(timeZoneChangeListeners);
    }

    private void stopTimeZoneChangeListening()
    {
        if (NSSystemTimeZoneDidChangeNotificationManager.instance != null)
        {
            NSSystemTimeZoneDidChangeNotificationManager.getInstance().setListeners(null);
        }
    }


    public boolean removeTimeZoneChangeListener(JTimeZoneChangeListener listener)
    {
        return timeZoneChangeListeners.remove(listener);
    }

    public String getDefaultTimeZoneName()
    {
        return NSTimeZone.defaultTimeZone().getName();
    }
}

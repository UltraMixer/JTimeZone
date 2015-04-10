package com.ultramixer.jtimezone.mac;

import ca.weblite.objc.NSObject;
import ca.weblite.objc.Proxy;
import ca.weblite.objc.RuntimeUtils;
import ca.weblite.objc.annotations.Msg;
import com.ultramixer.jtimezone.JTimeZoneChangeListener;
import org.joda.time.DateTimeZone;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by TB on 10.04.15.
 */
public class NSSystemTimeZoneDidChangeNotificationManager extends NSObject
{
    public static NSSystemTimeZoneDidChangeNotificationManager instance;
    private ArrayList<JTimeZoneChangeListener> listeners;

    private Logger logger = Logger.getLogger(getClass().getName());

    public NSSystemTimeZoneDidChangeNotificationManager()
    {
        init("NSObject");

        Proxy defaultCenter = getClient().sendProxy("NSNotificationCenter", "defaultCenter");

        if (defaultCenter != null)
        {
            defaultCenter.send("addObserver:selector:name:object:", this, RuntimeUtils.sel("nsSystemTimeZoneDidChangeNotification"), "kCFTimeZoneSystemTimeZoneDidChangeNotification", null);
        }

    }

    public static NSSystemTimeZoneDidChangeNotificationManager getInstance()
    {
        if (instance == null)
        {
            instance = new NSSystemTimeZoneDidChangeNotificationManager();
        }
        return instance;
    }

    private void fireTimeZoneChanged(String timeZoneName)
    {
        if (getListeners() == null)
        {
            return;
        }

        DateTimeZone dtz = DateTimeZone.forID(timeZoneName);

        for (JTimeZoneChangeListener listener : listeners)
        {
            listener.timeZoneChanged(dtz);
        }
    }

    public ArrayList<JTimeZoneChangeListener> getListeners()
    {
        return listeners;
    }

    public void setListeners(ArrayList<JTimeZoneChangeListener> listeners)
    {
        this.listeners = listeners;


    }

    @Msg(selector = "nsSystemTimeZoneDidChangeNotification", signature = "v@:@")
    public void nsSystemTimeZoneDidChangeNotification(Proxy nsNotification)
    {
        try
        {
            Proxy proxy = getClient().sendProxy("NSTimeZone", "defaultTimeZone");
            String timeZoneName = proxy.sendString("name");
            fireTimeZoneChanged(timeZoneName);
        }
        catch (Exception e)
        {
            logger.log(Level.WARNING, "", e);
        }

    }
}

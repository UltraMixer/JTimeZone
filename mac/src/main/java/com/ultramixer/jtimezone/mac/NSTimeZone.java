package com.ultramixer.jtimezone.mac;

import ca.weblite.objc.NSObject;
import ca.weblite.objc.Proxy;

/**
 * Created by TB on 10.04.15.
 */
public class NSTimeZone extends NSObject
{
    private Proxy proxy;


    public NSTimeZone()
    {
        super();
        init("NSObject");
    }

    public static NSTimeZone defaultTimeZone()
    {
        NSTimeZone tz = new NSTimeZone();
        tz.proxy = tz.getClient().sendProxy("NSTimeZone", "defaultTimeZone");
        return tz;
    }


    @Override
    public String toString()
    {
        return proxy.toString();
    }

    public String getName()
    {
        return proxy.sendString("name");
    }
}

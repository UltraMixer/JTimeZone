package com.ultramixer.jtimezone.win;

import com.sun.jna.Native;
import com.sun.jna.platform.win32.WinDef;
import com.ultramixer.jtimezone.JTimeZoneChangeListener;
import com.ultramixer.jtimezone.JTimeZoneProvider;
import org.joda.time.DateTimeZone;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.logging.Logger;

/**
 * Created by TB on 10.04.15.
 */
public class WinJTimeZoneProvider implements JTimeZoneProvider
{
    private Logger logger = Logger.getLogger(getClass().getName());
    private ArrayList<JTimeZoneChangeListener> timeZoneChangeListeners = new ArrayList<JTimeZoneChangeListener>(1);
    private MyListener listener;
    private WinDef.HWND hWnd;

    public WinJTimeZoneProvider()
    {


    }

    public String getDefaultTimeZoneName()
    {
        TIME_ZONE_INFORMATION tzi = new TIME_ZONE_INFORMATION();
        MyKernel32.MYINSTANCE.GetTimeZoneInformation(tzi);

        return String.valueOf(tzi.StandardName);
    }

    public Long getDefaultTimeZoneOffsetInMillis()
    {
        TIME_ZONE_INFORMATION tzi = new TIME_ZONE_INFORMATION();
        MyKernel32.MYINSTANCE.GetTimeZoneInformation(tzi);

        return (tzi.Bias.longValue() * 60 * 1000 * -1);
    }

    public DateTimeZone getDefaultTimeZone()
    {
        return DateTimeZone.forOffsetMillis(getDefaultTimeZoneOffsetInMillis().intValue());
    }

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
        Window window = null;
        if (Window.getWindows().length > 0)
        {
            window = window.getWindows()[0];
        }
        else
        {
            window = new JWindow();
            window.setVisible(false);
        }


        if (listener == null)
        {
            listener = new MyListener()
            {
                public WinDef.LRESULT callback(WinDef.HWND hWnd, int uMsg, WinDef.WPARAM uParam, WinDef.LPARAM lParam)
                {
                    if (uMsg == MyWinUser.WM_TIMECHANGE)
                    {
                        return new WinDef.LRESULT(1);
                    }
                    return new WinDef.LRESULT(0);
                }
            };
        }

        if (hWnd == null)
        {
            hWnd = new WinDef.HWND();
            hWnd.setPointer(Native.getWindowPointer(window));
        }

        MyUser32.MYINSTANCE.SetWindowLong(hWnd, MyUser32.GWLP_WNDPROC, listener);

    }

    public boolean removeTimeZoneChangeListener(JTimeZoneChangeListener listener)
    {
        return timeZoneChangeListeners.remove(listener);
    }


}

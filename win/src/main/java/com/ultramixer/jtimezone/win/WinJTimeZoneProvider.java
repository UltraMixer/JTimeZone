package com.ultramixer.jtimezone.win;

import com.sun.jna.Native;
import com.sun.jna.platform.win32.WinDef;
import com.sun.jna.platform.win32.WinUser;
import com.ultramixer.jtimezone.JTimeZoneChangeListener;
import com.ultramixer.jtimezone.JTimeZoneProvider;

import javax.swing.*;
import java.util.logging.Logger;

/**
 * Created by TB on 10.04.15.
 */
public class WinJTimeZoneProvider implements JTimeZoneProvider
{
    private Logger logger = Logger.getLogger(getClass().getName());

    public WinJTimeZoneProvider()
    {
        JFrame frame = new JFrame("Test");
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        WinUser.WindowProc proc = new WinUser.WindowProc()
        {
            public WinDef.LRESULT callback(WinDef.HWND hwnd, int i, WinDef.WPARAM wparam, WinDef.LPARAM lparam)
            {
                return null;
            }
        };
        //WM_TIMECHANGE

        WinDef.HWND hWnd = new WinDef.HWND();
        hWnd.setPointer(Native.getWindowPointer(frame));


        MyListener listener = new MyListener()
        {
            public WinDef.LRESULT callback(WinDef.HWND hWnd, int uMsg, WinDef.WPARAM uParam, WinDef.LPARAM lParam)
            {
                System.out.println("uMsg = " + uMsg);
                /*
                if (uMsg == MyWinUser.WM_DEVICECHANGE)
                {
                    // TODO Check If my device was attached or detached
                    return new WinDef.LRESULT(1);
                }
                */
                return new WinDef.LRESULT(0);
            }
        };

        MyUser32.MYINSTANCE.SetWindowLong(hWnd, MyUser32.GWLP_WNDPROC, listener);

    }

    public String getDefaultTimeZoneName()
    {
        return null;
    }

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

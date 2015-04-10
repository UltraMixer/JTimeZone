package com.ultramixer.jtimezone.win;

import com.sun.jna.Native;
import com.sun.jna.NativeLong;
import com.sun.jna.platform.win32.*;
import com.ultramixer.jtimezone.JTimeZoneChangeListener;
import com.ultramixer.jtimezone.JTimeZoneProvider;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;

import javax.swing.*;
import java.awt.*;
import java.util.TimeZone;
import java.util.logging.Logger;

/**
 * Created by TB on 10.04.15.
 */
public class WinJTimeZoneProvider implements JTimeZoneProvider {
    private Logger logger = Logger.getLogger(getClass().getName());

    public WinJTimeZoneProvider() {
        //WM_TIMECHANGE

        System.out.println("Kernel32Util.getComputerName() = " + Kernel32Util.getComputerName());


        TIME_ZONE_INFORMATION tzi = new TIME_ZONE_INFORMATION();
        MyKernel32.MYINSTANCE.GetTimeZoneInformation(tzi);

        String nativeTimeZone = String.valueOf(tzi.StandardName);
       // System.out.println("tzi = " + tzi);

        NativeLong offsetInMinutes = tzi.Bias;
        System.out.println("offsetInMinutes = " + offsetInMinutes);


        DateTime now = DateTime.now(DateTimeZone.UTC);
        System.out.println("now = " + now);

        DateTimeZone dtz = DateTimeZone.forOffsetMillis((int) (tzi.Bias.longValue() * 60 * 1000 * -1));
        //DateTimeZone dtz = DateTimeZone.forOffsetHours(2);
        System.out.println("dtz = " + dtz.toTimeZone().getID());

        System.out.println("nativeTimeZone = " + nativeTimeZone);

        System.out.println("now.toDateTime(dtz) = " + now.toDateTime(dtz));




        Frame frame = Frame.getFrames()[0];
       // System.out.println("frame = " + frame);

        WinDef.HWND hWnd = new WinDef.HWND();
        hWnd.setPointer(Native.getWindowPointer(frame));



        MyListener listener = new MyListener() {
            public WinDef.LRESULT callback(WinDef.HWND hWnd, int uMsg, WinDef.WPARAM uParam, WinDef.LPARAM lParam) {
                if (uMsg == MyWinUser.WM_TIMECHANGE) {
                    System.out.println("Time Changed!");
                    // TODO Check If my device was attached or detached

                    TimeZone.setDefault(null);
                    System.out.println("  DateTimeZone.getDefault() = " + DateTimeZone.getDefault());
                    return new WinDef.LRESULT(1);
                }
                return new WinDef.LRESULT(0);
            }
        };

        MyUser32.MYINSTANCE.SetWindowLong(hWnd, MyUser32.GWLP_WNDPROC, listener);

    }

    public String getDefaultTimeZoneName() {
        return null;
    }

    public void addTimeZoneChangeListener(JTimeZoneChangeListener listener) {
        logger.info("Not yet implemented on Win");
    }

    public boolean removeTimeZoneChangeListener(JTimeZoneChangeListener listener) {
        logger.info("Not yet implemented on Win");
        return false;
    }
}

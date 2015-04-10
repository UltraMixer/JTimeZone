package com.ultramixer.jtimezone.win;

import com.sun.jna.Native;
import com.sun.jna.platform.win32.Kernel32;
import com.sun.jna.win32.W32APIOptions;

/**
 * Created by AIDA on 10.04.2015.
 */
public interface MyKernel32 extends Kernel32 {

    public static final MyKernel32 MYINSTANCE = (MyKernel32) Native.loadLibrary("kernel32", MyKernel32.class, W32APIOptions.UNICODE_OPTIONS);


    public void GetTimeZoneInformation(TIME_ZONE_INFORMATION zoneInformation);
}

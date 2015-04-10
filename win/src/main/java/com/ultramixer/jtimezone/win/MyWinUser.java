package com.ultramixer.jtimezone.win;

import com.sun.jna.platform.win32.WinUser;

/**
 * Created by AIDA on 10.04.2015.
 */
public interface MyWinUser extends WinUser {

    public static final int WM_DEVICECHANGE = 0x0219;
    public static final int WM_TIMECHANGE = 0x001E;
}

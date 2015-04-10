package com.ultramixer.jtimezone.win;

import com.sun.jna.Callback;
import com.sun.jna.Native;
import com.sun.jna.platform.win32.User32;
import com.sun.jna.platform.win32.WinDef;
import com.sun.jna.win32.W32APIOptions;

/**
 * Created by TB on 10.04.15.
 */

public interface MyUser32 extends User32
{

    public static final MyUser32 MYINSTANCE = (MyUser32) Native.loadLibrary("user32", MyUser32.class, W32APIOptions.UNICODE_OPTIONS);

    /**
     * Sets a new address for the window procedure (value to be set).
     */
    public static final int GWLP_WNDPROC = -4;

    /**
     * Changes an attribute of the specified window
     *
     * @param hWnd     A handle to the window
     * @param nIndex   The zero-based offset to the value to be set.
     * @param callback The callback function for the value to be set.
     */
    public int SetWindowLong(WinDef.HWND hWnd, int nIndex, Callback callback);
}

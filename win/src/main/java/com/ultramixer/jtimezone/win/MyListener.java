package com.ultramixer.jtimezone.win;

import com.sun.jna.platform.win32.WinDef;
import com.sun.jna.win32.StdCallLibrary;

/**
 * Created by TB on 10.04.15.
 */
public interface MyListener extends StdCallLibrary.StdCallCallback
{

    public WinDef.LRESULT callback(WinDef.HWND hWnd, int uMsg, WinDef.WPARAM uParam, WinDef.LPARAM lParam);
}
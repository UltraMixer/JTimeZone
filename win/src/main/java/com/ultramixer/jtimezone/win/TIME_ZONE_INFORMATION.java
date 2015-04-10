package com.ultramixer.jtimezone.win;

import com.sun.jna.NativeLong;
import com.sun.jna.Structure;
import com.sun.jna.platform.win32.WinBase;

import java.util.Arrays;
import java.util.List;

/**
 * Created by AIDA on 10.04.2015.
 */
public class TIME_ZONE_INFORMATION extends Structure {

    public NativeLong Bias; //offset zu UTC in minuten
    public char[] StandardName = new char[32];//WCHAR[32]
    public WinBase.SYSTEMTIME StandardDate;
    public long StandardBias;
    public char[] DaylightName= new char[32]; //[//WCHAR 32]
    public WinBase.SYSTEMTIME DaylightDate;
    public long DaylightBias;


    public TIME_ZONE_INFORMATION() {
    }

    protected List getFieldOrder() {
        return Arrays.asList(new String[]{"Bias", "StandardName", "StandardDate","StandardBias", "DaylightName","DaylightDate","DaylightBias"});
    }
}
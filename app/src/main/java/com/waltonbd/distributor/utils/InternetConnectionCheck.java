package com.waltonbd.distributor.utils;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by omor on 3/28/2018.
 */

public class InternetConnectionCheck {


    public static boolean checkConn(Context ctx)
    {
        try {
            ConnectivityManager conMgr =  (ConnectivityManager)ctx.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo info = conMgr.getActiveNetworkInfo();
            if (info == null)
                return false;
            if (!info.isConnected())
                return false;
            if (!info.isAvailable())
                return false;
            return true;
        }
        catch (Exception e)
        {
             return false;
        }



    }
    /**
     * Validation of Phone Number
     */

}

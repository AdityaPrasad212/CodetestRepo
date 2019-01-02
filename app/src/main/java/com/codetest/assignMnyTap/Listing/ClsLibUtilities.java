package com.codetest.assignMnyTap.Listing;

import android.content.Context;
import android.content.res.Resources;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.util.TypedValue;


/*
  Application MnyTapAssign
  Filename ClsLibUtilities.java
  Description: ClsLibUtilities class is a utility class, which has some commonly used functions
  Created December 28,2018.
  Created by Aditya Prasad
  Updated December 29,2018.
  Updator Aditya Prasad
 */

public class ClsLibUtilities {


    /**************************************************************************************
     * Function[dpTopx] - function for converting value in dp to px
     * Created by Aditya Prasad
     * Created December 28,2018.
     * Updator  by Aditya Prasad
     * Updated December 28,2018.
     *
     * @param context : context
     * @param intdx   : integer value in dp
     */
    public static int dpTopx(Context context, int intdx) {
        int px = 0;
        try {
            Resources resources = context.getResources();
            px = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, intdx, resources.getDisplayMetrics());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return px;
    }

    /**************************************************************************************
     * Function[getColorFromRes] - function for getting the color value from resource
     * Created by Aditya Prasad
     * Created December 28,2018.
     * Updator  by Aditya Prasad
     * Updated December 28,2018.
     *
     * @param context : context
     * @param intId   : resource ID
     */
    public static int getColorFromRes(Context context, int intId) {
        final int version = Build.VERSION.SDK_INT;
        if (version >= 23) {
            return ContextCompat.getColor(context, intId);
        } else {
            return context.getResources().getColor(intId);
        }
    }
}
/*End of file*/
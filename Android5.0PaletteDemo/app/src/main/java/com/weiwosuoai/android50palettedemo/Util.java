package com.weiwosuoai.android50palettedemo;

import android.os.Build;

/**
 * Created by jiangbing on 2016/4/29.
 */
public class Util {

    /**
     * Get the current system version code.
     *
     * @return
     */
    public static int getSystemVersion() {
        int currSystemVersion = Integer.valueOf(Build.VERSION.SDK);
        return currSystemVersion;
    }
}

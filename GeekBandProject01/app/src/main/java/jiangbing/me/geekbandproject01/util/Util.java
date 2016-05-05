package jiangbing.me.geekbandproject01.util;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * 通用工具类.
 * @Author: jiangbing
 * @Date: 2016/5/4
 */
public class Util {

    /**
     * 获取 SharedPreferences.
     * @param ctx
     * @return
     */
    public static SharedPreferences getSharedPref(Context ctx) {
        return ctx.getSharedPreferences("APP_PREF", Context.MODE_PRIVATE);
    }

    /**
     * 获取 SharedPreferences 的 editor.
     * @param ctx
     * @return
     */
    public static SharedPreferences.Editor getSharedPrefEditor(Context ctx) {
        return getSharedPref(ctx).edit();
    }
}

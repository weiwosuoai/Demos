package jiangbing.me.geekbandproject01.util;

import android.util.Log;

/**
 * 日志工具类
 *
 * <p>对输出日志进行控制，项目上线后，需屏蔽所有日志.
 * <ul>
 *     <li>1.上线后的项目打印大量日志，影响app运行效率</li>
 *     <li>2.可能泄露机密信息</li>
 * </ul>
 * </p>
 *
 *
 * @Author: jiangbing
 * @Date: 2016/5/5
 */
public class LogUtil {

    /**
     * 日志输出开关，项目上线后，请设置为 false.
     */
    private static final boolean IS_OUTPUT_LOG = true;

    public static void v(String tag, String msg) {
        if (IS_OUTPUT_LOG) {
            Log.v(tag, msg);
        }
    }

    public static void d(String tag, String msg) {
        if (IS_OUTPUT_LOG) {
            Log.d(tag, msg);
        }
    }

    public static void i(String tag, String msg) {
        if (IS_OUTPUT_LOG) {
            Log.i(tag, msg);
        }
    }

    public static void w(String tag, String msg) {
        if (IS_OUTPUT_LOG) {
            Log.w(tag, msg);
        }
    }

    public static void e(String tag, String msg) {
        if (IS_OUTPUT_LOG) {
            Log.e(tag, msg);
        }
    }
}

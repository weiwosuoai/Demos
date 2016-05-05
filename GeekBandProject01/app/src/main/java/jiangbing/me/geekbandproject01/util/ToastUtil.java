package jiangbing.me.geekbandproject01.util;

import android.content.Context;
import android.widget.Toast;

/**
 * Toast 工具类
 * @Author: jiangbing
 * @Date: 2016/5/5
 */
public class ToastUtil {

    public static Toast sToast;

    /**
     * 防止连续弹 toast, 而是使用同一个 toast.
     * @param ctx
     * @param msg
     */
    public static void showToast(Context ctx, String msg) {
        if (sToast == null) {
            sToast = Toast.makeText(ctx, msg, Toast.LENGTH_SHORT);
        }
        sToast.setText(msg);
        sToast.show();
    }
}

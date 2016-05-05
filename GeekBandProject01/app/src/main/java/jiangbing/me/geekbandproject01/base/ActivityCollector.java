package jiangbing.me.geekbandproject01.base;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

/**
 * Activity 收集器，用来快速退出应用.
 * @Author: jiangbing
 * @Date: 2016/5/5
 */
public class ActivityCollector {

    public static List<Activity> activities = new ArrayList<Activity>();

    public static void addActivity(Activity activity) {
        activities.add(activity);
    }

    public static void removeActivity(Activity activity) {
        activities.remove(activity);
    }

    public static void finishAll() {
        for (Activity activity : activities) {
            if (!activity.isFinishing()) {
                activity.finish();
            }
        }
    }
}

package jiangbing.me.geekbandproject01.base;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import jiangbing.me.geekbandproject01.util.LogUtil;

/**
 * 基 Activity，后续创建的每个 activity 均需继承自此基 activity.
 * @Author: jiangbing
 * @Date: 2016/5/5
 */
public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // 打印当前所在的 activity 名称.
        LogUtil.d("-- Position --", getClass().getSimpleName().toString());
        ActivityCollector.addActivity(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityCollector.removeActivity(this);
    }
}

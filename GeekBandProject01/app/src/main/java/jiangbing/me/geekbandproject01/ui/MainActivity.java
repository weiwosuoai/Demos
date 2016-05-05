package jiangbing.me.geekbandproject01.ui;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.KeyEvent;

import jiangbing.me.geekbandproject01.R;
import jiangbing.me.geekbandproject01.base.ActivityCollector;
import jiangbing.me.geekbandproject01.base.BaseActivity;
import jiangbing.me.geekbandproject01.base.Constants;
import jiangbing.me.geekbandproject01.util.ToastUtil;
import jiangbing.me.geekbandproject01.util.Util;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        splashActivtiyOperation();
    }


    /**
     * 退出时间
     */
    private long mExitTime = 0;

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // 点击两次返回键退出应用.
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            long currentTime = System.currentTimeMillis();
            if (currentTime - mExitTime > 2000) {
                ToastUtil.showToast(this, "再按一次退出应用");
                mExitTime = currentTime;
            } else {
                ActivityCollector.finishAll();
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    /**
     * 关于引导页的相关操作
     */
    private void splashActivtiyOperation() {
        boolean isFromSplash = getIntent()
                .getBooleanExtra(SplashActivity.TO_MAIN_FROM_BANNER, false);
        if (isFromSplash) {
            SharedPreferences.Editor editor = Util.getSharedPrefEditor(this);
            editor.putBoolean(Constants.KEY_FIRST_STARTUP, false);
            editor.commit();
        }
    }
}

package jiangbing.me.toolbardemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

public class MainActivity extends AppCompatActivity {

    // Find the toolbar view inside the activity layout.
    Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        // Sets the Toolbar to act as the ActionBar for this Activity window.
        // Make sure the toolbar exists in the activity and is not null.
        setSupportActionBar(mToolbar);

        // In certain situations, we might want to display an app icon within the
        // <code>Toolbar</code>. This can be done by adding this code into the Activity.
//        getSupportActionBar().setDisplayShowHomeEnabled(true);
//        getSupportActionBar().setLogo(R.mipmap.ic_launcher);
//        getSupportActionBar().setDisplayUseLogoEnabled(true);

        // 对你已设置的 Title 和 Subtilte 设置是否可见.
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        // Remove default title text.

        // 设置返回箭头可见
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        mToolbar.setNavigationIcon(R.mipmap.ic_launcher);
//        mToolbar.setLogo(R.mipmap.ic_launcher);
//        mToolbar.setTitle("关于微信");
//        mToolbar.setSubtitle("子标题");

    }

    /**
     * Menu icons are inflated just as they were with actionbar.
     *
     * @param menu
     * @return
     */
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
////        getMenuInflater().inflate(R.menu.menu_main, menu);
//        return true;
//    }
}

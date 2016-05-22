package jiangbing.me.toolbardemo;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {


    // Find the toolbar view inside the activity layout.
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.lv)
    ListView mLv;

    @BindString(R.string.weixin_about_aty_item00_title)
    String mItem00Title;
    @BindString(R.string.weixin_about_aty_item01_title)
    String mItem01Title;
    @BindString(R.string.weixin_about_aty_item02_title)
    String mItem02Title;
    @BindString(R.string.weixin_about_aty_item03_title)
    String mItem03Title;
    @BindString(R.string.weixin_about_aty_item04_title)
    String mItem04Title;
    @BindString(R.string.weixin_about_aty_item05_title)
    String mItem05Title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
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

        operateWeixinAboutListView();
    }

    private void operateWeixinAboutListView() {
        String[] titles = new String[] {
                mItem00Title,
                mItem01Title,
                mItem02Title,
                mItem03Title,
                mItem04Title,
                mItem05Title};
        WeixinAboutAdapter adapter = new WeixinAboutAdapter(this, titles);
        mLv.setAdapter(adapter);

        // Header view.
        View headerView = LayoutInflater.from(this).inflate(R.layout.header_lv_weixin_about, null);
        mLv.addHeaderView(headerView, null, false);

        // Footer view.
        View footerView = LayoutInflater.from(this).inflate(R.layout.footer_lv_weixin_about, null);
        mLv.addFooterView(footerView, null, false);

        mLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String title = ((ViewHolder) view.getTag()).title.getText().toString();
                Toast.makeText(MainActivity.this,
                        "你点击了第 " + position  + " 个 Item: " + title,
                        Toast.LENGTH_SHORT)
                        .show();
            }
        });

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

    /**
     * Created by Allen on 2016/5/21.
     */
    public class WeixinAboutAdapter extends BaseAdapter {

        private Context mContext;
        private String[] mStrArr;
        private LayoutInflater mInflater;

        public WeixinAboutAdapter(Context context, String[] strArr) {
            this.mContext = context;
            this.mStrArr = strArr;
            this.mInflater = LayoutInflater.from(this.mContext);
        }

        @Override
        public int getCount() {
            return mStrArr.length;
        }

        @Override
        public Object getItem(int position) {
            return mStrArr[position];
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder viewHolder;
            // 判断是否存在缓存
            if (convertView == null) {
                convertView = this.mInflater.inflate(R.layout.item_lv_weixn_about, null);

                viewHolder = new ViewHolder(convertView);
                convertView.setTag(viewHolder);
            } else {
                // 若有，取出缓存中存放的 ViewHolder,避免再次 findViewById().
                viewHolder = (ViewHolder) convertView.getTag();
            }

            // 更新视图
            viewHolder.title.setText(mStrArr[position]);

            return convertView;
        }


    }

    static class ViewHolder {

        @BindView(R.id.tv_item_title)
        TextView title; // The title of item.

        public ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}

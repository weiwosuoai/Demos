package jiangbing.me.geekbandproject01.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import jiangbing.me.geekbandproject01.R;
import jiangbing.me.geekbandproject01.adapter.ViewPagerAdapter;
import jiangbing.me.geekbandproject01.base.BaseActivity;
import jiangbing.me.geekbandproject01.base.Constants;
import jiangbing.me.geekbandproject01.util.Util;


public class SplashActivity extends BaseActivity implements ViewPager.OnPageChangeListener,
        View.OnClickListener {

    private static final String TAG = SplashActivity.class.getSimpleName();
    // The viewPager.
    private ViewPager mViewPager;

    // The adapter of the viewPager.
    private ViewPagerAdapter mViewPagerAdapter;

    // We will put all the view on the arr.
    private ArrayList<View> mViews;

    // All the splash pics.
    private static final int[] mPics = {
            R.drawable.guide_first,
            R.drawable.guide_second,
            R.drawable.guide_third};

    // All the dot image.
    private ArrayList<ImageView> mDots;

    // Current position which was seleted.
    private int mCurrentIndex;

    private LinearLayout mDotLl;
    private ImageView mIvFirst;
    private TextView mBtnBanner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        // 判断是否第一次启动，若是，打开引导页，若不是，直接跳转 MainActivity.
        beforeStartupSplashOperation();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        initView();
        initData();
    }

    private void initView() {
        mViews = new ArrayList<View>();
        mViewPager = (ViewPager) findViewById(R.id.vp);
        mViewPagerAdapter = new ViewPagerAdapter(mViews);
        mDotLl = (LinearLayout) findViewById(R.id.ll_dot);
        mIvFirst = (ImageView) findViewById(R.id.iv_first);
        mBtnBanner = (TextView) findViewById(R.id.btn_banner);
        mBtnBanner.setOnClickListener(this);
    }

    private void initData() {
        // Step1: init the viewPager.
        initViewPager();

        // Step2: init the dots.
        initDot();
    }

    private void initViewPager() {
        // Initialize a layout params, it was used for setting up the imageView.
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT
        );

        // Initialize the pictures of the splash activity.
        for (int i = 0; i < mPics.length; i++) {
            ImageView imageView = new ImageView(this);
            imageView.setLayoutParams(params);
            // Make true to fill the entire layout.
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            imageView.setImageResource(mPics[i]);
            mViews.add(imageView);
        }

        // Set up the adapter of viewPager.
        mViewPager.setAdapter(mViewPagerAdapter);
        // Set up the listener of viewPager.
        mViewPager.setOnPageChangeListener(this);
    }

    private void initDot() {
        mDots = new ArrayList<ImageView>();
        for (int i = 0; i < mPics.length; i++) {
            ImageView imageView = (ImageView) mDotLl.getChildAt(i);
            mDots.add(imageView);
        }

        // By Default, the first dot was selected.
        mCurrentIndex = 0;
        mIvFirst.setSelected(true);
    }

    /**
     * Display the hide button if the current index is <code>mPics</code>'s length.
     */
    private void displayToMainButton() {
        if (mCurrentIndex == (mPics.length- 1)) {
            mBtnBanner.setVisibility(View.VISIBLE);
        } else { // if not, hide it.
            mBtnBanner.setVisibility(View.GONE);
        }
    }


    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {}

    @Override
    public void onPageSelected(int position) {
        Log.d(TAG, "onPageSelected: " + position);

        // Update the current index and the state of dot.
        mCurrentIndex = position;
        for (int i = 0; i < mDots.size(); i++) {
            if (position == i) {
                mDots.get(i).setSelected(true);
            } else {
                mDots.get(i).setSelected(false);
            }
        }

        displayToMainButton();
    }

    @Override
    public void onPageScrollStateChanged(int state) {}

    public static final String TO_MAIN_FROM_BANNER = "toMainFromBanner";

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case (R.id.btn_banner):
                // Go to the main activity.
                Intent intent = new Intent(this, MainActivity.class);
                intent.putExtra(TO_MAIN_FROM_BANNER, true);
                startActivity(intent);
                break;
        }
    }

    /**
     * 启动引导页之前相关操作，对用户是否首次启动做判断，若是启动引导页，否则，跳 MainActivity.
     */
    private void beforeStartupSplashOperation() {
        boolean isFirstStartup = Util.getSharedPref(this)
                .getBoolean(Constants.KEY_FIRST_STARTUP, true);
        if (!isFirstStartup) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
    }

}

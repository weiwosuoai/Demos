package me.jiangbing.geekbandproject03customview;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = MainActivity.class.getSimpleName();

    private static final int UPDATE_TIMER_VIEW = 0;

    private TimerView mTimerView;
    private ListView mListView;
    private Button mResetBtn, mStartAndPauseBtn;

    private List<Timer> mTimerList;

    private boolean mNeedRunning = true;

    private Handler sHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {

                case UPDATE_TIMER_VIEW: // 重绘 timer
                    Timer timer = (Timer) msg.obj;
                    mTimerView.setMillisecond(Integer.valueOf(timer.getMillisecond()));
                    mTimerView.setSecond(Integer.valueOf(timer.getSecond()));
                    mTimerView.invalidate();
                    break;

            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initData();
        initView();
    }

    private void initData() {
        mTimerList = new ArrayList<Timer>();
//        Timer timer = new Timer();
//        timer.setStartTime("1 14.45");
//        timer.setEndTime("2 23.45");
//        mTimerList.add(timer);

    }

    private void initView() {
        mTimerView = (TimerView) findViewById(R.id.timerview);
        mListView = (ListView) findViewById(R.id.listview);
        mListView.setAdapter(new TimerAdaper(this, mTimerList));
        mResetBtn = (Button) findViewById(R.id.btn_reset);
        mResetBtn.setOnClickListener(this);
        mStartAndPauseBtn = (Button) findViewById(R.id.btn_start_pause);
        mStartAndPauseBtn.setOnClickListener(this);
    }


    private long mStartTime;
    private long mCurTime;

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_reset: // 重置

                break;
            case R.id.btn_start_pause: // 开始计时和暂停
                Button btn = (Button) v;
                String tag = (String) btn.getTag();
                if ("0".equals(tag)) { // 开始


                    mNeedRunning = true;
                    btn.setText("暂停");
                    btn.setTag("1");

                    // 开始计时的时间
                    mStartTime = System.currentTimeMillis();
                    final Timer timer = new Timer();
                    // 启动子线程去计时
                    new Thread(new Runnable() {

                        @Override
                        public void run() {
                            while (mNeedRunning) {
                                mCurTime = System.currentTimeMillis();

                                // mHandler 发送消息更新 Timer 控件
                                long time = mCurTime - mStartTime;

                                long min = time / (60 * 1000); // / : 取整 % ：取整
                                long second = time % 1000;
                                long millisecond = time % 100;
                                Log.d(TAG, "run: second - : " + second + " | millsecond - " + millisecond);

                                Message msg = Message.obtain();
                                msg.what = UPDATE_TIMER_VIEW;
                                timer.setMillisecond(String.valueOf(millisecond));
                                timer.setSecond(String.valueOf(second));
                                msg.obj = timer;
                                sHandler.sendMessage(msg);

                            }

                        }
                    }).start();


                } else { // 暂停

                    mNeedRunning = false;

                    btn.setText("开始");
                    btn.setTag("0");
                }
                break;
        }
    }

    private class Timer {
        private String startTime; // 开始时间
        private String endTime; // 结束时间
        private String millisecond; // 百分制毫秒
        private String second;

        public String getSecond() {
            return second;
        }

        public void setSecond(String second) {
            this.second = second;
        }

        public String getMillisecond() {
            return millisecond;
        }

        public void setMillisecond(String millisecond) {
            this.millisecond = millisecond;
        }

        public String getStartTime() {
            return startTime;
        }

        public void setStartTime(String startTime) {
            this.startTime = startTime;
        }

        public String getEndTime() {
            return endTime;
        }

        public void setEndTime(String endTime) {
            this.endTime = endTime;
        }
    }

    private class TimerAdaper extends BaseAdapter {

        private Context mContext;
        private List<Timer> mTimerList;
        private LayoutInflater mInflater;

        public TimerAdaper(Context context, List<Timer> timerList) {
            this.mContext = context;
            this.mTimerList = timerList;
            this.mInflater = LayoutInflater.from(this.mContext);
        }

        @Override
        public int getCount() {
            return this.mTimerList.size();
        }

        @Override
        public Object getItem(int position) {
            return this.mTimerList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view = this.mInflater.inflate(R.layout.item_timer_listview, null);
            TextView numTv = (TextView) view.findViewById(R.id.item_num);
            TextView startTimeTv = (TextView) view.findViewById(R.id.item_start_time);
            TextView endTimeTv = (TextView) view.findViewById(R.id.item_start_time);

            numTv.setText(String.valueOf(position + 1));
            startTimeTv.setText(this.mTimerList.get(position).getStartTime());
            endTimeTv.setText(this.mTimerList.get(position).getEndTime());
            return view;
        }
    }
}

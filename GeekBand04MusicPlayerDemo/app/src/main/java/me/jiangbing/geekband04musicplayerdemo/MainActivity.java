package me.jiangbing.geekband04musicplayerdemo;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.renderscript.Sampler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.lang.ref.WeakReference;


public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

//    private Button mBtnPlay, mBtnStop, mBtnPlayPre, mBtnPlayNext;
//    private TextView mTvMusicTitle;

    /**
     * Instances of static inner classes do not hold an implicit
     * reference to their outer class.
     */
    private static class MyHandler extends Handler {
        private final WeakReference<MainActivity> mActivity;

        public MyHandler(MainActivity activity) {
            mActivity = new WeakReference<MainActivity>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            MainActivity activity = mActivity.get();
            if (activity != null) {
                // ...
            }
        }
    }

    private final MyHandler mMyHandler = new MyHandler(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        mBtnPlay = (Button) findViewById(R.id.btn_play);
//        mBtnStop = (Button) findViewById(R.id.btn_stop);
//        mBtnPlayPre = (Button) findViewById(R.id.btn_play_pre);
//        mBtnPlayNext = (Button) findViewById(R.id.btn_play_next);
//        mTvMusicTitle = (TextView) findViewById(R.id.tv_music_title);
//
//        mBtnPlay.setOnClickListener(this);
//        mBtnStop.setOnClickListener(this);
//        mBtnPlayPre.setOnClickListener(this);
//        mBtnPlayNext.setOnClickListener(this);

    }

//    private boolean isFirstTimeClickPlayBtn = true;
//
//    @Override
//    public void onClick(View v) {
//        switch (v.getId()) {
//
//            case R.id.btn_play:
//                Button btn = (Button) v;
//                String tag = (String) btn.getTag();
//                if ("0".equals(tag)) { // 暂停
//                    Log.d(TAG, "onClick: Clicked the play button.");
//                    btn.setTag("1");
//                    btn.setText("暂停");
//
//                    // 发送播放音乐/暂停播放音乐的广播
//                    if (isFirstTimeClickPlayBtn) {
//                        startService(new Intent(this, MusicPlayService.class));
//                        isFirstTimeClickPlayBtn = false;
//                    } else {
//                        Intent intent = new Intent();
//                        intent.setAction(MusicPlayService.ACTION_PLAY);
//                        sendBroadcast(intent);
//                    }
//                } else if ("1".equals(tag)) {
//                    Log.d(TAG, "onClick: Clicked the pause.");
//                    btn.setTag("0");
//                    btn.setText("播放");
//
//                    // 发送暂停音乐的广播
//                    Intent intent = new Intent();
//                    intent.setAction(MusicPlayService.ACTION_PAUSE);
//                    sendBroadcast(intent);
//                }
//                break;
//
//            case R.id.btn_play_pre:
//                // 发送播放下一首的广播
//                Intent intentPre = new Intent();
//                intentPre.setAction(MusicPlayService.ACTION_PRE);
//                sendBroadcast(intentPre);
//                break;
//
//            case R.id.btn_play_next:
//                // 发送播放下一首的广播
//                Intent intentNext = new Intent();
//                intentNext.setAction(MusicPlayService.ACTION_NEXT);
//                sendBroadcast(intentNext);
//                break;
//
//            case R.id.btn_stop:
//                Log.d(TAG, "onClick: Begin to stop the service.");
//                stopService(new Intent(this, MusicPlayService.class));
//                mBtnPlay.setText("播放");
//                break;
//        }
//    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}

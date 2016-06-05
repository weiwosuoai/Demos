package me.jiangbing.geekband04musicplayerdemo;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class MusicPlayService extends Service {
    private static List<Music> sMusicList;
    static {
        sMusicList = new ArrayList<>();
        Music music1 = new Music();
        music1.setTitle("Sober");
        music1.setSingerName("BIGBANG");
        sMusicList.add(music1);

        Music music = new Music();
        music.setTitle("If you");
        music.setSingerName("BIGBANG");
        sMusicList.add(music);

        Music music2 = new Music();
        music2.setTitle("暗河");
        music2.setSingerName("冯佳界");
        sMusicList.add(music2);

        Music music3 = new Music();
        music3.setTitle("牧马人");
        music3.setSingerName("冯佳界");
        sMusicList.add(music3);
    }

    public static final String ACTION_PLAY = "me.jiangbing.geekband04musicplayerdemo.play";
    public static final String ACTION_PAUSE = "me.jiangbing.geekband04musicplayerdemo.pause";
    public static final String ACTION_PRE = "me.jiangbing.geekband04musicplayerdemo.pre";
    public static final String ACTION_NEXT = "me.jiangbing.geekband04musicplayerdemo.next";
    public static final String ACTION_GET_MUSIC = "me.jiangbing.geekband04musicplayerdemo.GET_MUSIC";

    private static final String TAG = MusicPlayService.class.getSimpleName();

    private static MediaPlayer mMediaPlayer;

    /**
     * 用来存放所有歌曲
     */
    private static List<Integer> sResList;
    /**
     * 歌曲总数
     */
    private static int mMusicsNum = 0;
    /**
     * 记录当前正在播放的歌曲的坐标
     */
    private static int mCurPosition = 0;
    /**
     * 音乐是否循环
     */
    private boolean isLooping = false;

    public MusicPlayService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate: create the service.");

        // Set up the all musics.
        sResList = new ArrayList<>();
//        String musicPath = "file:///android_raw/";
//        Log.d(TAG, "onCreate: musicPath - " + musicPath);
        sResList.add(Integer.valueOf(R.raw.sober));
        sResList.add(Integer.valueOf(R.raw.ifyou));
        sResList.add(Integer.valueOf(R.raw.river));
        sResList.add(Integer.valueOf(R.raw.wrangler));

        // Default is playing the first music.
        if (sResList != null && sResList.size() > 0) {
            int position = 0;
            mMediaPlayer = MediaPlayer.create(this, sResList.get(position));
            // 设置循环播放
//            mMediaPlayer.setLooping(true);
            mMusicsNum = sResList.size();
            mCurPosition = position;
        }
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "onStartCommand : start the service.");
        mMediaPlayer.start();
        return super.onStartCommand(intent, flags, startId);

    }

    @Override
    public void onDestroy() {
        mMediaPlayer.stop();
        super.onDestroy();
        Log.i(TAG, "onDestroy : destory the service.");
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    /**
     * 是否是第一次播放音乐
     */
    private static boolean isFirstTimePlaying = true;

    /**
     * 播放音乐的操作接受者，用于接收 暂停，继续播放 etc. 的相关操作
     */
        public static class MusicOperationReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (TextUtils.equals(ACTION_PLAY, action) && isFirstTimePlaying) { // Start the play music service.
                context.startService(new Intent(context, MusicPlayService.class));
                isFirstTimePlaying = false;

                sendUpdateBtnToPauseBroadcase(context);

            } else if (TextUtils.equals(ACTION_PLAY, action) && !isFirstTimePlaying) {
                mMediaPlayer.start();
                sendUpdateBtnToPauseBroadcase(context);
            } else if (TextUtils.equals(ACTION_PAUSE, action)) { // 暂停
                mMediaPlayer.pause();
                sendUpdateBtnToPlayBroadcase(context);
            } else if (TextUtils.equals(ACTION_PRE, action)) { // 上一首
                sendUpdateBtnToPauseBroadcase(context);
                int prePosition = mCurPosition - 1;
                mMediaPlayer.reset();
                if (prePosition < 0) { // 如果当前播放的是第一首歌，点击上一首则跳转最后一首歌曲
                    mMediaPlayer = MediaPlayer.create(context, sResList.get(mMusicsNum - 1));
                    mCurPosition = mMusicsNum - 1;
                } else {
                    mMediaPlayer = MediaPlayer.create(context, sResList.get(prePosition));
                    mCurPosition = prePosition;
                }
                mMediaPlayer.start();

                Music music = MusicPlayService.sMusicList.get(mCurPosition);
                sendUpdateMusicTitleBroadcase(context, music);
            } else if (TextUtils.equals(ACTION_NEXT, action)) { // 下一首
                sendUpdateBtnToPauseBroadcase(context);
                int nextPosition = mCurPosition + 1;
                mMediaPlayer.reset();
                if (nextPosition >= mMusicsNum) { // 若当前播放的是最后一首，则跳转到第一首播放
                    mMediaPlayer = MediaPlayer.create(context, sResList.get(0));
                    mCurPosition = 0;
                } else {
                    mMediaPlayer = MediaPlayer.create(context, sResList.get(nextPosition));
                    mCurPosition = nextPosition;
                }
                mMediaPlayer.start();

                Music music = MusicPlayService.sMusicList.get(mCurPosition);
                sendUpdateMusicTitleBroadcase(context, music);
            } else if (TextUtils.equals(ACTION_GET_MUSIC, action)) {
                int pos = intent.getIntExtra("pos", mCurPosition);
                Music music = MusicPlayService.sMusicList.get(pos);

                sendUpdateMusicTitleBroadcase(context, music);

            }
        }
    }

    private static void sendUpdateBtnToPauseBroadcase(Context context) {
        Intent updatePlayBtnIntent = new Intent();
        updatePlayBtnIntent.setAction(MusicWidget.ACTION_UPDATE_BTN_TO_PAUSE);
        context.sendBroadcast(updatePlayBtnIntent);
    }

    private static void sendUpdateBtnToPlayBroadcase(Context context) {
        Intent updatePlayBtnIntent = new Intent();
        updatePlayBtnIntent.setAction(MusicWidget.ACTION_UPDATE_BTN_TO_PLAY);
        context.sendBroadcast(updatePlayBtnIntent);
    }

    private static void sendUpdateMusicTitleBroadcase(Context context, Music music) {
        Intent musicIntent = new Intent();
        Bundle bundle = new Bundle();
        bundle.putSerializable("music", music);
        musicIntent.putExtras(bundle);
        musicIntent.setAction(MusicWidget.ACTION_MUSIC_INFO);
        context.sendBroadcast(musicIntent);
    }
}

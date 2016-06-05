package me.jiangbing.geekband04musicplayerdemo;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.RemoteViews;

/**
 * Function: The widget of playing music.
 *
 * @Author: Allen
 * @Date: 2016/6/3 14:15
 * @Version: 1.0.0
 */
public class MusicWidget extends AppWidgetProvider {
    private static final String TAG = MusicWidget.class.getSimpleName();

    public static final String ACTION_UPDATE_BTN_TO_PAUSE
            = "me.jiangbing.geekband04musicplayerdemo.action.UPDATE_BTN_TO_PAUSE";
    public static final String ACTION_UPDATE_BTN_TO_PLAY
            = "me.jiangbing.geekband04musicplayerdemo.action.UPDATE_BTN_TO_PLAY";
    public static final String ACTION_MUSIC_INFO
            = "me.jiangbing.geekband04musicplayerdemo.action.MUSIC_INFO";

    /**
     * The method will be invoked when user move the widget on home screen.
     * @param context
     */
    @Override
    public void onEnabled(Context context) {
        super.onEnabled(context);
        Log.d(TAG, "onEnabled: invoked");

        // Send the broadcastReceiver to get the first music info.
        Intent getMusicInent = new Intent();
        getMusicInent.setAction(MusicPlayService.ACTION_GET_MUSIC);
        getMusicInent.putExtra("pos", 0);
        context.sendBroadcast(getMusicInent);

        RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.layout_widget);

        // Set up the listener of the play button.
        Intent playMusicIntent = new Intent();
        playMusicIntent.setAction(MusicPlayService.ACTION_PLAY);
        PendingIntent pendingIntentPlay = PendingIntent.getBroadcast(context, 0, playMusicIntent, 0);
        remoteViews.setOnClickPendingIntent(R.id.btn_play, pendingIntentPlay);

        Intent pauseMusicIntent = new Intent();
        pauseMusicIntent.setAction(MusicPlayService.ACTION_PAUSE);
        PendingIntent pendingIntentPause = PendingIntent.getBroadcast(context, 0, pauseMusicIntent, 0);
        remoteViews.setOnClickPendingIntent(R.id.btn_pause, pendingIntentPause);

        Intent preMusicIntent = new Intent();
        preMusicIntent.setAction(MusicPlayService.ACTION_PRE);
        PendingIntent pendingIntentPre = PendingIntent.getBroadcast(context, 0, preMusicIntent, 0);
        remoteViews.setOnClickPendingIntent(R.id.btn_play_pre, pendingIntentPre);

        Intent nextMusicIntent = new Intent();
        nextMusicIntent.setAction(MusicPlayService.ACTION_NEXT);
        PendingIntent pendingIntentNext = PendingIntent.getBroadcast(context, 0, nextMusicIntent, 0);
        remoteViews.setOnClickPendingIntent(R.id.btn_play_next, pendingIntentNext);

        // The AppWidgetManager class used to manager the widget.
        updateAppWidget(context, remoteViews);
    }

    /**
     * The method will be invoked user remove the per widget.
     * @param context
     * @param appWidgetIds
     */
    @Override
    public void onDeleted(Context context, int[] appWidgetIds) {
        Log.d(TAG, "onDeleted: invoked");
        context.stopService(new Intent(context, MusicPlayService.class));
        super.onDeleted(context, appWidgetIds);
    }

    /**
     * The method will be invoked when user remove the last widget.
     * @param context
     */
    @Override
    public void onDisabled(Context context) {
        super.onDisabled(context);
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        super.onReceive(context, intent);
        Log.i(TAG, "onReceive");
        if (intent != null && TextUtils.equals(intent.getAction(), ACTION_UPDATE_BTN_TO_PAUSE)) {
            RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.layout_widget);
            remoteViews.setViewVisibility(R.id.btn_play, View.GONE);
            remoteViews.setViewVisibility(R.id.btn_pause, View.VISIBLE);

            // The AppWidgetManager class used to manager the widget.
            updateAppWidget(context, remoteViews);
        } else if (intent != null && TextUtils.equals(intent.getAction(), ACTION_UPDATE_BTN_TO_PLAY)) {
            RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.layout_widget);
            remoteViews.setViewVisibility(R.id.btn_pause, View.GONE);
            remoteViews.setViewVisibility(R.id.btn_play, View.VISIBLE);

            // The AppWidgetManager class used to manager the widget.
            updateAppWidget(context, remoteViews);
        } else if (intent != null && TextUtils.equals(intent.getAction(), ACTION_MUSIC_INFO)) {
            Bundle bundle = intent.getExtras();
            Music music = (Music) bundle.get("music");

            RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.layout_widget);
            String content = "《" + music.getTitle() + "》" + " - " + music.getSingerName();
            Log.d(TAG, "onReceive: - content " + content);
            remoteViews.setTextViewText(R.id.tv_music_title, content);

            // The AppWidgetManager class used to manager the widget.
            updateAppWidget(context, remoteViews);
        }
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        super.onUpdate(context, appWidgetManager, appWidgetIds);
    }

    private void updateAppWidget(Context context, RemoteViews remoteViews) {
        // The AppWidgetManager class used to manager the widget.
        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);
        ComponentName componentName = new ComponentName(context, MusicWidget.class);
        appWidgetManager.updateAppWidget(componentName, remoteViews);
    }
}

package com.example.apptest;

import java.util.Timer;
import java.util.TimerTask;

import android.app.ActivityManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.widget.Toast;

/**
 * 重启app服务
 * @author jiangbing
 *
 */
public class RestartAppService extends Service {

	private static final long RESTART_DELAY = 30 * 1000; // 多少时间后重启
	private MyBinder mBinder;
	
	// 此对象用于绑定的service与调用者之间的通信
	public class MyBinder extends Binder {
		
		/**
		 * 获取service实例
		 * @return
		 */
		public RestartAppService getService() {
			return RestartAppService.this;
		}
		
		/**
		 * 启动app重启任务
		 */
		public void startRestartTask(final Context context) {
			Toast.makeText(context, "start", Toast.LENGTH_SHORT).show();
			
			TimerTask task = new TimerTask() {

				@Override
				public void run() {
					// restart
					Intent intent = getPackageManager().getLaunchIntentForPackage(
							getApplication().getPackageName());
					intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
					startActivity(intent);
					System.exit(0);
				}
			};
			
			Timer timer = new Timer();
//			long delay = 0;
//			long intervalPeriod = 1 * 1000;
			timer.schedule(task, RESTART_DELAY);
		}
	}
	
	@Override
	public IBinder onBind(Intent intent) {
		// Create MyBinder object
		if (mBinder == null) {
			mBinder = new MyBinder();
		}
		return mBinder;
	}
	
	@Override
	public void onDestroy() {
		super.onDestroy();
	}
	
}

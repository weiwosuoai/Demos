package com.example.apptest;

import android.app.Application;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.widget.Toast;

public class App extends Application {

	private ServiceConnection conn = new ServiceConnection() {

		/**
		 * Called when a connection to the Service has been established, 
		 * with the android.os.IBinder of the communication channel to the Service.
		 */
		@Override
		public void onServiceConnected(ComponentName name, IBinder service) {
			RestartAppService.MyBinder mBinder = (RestartAppService.MyBinder) service;
			mBinder.startRestartTask(App.this);
		}

		@Override
		public void onServiceDisconnected(ComponentName name) {}
		
	};
	
	@Override
	public void onCreate() {
		super.onCreate();
		Toast.makeText(this, "app启动了", Toast.LENGTH_LONG).show();
		// 启动服务，用于定时重启app
		Intent intent = new Intent(this, RestartAppService.class);
		bindService(intent, conn, Context.BIND_AUTO_CREATE);
	}
}

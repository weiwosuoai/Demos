package com.example.apptest;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * �����㲥������
 * @author jiangbing
 *
 */
public class BootBroadcastReceiver extends BroadcastReceiver {
	
	private static final String ACTION = "android.intent.action.BOOT_COMPLETED";

	@Override
	public void onReceive(Context context, Intent intent) {
		if (ACTION.equals(intent.getAction())) {
			// ������������Ӧ��
			Intent mIntent = new Intent(context, MainActivity.class);
			mIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			context.startActivity(mIntent);
		}
	}

}

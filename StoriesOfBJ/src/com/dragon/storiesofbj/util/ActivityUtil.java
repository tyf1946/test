package com.dragon.storiesofbj.util;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;

public class ActivityUtil {
	
	public static void showActExitDialog(final Activity activity){
		AlertDialog.Builder dialog = new AlertDialog.Builder(
				activity);
		dialog.setMessage("真的要退出吗？");
		dialog.setPositiveButton("退出",
				new AlertDialog.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog,
							int which) {
						activity.finish();
					}
				});
		dialog.setNegativeButton("取消",
				new AlertDialog.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog,
							int which) {
						dialog.cancel();
					}
				});
		dialog.create().show();
	}
	
}

package com.dragon.storiesofbj.util;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class SpUtils {
	
	@SuppressWarnings("deprecation")
	private static int mode = Context.MODE_WORLD_WRITEABLE;

	// 将参数保存到SharedPreferences中
	public static void putString(Context context, String name, String key,
			String value) {
		SharedPreferences pref = context.getSharedPreferences(name,
				mode);
		Editor edit = pref.edit();
		edit.putString(key, value);
		edit.commit();
	}

	// 获取SharedPreferences中保存的参数
	public static String getString(Context context, String name, String key) {
		SharedPreferences pref = context.getSharedPreferences(name,
				mode);
		return pref.getString(key, "");
	}

	public static void putBoolean(Context context, String name, String key,
			boolean value) {
		SharedPreferences pref = context.getSharedPreferences(name,
				mode);
		Editor edit = pref.edit();
		edit.putBoolean(key, value);
		edit.commit();
	}

	public static boolean getBoolean(Context context, String name, String key) {
		SharedPreferences pref = context.getSharedPreferences(name,
				mode);
		return pref.getBoolean(key, false);
	}

	@SuppressLint("CommitPrefEdits")
	public static void clear(Context context, String name) {
		SharedPreferences pref = context.getSharedPreferences(name,
				mode);
		Editor edit = pref.edit();
		edit.clear();
	}
}

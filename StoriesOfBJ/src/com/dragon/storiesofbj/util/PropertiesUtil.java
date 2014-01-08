package com.dragon.storiesofbj.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Enumeration;
import java.util.Properties;

import android.content.Context;

/**
 * 读取配置文件的工具类
 * 
 * @author Dragon
 *
 */
public class PropertiesUtil {
	
	private static Properties prop = null;
	
	/**
	 * 加载assets下voole.properties文件
	 * 
	 * @param context
	 */
	private static void propertiesInstance(Context context) {
		if (prop == null) {
			InputStream in = null;
			try {
				in = context.getAssets().open("story.properties");
				prop = new Properties();
				prop.load(in);
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				if (in != null) {
					try {
						in.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}
	
	/**
	 * 获取assets下voole.properties文件中对应名称的值
	 * 
	 * @param context
	 * @param name
	 * @return
	 */
	public static String getProperty(Context context, String name) {
		propertiesInstance(context);
		return prop.getProperty(name);
	}

	/**
	 * 增加键值对
	 * 
	 * @param context
	 * @param key
	 * @param value
	 */
	public static void setProperty(Context context, String key, String value) {
		propertiesInstance(context);
		try {
			OutputStream out = context.openFileOutput("story.propertie",
					Context.MODE_PRIVATE);
			Enumeration<?> e = prop.propertyNames();
			if (e.hasMoreElements()) {
				while (e.hasMoreElements()) {
					String s = (String) e.nextElement();
					if (!s.equals(key)) {
						prop.setProperty(s, prop.getProperty(s));
					}
				}
			}
			prop.setProperty(key, value);
			prop.store(out, null);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}

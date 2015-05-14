package com.broadking.v3.util;

import android.util.Log;

import com.broadking.v3.AppConfig;

public class LogUtil {

	/** 正式打包时debug为false */
	private static final boolean DEBUG = AppConfig.LOG_DEBUG;
	private static final String TAG = "Broadking_V3";

	public static void i(String msg) {
		if (DEBUG) {
			Log.i(TAG, msg);
		}
	}

	public static void d(String msg) {
		if (DEBUG) {
			Log.d(TAG, msg);
		}
	}

	public static void e(String msg) {
		Log.e(TAG, msg);
	}

}

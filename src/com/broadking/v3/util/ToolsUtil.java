package com.broadking.v3.util;

import android.content.Context;
import android.util.DisplayMetrics;

public class ToolsUtil {

	/**
	 * 屏幕宽度（像素）
	 * 
	 * @param contex
	 * @return
	 */
	public static int getScreenWidth(Context contex) {
		DisplayMetrics dm = contex.getResources().getDisplayMetrics();
		return dm.widthPixels;
	}

	/**
	 * 屏幕高度（像素）
	 * 
	 * @param context
	 * @return
	 */
	public static int getScreenHeight(Context context) {
		DisplayMetrics dm = context.getResources().getDisplayMetrics();
		return dm.heightPixels;
	}
	/**
	 * 获取屏幕分辨率
	 */
	public static void getDisplayMetrics(Context context) {
		DisplayMetrics dm = new DisplayMetrics();
		dm = context.getResources().getDisplayMetrics();
		int width = dm.widthPixels; // 屏幕宽度（像素）
		int height = dm.heightPixels; // 屏幕高度（像素）
		float density = dm.density; // 屏幕密度（0.75 / 1.0 / 1.5 / 2.0）
		int densityDpi = dm.densityDpi; // 屏幕密度DPI（120 / 160 / 240 / 320）
		LogUtil.i("/------------------/");
		LogUtil.i("屏幕分辨率：" + width + " X " + height);
		LogUtil.i("屏幕密度：" + density);
		LogUtil.i("屏幕密度DPI：" + densityDpi);
		LogUtil.i("/------------------/");
	}

}

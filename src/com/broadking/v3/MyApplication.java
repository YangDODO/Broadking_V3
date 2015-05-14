package com.broadking.v3;

import android.app.Application;

import com.broadking.v3.util.ToolsUtil;

public class MyApplication extends Application {

	@Override
	public void onCreate() {
		super.onCreate();
		ToolsUtil.getDisplayMetrics(getApplicationContext());
	}

}

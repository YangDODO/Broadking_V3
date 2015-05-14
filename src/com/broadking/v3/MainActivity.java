package com.broadking.v3;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTabHost;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TabHost.TabSpec;

import com.broadking.v3.fragment.ContactFragment;
import com.broadking.v3.fragment.CustomerFragment;
import com.broadking.v3.fragment.FindFragment;
import com.broadking.v3.fragment.MerchantFragment;
import com.broadking.v3.fragment.MyFragment;

/**
 * 主页Activity
 */
public class MainActivity extends FragmentActivity {

	// 定义FragmentTabHost对象
	public FragmentTabHost mTabHost;
	public RadioGroup mTabRg;

	private int ID = 0;// 0为博金客，1为博金商
	private RadioButton customerRb;// 博金客
	private RadioButton merchantRb;// 博金商

	private final Class<?>[] fragments = { CustomerFragment.class,
			MerchantFragment.class, ContactFragment.class, FindFragment.class,
			MyFragment.class };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);
		initView();
	}

	private void initView() {
		mTabHost = (FragmentTabHost) findViewById(android.R.id.tabhost);
		customerRb = (RadioButton) findViewById(R.id.tab_rb_customer);
		merchantRb = (RadioButton) findViewById(R.id.tab_rb_merchant);
		mTabHost.setup(this, getSupportFragmentManager(), R.id.realtabcontent);
		// 得到fragment的个数
		int count = fragments.length;
		for (int i = 0; i < count; i++) {
			// 为每一个Tab按钮设置图标、文字和内容
			TabSpec tabSpec = mTabHost.newTabSpec(i + "").setIndicator(i + "");
			// 将Tab按钮添加进Tab选项卡中
			mTabHost.addTab(tabSpec, fragments[i], null);
		}

		if (ID == 1) {
			mTabHost.setCurrentTab(1);
			customerRb.setVisibility(View.GONE);
			merchantRb.setVisibility(View.VISIBLE);
			merchantRb.setChecked(true);
		} else {
			mTabHost.setCurrentTab(0);
			customerRb.setVisibility(View.VISIBLE);
			merchantRb.setVisibility(View.GONE);
			customerRb.setChecked(true);
		}

		mTabRg = (RadioGroup) findViewById(R.id.tab_rg_menu);
		mTabRg.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				switch (checkedId) {
				case R.id.tab_rb_customer:
					mTabHost.setCurrentTab(0);
					break;
				case R.id.tab_rb_merchant:
					mTabHost.setCurrentTab(1);
					break;
				case R.id.tab_rb_contact:
					mTabHost.setCurrentTab(2);
					break;
				case R.id.tab_rb_find:
					mTabHost.setCurrentTab(3);
					break;
				case R.id.tab_rb_my:
					mTabHost.setCurrentTab(4);

				default:
					break;
				}
			}
		});
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			moveTaskToBack(false);
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}
}
package com.broadking.v3.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.broadking.v3.R;
public abstract class BaseFragment extends Fragment {

	private View mView;
	Context mContext;
	LayoutInflater mInflater;
	/* 标题文字 */
	TextView titleTx;
	/* 标题右边按钮 */
	Button rightBt;
	/* 内容view */
	RelativeLayout contentLayout;
	/* 进度条 */
	ProgressBar mBar;

	View mContentView;
	public static final int SUCCESS = 1000;
	public static final int FAILURE = 1001;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mContext = getActivity();
		mInflater = LayoutInflater.from(mContext);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		mView = inflater.inflate(R.layout.baselayout, null);
		initView();
		return mView;
	}

	private void findBaseView() {
		titleTx = (TextView) mView.findViewById(R.id.title_Tx);
		rightBt = (Button) mView.findViewById(R.id.title_right_Bt);
		mBar = (ProgressBar) mView.findViewById(R.id.title_bar);
		contentLayout = (RelativeLayout) mView.findViewById(R.id.contentLayout);
	}


	private void initView() {
		findBaseView();
		addContentView();
		findView();
		setListener();
	}

	abstract void addContentView();

	abstract void findView();

	abstract void setListener();

	abstract void requestDate();
}

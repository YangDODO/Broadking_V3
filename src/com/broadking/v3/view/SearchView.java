package com.broadking.v3.view;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.broadking.v3.R;

public class SearchView extends RelativeLayout {

	private RelativeLayout layout;
	private EditText searchEd;
	private ImageView clearImg;
	public SearchView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		initView(context);
	}

	public SearchView(Context context, AttributeSet attrs) {
		super(context, attrs);
		initView(context);
	}

	public SearchView(Context context) {
		super(context);
		initView(context);
	}

	/**
	 * 
	 */
	private void initView(Context context) {
		LayoutInflater inflater = LayoutInflater.from(context);
		inflater.inflate(R.layout.searchlayout, this);
		searchEd = (EditText) findViewById(R.id.search_ed);
		clearImg = (ImageView) findViewById(R.id.search_clear);
		layout = (RelativeLayout) findViewById(R.id.search_layout);
		setListener();
	}

	/**
	 * 
	 */
	public void setSearchLayoutParams(int marginRight) {
		LayoutParams params = (LayoutParams) layout.getLayoutParams();
		params.setMargins(10, 10, marginRight + 10, 10);
	}

	/**
	 * 
	 */
	private void setListener() {

		searchEd.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
			}

			@Override
			public void afterTextChanged(Editable s) {
				if (s.length() > 0)
					clearImg.setVisibility(View.VISIBLE);
				else
					clearImg.setVisibility(View.INVISIBLE);
			}
		});

		clearImg.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				searchEd.setText("");
			}
		});
	}

}

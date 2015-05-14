package com.broadking.v3.fragment;

import java.util.ArrayList;
import java.util.List;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.broadking.v3.R;
import com.broadking.v3.util.ViewHolderUtil;
import com.broadking.v3.view.SearchView;

/**
 * 博金客
 */
public class CustomerFragment extends BaseFragment {



	private ListView listView;

	@Override
	void addContentView() {
		titleTx.setText(R.string.tab_customer);
		mContentView = mInflater.inflate(R.layout.list, null);
		contentLayout.addView(mContentView);

	}

	@Override
	void findView() {
		initData();
		listView = (ListView) mContentView.findViewById(R.id.list);
		addHeaderViews();
		addFooterView();
		Adapter adapter = new Adapter();
		listView.setAdapter(adapter);
	}

	/**
	 * ListView 添加HeaderView
	 */
	private void addHeaderViews() {
		addSearchView();
		addFindShareView();
		addDiscountView();
	}

	/**
	 * ListView添加FooterView
	 */
	private void addFooterView() {

		addBrandView();
		addTeamView();
	}

	/**
	 * 添加搜索
	 */
	private void addSearchView() {
		SearchView searchView = new SearchView(mContext);
		listView.addHeaderView(searchView);
	}

	/**
	 * 添加发现商品分享商品
	 */
	private void addFindShareView() {
		LayoutInflater inflater = LayoutInflater.from(mContext);
		View findShareView = inflater.inflate(R.layout.list_item_arrow, null);
		ImageView headImg = (ImageView) findShareView
				.findViewById(R.id.item_head_img);
		TextView name = ((TextView) findShareView
				.findViewById(R.id.item_big_tx));
		TextView introduce = (TextView) findShareView
				.findViewById(R.id.item_small_tx);
		listView.addHeaderView(findShareView);
	}

	/**
	 * 添加打折优惠抽奖活动
	 */
	private void addDiscountView() {
		LayoutInflater inflater = LayoutInflater.from(mContext);
		View discountView = inflater.inflate(R.layout.list_item_arrow, null);
		ImageView headImg = (ImageView) discountView
				.findViewById(R.id.item_head_img);
		TextView name = ((TextView) discountView
				.findViewById(R.id.item_big_tx));
		name.setText("打折优惠，抽奖活动");
		TextView introduce = (TextView) discountView
				.findViewById(R.id.item_small_tx);
		introduce.setText("数码家电年货，板鞋第一品牌，双重好礼相送。");
		listView.addHeaderView(discountView);

	}

	/**
	 * 添加品牌推荐
	 */
	private void addBrandView() {

		LayoutInflater inflater = LayoutInflater.from(mContext);
		View findShareView = inflater.inflate(R.layout.list_item_arrow, null);
		ImageView headImg = (ImageView) findShareView
				.findViewById(R.id.item_head_img);
		TextView name = ((TextView) findShareView
				.findViewById(R.id.item_big_tx));
		TextView introduce = (TextView) findShareView
				.findViewById(R.id.item_small_tx);
		listView.addFooterView(findShareView);
	}

	/**
	 * 添加博金团队
	 */
	private void addTeamView() {
		LayoutInflater inflater = LayoutInflater.from(mContext);
		View teamView = inflater.inflate(R.layout.list_item_arrow, null);
		ImageView headImg = (ImageView) teamView
				.findViewById(R.id.item_head_img);
		TextView name = ((TextView) teamView.findViewById(R.id.item_big_tx));
		TextView introduce = (TextView) teamView
				.findViewById(R.id.item_small_tx);
		listView.addFooterView(teamView);

	}

	/**
	 * 
	 */
	private void initData() {
		list = new ArrayList<String>();
		for (int i = 1; i < 11; i++) {
			list.add("测试" + i);
		}
	}

	@Override
	void setListener() {

	}

	@Override
	void requestDate() {

	}

	private List<String> list;//

	private class Adapter extends BaseAdapter {

		@Override
		public int getCount() {
			return list.size();
		}

		@Override
		public Object getItem(int position) {
			return position;
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {

			if (convertView == null) {
				LayoutInflater inflater = LayoutInflater.from(mContext);
				convertView = inflater.inflate(R.layout.list_item, parent,
						false);
			}
			TextView tx = ViewHolderUtil.get(convertView, R.id.item_big_tx);
			tx.setText(list.get(position));
			return convertView;
		}

	}

}

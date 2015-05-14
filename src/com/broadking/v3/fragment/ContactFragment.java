package com.broadking.v3.fragment;

import java.util.Arrays;
import java.util.Comparator;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;
import android.content.Context;
import android.graphics.PixelFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ListView;
import android.widget.SectionIndexer;
import android.widget.TextView;

import com.broadking.v3.R;
import com.broadking.v3.util.PingYinUtil;
import com.broadking.v3.util.ViewHolderUtil;
import com.broadking.v3.view.IndexSideBar;
import com.broadking.v3.view.SearchView;

/**
 * 通讯录的Fragment
 */
public class ContactFragment extends BaseFragment {

	private WindowManager mWindowManager;
	private ListView listView;


	@Override
	void addContentView() {
		mWindowManager = (WindowManager) mContext
				.getSystemService(Context.WINDOW_SERVICE);
		titleTx.setText(R.string.tab_contact);
		mContentView = mInflater.inflate(R.layout.contactlayout, null);
		contentLayout.addView(mContentView);
	}

	@Override
	void findView() {
		listView = (ListView) mContentView.findViewById(R.id.list);
		addHeaderView();
		listView.setAdapter(new ContactAdapter());
		IndexSideBar indexBar = (IndexSideBar) mContentView
				.findViewById(R.id.indexSideBar);
		indexBar.setListView(listView);
		TextView mDialogText = (TextView) LayoutInflater.from(mContext)
				.inflate(R.layout.index_toast, null);
		mDialogText.setVisibility(View.INVISIBLE);
		WindowManager.LayoutParams lp = new WindowManager.LayoutParams(
				LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT,
				WindowManager.LayoutParams.TYPE_APPLICATION,
				WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE
						| WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
				PixelFormat.TRANSLUCENT);
		mWindowManager.addView(mDialogText, lp);
		indexBar.setTextView(mDialogText);
	}

	
	private void addHeaderView() {
		addSearchView();
		addNewFriendsView();
		addMerchantView();
	}
	
	/**
	 * 添加搜索
	 */
	private void addSearchView(){
		SearchView searchView = new SearchView(mContext);
		int marginRight = (int) getResources().getDimension(
				R.dimen.index_sidebar_width);
		searchView.setSearchLayoutParams(marginRight);
		listView.addHeaderView(searchView);
	}
	
	/**
	 * ListView添加新的朋友HeaderView
	 */
	private void addNewFriendsView() {
		LayoutInflater inflater = LayoutInflater.from(mContext);
		View newFriendsView = inflater.inflate(R.layout.list_item, null);
		ImageView headImg = (ImageView) newFriendsView
				.findViewById(R.id.item_head_img);
		((TextView) newFriendsView.findViewById(R.id.item_big_tx))
				.setText(R.string.contact_newFriends);
		listView.addHeaderView(newFriendsView);
	}

	/**
	 * 添加博金商(供应商)HeaderView
	 */
	private void addMerchantView() {
		LayoutInflater inflater = LayoutInflater.from(mContext);
		View merchantView = inflater.inflate(R.layout.list_item, null);
		ImageView headImg = (ImageView) merchantView
				.findViewById(R.id.item_head_img);
		((TextView) merchantView.findViewById(R.id.item_big_tx))
				.setText(R.string.contact_merchant);
		listView.addHeaderView(merchantView);
	}

	@Override
	void setListener() {

	}

	@Override
	void requestDate() {

	}

	private class ContactAdapter extends BaseAdapter implements SectionIndexer {
		private String[] mNicks;
		@SuppressWarnings("unchecked")
		public ContactAdapter() {
			this.mNicks = nicks;
			// 排序(实现了中英文混排)
			Arrays.sort(mNicks, new PinyinComparator());
		}

		@Override
		public int getCount() {
			return mNicks.length;
		}

		@Override
		public Object getItem(int position) {
			return mNicks[position];
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			final String nickName = mNicks[position];
			if (convertView == null) {
				convertView = LayoutInflater.from(mContext).inflate(
						R.layout.contact_item, null);
			}
			TextView tvCatalog = ViewHolderUtil.get(convertView,
					R.id.contact_item_indexTx);// 目录
			ImageView ivAvatar = ViewHolderUtil.get(convertView,
					R.id.item_head_img);// 头像
			TextView tvNick = ViewHolderUtil.get(convertView, R.id.item_big_tx);// 昵称
			String catalog = converterToFirstSpell(nickName).substring(0, 1);
			if (position == 0) {
				tvCatalog.setVisibility(View.VISIBLE);
				tvCatalog.setText(catalog);
			} else {
				String lastCatalog = converterToFirstSpell(mNicks[position - 1])
						.substring(0, 1);
				if (catalog.equals(lastCatalog)) {
					tvCatalog.setVisibility(View.GONE);
				} else {
					tvCatalog.setVisibility(View.VISIBLE);
					tvCatalog.setText(catalog);
				}
			}

			ivAvatar.setImageResource(R.drawable.default_avatar);
			tvNick.setText(nickName);
			return convertView;
		}


		@Override
		public int getPositionForSection(int section) {
			for (int i = 0; i < mNicks.length; i++) {
				String l = converterToFirstSpell(mNicks[i]).substring(0, 1);
				char firstChar = l.toUpperCase().charAt(0);
				if (firstChar == section) {
					return i;
				}
			}
			return -1;
		}

		@Override
		public int getSectionForPosition(int position) {
			return 0;
		}

		@Override
		public Object[] getSections() {
			return null;
		}
	}

	/**
	 * 昵称
	 */
	private static String[] nicks = { "阿雅", "北风", "张山", "李四", "欧阳锋", "郭靖",
			"黄蓉", "杨过", "凤姐", "芙蓉姐姐", "移联网", "樱木花道", "风清扬", "张三丰", "梅超风" };

	/**
	 * 汉字转换位汉语拼音首字母，英文字符不变
	 * 
	 * @param chines
	 *            汉字
	 * @return 拼音
	 */
	public static String converterToFirstSpell(String chines) {
		String pinyinName = "";
		char[] nameChar = chines.toCharArray();
		HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();
		defaultFormat.setCaseType(HanyuPinyinCaseType.UPPERCASE);
		defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
		for (int i = 0; i < nameChar.length; i++) {
			if (nameChar[i] > 128) {
				try {
					pinyinName += PinyinHelper.toHanyuPinyinStringArray(
							nameChar[i], defaultFormat)[0].charAt(0);
				} catch (BadHanyuPinyinOutputFormatCombination e) {
					e.printStackTrace();
				}
			} else {
				pinyinName += nameChar[i];
			}
		}
		return pinyinName;
	}

	public class PinyinComparator implements Comparator {

		@Override
		public int compare(Object o1, Object o2) {
			String str1 = PingYinUtil.getPingYin((String) o1);
			String str2 = PingYinUtil.getPingYin((String) o2);
			return str1.compareTo(str2);
		}

	}

}

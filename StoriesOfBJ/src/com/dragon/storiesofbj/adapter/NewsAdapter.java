package com.dragon.storiesofbj.adapter;

import java.util.ArrayList;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.dragon.storiesofbj.R;
import com.dragon.storiesofbj.vo.ContentInfo;

public class NewsAdapter extends BaseAdapter {
	
	private Context context;
	private ArrayList<ContentInfo> list;
	
	public NewsAdapter(Context context,ArrayList<ContentInfo> list){
		this.context = context;
		this.list = list;
	}

	@Override
	public int getCount() {
		return list.size();
	}

	@Override
	public Object getItem(int position) {
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View arg1, ViewGroup arg2) {
		ContentInfo info = list.get(position);
		View view = View.inflate(context, R.layout.lv_news_item, null);
		TextView tv_news_content = (TextView) view.findViewById(R.id.tv_news_content);
		TextView tv_news_item_zan = (TextView) view.findViewById(R.id.tv_news_item_zan);
		TextView tv_news_item_buzan = (TextView) view.findViewById(R.id.tv_news_item_buzan);
		TextView tv_news_item_topic = (TextView) view.findViewById(R.id.tv_news_item_topic);
		TextView tv_news_content_usesrname = (TextView) view.findViewById(R.id.tv_news_content_usesrname);
		
		tv_news_content.setText(info.content);
		tv_news_item_zan.setText(info.up+"  ");
		tv_news_item_buzan.setText(info.down+"  ");
		tv_news_item_topic.setText(info.comments_count+"  ");
		tv_news_content_usesrname.setText(info.login+"  ");
		
		return view;
	}

}

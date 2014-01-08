package com.dragon.storiesofbj.fragments;

import java.sql.Date;
import java.util.ArrayList;

import net.tsz.afinal.FinalHttp;
import net.tsz.afinal.http.AjaxCallBack;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.dragon.storiesofbj.R;
import com.dragon.storiesofbj.activity.BJStoryActivity;
import com.dragon.storiesofbj.adapter.NewsAdapter;
import com.dragon.storiesofbj.view.MyListView;
import com.dragon.storiesofbj.view.MyListView.OnRefreshListener;
import com.dragon.storiesofbj.vo.ContentInfo;

@SuppressLint("HandlerLeak")
public class NewsFragment extends Fragment implements OnTouchListener{
	private static final String TAG = "NewsFragment";

	private MyListView lv_news;

	private Button btn_news_title_toggle;
	private Button btn_news_title_today;
	private Button btn_news_title_refresh;        
	
	private ArrayList<ContentInfo> contentList;
	private ProgressDialog progressDialog;
	
	private FinalHttp finalHttp;
	

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setRetainInstance(true);
		Log.i(TAG, "onCreate");
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		Log.i(TAG, "onCreateView");
		// inflater the layout       
		View view = inflater.inflate(R.layout.fragment_news, null);
		return view;
	}

	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		Log.i(TAG, "onActivityCreated");
		
		contentList = new ArrayList<ContentInfo>();
		progressDialog = new ProgressDialog(getActivity());
		progressDialog.setMessage("加载中...");
		progressDialog.show();
		findViewById();
		getData();
		setData();
		
		
		test();
	}
	
	private void test(){
		Log.i("NewsFragment", "时间："+new Date(1387765499));
	}
	

	private void getData() {            
		String url = "http://m2.qiushibaike.com/article/list/suggest?page=1&count=30&rqcnt=1";
		finalHttp = new FinalHttp();
		finalHttp.get(url, new AjaxCallBack<String>() {
			@Override
			public void onSuccess(String t) {
				super.onSuccess(t);
//				Log.i("getData", "t:" + t);
				try { 
					JSONObject jsonObject = new JSONObject(t);
					int count = (Integer) jsonObject.get("count");
					JSONArray items = (JSONArray) jsonObject.get("items");
					
					for(int i = 0; i<count; i++){
						Log.i(TAG, "i:" + i);
						JSONObject object = (JSONObject) items.get(i);
						String content = (String) object.get("content");
						
						JSONObject votes = (JSONObject) object.get("votes");
						int up = (Integer) votes.get("up");
						int down = (Integer) votes.get("down");
						
						int comments_count = (Integer) object.get("comments_count");
						String login = "";
						if(!object.isNull("user")){
							JSONObject user = (JSONObject) object.get("user");
							login = (String) user.get("login");
						}else {
							login = "匿名人";
						}
						
						contentList.add(new ContentInfo(content, up, down, comments_count, login));
					}
					lv_news.setAdapter(new NewsAdapter(getActivity(), contentList));
					progressDialog.cancel();
					lv_news.onRefreshComplete();
				} catch (JSONException e) {
					e.printStackTrace();
				}

			}
		});
	}

	private void setData() {    
		btn_news_title_toggle.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				((BJStoryActivity) getActivity()).getSlidingMenu().toggle();
			}
		});

		lv_news.setonRefreshListener(new OnRefreshListener() {
			// 刷新
			public void onRefresh() {
				new Thread() {
					@Override
					public void run() {
						super.run();
						myHandler.sendEmptyMessage(1);    
					}
				}.start(); 
			}
		});
  	}

	private Handler myHandler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			switch (msg.what) {
			case 1:
				getData();
				break;

			default:
				break;
			}
		}
	};
	
	@Override
	public boolean onTouch(View v, MotionEvent event) {
		doTouchAnim(v, event);
		return false;
	}
	
	private void findViewById() {
		lv_news = (MyListView) getActivity().findViewById(R.id.lv_news);
		btn_news_title_toggle = (Button) getActivity().findViewById(R.id.btn_news_title_toggle);
		btn_news_title_today = (Button) getActivity().findViewById(R.id.btn_news_title_today);
		btn_news_title_refresh = (Button) getActivity().findViewById(R.id.btn_news_title_refresh);

	}
	
	private void doTouchAnim(View v, MotionEvent event) {
		ScaleAnimation scaleAnim = null;
		if (event.getAction() == MotionEvent.ACTION_DOWN) {
			scaleAnim = new ScaleAnimation(1.0f, 0.95f, 1.0f, 0.95f,
					Animation.RELATIVE_TO_SELF, 0.5f,
					Animation.RELATIVE_TO_SELF, 0.5f);
		} else {
			scaleAnim = new ScaleAnimation(0.95f, 1.0f, 0.95f, 1.0f,
					Animation.RELATIVE_TO_SELF, 0.5f,
					Animation.RELATIVE_TO_SELF, 0.5f);
		}
		scaleAnim.setDuration(30);
		scaleAnim.setFillAfter(true);
		v.startAnimation(scaleAnim);
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		Log.i(TAG, "onDestroy");
	}

	@Override
	public void onDetach() {
		super.onDetach();
		Log.i(TAG, "onDetach");
	}

	@Override
	public void onPause() {
		super.onPause();
		Log.i(TAG, "onPause");
	}

	@Override
	public void onResume() {
		super.onResume();
		Log.i(TAG, "onResume");
	}

	@Override
	public void onStart() {
		super.onStart();
		Log.i(TAG, "onStart");
	}

	@Override
	public void onStop() {
		super.onStop();
		Log.i(TAG, "onStop");
	}
}

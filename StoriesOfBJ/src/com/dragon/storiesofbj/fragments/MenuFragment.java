package com.dragon.storiesofbj.fragments;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dragon.storiesofbj.R;
import com.dragon.storiesofbj.activity.BJStoryActivity;

@SuppressLint("HandlerLeak")
public class MenuFragment extends Fragment implements OnClickListener{
	private static final String TAG = "MenuFragment";

	private String userName = "谭小贤";
	
	// 新鲜事
	private RelativeLayout rl_menu_news;
	private ImageView iv_menu_news_news;
	// 用户中心
	private RelativeLayout rl_menu_user;
	private TextView tv_menu_user;
	private ImageView iv_menu_user_news;
	// 乐吧
	private RelativeLayout rl_menu_leba;
	private ImageView iv_menu_leba_news;
	// 推荐
	private RelativeLayout rl_menu_recommend;
	private ImageView iv_menu_recommend_news;
	// 设置
	private RelativeLayout rl_menu_setting;
	private ImageView iv_menu_setting_news;
	
	private String index = "NEWS";
	private boolean isCurrentFragment = false;
	private String tag = "";
	
	private final String USER = "USER";
	private final String NEWS = "NEWS";    
	private final String LEBA = "LEBA";
	private final String RECOMMEND = "RECOMMEND";
	private final String SETTING = "SETTING";
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		Log.i(TAG, "onCreateView");
		return inflater.inflate(R.layout.layout_menu, null);
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		Log.i(TAG, "onActivityCreated");
		
		init();
	}
	
	private void init(){
		findViewById();
		setListener();
		setData();
	}
	
	private void setData(){
		iv_menu_news_news.setVisibility(View.VISIBLE);
	}
	
	private void findViewById(){
		// 新鲜事
		rl_menu_news = (RelativeLayout) getActivity().findViewById(R.id.rl_menu_news);
		iv_menu_news_news = (ImageView) getActivity().findViewById(R.id.iv_menu_news_news);
		// 用户中心
		rl_menu_user = (RelativeLayout) getActivity().findViewById(R.id.rl_menu_user);
		tv_menu_user = (TextView) getActivity().findViewById(R.id.tv_menu_user);
		iv_menu_user_news = (ImageView) getActivity().findViewById(R.id.iv_menu_user_news);
		// 乐吧
		rl_menu_leba = (RelativeLayout) getActivity().findViewById(R.id.rl_menu_leba);
		iv_menu_leba_news = (ImageView) getActivity().findViewById(R.id.iv_menu_leba_news);
		// 推荐
		rl_menu_recommend = (RelativeLayout) getActivity().findViewById(R.id.rl_menu_recommend);
		iv_menu_recommend_news = (ImageView) getActivity().findViewById(R.id.iv_menu_recommend_news);
		// 设置
		rl_menu_setting = (RelativeLayout) getActivity().findViewById(R.id.rl_menu_setting);
		iv_menu_setting_news = (ImageView) getActivity().findViewById(R.id.iv_menu_setting_news);
	}
	
	private void setListener(){
		rl_menu_news.setOnClickListener(this);
		rl_menu_user.setOnClickListener(this);
		rl_menu_leba.setOnClickListener(this);
		rl_menu_recommend.setOnClickListener(this);
		rl_menu_setting.setOnClickListener(this);
	}
	
	private boolean isMenuClick = false;
	@Override
	public void onClick(View v) {
		FragmentManager fragmentManager = ((BJStoryActivity)getActivity()).getFragmentManager();
		Fragment fragment = null;
		switch (v.getId()) {
		case R.id.rl_menu_news:// 新鲜事
			if(isCurrentFragment(NEWS))
				break;
			fragment = (NewsFragment)fragmentManager.findFragmentByTag(NEWS);
			fragment = fragment == null ?new NewsFragment():fragment;
			break;
		case R.id.rl_menu_leba:// 乐吧
//			if(isCurrentFragment(LEBA))
//				break;
//			fragment = (HomeFragment)fragmentManager.findFragmentByTag(LEBA);
//			fragment = fragment == null ?new HomeFragment():fragment;
			break;
		case R.id.rl_menu_recommend:// 推荐
//			if(isCurrentFragment(RECOMMEND))
//				break;
//			fragment = (HomeFragment)fragmentManager.findFragmentByTag(RECOMMEND);
//			fragment = fragment == null ?new HomeFragment():fragment;
			break;
		case R.id.rl_menu_user:// 用户中心
//			if(isCurrentFragment(USER))
//				break;
//			fragment = (HomeFragment)fragmentManager.findFragmentByTag(USER);
//			fragment = fragment == null ?new HomeFragment():fragment;
			break;
		case R.id.rl_menu_setting:// 设置
//			if(isCurrentFragment(SETTING))
//				break;
//			fragment = (HomeFragment)fragmentManager.findFragmentByTag(SETTING);
//			fragment = fragment == null ?new HomeFragment():fragment;
			break;

		default:
			break;
		}
		if(isMenuClick){
			if(!isCurrentFragment)
				fragmentManager.beginTransaction().replace(R.id.content, fragment, tag).commit();
			((BJStoryActivity) getActivity()).getSlidingMenu().toggle();
			isMenuClick = false;
		}
	}
	
	private boolean isCurrentFragment(String index){
		isMenuClick = true;
		tag = index;
		if(this.index.equals(index)) {
            return isCurrentFragment = true;
        }
		this.index = index;
		return isCurrentFragment = false;
	}

	@Override
    public void onDestroy() {
        Log.e(TAG, "onDestroy:");
        super.onDestroy();
    }

    @Override
    public void onDetach() {
        Log.e(TAG, "onDetach:");
        super.onDetach();
    }

    @Override
    public void onPause() {
        Log.e(TAG, "onPause:");
        super.onPause();
    }

    @Override
    public void onResume() {
        Log.e(TAG, "onResume:");
        super.onResume();
    }

    @Override
    public void onStart() {
        Log.e(TAG, "onStart:");
        super.onStart();
    }

    @Override
    public void onStop() {
        Log.e(TAG, "onStop:");
        super.onStop();
    }

}

package com.dragon.storiesofbj.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.dragon.storiesofbj.R;
import com.dragon.storiesofbj.activity.BJStoryActivity;
import com.dragon.storiesofbj.view.MyListView;

public class HomeFragment extends Fragment {
	private static final String TAG = "HomeFragment";
	
	private MyListView lv_home;
	
	private Button btn_home_title_toggle;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        Log.i(TAG, "onCreate");
        
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    	Log.i(TAG, "onCreateView");
        //inflater the layout 
        View view = inflater.inflate(R.layout.fragment_home, null);
        return view;
    }
    
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
    	super.onActivityCreated(savedInstanceState);
    	Log.i(TAG, "onActivityCreated");
    	
    	setData();
    }
    
    private void setData(){
    	lv_home = (MyListView) getActivity().findViewById(R.id.lv_home);
    	btn_home_title_toggle = (Button) getActivity().findViewById(R.id.btn_home_title_toggle);
        
    	btn_home_title_toggle.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				((BJStoryActivity) getActivity()).getSlidingMenu().toggle();
			}
		});
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

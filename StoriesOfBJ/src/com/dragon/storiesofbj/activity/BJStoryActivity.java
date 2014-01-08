package com.dragon.storiesofbj.activity;

import android.app.FragmentTransaction;
import android.graphics.Canvas;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MenuItem;

import com.dragon.storiesofbj.R;
import com.dragon.storiesofbj.fragments.MenuFragment;
import com.dragon.storiesofbj.fragments.NewsFragment;
import com.dragon.storiesofbj.util.ActivityUtil;
import com.slidingmenu.lib.SlidingMenu;
import com.slidingmenu.lib.SlidingMenu.CanvasTransformer;
import com.slidingmenu.lib.app.SlidingActivity;

/**
 * 北京茶馆
 * 
 * @author yeguolong
 * @version 1.0.0
 */
public class BJStoryActivity extends SlidingActivity {
	public final String TAG = "BJStoryActivity";
	
	public boolean hasNews = false;// TODO
	
    @Override  
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        setContentView(R.layout.frame_content);  
        setBehindContentView(R.layout.frame_menu);  
        
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.menu, new MenuFragment(),"menu");
        fragmentTransaction.replace(R.id.content, new NewsFragment(),"NEWS");
        fragmentTransaction.commit();

        setSlidingMenu();
        
    }
    
    /** 设置SlidingMenu的滑动效果  */ 
    private void setSlidingMenu(){
    	CanvasTransformer mTransformer = new CanvasTransformer(){  
    		@Override 
    		public void transformCanvas(Canvas canvas, float percentOpen) {  
    			canvas.scale(percentOpen, 1, 0, 0);       
    		}         
    	};  
        SlidingMenu sm = getSlidingMenu();
        sm.setShadowDrawable(R.drawable.shadow);
		sm.setShadowWidthRes(R.dimen.shadow_width);
		sm.setBehindOffsetRes(R.dimen.slidingmenu_offset);
		sm.setBehindScrollScale(0.0f);
		sm.setFadeDegree(0.35f);
		sm.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
		sm.setBehindCanvasTransformer(mTransformer); 
		
		
//		getActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
        case android.R.id.home:
            toggle();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
    	if (keyCode == KeyEvent.KEYCODE_BACK) 
    		ActivityUtil.showActExitDialog(BJStoryActivity.this);
    	return super.onKeyDown(keyCode, event);
    }
}

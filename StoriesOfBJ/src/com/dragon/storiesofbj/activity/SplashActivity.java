package com.dragon.storiesofbj.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.widget.ImageView;

import com.dragon.storiesofbj.R;

public class SplashActivity extends Activity {
	
	private ImageView splash;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.splash);
		splash = (ImageView) findViewById(R.id.iv_splash);
		
		AlphaAnimation alphaAnimation = new AlphaAnimation(0.0f, 1.0f);
		alphaAnimation.setFillAfter(true);
		alphaAnimation.setDuration(3000);
		alphaAnimation.setAnimationListener(new AnimationListener() {
			
			@Override
			public void onAnimationStart(Animation animation) {
			}
			
			@Override
			public void onAnimationRepeat(Animation animation) {
			}
			
			@Override
			public void onAnimationEnd(Animation animation) {
				Intent intent = new Intent(SplashActivity.this,BJStoryActivity.class);
				startActivity(intent);
				SplashActivity.this.finish();
				overridePendingTransition(R.anim.alpha_in, R.anim.alpha_out);
			}
		});
		splash.startAnimation(alphaAnimation);
	}
	
}

package com.facebook.login;


import android.app.Activity;
import android.content.Intent;
import de.greenrobot.event.EventBus;

public class BaseActivity extends Activity{
	public boolean registered = false;
	
	public void startActivity(Class<? extends Activity> clazz){
		Intent myIntent = new Intent(this, clazz);
		startActivity(myIntent);
	}
	
	public void startActivity(Class<? extends Activity> clazz, String... params){
		Intent myIntent = new Intent(this, clazz);
		int len = params.length;
		for (int i=0; i<len; i = i + 2) {
			myIntent.putExtra(params[i], params[i+1]);
		}
		startActivity(myIntent);
	}	

	@Override
	protected void onPause() {
		EventBus.getDefault().unregister(this);
		registered = false;
		super.onPause();
	}

	@Override
	protected void onResume() {
		if(! registered) {
			EventBus.getDefault().register(this);
			registered = true;
		}
		
		super.onResume();
	}
}

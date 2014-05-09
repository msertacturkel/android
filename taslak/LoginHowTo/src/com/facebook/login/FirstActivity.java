package com.facebook.login;


import tr.com.agmlab.events.LoginEvent;
import tr.com.agmlab.tasks.LoginTask;
import tr.com.agmlab.utils.JsonUtil;
import tr.com.agmlab.utils.ServiceUtil;
import tr.com.agmlab.utils.SharedPreferencesUtility;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.agmlab.localsearch.core.domain.user.dto.UserAuthResponse;
import com.agmlab.localsearch.core.enums.Language;
import com.agmlab.localsearch.core.enums.ResultCodeEnum;
import com.facebook.LoginActivity;

public class FirstActivity extends BaseActivity {
	private ProgressBar progressBar;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.first_page, menu);
		return true;
	}
	public void onEvent(LoginEvent autoLoginEvent) {
		Log.i("login_event", "autoLoginEvent: " + JsonUtil.getJson(autoLoginEvent));
		progressBar.setVisibility(View.INVISIBLE);
		
		// if success
		if(autoLoginEvent != null && autoLoginEvent.getResultCode() !=null && autoLoginEvent.getResultCode().getResultCode() == ResultCodeEnum.SUCCESS.getErrorCode()) {
			ServiceUtil.token = autoLoginEvent.getLoginResponse().getAccesstoken();
			//startActivity(SmeFilterActivity.class);
		} else {
			//startActivity(FacebookLoginActivity.class);
		}
		
		finish();
	}
	

}

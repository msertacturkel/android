package com.facebook.login;

import com.agmlab.localsearch.core.domain.user.dto.UserAuthResponse;
import com.agmlab.localsearch.core.enums.Language;

import tr.com.agmlab.events.LoginEvent;
import tr.com.agmlab.tasks.LoginTask;
import tr.com.agmlab.utils.ServiceUtil;
import tr.com.agmlab.utils.SharedPreferencesUtility;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

public class FacebookLoginActivity extends FragmentActivity {

	private FacebookMainFragment mainFragment;

	private ProgressBar progressBar;

	private EditText usernameEditText;
	private EditText passwordEditText;
	private ProgressBar loginProgress;
	private String username;
	private String password;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_first_page);
		//LoginActivity();
		
		// Build Fragment
		if (savedInstanceState == null) {
			// Add the fragment on initial activity setup
			mainFragment = new FacebookMainFragment();
			getSupportFragmentManager().beginTransaction()
					.add(android.R.id.content, mainFragment).commit();
		} else {
			// Or set the fragment from restored state info
			mainFragment = (FacebookMainFragment) getSupportFragmentManager()
					.findFragmentById(android.R.id.content);
		}

		Log.d("facebook", "On Create");
		
		progressBar = (ProgressBar) findViewById(R.id.firstpage_loading);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	public void onEvent(LoginEvent loginEvent) {

		loginProgress.setVisibility(View.GONE);

		if (loginEvent != null) {
			UserAuthResponse loginResponse = loginEvent.getLoginResponse();

			if (loginResponse != null) {
				// success
				ServiceUtil.language = Language.TURKISH;

				// Toast.makeText(getApplicationContext(),
				// loginResponse.getAccesstoken(), Toast.LENGTH_LONG).show();

				ServiceUtil.token = loginResponse.getAccesstoken();
				if (SharedPreferencesUtility.addOrUpdateAccount(
						getApplicationContext(), username, password)) {
					SharedPreferencesUtility.writeString(
							getApplicationContext(), username, "username");
				}

				// startActivity(SecondActivity.class);
				finish();
			} else {
				Toast.makeText(getApplicationContext(),
						R.string.message_wrong_username_password,
						Toast.LENGTH_LONG).show();
			}
		} else {
			Toast.makeText(getApplicationContext(),
					R.string.message_login_error, Toast.LENGTH_LONG).show();
		}
	}

	public void LoginActivity() {
		// Try autoLogin
		SharedPreferencesUtility.setAccountType(getApplicationContext()
				.getPackageName());
		LoginTask autoLoginTask = new LoginTask(
				SharedPreferencesUtility.getUserName(getApplicationContext()),
				SharedPreferencesUtility.getPassword(getApplicationContext()));
		autoLoginTask.execute((Void) null);
		
		// Get fields
		usernameEditText = (EditText) findViewById(R.id.username_edittext);
		passwordEditText = (EditText) findViewById(R.id.password_edittext);
		loginProgress = (ProgressBar) findViewById(R.id.login_progress);
		Button loginButton = (Button) findViewById(R.id.login_button);

		loginButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				username = usernameEditText.getText().toString();
				password = passwordEditText.getText().toString();

				if (username != null && !username.isEmpty() && password != null
						&& !password.isEmpty()) {
					loginProgress.setVisibility(View.VISIBLE);

					LoginTask loginTask = new LoginTask(username, password);
					loginTask.execute((Void) null);
				} else {
					// show toast to fill fields
					Toast.makeText(getApplicationContext(),
							R.string.message_empty_username_password,
							Toast.LENGTH_LONG).show();
				}
			}
		});

	}
}

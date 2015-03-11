package com.gss.libraryapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

/**
 * This class represents the login screen.
 * 
 * @author GVIT
 * @version 1.0
 * @since 2014
 * 
 */
public class LoginActivity extends Activity {

	/**
	 * Creates the view for the login screen.
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);

		Button mEmailSignInButton = (Button) findViewById(R.id.email_sign_in_button);
		mEmailSignInButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View view) {
				attemptLogin();
			}
		});
	}

	/**
	 * Navigates to the main screen.
	 */
	public void attemptLogin() {
		Intent mainActivity = new Intent(LoginActivity.this, MainActivity.class);
		startActivity(mainActivity);
	}
}

package com.gss.libraryapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ProgressBar;

/**
 * Creates the main screen activity.
 * 
 * @author GVIT
 * @version 1.0
 *
 */
public class MainActivity extends Activity {

	/**
	 * Creates the view for the main screen.
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Button booksBtn = (Button) findViewById(R.id.booksBtn);
		booksBtn.setOnClickListener(createBooksBtnListener());

		Button loginBtn = (Button) findViewById(R.id.loginBtn);
		loginBtn.setOnClickListener(createLoginBtnListener());

		Button syncButton = (Button) findViewById(R.id.syncButton);
		syncButton.setOnClickListener(new SyncBooksListener(MainActivity.this));

		ProgressBar bookSyncbar = (ProgressBar) findViewById(R.id.progressBar1);
		bookSyncbar.setVisibility(View.INVISIBLE);
	}

	/**
	 * When login button is clicked, the page is navigated to the main screen.
	 * 
	 * @return listener
	 */
	private OnClickListener createLoginBtnListener() {
		OnClickListener listener = new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(MainActivity.this,
						LoginActivity.class);
				startActivity(intent);
			}
		};
		return listener;
	}

	/**
	 * When books button is clicked, it is navigated to the department screen.
	 * 
	 * @return listener
	 */
	private OnClickListener createBooksBtnListener() {
		OnClickListener listener = new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(MainActivity.this,
						DepartmentActivity.class);
				startActivity(intent);
			}
		};
		return listener;
	}
}

package com.gss.libraryapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

/**
 * Creates the view for the semester.
 * 
 * @author GVIT
 * @version 1.0
 *
 */
public class SemesterActivity extends Activity {

	/** List view for the semester. **/
	private ListView semList;

	/** The department that is selected in the previous screen. */
	private String department;

	/**Creates the view for this activity. */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_semester);
		department = getIntent().getStringExtra("department");

		semList = (ListView) findViewById(R.id.semList);
		semList.setOnItemClickListener(createOnItenClickListener());
	}

	/**
	 * Sets the semester, department info as extra information and navigates the screen
	 * @return listener
	 */
	private OnItemClickListener createOnItenClickListener() {
		OnItemClickListener listener = new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				String semester = (String) semList.getItemAtPosition(position);
				Intent intent = new Intent(SemesterActivity.this,
						SubjectActivity.class);
				intent.putExtra("semester", semester);
				intent.putExtra("department", department);
				startActivity(intent);
			}
		};
		return listener;
	}
}

package com.gss.libraryapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

/**
 * This class represents the activity for showing the list of departments.
 * 
 * @author GVIT
 * @version 1.0
 *
 */
public class DepartmentActivity extends Activity {

	/** The list view of the department. */
	private ListView departList;

	/**
	 * Creates the view for this screen.
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_department);
		departList = (ListView) findViewById(R.id.departList);
		departList.setOnItemClickListener(createOnItemClickListener());
	}

	/**
	 * when the user selects the department,it navigates to the semester
	 * activity. Also, sets the selected department as extra info.
	 * 
	 * @return
	 */
	private OnItemClickListener createOnItemClickListener() {
		OnItemClickListener listener = new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				String department = (String) departList
						.getItemAtPosition(position);
				Intent intent = new Intent(DepartmentActivity.this,
						SemesterActivity.class);
				intent.putExtra("department", department);
				startActivity(intent);
			}
		};
		return listener;
	}
}

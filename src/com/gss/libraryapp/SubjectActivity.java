package com.gss.libraryapp;

import java.util.List;

import com.gss.libraryapp.database.DatabaseHandler;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * Creates the view for the subject.
 * 
 * @author GVIT
 * @version 1.0
 *
 */
public class SubjectActivity extends Activity {

	/** Shows the subject list. */
	private ListView subjectList;

	/**
	 * Creates the view for showing the list of subjects.
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_subject);
		final String semester = getIntent().getStringExtra("semester");
		final String department = getIntent().getStringExtra("department");

		subjectList = (ListView) findViewById(R.id.subjectList);
		subjectList.setOnItemClickListener(createOnItemClickListener());

		// Based on the selected semester and department the subjects are loaded
		// from the database.
		DatabaseHandler handler = new DatabaseHandler(this);
		List<String> subjects = handler.getSubjects(department, semester);
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, subjects);
		subjectList.setAdapter(adapter);
	}

	/**
	 * 
	 * @return
	 */
	private OnItemClickListener createOnItemClickListener() {
		OnItemClickListener listener = new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				String subject = (String) subjectList
						.getItemAtPosition(position);
				Intent intent = new Intent(SubjectActivity.this,
						BookActivity.class);
				intent.putExtra("subject", subject);
				startActivity(intent);

			}
		};
		return listener;
	}
}

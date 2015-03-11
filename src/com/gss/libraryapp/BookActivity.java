package com.gss.libraryapp;

import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

import com.gss.libraryapp.book.CustBookList;
import com.gss.libraryapp.database.DatabaseHandler;
import com.gss.libraryapp.database.IBook;

/**
 * This class represents the activity for showing the list of books based on the
 * subject. The subject information is taken from the Intent extra which is set
 * in the subject activity.
 * 
 * @author GVIT
 * @version 1.0
 *
 */
public class BookActivity extends Activity {

	/**
	 * Creates the view of the book activity.
	 */

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_book);

		// Gets the extra information that is set.
		final String subject = getIntent().getStringExtra("subject");

		// Calls the database for retrieving the books based on the subject
		DatabaseHandler handler = new DatabaseHandler(this);

		List<IBook> books = handler.getBooks(subject);
		CustBookList listAdapter = new CustBookList(BookActivity.this, books);
		ListView bookList = (ListView) findViewById(R.id.bookList);
		bookList.setAdapter(listAdapter);
		bookList.setOnItemClickListener(createOnItemClickListener());

	}

	/**
	 * When the user selects particular book, it is navigated back to the login
	 * screen.
	 * 
	 * @return
	 */
	private OnItemClickListener createOnItemClickListener() {
		OnItemClickListener listener = new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Intent intent = new Intent(BookActivity.this,
						LoginActivity.class);
				startActivity(intent);
			}
		};
		return listener;
	}
}

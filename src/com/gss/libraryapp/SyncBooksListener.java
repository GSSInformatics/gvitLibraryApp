/**
 * 
 */
package com.gss.libraryapp;

import android.app.Activity;
import android.content.ContentValues;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ProgressBar;

import com.gss.libraryapp.database.DatabaseHandler;
import com.gss.libraryapp.webservice.Book;
import com.gss.libraryapp.webservice.LibraryBook;
import com.gss.libraryapp.webservice.VectorBook;

/**
 * @author AjaykumarVasireddy
 * @version 1.0
 *
 */
public class SyncBooksListener implements OnClickListener,
		com.gss.libraryapp.webservice.IWsdl2CodeEvents {

	private ProgressBar bookSyncbar;
	private DatabaseHandler handler;
	private Button syncButton;

	public SyncBooksListener(Activity context) {
		bookSyncbar = (ProgressBar) context.findViewById(R.id.progressBar1);
		syncButton = (Button)context.findViewById(R.id.syncButton);
		handler = new DatabaseHandler(context);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.syncButton:
			LibraryBook webserviceCall = new LibraryBook(this);
			try {
				webserviceCall.getBooksAsync();
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;

		default:
			break;
		}

	}

	@Override
	public void Wsdl2CodeStartedRequest() {
		bookSyncbar.setVisibility(View.VISIBLE);
		bookSyncbar.setProgress(0);
		syncButton.setEnabled(false);
	}

	@Override
	public void Wsdl2CodeFinished(String methodName, Object Data) {
		VectorBook bookList = (VectorBook) Data;
		int i = 0;
		bookSyncbar.setMax(bookList.size());
		for (Book book : bookList) {
			bookSyncbar.incrementProgressBy(++i);
			ContentValues rowValue = new ContentValues();
			rowValue.put("DEPARTMENT", book.department);
			rowValue.put("SEMESTER", book.semester);
			rowValue.put("SUBJECT", book.subject);
			rowValue.put("SERIALNUM", book.serialnum);
			rowValue.put("BOOKNAME", book.bookName);
			rowValue.put("AUTHOR", book.author);
			rowValue.put("EDITION", book.edition);
			rowValue.put("SEMESTER", book.semester);
			handler.getWritableDatabase().insert(DatabaseHandler.TABLE_NAME,
					null, rowValue);
		}
		syncButton.setEnabled(true);

	}

	@Override
	public void Wsdl2CodeFinishedWithException(Exception ex) {
		bookSyncbar.setVisibility(View.INVISIBLE);
		syncButton.setEnabled(true);

	}

	@Override
	public void Wsdl2CodeEndedRequest() {

	}

}

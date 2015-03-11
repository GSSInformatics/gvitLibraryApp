/**
 * 
 */
package com.gss.libraryapp.book;

import java.util.List;

import com.gss.libraryapp.R;
import com.gss.libraryapp.database.IBook;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

/**
 * @author janaki
 *
 */
public class CustBookList extends ArrayAdapter<IBook>{
	
	private Activity context;
	private List<IBook> booksList;

	public CustBookList(Activity context, List<IBook> booksList) {
		super(context, R.layout.bookdisplay, booksList);
		this.context = context;
		this.booksList = booksList;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater inflater = context.getLayoutInflater();
		View rowView = inflater.inflate(R.layout.bookdisplay, null, true);
		TextView serialNum = (TextView) rowView
				.findViewById(R.id.serialNum);
		serialNum.setText(booksList.get(position).getSerialNumber());
		
		TextView bookName = (TextView) rowView
				.findViewById(R.id.bookName);
		bookName.setText(booksList.get(position).getName());

		TextView author = (TextView) rowView.findViewById(R.id.author);
		author.setText(booksList.get(position).getAuthor());

		TextView edition = (TextView) rowView.findViewById(R.id.edition);
		edition.setText(booksList.get(position).getEdition());

		TextView available = (TextView) rowView.findViewById(R.id.available);
		available.setText(booksList.get(position).getAvailability());

		return rowView;
	}
}

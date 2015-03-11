/**
 * 
 */
package com.gss.libraryapp.database;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;

import com.gss.libraryapp.book.Book;

/**
 * @author VAMSI KRISHNA
 *
 */
public class DatabaseHandler extends SQLiteOpenHelper {

	private static final String DATABASE_NAME = "Library";
	private static final int VERSION = 1;
	public static final String TABLE_NAME = "BOOK";
	private static final String KEY_ID = "ID";
	private static final String KEY_DEPARTMENT = "DEPARTMENT";
	private static final String KEY_SEMESTER = "SEMESTER";
	private static final String KEY_SUBJECT = "SUBJECT";
	private static final String KEY_SNUM = "SERIALNUM";
	private static final String KEY_BOOKNAME = "BOOKNAME";
	private static final String KEY_AUTHOR = "AUTHOR";
	private static final String KEY_EDITION = "EDITION";

	public DatabaseHandler(Context context) {
		super(context, DATABASE_NAME, null, VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		String createTableSQL = "CREATE TABLE " + TABLE_NAME + "(" + KEY_ID
				+ " INTEGER PRIMARY KEY," + KEY_DEPARTMENT + " TEXT,"
				+ KEY_SEMESTER + " TEXT," + KEY_SUBJECT + " TEXT," + KEY_SNUM
				+ " TEXT," + KEY_BOOKNAME + " TEXT," + KEY_AUTHOR + " TEXT,"
				+ KEY_EDITION + " TEXT" + " )";
		db.execSQL(createTableSQL);
	}

	public List<String> getSubjects(String department, String semester) {
		SQLiteDatabase database = this.getReadableDatabase();

		String[] columns = { KEY_SUBJECT };

		SQLiteQueryBuilder qBuilder = new SQLiteQueryBuilder();
		qBuilder.setDistinct(true);
		qBuilder.setTables(TABLE_NAME);

		StringBuilder sBuilder = new StringBuilder(KEY_DEPARTMENT);
		sBuilder.append("=?");
		String[] selectionArgs = new String[2];
		selectionArgs[0] = department;
		sBuilder.append("AND ").append(KEY_SEMESTER).append("=?");
		selectionArgs[1] = semester;
		Cursor cursor = qBuilder.query(database, columns, sBuilder.toString(),
				selectionArgs, null, null, null);
		List<String> subjects = new ArrayList<String>();
		while (cursor.moveToNext()) {
			String subject = cursor.getString(cursor
					.getColumnIndex(KEY_SUBJECT));
			subjects.add(subject);
		}
		return subjects;
	}

	public List<IBook> getBooks(String subject) {

		SQLiteDatabase database = this.getReadableDatabase();

		String[] columns = { KEY_BOOKNAME, KEY_AUTHOR, KEY_SNUM, KEY_EDITION };

		SQLiteQueryBuilder qBuilder = new SQLiteQueryBuilder();
		qBuilder.setDistinct(true);
		qBuilder.setTables(TABLE_NAME);

		StringBuilder sBuilder = new StringBuilder(KEY_SUBJECT);
		sBuilder.append("=?");
		String[] selectionArgs = new String[1];
		selectionArgs[0] = subject;

		Cursor cursor = qBuilder.query(database, columns, sBuilder.toString(),
				selectionArgs, null, null, null);
		List<IBook> books = new ArrayList<IBook>();
		while (cursor.moveToNext()) {
			String bookName = cursor.getString(cursor
					.getColumnIndex(KEY_BOOKNAME));

			String author = cursor.getString(cursor.getColumnIndex(KEY_AUTHOR));

			String serialNum = cursor
					.getString(cursor.getColumnIndex(KEY_SNUM));

			String edition = cursor.getString(cursor
					.getColumnIndex(KEY_EDITION));

			Book book = new Book(bookName, author, serialNum, edition, "5");
			books.add(book);

		}
		return books;
	}

	public void insertValues() {
		SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(KEY_DEPARTMENT, "CSE");
		values.put(KEY_SEMESTER, "2-1");
		values.put(KEY_SUBJECT, "MATHS");
		values.put(KEY_BOOKNAME, "ALL IN ONE");
		values.put(KEY_AUTHOR, "VAMSI");
		values.put(KEY_SNUM, "1234");
		values.put(KEY_EDITION, "3rd");
		sqLiteDatabase.insert(TABLE_NAME, null, values);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// Drop older table if existed
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);

		// Create tables again
		onCreate(db);
	}

}

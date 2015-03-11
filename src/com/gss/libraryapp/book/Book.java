/**
 * 
 */
package com.gss.libraryapp.book;

import com.gss.libraryapp.database.IBook;

/**
 * @author VAMSI KRISHNA
 *
 */
public class Book implements IBook {

	private String name;
	private String author;
	private String sNum;
	private String edition;
	private String availability;

	public Book(String name, String author, String sNum, String edition, String availability) {
		this.name = name;
		this.author = author;
		this.sNum = sNum;
		this.edition = edition;
		this.availability = availability;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public String getAuthor() {
		return author;
	}

	@Override
	public String getSerialNumber() {
		return sNum;
	}

	@Override
	public String getEdition() {
		return edition;
	}

	public String getAvailability() {
		return availability;
	}
}

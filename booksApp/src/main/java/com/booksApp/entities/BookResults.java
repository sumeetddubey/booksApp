package com.booksApp.entities;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author sumeetdubey
 * Class for storing search results returned from the itunes api
 *
 */
public class BookResults {
	public BookResults() {
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	
	public List<Book> getBooks() {
		return books;
	}

	public void setBooks(List<Book> books) {
		this.books = books;
	}

//	Jackson json parser annotations 
	@JsonProperty("resultCount")
	private int count;
	@JsonProperty("results")
	private List<Book> books=new ArrayList<Book>();
}

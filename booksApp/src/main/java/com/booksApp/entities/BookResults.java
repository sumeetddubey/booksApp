package com.booksApp.entities;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

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

	@JsonProperty("resultCount")
	private int count;
	@JsonProperty("results")
	private List<Book> books=new ArrayList<Book>();
}

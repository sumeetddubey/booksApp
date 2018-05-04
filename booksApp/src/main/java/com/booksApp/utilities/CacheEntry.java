package com.booksApp.utilities;

import com.booksApp.entities.BookResults;


/**
 * @author sumeetdubey
 * Class for a cache entry having attributes for storing a query url and its search results
 */
public class CacheEntry {
	public CacheEntry(String queryString, BookResults result) {
		this.queryString = queryString;
		this.result = result;
	}
	public BookResults getResult() {
		return result;
	}
	public void setResult(BookResults result) {
		this.result = result;
	}
	public String getQueryString() {
		return queryString;
	}
	public void setQueryString(String queryString) {
		this.queryString = queryString;
	}
	private BookResults result;
	private String queryString;
}

package com.booksApp.utilities;

import java.util.HashMap;
import java.util.LinkedList;

import com.booksApp.entities.BookResults;

public class QueryCache {
	private final int MaxCacheSize=50;
	private LinkedList<CacheEntry> results;
	private HashMap<String, CacheEntry> cacheMap;
	
	public QueryCache() {
		results = new LinkedList<CacheEntry>();
		cacheMap = new HashMap<String, CacheEntry>();
	}
	
	public void addToCache(String query, BookResults result) {
		CacheEntry entry = new CacheEntry(query, result);
		cacheMap.put(query, entry);
		results.addFirst(entry);
		if(results.size()>MaxCacheSize) {
			System.out.println("cache is full");
			CacheEntry last =results.removeLast();
			String queryString = last.getQueryString();
			System.out.println("removing " +queryString);
			cacheMap.remove(queryString);
		}
	}
	
	public boolean inCache(String queryString) {
		return cacheMap.containsKey(queryString);
	}
	
	public BookResults getFromCache(String queryString) {
		if(!inCache(queryString))
			return null;
		CacheEntry entry = cacheMap.get(queryString);
		return entry.getResult();
	}
}

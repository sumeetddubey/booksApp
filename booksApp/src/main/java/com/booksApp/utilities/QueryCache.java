package com.booksApp.utilities;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;

import com.booksApp.entities.BookResults;


/**
 * @author sumeetdubey
 * Class for maintaining a cache of results from the itunes api
 */
public class QueryCache {
//	maximum number of items the cache can hold
	private final int MaxCacheSize=50;
//	each CacheEntry object is stored in a linked list 
	private LinkedList<CacheEntry> results;
//	HashMap for mapping query urls to cached results
	private HashMap<String, CacheEntry> cacheMap;
	
	public QueryCache() {
		results = new LinkedList<CacheEntry>();
		cacheMap = new HashMap<String, CacheEntry>();
	}
	
	/**
	 * @param query
	 * @param result
	 * Adds the given url and its results in the cache
	 */
	public void addToCache(String query, BookResults result) {
		CacheEntry entry = new CacheEntry(query, result);
		cacheMap.put(query, entry);
		results.addFirst(entry);
//		if the cache is full, delete the least recently used cache antry and add new entry 
//		in the beginning
		if(results.size()>MaxCacheSize) {
			CacheEntry last =results.removeLast();
			String queryString = last.getQueryString();
			cacheMap.remove(queryString);
		}
	}
	
	
	/**
	 * @param query
	 * updates the position of query in the list by moving it to the front 
	 */
	public void updateInCache(String query) {
		if(!cacheMap.containsKey(query))
			return;
		Iterator<CacheEntry> it = results.iterator();
		int index=0;
		CacheEntry result=null;
		while(it.hasNext()) {
			CacheEntry entry = it.next();
//			find the query in the list
			if(entry.getQueryString().equals(query)) {
//				remove and insert in the beginning
				result = results.get(index);
			}
			index++;
		}
		if(result!=null)
			results.remove(result);
			results.addFirst(result);
	}
	
	
	/**
	 * @param queryString
	 * @return true is query url is cached
	 */
	public boolean inCache(String queryString) {
		return cacheMap.containsKey(queryString);
	}
	
	
	/**
	 * @param queryString
	 * @return query results for the given url if present in cache, null otherwise
	 */
	public BookResults getFromCache(String queryString) {
		if(!inCache(queryString))
			return null;
		CacheEntry entry = cacheMap.get(queryString);
		updateInCache(queryString);
		return entry.getResult();
	}
}

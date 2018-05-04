package com.booksApp.servlet;

import java.util.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.booksApp.entities.Book;
import com.booksApp.entities.BookResults;
import com.booksApp.utilities.QueryCache;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;

/**
 * Servlet implementation class ItunesSearch
 */
public class ItunesSearch extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private QueryCache cache;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ItunesSearch() {
        super();
        cache = new QueryCache();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String term = request.getParameter("text");
		String media = "ebook";
		String country  = "US";
//		query string for itunes api
		String urlString="https://itunes.apple.com/search?term="+term+"&media="+media+"&country="+country;
		urlString=urlString.replaceAll("\\s+", "+");
		BookResults result;
//		maps results from api to java objects
		ObjectMapper objectMapper = new ObjectMapper();
//		check if entry exists in cache
		if(cache.inCache(urlString)) {
			result=cache.getFromCache(urlString);
		}
		else {
//			get results from itunes api
			URL url = new URL(urlString);
			HttpURLConnection conn = (HttpURLConnection)url.openConnection();  
	        conn.setRequestMethod("GET");
	        
	        if (conn.getResponseCode() != 200) {
	            throw new RuntimeException("HTTP GET Request Failed with Error code : "
	                          + conn.getResponseCode());
	        }
//	        add new results to cache
	        result = objectMapper.readValue(conn.getInputStream(), BookResults.class);
	        cache.addToCache(urlString, result);
		}
//		send response as json
		String jsonResponse = objectMapper.writeValueAsString(result);
        response.setContentType("application/json");
        response.getWriter().write(jsonResponse);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

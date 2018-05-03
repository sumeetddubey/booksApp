package com.booksApp.servlet;

import java.util.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.booksApp.entities.Book;
import com.booksApp.entities.BookResults;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;

/**
 * Servlet implementation class ItunesSearch
 */
public class ItunesSearch extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ItunesSearch() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		String term = request.getParameter("text");
		String media = "ebook";
		String country  = "US";
		URL url = new URL("https://itunes.apple.com/search?term="+term+"&media="+media+"&country="+country);  
        HttpURLConnection conn = (HttpURLConnection)url.openConnection();  
        conn.setRequestMethod("GET");
        
        if (conn.getResponseCode() != 200) {
            throw new RuntimeException("HTTP GET Request Failed with Error code : "
                          + conn.getResponseCode());
        }
//        BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
//        StringBuilder sb = new StringBuilder();
//        String output;
//        while ((output = br.readLine()) != null) {
//        	sb.append(output);
//        }
        
        ObjectMapper objectMapper = new ObjectMapper();
//        TypeFactory typeFactory = objectMapper.getTypeFactory();
//        JsonNode rootNode = objectMapper.readTree(conn.getInputStream());
//        JsonNode resultsNode = rootNode.path("results");
        BookResults o = objectMapper.readValue(conn.getInputStream(), BookResults.class);
        String jsonResponse = objectMapper.writeValueAsString(o);

        request.setAttribute("bookResults", jsonResponse);
        request.getRequestDispatcher("index.jsp").forward(request, response);
//        response.setContentType("application/json");
//        response.getWriter().write(jsonResponse);
//        List<Book> someClassList = objectMapper.readValue(resultsNode, typeFactory.constructCollectionType(List.class, Book.class));
//        for(Book book: someClassList) {
//        	System.out.println(book.getArtist());
//        }
//        response.setContentType("application/json");
//        response.getWriter().write(sb.toString());
        
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

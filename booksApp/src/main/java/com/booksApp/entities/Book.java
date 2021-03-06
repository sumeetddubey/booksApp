package com.booksApp.entities;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

// annotation for storing only relevant details from itunes api
@JsonIgnoreProperties(ignoreUnknown=true)
/**
 * @author sumeetdubey
 * Class for storing each book returned from the itunes api
 */
public class Book {
	public Book() {
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	public String getArtist() {
		return artist;
	}
	public void setArtist(String artist) {
		this.artist = artist;
	}
	public String getArtworkUrl() {
		return artworkUrl;
	}
	public void setArtworkUrl(String artworkUrl) {
		this.artworkUrl = artworkUrl;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public List<String> getGenres() {
		return genres;
	}
	public void setGenres(List<String> genres) {
		this.genres = genres;
	}
	public String getReleaseDate() {
		return releaseDate;
	}
	public void setReleaseDate(String releaseDate) {
		this.releaseDate = releaseDate;
	}
	public String getTrackViewUrl() {
		return trackViewUrl;
	}
	public void setTrackviewUrl(String trackViewUrl) {
		this.trackViewUrl = trackViewUrl;
	}
	
//	Jackson json parser annotations
	@JsonProperty("trackName")
	private String name;
	@JsonProperty("artistName")
	private String artist;
	@JsonProperty("artworkUrl100")
	private String artworkUrl;
	@JsonProperty("description")
	private String description;
	@JsonProperty("formattedPrice")
	private String price;
	@JsonProperty("genres")
	List<String> genres;
	@JsonProperty("releaseDate")
	private String releaseDate;
	@JsonProperty("trackViewUrl")
	private String trackViewUrl;
}

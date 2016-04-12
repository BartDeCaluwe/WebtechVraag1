package edu.ap.webtechvraag1;

import java.io.*;
import java.util.HashSet;
import java.util.Map;

import javax.ws.rs.*;
import javax.json.*;
import redis.clients.jedis.*;

@Path("/quotes")
public class QuoteResource {
	//SET statement:
	//HMSET quotes "Joske" "To be or not to be" "Eddy Wally" "Waauw"
	Jedis jedis;
	
	@GET
	@Produces({"text/html"})
	public String getQuotes() {
		String htmlString = "<html><body>";
		try {
			jedis = new Jedis("localhost");
			
			Map<String, String> hset = jedis.hgetAll("quotes");
			
			for(int i = 0; i < hset.size(); i++){
				htmlString += "<br>Author : " + hset.get("author") + "</br><br>";
				htmlString += "Quote : " + hset.get("quote") + "<br>";
				htmlString += "<br><br>";
			}
		} catch (Exception e) {
			htmlString = e.getMessage();
		}
		return htmlString + "</body></html>";
	}	
	
	@POST
	@Path("{author}")
	@Produces({"text/html"})
	public String getQuotesAuthor(@PathParam("author") String author) {
		String htmlString = "<html><body>";
		try {
			jedis = new Jedis("localhost");
	        
			Map<String, String> hset = jedis.hgetAll("quotes");
			for(int i = 0; i < hset.size(); i++){
				if(hset.get(author) == author){
					htmlString += "Quote : " + hset.get("quote") + "<br>";
					htmlString += "<br><br>";
				}
			}
		} 
		catch (Exception ex) {
			htmlString = ex.getMessage();
		}
		
		return htmlString;
	}
}

package edu.ap.webtechvraag1;

import java.io.*;
import java.util.HashSet;
import java.util.Map;

import javax.ws.rs.*;
import javax.json.*;
import redis.clients.jedis.*;

@Path("/products")
public class ProductResource {
	
	Jedis jedis;
	
	@GET
	@Produces({"text/html"})
	public String getQuotes() {
		String htmlString = "<html><body>";
		try {
			jedis = new Jedis("localhost");		
			jedis.hgetAll("quotes");
			
			Map<String, String> hset = jedis.hgetAll("quotes");
			
			for(int i = 0; i < hset.size(); i++){
				htmlString += "<br>Author : " + hset.get("author") + "</br><br>";
				htmlString += "ID : " + hset.get("quote") + "<br>";
				htmlString += "<br><br>";
			}
		} catch (Exception e) {
			htmlString = e.getMessage();
		}
		return htmlString + "</body></html>";
	}	
	
}

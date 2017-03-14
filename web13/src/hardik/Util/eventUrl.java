package hardik.Util;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.servlet.http.*;

import oauth.signpost.OAuthConsumer;
import oauth.signpost.basic.DefaultOAuthConsumer;

public class eventUrl implements eventUrlInterface {
	public String getEventUrl(HttpServletRequest request, HttpServletResponse response) throws IOException {

		response.setContentType("application/json");
	    String eventUrl= (String) request.getParameter("eventUrl");
	    return eventUrl;
	    
	}
	
	public HttpURLConnection oathConnection(String eventUrl) throws Exception {
		
		OAuthConsumer consumer = new DefaultOAuthConsumer("growhealth-152806", "CiDfzbHZy4aTKLl0");
        URL url = new URL(eventUrl);
        HttpURLConnection req = (HttpURLConnection) url.openConnection();
        req.setRequestProperty("Accept", "application/json");
        consumer.sign(req);
        return req;
	}
	
	public String readJson(HttpURLConnection req) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(req.getInputStream())); // get JSON containing information about an event
	    String line = br.readLine();
	    br.close();
	    return line;
	}
	
}

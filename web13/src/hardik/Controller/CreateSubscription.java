package hardik.Controller;
import hardik.Util.*;
import java.io.*;
import java.net.*;
import org.codehaus.jackson.annotate.JsonAutoDetect.Visibility;
import org.codehaus.jackson.annotate.JsonMethod;
import org.codehaus.jackson.map.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import org.codehaus.jackson.map.ObjectMapper;

import hardik.Model.ResponseSuccess;
import hardik.Service.createSubscriptionService;
import hardik.beans.*;
import oauth.signpost.*;
import oauth.signpost.basic.DefaultOAuthConsumer;

@WebServlet("/CreateSubscription")

public class CreateSubscription extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		   PrintWriter out = response.getWriter();
	       String eventUrl = new eventUrl().getEventUrl(request, response);
	       
	       ObjectMapper mapper = new ObjectMapper();
	       mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
	       
	       response.setStatus(200);
	       ResponseSuccess responseObject=null;     
	       try{    	   
	            OAuthConsumer consumer = new DefaultOAuthConsumer("growhealth-152806", "6ySQmVPVFC2q");
	            URL url = new URL(eventUrl);
	            HttpURLConnection req = (HttpURLConnection) url.openConnection();
	            req.setRequestProperty("Accept", "application/json");
	            consumer.sign(req);
	            req.connect();        	   
               	                    
	            BufferedReader br = new BufferedReader(new InputStreamReader(req.getInputStream()));
	            String line = br.readLine();
	            br.close();
	            
	            MainClass mainClass = mapper.readValue(line,MainClass.class);           
	            createSubscriptionService css = new createSubscriptionService();
	            Boolean result = css.createService(mainClass);
	            
	            if(result){
	                responseObject = new ResponseSuccess(true,"identifier-account");
	            }else{
	                responseObject = new ResponseSuccess(false,"identifier-account");
	            }   
	            
	            String json = mapper.writeValueAsString(responseObject);   
	            out.print(json);
	            out.flush();
	       }catch(Exception e){
	           e.printStackTrace();
	       }
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}
}

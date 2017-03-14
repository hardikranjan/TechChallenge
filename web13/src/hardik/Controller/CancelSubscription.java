package hardik.Controller;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;

import hardik.Model.ResponseSuccess;
import hardik.Service.cancelSubscriptionService;
import hardik.Service.createSubscriptionService;
import hardik.Util.eventUrl;
import hardik.beans.MainClass;
import oauth.signpost.OAuthConsumer;
import oauth.signpost.basic.DefaultOAuthConsumer;

@WebServlet("/CancelSubscription")
public class CancelSubscription extends HttpServlet {
	private static final long serialVersionUID = 1L;    
    public CancelSubscription() {
        super();
    }
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		PrintWriter out = response.getWriter();
	    String eventUrl = new eventUrl().getEventUrl(request, response);
	       
	    ObjectMapper mapper = new ObjectMapper();
	    mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
	    response.setStatus(200);
	    ResponseSuccess responseObject=null;  
		try{
			OAuthConsumer consumer = new DefaultOAuthConsumer("growhealth-152806", "6ySQmVPVFC2q"); //URL Hitting    
		
			URL url = new URL(eventUrl); //HTTP get request send to URL
			HttpURLConnection req = (HttpURLConnection) url.openConnection();	    
			req.setRequestProperty("Accept", "application/json");
			try{
				consumer.sign(req);
			}catch(Exception e){
				e.printStackTrace();
			}
			req.connect();
	    String line = new eventUrl().readJson(req);
	    MainClass mainClass = mapper.readValue(line,MainClass.class);
	    cancelSubscriptionService css = new cancelSubscriptionService();	    
	    ResponseSuccess result = css.cancelService(mainClass);
        if(result!=null){
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

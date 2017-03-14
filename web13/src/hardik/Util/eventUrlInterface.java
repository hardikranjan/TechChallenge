package hardik.Util;

import java.io.IOException;
import java.net.HttpURLConnection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface eventUrlInterface {
	public String getEventUrl(HttpServletRequest request, HttpServletResponse response) throws IOException;
	public HttpURLConnection oathConnection(String eventUrl) throws Exception;
	public String readJson(HttpURLConnection req) throws IOException;
}

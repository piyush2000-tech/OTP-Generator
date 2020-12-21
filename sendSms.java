import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.*;
import java.net.*;
 
public class sendSms {
	public String sendSms() {
		
		try {
			// Construct data
			String apiKey = "apikey=" + "PrrZB41DBZc-P8q9TeFyHwFmdHTN7Jb2U8DCsV92zT";
			String message = "&message=" + "Hey! GUNJAN SHRIMALI Congratulations... You are memeber Of Java Developer team and please verify your no. this link: https://DevelopersJava.com Founded by Piyush";
			String sender = "&sender=" + "TXTLCL";
			String numbers = "&numbers=" + "917982723420";
			
			// Send data
			HttpURLConnection conn = (HttpURLConnection) new URL("https://api.textlocal.in/send/?").openConnection();
			String data = apiKey + numbers + message + sender;
			conn.setDoOutput(true);
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Length", Integer.toString(data.length()));
			conn.getOutputStream().write(data.getBytes("UTF-8"));
			final BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			final StringBuffer stringBuffer = new StringBuffer();
			String line;
			while ((line = rd.readLine()) != null) {
				stringBuffer.append(line);
			}
			rd.close();
			System.out.println("Message send successfully");
			return stringBuffer.toString();
			
		} catch (Exception e) {
			System.out.println("Error SMS "+e);
			return "Error "+e;
		}
		
	}
	public static void main(String args[])
	{
			new sendSms();
	}
}
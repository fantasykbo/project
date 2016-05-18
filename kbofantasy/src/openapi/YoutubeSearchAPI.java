package openapi;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.ParseException;

public class YoutubeSearchAPI {
	
	
	public String getJSON(String teamname, String pagetoken) throws UnsupportedEncodingException{

		String key = "AIzaSyBxKEEJ5vrVJoh0oY2iAjazjG_b3F0sN2I";
		String query = URLEncoder.encode(teamname,"utf-8");
		
		String url = "https://www.googleapis.com/youtube/v3/search?"
				+ "part=snippet"
				+ "&channelId=UCtm_QoN2SIxwCE-59shX7Qg"
				+ "&maxResults=6"
				+ "&order=date" 
				+ "&pageToken="+pagetoken
				+ "&q="+query
				+ "&key="+key;
		
		URL openurl;
		InputStreamReader isr;
		JSONObject object;
		String myjson="";
		
		try {
			openurl = new URL(url);
			isr = new InputStreamReader(openurl.openConnection().getInputStream(),"UTF-8");
			object = (JSONObject)JSONValue.parseWithException(isr);
			myjson = object.toJSONString();
			
		}catch (MalformedURLException e) {
			e.printStackTrace();
		}catch (IOException e) {
			e.printStackTrace();
		}catch (ParseException e) {
			e.printStackTrace();
		}
		return myjson;
	}

}

package openapi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;

public class NaverSearchAPI {
	
	String query;

	public NaverSearchAPI(String query) {
		this.query = query;
	}
	
	public String getXML() throws ClientProtocolException, IOException{
		
		String clientId = "kM3z_TTF70jgaTvGxtrV";
		String clientSecret = "v4LXgoU_CL";
		String url = "https://openapi.naver.com/v1/search/news.xml?query="+ query +"&display=100&start=1&sort=date";
	    
		/*요청처리*/
	    HttpClient client = HttpClientBuilder.create().build();//요청주체
	    HttpGet req = new HttpGet(url);//reqest객체
	    req.addHeader("X-Naver-Client-Id",clientId);
	    req.addHeader("X-Naver-Client-Secret",clientSecret);
	    
	    /*요청*/
	    HttpResponse res= client.execute(req);//execute의  리스펀스값을 HttpResponse객체에 할당 

	    /*HttpResponse 객체의 컨텐츠 읽어오기*/ 
	    HttpEntity entity = res.getEntity();
	    InputStream is = entity.getContent(); 
	    BufferedReader reader = new BufferedReader(new InputStreamReader(is,"UTF-8"));
	    StringBuilder sb = new StringBuilder();
	    String line = null;
	    
	    try{
	       while((line = reader.readLine())!= null){
	          sb.append(line+"\n");
	       }
	    }catch(Exception e){
	       sb = new StringBuilder();
	       sb.append("<item><message>에러</message></item>");
	    }
	    
	    String xml = sb.toString();
		
		return xml;
		
	}
	

}

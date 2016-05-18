package real.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import openapi.YoutubeSearchAPI;

@WebServlet(name = "videosearch", urlPatterns = { "/videosearch.do" })
public class VideoServlet extends HttpServlet {
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
		doGet(request,response);
	}
	public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
		
		request.setCharacterEncoding("euc-kr");
		response.setContentType("text/html;charset=euc-kr");
		PrintWriter pw = response.getWriter();
		
		String teamname = (String)request.getParameter("teamname");
		String pagetoken =(String)request.getParameter("pagetoken");
		
		if(teamname.equals("전체보기")){
			teamname="kbo리그";
		}
		teamname = teamname.replaceAll("\\p{Space}", "");

		
		if(pagetoken == null){
			pagetoken="";
		}
		
		YoutubeSearchAPI api = new YoutubeSearchAPI();
		String myjson = api.getJSON(teamname, pagetoken);
		
		pw.print(myjson);
		
	}
}

package real.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import real.dto.NewsDTO;
import real.parser.XMLparser;

@WebServlet(name = "ajaxnewssearch", urlPatterns = { "/ajaxnewssearch.do" })
public class AjaxNewsServlet extends HttpServlet {
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
		doGet(request,response);
	}
	public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
		
		request.setCharacterEncoding("euc-kr");
		response.setContentType("text/html;charset=euc-kr");
		PrintWriter pw = response.getWriter();
		
		String end =(String)request.getParameter("end");
		String teamname =(String)request.getParameter("teamname");
		int e = Integer.parseInt(end);
		if(e <100){
			XMLparser parser = new XMLparser();
			ArrayList<NewsDTO> news = parser.getdata(teamname);
			
			for(int i=0;i<e;i++){
				NewsDTO n = news.get(i);
				String[] pubdate = n.getPubdate();
				pw.print("<li class='media'>"
						+ "<a href='#' class='pull-left'>"
						+ "<img class='media-object'"
						+ " src='http://pingendo.github.io/pingendo-bootstrap/assets/placeholder.png'"
						+ " height='64' width='64'></a><div class='media-body'><h4 class='media-heading'>"
						+ "<a href='"+n.getLink()+"'>"+n.getTitle()+"</a>&nbsp;"
						+ "<small>"+pubdate[0]+"년"+ pubdate[1]+"월 "+pubdate[2]+"일</small></h4><p>"
						+ n.getDescription()+"</p></div></li>");
			}
		}else{
			XMLparser parser = new XMLparser();
			ArrayList<NewsDTO> news = parser.getdata(teamname);
			
			for(int i=0;i<100;i++){
				NewsDTO n = news.get(i);
				String[] pubdate = n.getPubdate();
				pw.print("<li class='media'>"
						+ "<a href='#' class='pull-left'>"
						+ "<img class='media-object'"
						+ " src='http://pingendo.github.io/pingendo-bootstrap/assets/placeholder.png'"
						+ " height='64' width='64'></a><div class='media-body'><h4 class='media-heading'>"
						+ "<a href='"+n.getLink()+"'>"+n.getTitle()+"</a>&nbsp;"
						+ "<small>"+pubdate[0]+"년"+ pubdate[1]+"월 "+pubdate[2]+"일</small></h4><p>"
						+ n.getDescription()+"</p></div></li>");
			}
			pw.println();
			pw.print("모든 뉴스를 반환하였습니다.");
			
		}

	
	}

}

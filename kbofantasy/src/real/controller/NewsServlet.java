package real.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import real.dto.NewsDTO;
import real.parser.XMLparser;

@WebServlet(name = "newssearch", urlPatterns = { "/newssearch.do" })
public class NewsServlet extends HttpServlet {
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
		doGet(request,response);
	}
	public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
		
		request.setCharacterEncoding("euc-kr");
		String teamname = (String)request.getParameter("teamname");
		
		XMLparser parser = new XMLparser();
		ArrayList<NewsDTO> news = parser.getdata(teamname);
		
		request.setAttribute("news", news);
		request.setAttribute("teamname", teamname);
		RequestDispatcher rd= request.getRequestDispatcher("/real/news.jsp");
		rd.forward(request, response);
		
	}

}

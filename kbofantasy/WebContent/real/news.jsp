<%@page import="java.util.ArrayList"%>
<%@page import="real.dto.*"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<script type="text/javascript"
		src="http://cdnjs.cloudflare.com/ajax/libs/jquery/2.0.3/jquery.min.js"></script>
	<script type="text/javascript"
		src="http://netdna.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
	<link
		href="http://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.3.0/css/font-awesome.min.css"
		rel="stylesheet" type="text/css">
	<link
		href="http://pingendo.github.io/pingendo-bootstrap/themes/default/bootstrap.css"
		rel="stylesheet" type="text/css">
		
	<% 
		ArrayList<NewsDTO> news = (ArrayList<NewsDTO>)request.getAttribute("news");
		String teamname= (String)request.getParameter("teamname");

		int size = news.size();
		int view = 0;
	%>
	
	<script type="text/javascript">
	$(document).ready(function() {
		end = 0;
		count = 0;
		$("#addcontent").on("click",function() {
				count = count+1;
				end = count*10;
				getNews(end);

			
		});
		function getNews(end) {
			s = end.toString();
			teamname='<%=teamname%>';

			var xhr = new XMLHttpRequest();
			mydiv = document.getElementById("content");
			xhr.onreadystatechange = function() {
				if (xhr.readyState == 4 && xhr.status == 200) {
					mydiv.innerHTML = xhr.responseText;
				}
			}
			xhr.open("GET", "/kbofantasy/ajaxnewssearch.do?end="+s+"&teamname="+teamname, true);
			xhr.send();

		}

	});
</script>
	</head>
	<body>
		<div class="section">
			<div class="container">
				<div class="row">
					<div class="col-md-12">
						<div class="page-header text-primary">
							<h1>
								뉴스센터 <small><%=teamname %></small>
							</h1>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-md-10">
						<ul id="content" class="media-list">
						<%for(int i=0; i<10;i++){
							NewsDTO obj = news.get(i);
							String[] pubdate = obj.getPubdate();%>
							<li class="media"><a href="#" class="pull-left"><img
									class="media-object"
									src="http://pingendo.github.io/pingendo-bootstrap/assets/placeholder.png"
									height="64" width="64"></a>
								<div class="media-body">
									<h4 class="media-heading">
										<a href="<%=obj.getLink()%>"><%=obj.getTitle()%></a>&nbsp; <small><%=pubdate[0]%>년 <%=pubdate[1]%>월 <%=pubdate[2] %>일</small>
									</h4>
									<p><%=obj.getDescription() %></p>
								</div></li>
							<%} %>
							<div id="content"></div>
						</ul>
						<div class="row">
							<div class="col-md-12 text-center">
								<ul class="pagination">
									<li id="addcontent">더보기</li>
								</ul>
							</div>
						</div>
					</div>
					<div class="col-md-2">
						<ul class="list-group text-center">
							<li class="list-group-item">
								<a href="/kbofantasy/newssearch.do?teamname=kbo리그">전체보기</a></li>
							<li class="list-group-item">
								<a href="/kbofantasy/newssearch.do?teamname=두산베어스">두산 베어스</a></li>
				
							<li class="list-group-item">
								<a href="/kbofantasy/newssearch.do?teamname=삼성라이온즈">삼성 라이온즈</a></li>
						
							<li class="list-group-item">
								<a href="/kbofantasy/newssearch.do?teamname=NC다이노스">NC 다이노스</a></li>
							<li class="list-group-item">
							<a href="/kbofantasy/newssearch.do?teamname=넥센히어로즈">넥센 히어로즈</a></li>
							<li class="list-group-item">
							<a href="/kbofantasy/newssearch.do?teamname=SK와이번스">SK 와이번스</a></li>
							<li class="list-group-item">
							<a href="/kbofantasy/newssearch.do?teamname=한화이글스">한화 이글스</a></li>
							<li class="list-group-item">
							<a href="/kbofantasy/newssearch.do?teamname=KIA타이거즈">KIA 타이거즈</a></li>
							<li class="list-group-item">
							<a href="/kbofantasy/newssearch.do?teamname=롯데자이언츠">롯데 자이언츠</a></li>
							<li class="list-group-item">
							<a href="/kbofantasy/newssearch.do?teamname=LG트윈스">LG 트윈스</a></li>
							<li class="list-group-item">
							<a href="/kbofantasy/newssearch.do?teamname=kt위즈">kt 위즈</a></li>
						</ul>
					</div>
				</div>
			</div>
		</div>
	
	
	</body>
</html>
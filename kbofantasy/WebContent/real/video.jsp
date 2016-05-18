<%@page import="openapi.YoutubeSearchAPI"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
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

		<script type="text/javascript">
		
		$(document).ready(function() {
			content='';
			teamname='kbo리그';
			pagetoken='';
			getVideo(teamname,pagetoken);
			
			$("#addcontent").click(function() {
				getVideo(teamname, pagetoken);
			});
			
			$(".list-group-item").click(function() {
				content="";
				teamname = $(this).text();
				document.getElementById("title").innerHTML= teamname;
				pagetoken="";	//초기화
				getVideo(teamname, pagetoken);
			});
	
			function getVideo(teamname, pagetoken) {
	
				var xhr = new XMLHttpRequest();
				
				xhr.onreadystatechange = function() {
					if (xhr.readyState == 4 && xhr.status == 200) {
						var myjson = xhr.responseText;
						jsonparser(myjson);
					}
				}
				xhr.open("GET", "/kbofantasy/videosearch.do?teamname=" +teamname+"&pagetoken="+pagetoken, true);
				xhr.send();
	
			}
			
			function jsonparser(myjson){
				
				var data= JSON.parse(myjson);
				var videoid;	
				var publishedAt;
				var title;
				var description;
				var images;
				
				pagetoken = data.nextPageToken;
				
				for(i=0;i<6;i++){
					videoid = data.items[i].id.videoId;
					publishedAt = data.items[i].snippet.publishedAt;
					title= data.items[i].snippet.title;
					description = data.items[i].snippet.description;
					images = data.items[i].snippet.thumbnails.medium.url;
					
					str = "<div class='col-md-4'><div class='thumbnail'><img src='"+images+"' class='img-responsive'>"
					+"<div class='caption'><a href='https://www.youtube.com/watch?v="+videoid+"'><h5>"+title+"</h5></a><p class='text-muted'>"+publishedAt+"</p></div></div></div>";
					
					content = content + str;
					

				}
				printpage(content);
				
			}
			
			function printpage(content){
				document.getElementById("content").innerHTML = content;
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
								영상센터 <small id="title">전체보기</small>
							</h1>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-md-10">
						<div id="content"></div>
						<div class="row">
							<div class="col-md-12">
								<ul class="pager">
									<li id="addcontent">더보기</li>
								</ul>
							</div>
						</div>
					</div>
					<div class="col-md-2">
						<ul class="list-group text-center">
							<li class="list-group-item">전체보기</li>
							<li class="list-group-item">두산 베어스</li>
							<li class="list-group-item">삼성 라이온즈</li>
							<li class="list-group-item">NC 다이노스</li>
							<li class="list-group-item">넥센 히어로즈</li>
							<li class="list-group-item">SK 와이번스</li>
							<li class="list-group-item">한화 이글스</li>
							<li class="list-group-item">KIA 타이거즈</li>
							<li class="list-group-item">롯데 자이언츠</li>
							<li class="list-group-item">LG 트윈스</li>
							<li class="list-group-item">kt 위즈</li>
						</ul>
					</div>
				</div>
			</div>
		</div>
	
	
	</body>
</html>
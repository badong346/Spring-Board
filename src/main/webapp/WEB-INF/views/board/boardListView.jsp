<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="C"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
<script src="https://code.jquery.com/jquery-3.5.1.js"></script>
<script
	src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"></script>
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>


<style>
#p_board {
	height: 600px;
	margin: auto;
	overflow-y: auto;
	border: 1px solid #dddddd;
}

#p_board::-webkit-scrollbar {
	
}

.article {
	float: left;
	height: 150px;
	width: 100%;
	border: 1px solid #dddddd;
	padding: 15px;
}

.specialty {
	float: left;
	width: 80%;
	height: 20%;
	text-align: left;
	line-height: 15px;
	font-size: 8pt;
	margin-left: 10px;
}

.title {
	float: left;
	width: 80%;
	height: 40%;
	text-align: left;
	line-height: 50px;
	margin-left: 10px;
	overflow: hidden;
}

.name {
	float: left;
	width: 80%;
	height: 20%;
	text-align: left;
	line-height: 25px;
	text-size: 25px;
	margin-left: 10px;
	font-size: 14px;
}

.phone {
	float: left;
	width: 80%;
	height: 20%;
	text-align: left;
	line-height: 25px;
	margin-left: 10px;
	font-size: 14px;
}

.profileImg {
	float: right;
	width: 120px;
	height: 90%;
	position: relative;
	bottom: 100px;
}

.thumbnail {
	border-radius: 70%;
	position: relative;
	right: 10px;
	padding-top: 100%;
	overflow: hidden;
}

.thumbnail .centered {
	position: absolute;
	top: 0;
	left: 0;
	right: 0;
	bottom: 0;
	transform: translate(50%, 50%);
}

.thumbnail .centered img {
	max-width: 100%;
	height: 100%;
	object-fit: cover;
	transform: translate(-50%, -50%);
}

input[type=button], input[type=submit] {
	border: 1px;
	border-radius: 4px;
	background-color: #616161;
	color: #f1f1f1;
	height: 25px;
	margin-top: 20px;
}

.name a {
	color: black;
	text-decoration: none;
}

.name a:hover {
	color: #f9a5a5;
}
</style>

<script>
	var pageNo = 1;
	$(function() {
		$.ajax({
			url : "boardListAjax",
			data : {
				cpage : pageNo++
			},
			dataType : "json"
		})
				.done(
						function(resp) {
							console.log(resp);
							for (var i = 0; i < resp.length; i++) {

								console.log(resp[i].seq);

								var line = $("<div>");
								line.addClass("card")

								var category = $("<div>");
								category.addClass("card-header");
								category.append("<b>[" + resp[i].category
										+ "]</b>");

								var title = $("<div>");
								title.addClass("card-body");
								title.append("<a href='toBoardContents?seq="
										+ resp[i].seq + "'><b>" + resp[i].title + "</b></a>");

								var blockquote_footer = $("<div>");
								blockquote_footer.addClass("blockquote-footer");
								blockquote_footer.append("<p>작성자 : "
										+ resp[i].writer +" &brvbar; 조회수 : " + resp[i].view_count
										+  " &brvbar; 작성일 : " + resp[i].write_date + "</p>");

					
						

								line.append(category);
								line.append(title);
								line.append(blockquote_footer);
				

								$("#p_board").append(line);
							}
						});

		$("#q_menu_btn").on("click", function() {
			$("#quickmenu").css("transform", "translate(100%, 0px)");
		})
		$("#q_back").on("click", function() {
			$(this).parent().css("transform", "translate(-100%, 0px)");
			$(this).parent().css("transition-duration", "1s");
		})

		$("#p_board").on("scroll", function() {
			var board = document.getElementById("p_board");
			if (board.offsetHeight + board.scrollTop > board.scrollHeight) {
				loadData();
			}
		})
		function loadData() {
			$
					.ajax({
						url : "boardListAjax",
						data : {
							cpage : pageNo++
						},
						dataType : "json"
					})
					.done(
							function(resp) {
								console.log(resp);
								for (var i = 0; i < resp.length; i++) {

									console.log("게시판 리스트 스크롤"+resp[i].seq);

									var line = $("<div>");
									line.addClass("card")

									var category = $("<div>");
									category.addClass("card-header");
									category.append("<b>[" + resp[i].category
											+ "]</b>");

									var title = $("<div>");
									title.addClass("card-body");
									title.append("<a href='toBoardContents?seq="
											+ resp[i].seq + "'><b>" + resp[i].title + "</b></a>");

									var blockquote_footer = $("<div>");
									blockquote_footer.addClass("blockquote-footer");
									blockquote_footer.append("<p>작성자 : "
											+ resp[i].writer +" &brvbar; 조회수 : " + resp[i].view_count
											+  " &brvbar; 작성일 : " + resp[i].write_date + "</p>");

						
							

									line.append(category);
									line.append(title);
									line.append(blockquote_footer);
					

									$("#p_board").append(line);
								}
							});

		}

		$("#boardwrite").on("click", function() {
			location.href = "toBoardWrite";
		})

	})
</script>

</head>
<body>
	<div class=container>
		<div class=contents>
			<h2 class="text-left">게시판</h2>
			<h3 class="text-left">대나무숲.</h3>
			<br>
			<c:choose>
				<c:when test="${not empty sessionScope.loginInfo}">
					<div class="col-12" align=right>
						<input type=button id=boardwrite value="글작성">
					</div>
					<br>
				</c:when>
			</c:choose>

			<div id="p_board"></div>

		</div>
		<!-- contents 영역 끝 -->

	</div>
	<!--container 끝-->
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="C"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
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
.chat-box {
	height: 500px;
	background-color: white;
}

.message-area {
	width: 100%;
	height: 90%;
	border: 1px solid #616161;
	overflow-y: auto;
	word-break: break-all;
}

.input-area {
	width: 100%;
	height: 10%;
	border: 1px solid #616161;
	overflow-y: auto;
}

img {
	width: 70%;
	height: auto;
}
</style>
<script>
	$(function() {

		var ws = new WebSocket("ws://192.168.60.7/chat")

		ws.onmessage = function(e) {
			console.log(e.data);
			var line = $("<div>");
			line.append(e.data);
			$(".message-area").append(line);
		}

		$(".input-area").on(
				"keydown",
				function(e) {
					if (e.keyCode == 13) {

						e.preventDefault();
						var text = $(".input-area").html();
						var line = $("<div>");
						line.append('${loginInfo.name}' + " (나) : " + text);
						$(".message-area").append(line);
						$(".input-area").html("");
						ws.send(text);
						$('.message-area').scrollTop(
								$('.message-area')[0].scrollHeight);
						
						return false;
					}
				})

	})
</script>
</head>
<body>


	<form action=member/auth.do method="post">

		<input type="text" name="e_mail" placeholder="Input your email">
		<br>

		<button type="submit" id="toEmailSingup">이메일회원가입</button>

	</form>



	<div class="jumbotron">

		<C:choose>
			<C:when test="${empty loginInfo}">
				<h1 class="display-4">Spring MVC 게시판</h1>
				<p class="lead">로그인을 해주셔야 채팅 및 게시판 기능을 이용할 수 있습니다. (임시 아이디/비밀번호
					: temp / temp)</p>
				<img src="/resources/img/라이언.jpg">
				<br>
				<hr class="my-4">

				<div class="btn-group" role="group" aria-label="Basic example">
					<button type="button" class="btn btn-secondary" data-toggle="modal"
						data-target="#exampleModal">로그인</button>
					<button type="button" class="btn btn-secondary" id="toBoard"
						disabled>게시판</button>
				</div>
			</C:when>

			<C:otherwise>
				<p class="lead">${loginInfo.name}님환영합니다!</p>
				<div class="chat-box">
					<div class="message-area"></div>
					<div class="input-area" contenteditable="true"></div>
				</div>
				<hr class="my-4">
				<div class="btn-group" role="group" aria-label="Basic example">
					<button type="button" class="btn btn-secondary" id="logout">로그아웃</button>
					<button type="button" class="btn btn-secondary" id="toBoard">게시판</button>
					<button type="button" class="btn btn-secondary" id="toMypage">마이페이지</button>
					<button type="button" class="btn btn-secondary" disabled>관리자</button>
				</div>
				<br>

				<script>
					$("#toBoard").on("click", function() {
						location.href = "board/toBoard";
					})
					$("#logout").on("click", function() {
						location.href = "member/logout";
					})
					$("#toMypage").on("click", function() {
						location.href = "member/toMypage";
					})
				</script>
			</C:otherwise>
		</C:choose>



	</div>









	<div class="modal fade" id="exampleModal" tabindex="-1" role="dialog"
		aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="exampleModalLabel">LOGIN</h5>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>

				<form action=member/login method="post">
					<div class="modal-body">
						<table align=center style="text-align: center">

							<tr>
								<td><input type="text" name="id"
									placeholder="Input your ID"></td>
							</tr>
							<tr>
								<td><input type="password" name="pw"
									placeholder="Input your PW"></td>
							</tr>
							<br>
							<tr>
								<td><input type="checkbox"> Remember my ID</td>
							</tr>
						</table>

					</div>
					<div class="modal-footer">
						<button type="submit" id="login" class="btn btn-secondary">로그인</button>
						<button type="button" id="signup" class="btn btn-secondary">회원가입</button>

					</div>
					<script>
						$("#signup").on("click", function() {
							location.href = "member/toSignup"
						})
					</script>
				</form>
			</div>
		</div>
	</div>

</body>
</html>
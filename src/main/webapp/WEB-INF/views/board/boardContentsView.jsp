<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="C"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
	integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh"
	crossorigin="anonymous">
<script src="https://code.jquery.com/jquery-3.5.1.js"></script>
<script
	src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
	integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo"
	crossorigin="anonymous"></script>
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"
	integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6"
	crossorigin="anonymous"></script>
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/animate.css/3.7.2/animate.min.css">
<script src="https://kit.fontawesome.com/a207991562.js"
	crossorigin="anonymous"></script>
</head>
<style>
.container {
	background-color: white;
}

body {
	background-color: #fafafa;
}

.footer {
	background-color: white;
}

.board-info {
	height: 60px;
	line-height: 60px;
	background-color: #868e96;
}

.page-link {
	color: #616161;
} /* 색상 설정 */
.btn {
	height: 40px;
}

.table-wrapper>.row>div {
	padding-left: 0px;
	padding-right: 0px;
}

#box1 {
	width: 100%;
	height: 200px;
	border: 2px solid #e8e8e8;
	float: left;
	overflow-y: auto;
}

#writeCmt>.row {
	background-color: white;
}

.contents div {
	padding-top: 10px;
}

.comments {
	padding: 10px;
	background-color: #e8e8e8;
} /*변호사 comments 영역*/
.comments_contents {
	border-bottom: 1px solid #dddddd;
}

.comments_contents>.row>div {
	background-color: #fafafa;
}

.comments_contents>div {
	padding-top: 10px;
}

.all>div {
	padding-top: 10px;
}

#req_category {
	font-size: 8pt;
}

#req_title>a {
	font-size: 16pt;
	text-decoration: none;
	color: black;
}

#req_contents {
	font-size: 12pt;
	padding: 30px;
}

#req_date {
	font-size: 10pt;
}
</style>
<script>
            $(function(){
        		$("#back").on("click", function() {
					location.href = "toBoard";
        		})
            })
</script>
<body>

	<div class="container">
		<div class=contents>
			<div class="row all">
				<div class="col-12 text-left" id="req_category">${bdtoList[0].category}</div>
				<div class="col-12 text-left" id="req_title">
					<a href="#" class="logo">${bdtoList[0].title}</a>
				</div>
				
				<div class="col-12 text-left" id="req_date">작성자 : ${bdtoList[0].writer} (${bdto[0].ip_address})</div>
				<div class="col-12 text-left" id="req_contents">
					${bdtoList[0].contents}</div>
					
					
				<div class="col-12 text-left" id="req_date">${bdtoList[0].sdate} 작성됨</div>
				
				<div class="col-12 text-left">
				<hr>
				<C:if test="${!empty fileList}">
				<br>
				<p>파일 목록</p>
					<ul>
						<C:forEach var="i" items="${fileList}">
							<li><a href='downLoadFile?seq=${i.seq}'>${i.original_file_name}</a></li>
						</C:forEach>
					</ul>
				</C:if>
			</div>
				
				
				<div class="col-12 text-right">
					<button type=button class="btn btn-secondary" id=back>
						<i class="fas fa-arrow-left"></i>
					</button>
					<C:choose>
						<C:when test="${logInfo.id == bdtoList[0].id}">
							<button type="button" class="btn btn-secondary" id="delete">
								<i class="fas fa-trash-alt"></i> 글 삭제
							</button>
							<button type="button" class="btn btn-secondary" id="modify">
								</i> 글 수정
							</button>
						</C:when>
					</C:choose>
				</div>
				<div class="col-12 text-left board-info">조회수
					${bdtoList[0].view_count}</div>
			</div>
			<div class="row comments">
				<div class="col-12 text-left">댓글 ${list.size()}개</div>
			</div>
			<C:choose>
				<C:when test="${empty list}">
					<div class="row">
						<div class="col-12">댓글 없음</div>
					</div>
				</C:when>
				<C:otherwise>
					<C:forEach var="cmt" items="${list}">
						<div class="row comments_contents">
							<div class="col-12 text-left">${cmt.writer}</div>
							<div class="col-12 text-left">${cmt.contents}</div>
							<div class="col-12 text-right">${cmt.sdate}작성됨</div>
						</div>
					</C:forEach>
				</C:otherwise>
			</C:choose>





			<!-- 코멘트 -->
			<form action="writeComments?seq=${bdtoList[0].seq}" method="post"
				id="writeCmt">
				<div class="row">
					<div class="col-12">
						<div contenteditable="true" id="box1"></div>
						<textarea id="sendcmt" style="display: none;" name=contents></textarea>
					</div>
					<div class="col-12">
						<button type="submit" class="btn btn-secondary">댓글 쓰기</button>
					</div>
				</div>
			</form>
		</div>
	</div>

</body>
</html>
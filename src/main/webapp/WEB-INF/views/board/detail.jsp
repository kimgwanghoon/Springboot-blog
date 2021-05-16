<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<body>
	<!-- 머리글 -->
	<%@ include file="../main/header.jsp" %> 
	<%-- <jsp:include page="../main/header.jsp" /> --%>
	<script>
		
	</script>
	<div class="container">
		<button id="btn-boardInsert" class="btn btn-secondary" onclick="history.back()">돌아가기</button>
		<br> <br>
		<div>
			<label for="title"><h3>Title : ${detail.title}</h3></label><br>
			<div>
				<h6>
					글번호 : <span id="id">${detail.id}</span>&emsp; 
					작성자 : <span id="username">${detail.user.username}</span>&emsp; 
					작성일 : <span id="createDate">${detail.createDate}</span>
				</h6>
			</div>
		</div>
		<hr />
		<div>
			<div>
				<c:out value="${detail.content}" escapeXml="false" />
			</div>
		</div>
		<hr />
			<c:if test="${principal.user.id == detail.user.id}">
				<a href="/board/${detail.id}/updateForm" class="btn btn-warning">글 수정</a>
				<button id="btn-delete" class="btn btn-danger">글 삭제</button>
			</c:if>

	</div>
	<br />
	<!-- 바닥글 -->
	<jsp:include page="../main/footer.jsp" />
	<script src="/js/board.js"></script>
</body>




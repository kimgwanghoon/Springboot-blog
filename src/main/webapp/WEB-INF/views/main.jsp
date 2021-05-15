<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<body>
	<!-- 머리글 -->
	<%@ include file="main/header.jsp" %> 
	<%-- <jsp:include page="main/header.jsp" /> --%>
	<div class="container">
		<c:forEach var="data" items="${list.content}">
			<div class="card m-2">
				<div class="card-body">
					<h4 class="card-title">${data.title}</h4>
					<a href="/board/${data.id}" class="btn btn-primary">상세보기</a>
				</div>
			</div>
		</c:forEach>
		<ul class="pagination justify-content-center">
			<c:choose>
				<c:when test="${list.first}">
					<li class="page-item disabled"><a class="page-link" href="?page${list.number-1 }">이전</a></li>
				</c:when>
				<c:otherwise>
					<li class="page-item"><a class="page-link" href="?page${list.number-1 }">이전</a></li>
				</c:otherwise>
			</c:choose>
				<!-- <li class="page-item active"><a class="page-link" href="#">2</a></li> -->
			<c:choose>
				<c:when test="${list.last}">
					<li class="page-item disabled"><a class="page-link" href="?page=${list.number+1 }">다음</a></li>
				</c:when>
				<c:otherwise>
					<li class="page-item"><a class="page-link" href="?page=${list.number+1 }">다음</a></li>
				</c:otherwise>
			</c:choose>
		</ul>
	</div>
	<br />
	<!-- 바닥글 -->
	<jsp:include page="main/footer.jsp" />

</body>




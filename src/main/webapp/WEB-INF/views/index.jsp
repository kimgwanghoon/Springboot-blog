<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<body>
	<!-- 머리글 -->
	<%-- <%@ include file="main/header.jsp" %> --%>
	<jsp:include page="main/header.jsp" />


	<div class="container">
		<div class="card m-2">
			<div class="card-body">
				<h4 class="card-title">제목</h4>
				<a href="#" class="btn btn-primary">상세보기</a>
			</div>
		</div>
	</div>
	<br />
	<!-- 바닥글 -->
	<jsp:include page="main/footer.jsp" />

</body>




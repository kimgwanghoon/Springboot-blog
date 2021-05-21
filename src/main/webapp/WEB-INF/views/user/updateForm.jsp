<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../main/header.jsp"%>
<body>
	<div class="container">
		<form action="/user/join" method="post">
			<input type="hidden" id="id" value="${principal.user.id }"/>
			<div class="form-group">
				<label for="username">Username : </label> <input type="text" class="form-control" placeholder="Enter username" id="username" value="${principal.user.username}" readonly>
			</div>
			<div class="form-group">
				<label for="password">Password:</label> <input type="password" class="form-control" placeholder="Enter password" id="password"<c:if test="${not empty principal.user.oauth }">readonly</c:if>>
			</div>
			<div class="form-group">
				<label for="email">Email address:</label> <input type="email" class="form-control" placeholder="Enter email" id="email" value="${principal.user.email}" >
			</div>
		</form>
		<button id="bth-update" class="btn btn-primary">회원정보수정</button>
	</div>
</body>
<script src="/js/user.js"></script>
<%@ include file="../main/footer.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../main/header.jsp"%>
<body>
	<div class="container">
		<form action="/user/joinForm" >
			<div class="form-group">
				<label for="username">Username : </label> <input type="text" class="form-control"  placeholder="Enter username"  id="username">
			</div>
			<div class="form-group">
				<label for="password">Password:</label> <input type="password" class="form-control"  placeholder="Enter password" id="password">
			</div>
			<div class="form-group form-check">
				<label class="form-check-label"> <input class="form-check-input" type="checkbox"> Remember me
				</label>
			</div>
		</form>
		<button id="btn-login" class="btn btn-primary">로그인</button>
	</div>
</body>
<script src="/js/user.js"></script>
<%@ include file="../main/footer.jsp"%>
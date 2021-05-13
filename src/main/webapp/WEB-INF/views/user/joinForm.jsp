<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../main/header.jsp"%>
<body>
	<div class="container">
		<form action="/user/join" method="post">
			<div class="form-group">
				<label for="username">Username : </label> <input type="text" class="form-control"  placeholder="Enter username"  id="username">
			</div>
			<div class="form-group">
				<label for="password">Password:</label> <input type="password" class="form-control"  placeholder="Enter password" id="password">
			</div>
			<div class="form-group">
				<label for="email">Email address:</label> <input type="email" class="form-control"  placeholder="Enter email" id="email">
			</div>
		</form>
		<button id="bth-sign" class="btn btn-primary">회원가입</button>
	</div>
</body>
<script src="/js/user.js"></script>
<%@ include file="../main/footer.jsp"%>
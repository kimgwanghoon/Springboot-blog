<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../main/header.jsp"%>
<body>
	<div class="container">
		<form action="/auth/procLogin" method="post" >
			<div class="form-group">
				<label for="username">Username : </label> <input type="text" name="username" class="form-control"  placeholder="Enter username"  id="username">
			</div>
			<div class="form-group">
				<label for="password">Password:</label> <input type="password" name="password" class="form-control"  placeholder="Enter password" id="password">
			</div>
			<button id="btn-login" class="btn btn-primary">로그인</button>
		</form>		
	</div>
</body>
<%@ include file="../main/footer.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../main/header.jsp"%>
<body>
	<div class="container">
		<form action="/user/joinForm">
			<div class="form-group">
				<label for="username">Username : </label> <input type="text" class="form-control"  placeholder="Enter username"  id="username">
			</div>
			<div class="form-group">
				<label for="pwd">Password:</label> <input type="password" class="form-control"  placeholder="Enter password" id="pwd">
			</div>
			<div class="form-group form-check">
				<label class="form-check-label"> <input class="form-check-input" type="checkbox"> Remember me
				</label>
			</div>
			<button type="submit" class="btn btn-primary">로그인</button>
		</form>
	</div>
</body>
<%@ include file="../main/footer.jsp"%>
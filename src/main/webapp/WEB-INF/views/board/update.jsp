<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<body>
	<!-- 머리글 -->
	<%@ include file="../main/header.jsp"%>
	<%-- <jsp:include page="../main/header.jsp" /> --%>
	<script>

    </script>
	<div class="container">
		<form>
			<input type="hidden" id="id" value="${board.id}"/>
			<div class="form-group">
				<label for="title">Title : </label> 
				<input type="text" value="${board.title }" name="username" class="form-control" placeholder="Enter Title" id="title">
			</div>
			<div class="form-group">
				<label for="content">내용 : </label>
				<textarea class="form-control summernote" rows="5" id="content"><c:out value="${board.content }"  escapeXml="false"/></textarea>
			</div>
		</form>
		<button id="btn-update" class="btn btn-primary">글 수정</button>
	</div>
	<script>
      $('.summernote').summernote({
        tabsize: 2,
        height: 500
      });
    </script>
	<br />
	<!-- 바닥글 -->
	<script src="/js/board.js"></script>
	<jsp:include page="../main/footer.jsp" />
</body>




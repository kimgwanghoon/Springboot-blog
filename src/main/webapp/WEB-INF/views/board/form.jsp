<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<body>
	<!-- 머리글 -->
	<%@ include file="../main/header.jsp"%>
	<%-- <jsp:include page="../main/header.jsp" /> --%>
	<script>

    </script>
	<div class="container">
		<form>
			<div class="form-group">
				<label for="title">Title : </label> 
				<input type="text" name="username" class="form-control" placeholder="Enter Title" id="title">
			</div>
			<div class="form-group">
				<label for="content">내용 : </label>
				<textarea class="form-control summernote" rows="5" id="content"></textarea>
			</div>
		</form>
		<button id="btn-boardSave" class="btn btn-primary">글 등록</button>
	</div>
	<script>
      $('.summernote').summernote({
        tabsize: 2,
        height: 300
      });
    </script>
	<br />
	<!-- 바닥글 -->
	<script src="/js/board.js"></script>
	<jsp:include page="../main/footer.jsp" />
</body>




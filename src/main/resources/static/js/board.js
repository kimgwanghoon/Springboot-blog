let index = {
	init: function() {
		$("#btn-boardSave").on("click", () => {
			this.save();
		});
		$("#btn-update").on("click", () => {
			this.update();
		});
		$("#btn-delete").on("click", () => {
			this.delete();
		});
	},
	//새로운 글쓰기
	save: function() {
		let data = {
			title: $("#title").val(),
			content: $("#content").val()
		};

		$.ajax({
			type: "POST",
			url: "/api/board",
			data: JSON.stringify(data),
			contentType: "application/json;charset=UTF-8",
			dataType: "json"
		}).done(function(resp) {
			if (resp.status === 200) {
				alert("글등록이 완료되었습니다..");
			} else {
				alert("글등록이 실패되었습니다..");
			}
			location.href = "/";
		}).fail(function(error) {
			alert(JSON.stringify(error));
		});
	},
	//글삭제
	delete: function() {
		let id = $("#id").text(); 

		$.ajax({
			type: "DELETE",
			url: "/api/board/"+id,
			dataType: "json"
		}).done(function(resp) {
			if (resp.status === 200) {
				alert("글삭제 완료되었습니다..");
			} else {
				alert("글삭제 실패되었습니다..");
			}
			location.href = "/";
		}).fail(function(error) {
			alert(JSON.stringify(error));
		});
	},
	
	//글수정
	update: function() {
		let id= $("#id").val();
		let data = {
			title: $("#title").val(),
			content: $("#content").val()
		};

		$.ajax({
			type: "PUT",
			url: "/api/board/"+id,
			data: JSON.stringify(data),
			contentType: "application/json;charset=UTF-8",
			dataType: "json"
		}).done(function(resp) {
			if (resp.status === 200) {
				alert("글수정이 완료되었습니다..");
			} else {
				alert("글수정이 실패되었습니다..");
			}
			location.href = "/";
		}).fail(function(error) {
			alert(JSON.stringify(error));
		});
	}
}
index.init();
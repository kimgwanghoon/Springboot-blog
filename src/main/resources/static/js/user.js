let index = {
	//let _this=this;
	//회원가입 동작
	init: function() {
		$("#bth-sign").on("click", () => { //()=> 방식으로 호출하면 this를 바인딩, function()으로 호출 할 경우 this를 선언하지않으면 바인딩이 되지않음
			this.save();
		});
		$("#bth-update").on("click", () => {
			this.update();
		});
	},

	save: function() {
		let data = {
			username: $("#username").val(),
			password: $("#password").val(),
			email: $("#email").val()
		};

		//ajax호출시 default가 비동기 호출
		// ajax통신을 통해서 3개의 데이터를 json으로 변경, insert요청
		//ajax가 통신을 성공하고 서버가 json을 리턴해주면 자동으로 자바오브젝트로 변환 
		$.ajax({
			type: "POST",
			url: "/auth/signup",
			data: JSON.stringify(data),	//그냥 위의 data를 전달하면 js오브젝트, JSON.stringify(data)로 data전달시 JSON문자열로 전달 body데이터
			contentType: "application/json;charset=UTF-8", //body데이터 타입전달
			dataType: "json"	//응답의 결과가 왔을때 기본적으로 문자열로 들어온다.(생긴게 json형태라면 ->javascript오브젝트로 변환)
		}).done(function(resp) {	//응답결과가 정상이면 실행
			if (resp.status === 200) {	//전달받은 HttpStatus 응답결과가 200(성공)이면 메인페이지로 이동 
				alert("회원가입이 완료되었습니다.");
				location.href = "/";
			} else {
				alert("회원가입에 실패하였습니다.");
				location.href = "joinForm";
			}
		}).fail(function(error) {	//응답결과가 실패하면 실행
			alert(JSON.stringify(error));
		});
	},

	update: function() {
		let data = {
			id:$("#id").val(),
			username: $("#username").val(),
			password: $("#password").val(),
			email: $("#email").val()
		};
		$.ajax({
			type: "PUT",
			url: "/user/update",
			data: JSON.stringify(data),	
			contentType: "application/json;charset=UTF-8", 
			dataType: "json"	
		}).done(function(resp) {	
			if (resp.status === 200) {	 
				alert("회원정보수정이 완료되었습니다.");
				location.href = "/";
			} else {
				alert("회원정보수정에 실패하였습니다.");
				location.href = "/user/updateForm";
			}
		}).fail(function(error) {	
			alert(JSON.stringify(error));
		});
	}
}
index.init();
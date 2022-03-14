let index = {

	init: function() {
		$("#btn-save").on("click", () => {	// function(){} 대신 ()=>{} this를 바인딩하기 위해서 화살표함수 사용!!
			this.save();
		});
			$("#btn-update").on("click", () => {	
			this.update();
		});
	},
	// 회원가입
	save: function() {
		// alert('user의 함수 호출됨!'); -> 함수호출 테스트용
		let data = {
			username: $("#username").val(),
			password: $("#password").val(),
			email: $("#email").val()
		};
		// ajax 통신을 이용해서 3개의 데이터를 json형식으로 변경하여 insert 요청. 
		// ajax 호출 시 default가 비동기 호출
		$.ajax({
			type: "POST",
			url: "/auth/joinProc",
			data: JSON.stringify(data),	// http body 데이터
			contentType: "application/json; charset=utf-8",	// body데이터 가 어떤 타입인지(MIME)
			dataType: "json"	// 요청을 서버로해서 응답이 왔을 때 기본적으로 모든 것이 문자열 (형태는 json이라면) => javascript 오브젝트로 변경!
		}).done(function(resp) {
			if(resp.status === 500 ){				
				alert("회원가입이 실패했습니다.");
			}else{
				alert("회원가입이 완료되었습니다.");
				location.href = "/";				
			}
		}).fail(function(error) {
			alert(JSON.stringify(error));
		});
	},
	// 회원정보 수정
	update: function() {
		let data = {
			id:$("#id").val(),
			username:$("#username").val(),
			password: $("#password").val(),
			email: $("#email").val()
		};
		$.ajax({
			type: "PUT",
			url: "/user",
			data: JSON.stringify(data),	// http body 데이터
			contentType: "application/json; charset=utf-8",	// body데이터 가 어떤 타입인지(MIME)
			dataType: "json"	// 요청을 서버로해서 응답이 왔을 때 기본적으로 모든 것이 문자열 (형태는 json이라면) => javascript 오브젝트로 변경!
		}).done(function(resp) {
			alert("회원수정이 완료되었습니다.");
			location.href = "/";
		}).fail(function(error) {
			alert(JSON.stringify(error));
		});
	}
}

index.init();

	/*
	
		$("#btn-login").on("click", () => {	
			this.login();
		});
		login: function() {
			// alert('user의 함수 호출됨!'); -> 함수호출 테스트용
			let data = {
				username: $("#username").val(),
				password: $("#password").val()
			};
			$.ajax({
				type: "POST",
				url: "/api/user/login",
				data: JSON.stringify(data),	// http body 데이터
				contentType: "application/json; charset=utf-8",	// body데이터 가 어떤 타입인지(MIME)
				dataType: "json"	// 요청을 서버로해서 응답이 왔을 때 기본적으로 모든 것이 문자열 (형태는 json이라면) => javascript 오브젝트로 변경!
			}).done(function(resp) {
				alert("로그인 성공.");
				// console.log(resp);
				location.href = "/";
			}).fail(function(error) {
				alert(JSON.stringify(error));
			});
		}
	*/

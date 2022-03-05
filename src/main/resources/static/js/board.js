// user.js 를 복사해서 이름 board.js 로 바꾸고 재활용
let index = {

	init: function() {
		$("#btn-save").on("click", () => {	// function(){} 대신 ()=>{} this를 바인딩하기 위해서 화살표함수 사용!!
			this.save();
		});
		$("#btn-delete").on("click", () => {	
			this.deleteById();
		});
		$("#btn-update").on("click", () => {	
			this.update();
		});
	},
	// 글 생성
	save: function() {
		let data = {
			title: $("#title").val(),
			content: $("#content").val(),
		};
		$.ajax({
			type: "POST",
			url: "/api/board",
			data: JSON.stringify(data),	// http body 데이터
			contentType: "application/json; charset=utf-8",	// body데이터 가 어떤 타입인지(MIME)
			dataType: "json"	// 요청을 서버로해서 응답이 왔을 때 기본적으로 모든 것이 문자열 (형태는 json이라면) => javascript 오브젝트로 변경!
		}).done(function(resp) {
			alert("글쓰기가 완료되었습니다.");
			location.href = "/";
		}).fail(function(error) {
			alert(JSON.stringify(error));
		});
	},
	// 글 삭제
	deleteById: function() {
		let id = $("#id").text();
		
		$.ajax({
			type: "DELETE",
			url: "/api/board/"+id,
			dataType: "json"
		}).done(function(resp) {
			alert("삭제가 완료되었습니다.");
			location.href = "/";
		}).fail(function(error) {
			alert(JSON.stringify(error));
		});
	},
	// 글 수정
	update: function() {
		let id = $("#id").val();
		let data = {
			title: $("#title").val(),
			content: $("#content").val(),
		};
		$.ajax({
			type: "PUT",
			url: "/api/board/"+id,
			data: JSON.stringify(data),	
			contentType: "application/json; charset=utf-8",	
			dataType: "json"	
		}).done(function(resp) {
			alert("글수정이 완료되었습니다.");
			location.href = "/";
		}).fail(function(error) {
			alert(JSON.stringify(error));
		});
	},
	
}
index.init();


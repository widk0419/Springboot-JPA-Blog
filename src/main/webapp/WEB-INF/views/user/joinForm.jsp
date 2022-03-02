<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="../layout/header.jsp"%>
<div class="container">

	<form>
		<div class="form-group">
			<label for="username">Username</label> 
			<input type="text" class="form-control" placeholder="username 입력" id="username">
		</div>
		<div class="form-group">
			<label for="password">Password</label> 
			<input type="password" class="form-control" placeholder="password 입력" id="password">
		</div>
		<div class="form-group">
			<label for="email">Email</label> 
			<input type="email" class="form-control" placeholder="email 입력" id="email">
		</div>
	</form>
	<button id="btn-save" class="btn btn-primary">회원가입 완료</button>
	
</div>
<script src="/js/user.js"></script>
<%@ include file="../layout/footer.jsp"%>




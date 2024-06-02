<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%-- JSTL Core --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
<style>
	main{
		width:1200px;
		margin:100px auto;
	}
	main h1{
		text-align:center;
		margin-bottom:50px;
	}
	
	table, tr, th, td{
		border-collapse:collapse;
		border:1px solid #ccc;
		padding:5px;
	}
	.table-area{
		width:1200px;
		margin:0 auto;
		display:flex;
		flex-direction:column;
		align-items:center;
	}
	table{
		margin:0 auto;
	}
	th{
		text-align:right;
	}
	
	input[type="text"]{
		width:440px;
		height:60px;
		background:#f7f7f7;
		color:#333;
		border:1px solid #ddd;
		border-radius:10px;
		font-size:16px;
		padding-left:20px;
		box-sizing:border-box;
		font-size:14px;
		margin-bottom:20px;
	}
	
	input[type="submit"]{
		width:440px;
		height:60px;
		background:#0071be;
		color:#fff;
		border:1px solid #0071be;
		border-radius:10px;
		font-size:16px;
		cursor:pointer;
	}
</style>
</head>
<body>
	<header>
		<%@ include file="./common/header.jsp" %>
	</header>
	
	<main>
	<h1>회원 가입</h1>
	<c:choose>
		<c:when test="${member.username eq null }">
			<form action="regist" method="post">
		</c:when>
		<c:when test="${member.username ne null }">
			<form action="update" method="post">
		</c:when>
	</c:choose>
			<div class="table-area">
						<c:choose>
							<c:when test="${member.name eq null }">
								<input type="text" name="name" placeholder="이름">
							</c:when>
							<c:when test="${member.name ne null }">
								<input type="text" name="name" placeholder="이름" value="${member.name }">
							</c:when>
						</c:choose>
						<c:choose>
							<c:when test="${member.username eq null }">
								<input type="text" name="username" placeholder="아이디">
							</c:when>
							<c:when test="${member.username ne null }">
								<input type="text" name="username" placeholder="아이디" value="${member.username }">
							</c:when>
						</c:choose>
						<c:choose>
							<c:when test="${member.password eq null }">
								<input type="text" name="password" placeholder="비밀번호">
							</c:when>
							<c:when test="${member.password ne null }">
								<input type="text" name="password" placeholder="비밀번호" value="${member.password }">
							</c:when>
						</c:choose>
						<c:choose>
							<c:when test="${member.phone eq null }">
								<input type="text" name="phone" placeholder="휴대폰번호">
							</c:when>
							<c:when test="${member.phone ne null }">
								<input type="text" name="phone" placeholder="휴대폰번호" value="${member.phone }">
							</c:when>
						</c:choose>
						<c:choose>
							<c:when test="${member.addr eq null }">
								<input type="text" name="addr" placeholder="주소">
							</c:when>
							<c:when test="${member.addr ne null }">
								<input type="text" name="addr" placeholder="주소" value="${member.addr }">
							</c:when>
						</c:choose>
					<c:choose>
						<c:when test="${member.username eq null }">
							<input type="submit" value="회원가입">
						</c:when>
						<c:when test="${member.username ne null }">
							<input type="submit" value="수정하기">
						</c:when>
					</c:choose>
			</div>
		</form>
	</main>
</body>
</html>
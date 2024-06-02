<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
	table, tr, th, td{
		border-collapse:collapse;
		border:1px solid #000;
		padding:5px;
	}
	h1{
		text-align:center;
		margin-bottom:50px;
	}
	.login-area{
		width:1200px;
		margin:0 auto;
		display:flex;
		flex-direction:column;
		align-items:center;
	}
	
	input[type="text"],
	input[type="password"]{
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
	}
	input[type="text"]{
		border-radius:10px 10px 0px 0px;
	}
	input[type="password"]{
		margin-bottom:50px;
		border-radius:0 0 10px 10px;
		border-top:0;
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
	<h1>로그인</h1>
		<form action="loginProc" method="post">
				<div class="login-area">
						<input type="text" name="username" id="username" placeholder="아이디">
						<input type="password" name="password" placeholder="비밀번호">
						<input type="submit" value="로그인">
				</div>
			</table>
		</form>
	</main>
	
	<!-- 에러 메시지 표시 -->
    <c:if test="${not empty error}">
        <script>
            const username = document.getElementById("username");
            alert("${error}");
            username.focus();
        </script>
    </c:if>
	
</body>
</html>
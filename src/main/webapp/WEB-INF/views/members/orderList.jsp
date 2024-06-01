<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%-- JSTL Core --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
main {
	width: 1240px;
	margin: 100px auto;
}

h1 {
	text-align: center;
	margin-bottom: 50px;
}

table, tr, th, td{
	border-collapse:collapse;
	border:1px solid #ddd;
	padding:10px;
	text-align:center;
}

.imgTd img{
	width:100px;
}

.orderTable{
	width:1200px;
}
table{
	width:100%;
	margin:0 auto;
}
</style>
</head>
<body>
	<header>
		<%@ include file="../common/header.jsp"%>
	</header>

	<main>
		<h1>주문 목록</h1>
		<div class="orderTable">
			<table>
				<thead>
					<tr>
						<th>상품명</th>
						<th>이미지</th>
						<th>주문수량</th>
						<th>가격</th>
						<th>총 가격</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="order" items="${list}">
						<tr>
							<td>${order.pname }</td>
							<td class="imgTd"><img src="/resource/img/${order.imgurl }"></td>
							<td>${order.quantity }</td>
							<td>${order.price } 원</td>
							<td>${order.totalprice } 원</td>
						</tr>
					</c:forEach>
				</tbody>

			</table>
		</div>
	</main>



</body>
</html>
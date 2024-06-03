<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
<style>
main {
	width: 1200px;
	margin: 100px auto;
	display: flex;
	gap: 100px;
}

.my-menu {
	width: 150px;
}

.my-menu h1 {
	border-bottom: 1px solid #000;
	padding-bottom: 25px;
	margin-bottom: 25px;
}

.my-menu div {
	margin-bottom: 15px;
	font-size: 18px;
}

.my-menu div a:hover {
	color: #0071be;
}

.order-list {
	width: 950px;
}

.order-list h1 {
	border-bottom: 1px solid #000;
	padding-bottom: 25px;
	margin-bottom: 25px;
	font-weight: 400;
}

.order-list img {
	width: 100px;
}
</style>
</head>
<body>

	<header>
		<%@ include file="../common/header.jsp"%>
	</header>

	<main>
		<div class="my-menu">
			<h1>마이 페이지</h1>
			<div>
				<a href="updateForm?username=${username }">내 정보수정</a>
			</div>
			<div>
				<a href="../members/cartPage?username=${username }">장바구니</a>
			</div>
			<div>
				<a href="../members/orderList?username=${username }">주문 목록</a>
			</div>
		</div>

		<div class="order-list">
			<h1>주문 목록</h1>
			<c:forEach var="order" items="${list}">
				<div>
					<img src="/resource/img/${order.imgurl }">
						${order.pname }
						${order.quantity }
						${order.price }원
						${order.totalprice }원
						<a href="">후기 작성</a>
				</div>
			</c:forEach>
		</div>
	</main>
</body>
</html>
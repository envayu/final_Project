<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<link rel="stylesheet" href="../resources/css/common.css" type="text/css">
<link rel="stylesheet" href="../resources/css/cartProduct.css" type="text/css">
</head>
<body>
	<div><jsp:include page="../common/header.jsp"></jsp:include></div>
	<div class="cart_form">
	<h1 id="cp_title">장바구니</h1>
	<hr width="1100px">
	<form name="checkBoxForm">
	<table border="1" width="80%">
		<c:forEach var="cp" items="${list }" varStatus="loop">
			<tr>
				<td><br><input type="checkbox" name="cartBox" onClick="javascript:itemSum(checkBoxForm, ${loop.index},${cp.product_price},${cp.product_cnt})"></td>
				 <input type="hidden" name="a" value="${loop.count}">
				 <input type="hidden" name="product_no" value="${cp.product_no}">
				 <input type="hidden" name="cart_no" value="${cp.cart_no}">
				 <input type="hidden" name="cust_id" value="${cust_id}">
				 <input type="hidden" name="product_img" value="${cp.product_img}">
				 <input type="hidden" name="product_name" value="${cp.product_name}">
				 <input type="hidden" name="product_price" value="${cp.product_price}">
				<td name="product_img"><img src="/images/${cp.product_img }" width="100" height="100"></td>
				<td name="product_name">${cp.product_name}</td>
				<td name="product_price">${cp.product_price}원</td>
				<td>
				<form action="updatecnt" method="post">
				<input type="text" name="product_cnt" value="${cp.product_cnt}">
				</td>
				<td><c:set var="result" value="${cp.product_price*cp.product_cnt}"/>${result}원</td>
				<td><br><button id="cp_update" type="button" onclick="javascript:CartUpdate(product_cnt,product_no, cart_no, cust_id, ${loop.index})">변경</button>&emsp;</td>
				<td><br><button id="cp_delete" type="button" onclick="javascript:CartDelete(cart_no, ${loop.index})">삭제</button>&emsp;</td>				
			</tr>	
		</c:forEach>
			<tr>
				<td colspan=7 style="text-align:right;">총 상품 금액 : <input name="total_sum" type="text" size="20" value="0" readonly>원</td>
			</tr>
			<tr align="center">
			
				<td colspan=7><button type="button" id="cp_select" onclick="javascript:CheckOrder(checkBoxForm)">선택 상품 주문</button>
				&emsp;<button type="button" id="cp_all" onclick="javascript:AllOrder(checkBoxForm);">전체 상품 주문</button></td>
			</tr>		
	</table>
	</form>
	</div>
	<div><jsp:include page="../common/footer.jsp"></jsp:include></div>
	<script src="${pageContext.request.contextPath}/resources/js/cart.js"></script>	
	
</body>
</html>
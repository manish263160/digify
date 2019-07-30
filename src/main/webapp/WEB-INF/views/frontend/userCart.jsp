<%@include file="../fragments/includetags.jsp"%>

<!DOCTYPE html>
<html lang="en">
<head>
<%@include file="../includes/web_css.jsp"%>
<link href="${digify}/static/css/cart.css" rel="stylesheet" type="text/css">
<title>My Cart</title>
</head>

<body id="page-top">
	<%@include file="../frontendfragments/frontendHeader.jsp"%>

	<div class="wrapper">
		<div class="container">
			<div class="shopping-cart">
			<c:if test="${(not empty productList || not empty servicesList ) }">
				<h2>Shopping Cart</h2>
				<div class="column-labels">
					<label class="product-image">Image</label> 
					<label class="product-details">Product</label> 
					<label class="product-price">Price</label>
					<!-- 	<label class="product-quantity">Quantity</label>  -->
					<label class="product-removal">Remove</label>
				</div>
				</c:if>
				<c:choose>
				<c:when test="${not empty productList || not empty servicesList }">
					<c:if test="${not empty productList }">
						<h4>Service List</h4>
						<hr>
						<c:forEach var="item" items="${productList }">
							<div class="product">
								<div class="product-image">
									<img src="${item.imageUrl }">
								</div>
								<div class="product-details">
									<a href="${digify }/home/productPage/${item.id}"
										target="_blank" class="product-title"><span>${item.productName}</span></a>
								</div>
								<div class="product-price">${item.cost }</div>
								<div class="product-removal">
									<a class="remove-product" href="${digify}/ecommerce/removeFromCart/products/${item.userCartProductPK}">Remove</a>
								</div>
							</div>
						</c:forEach>
					</c:if>
					<c:if test="${not empty servicesList }">
						<h4>Product List</h4>
						<hr>
						<c:forEach var="item" items="${servicesList }">
							<div class="product">
								<div class="product-image">
									<img src="${item.imageUrl }">
								</div>
								<div class="product-details">
									<a href="${digify }/home/servicePage/${item.id}"
										target="_blank" class="product-title"><span>${item.serviceName}</span></a>
								</div>
								<div class="product-price">${item.cost }</div>
								<div class="product-removal">
									<a class="remove-product" href="${digify}/ecommerce/removeFromCart/services/${item.userCartServicetPK}">Remove</a>
								</div>
							</div>
						</c:forEach>
					</c:if>
						<div class="totals">
							<div class="totals-item totals-item-total">
								<label>Grand Total</label>
								<div class="totals-value" id="cart-total">${totalCost }</div>
							</div>
						</div>
						<a class="checkout" href="${digify}/ecommerce/checkOut/${loggedInUserId}/${totalCost}">Checkout</a>
					</c:when>
					<c:otherwise>
						<img alt="empty-cart" src="${digify}/static/images/cart-empty.png" />
					</c:otherwise>
				</c:choose>
			</div>
		</div>
	</div>
</body>
<%@include file="../frontendfragments/frontEndFooter.jsp"%>
<%-- 	<jsp:include page="../frontendfragments/frontEndFooter.jsp" /> --%>
<%@include file="../includes/web_js.jsp"%>
<script src="${digify}/static/js/cart.js"></script>
</html>

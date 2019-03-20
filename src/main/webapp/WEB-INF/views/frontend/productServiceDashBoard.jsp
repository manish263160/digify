<%@include file="../fragments/includetags.jsp"%>

<!DOCTYPE html>
<html lang="en">

<head>
<jsp:include page="../includes/web_css.jsp" />
<link href="${digify}/static/frontEnd/css/productServcDashBoard.css" rel="stylesheet">
<link>
</head>

<body id="page-top">
	<%@include file="../frontendfragments/frontendHeader.jsp"%>
<section class="card-container">
<h2>All ${table eq 'products' ? 'products' : table eq 'services' ? 'Gate Management System' : table}.</h2>
<div class="row">
<c:if test="${table eq 'products' }">
<c:forEach var="item" items="${allProducts }">
<div class="col-md-4 col-sm-4 col-xs-12 main-card">
	<div class="card-p">
		<img src="${item.imageUrl }" alt="Avatar" class="avatarimg">
		<div class="container-p">
			<h4>
				<b>${item.productName }</b>
			</h4>
			<p><a class="knowmorebtn" href="${digify}/frontendAction/productPage/${item.id}" >Get Details</a></p>
		</div>
	</div>
	</div>
	</c:forEach>
	</c:if>
	<c:if test="${table eq 'services' }">
	<c:forEach var="item" items="${allServices }">
	<div class="col-md-4 col-sm-4 col-xs-12 main-card">
	<div class="card-p">
		<img src="${item.imageUrl }" alt="Avatar" class="avatarimg">
		<div class="container-p">
			<h4>
				<b>${item.serviceName }</b>
			</h4>
			<p><a class="knowmorebtn" href="${digify}/frontendAction/servicePage/${item.id}" >Get Details</a></p>
		</div>
	</div>
	</div>
	</c:forEach>
	</c:if>
</div>
</section>
		<%@include file="../frontendfragments/frontEndFooter.jsp"%>
	<jsp:include page="../includes/web_js.jsp" />
	
</body>
</html>

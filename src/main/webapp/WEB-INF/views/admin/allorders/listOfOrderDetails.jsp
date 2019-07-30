<%@include file="../../fragments/includetags.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>List Of Order Details</title>
    <link href="${digify}/static/lib/css/magnific-popup.css" type="text/css"
          rel="stylesheet" media="screen,projection">
<style type="text/css">
.panel-order .row {
	border-bottom: 1px solid #ccc;
}
.panel-order .row:last-child {
	border: 0px;
}
.panel-order .row .col-md-1  {
	text-align: center;
	padding-top: 15px;
}
.panel-order .row .col-md-3 img {
    width: 13rem;
    max-height: 100%;
}
.panel-order .row .row {
	border-bottom: 0;
}
/* .panel-order .row .col-md-9 {
	border-left: 1px solid #ccc;
} */
.panel-order .row .row .col-md-12 {
	padding-top: 7px;
	padding-bottom: 7px; 
}
.panel-order .row .row .col-md-12:last-child {
	font-size: 11px; 
	color: #555;  
	background: #efefef;
}
.panel-order .btn-group {
	margin: 0px;
	padding: 0px;
}
.panel-order .panel-body {
	padding-top: 0px;
	padding-bottom: 0px;
}
.panel-order .panel-deading {
	margin-bottom: 0;
}                    
</style>
</head>
<body class="${themecolor }">
<jsp:include page="../../fragments/admin_header.jsp"/>
<div class="container">
 <div class="section">
 <h3>ORDER ID : <b>${orderId}</b></h3>
   <div class="row">
                <div class="col s12 m8 l9">
					<div class="panel panel-default panel-order">
			<c:if test="${not empty listOfOrderDetails }">
				<div class="panel-body">
				<c:forEach var="item" items="${listOfOrderDetails }">
			  <div class="row">
			  	<c:if test="${not empty item.products }">
							
				  <div class="col-md-3"><a href="${digify }/home/productPage/${item.products.id}"><img src="${item.products.imageUrl }" class="media-object img-thumbnail"></a></div>
				  <div class="col-md-9">
					<div class="row">
					  <div class="col-md-12">
						<div class="pull-right"><label class="label label-danger">Accepted</label> </div>
						<span><strong>${item.products.productName}</strong></span> <br>
						cost: ${item.products.cost } <br>
						
					  </div>
					  <div class="col-md-12">
						order made on: ${item.createdOn }
					  </div>
					</div>
					</div>
				</c:if>
				<c:if test="${not empty item.services }">
							
				  <div class="col-md-3"><a href="${digify }/home/servicePage/${item.id}"><img src="${item.services.imageUrl }" class="media-object img-thumbnail"></a></div>
				  <div class="col-md-9">
					<div class="row">
					  <div class="col-md-12">
						<div class="pull-right"><label class="label label-danger">Accepted</label> </div>
						<span><strong>${item.services.serviceName}</strong></span> <br>
						cost: ${item.services.cost } <br>
						
					  </div>
					  <div class="col-md-12">
						order made on: ${item.createdOn }
					  </div>
					</div>
					</div>
				</c:if>
				</div>
					</c:forEach>
					</div>
					</c:if>
					</div>
                </div>
                </div>
           
   </div>
   </div> 
<%-- <jsp:include page="../../includes/admin_js.jsp"/> --%>
</body>

</html>

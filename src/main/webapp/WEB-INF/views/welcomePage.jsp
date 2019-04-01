<%@include file="./fragments/includetags.jsp"%>

<!DOCTYPE html>
<html lang="en">

<head>
<jsp:include page="./includes/web_css.jsp" />
</head>

<body id="page-top">

	<%@include file="./frontendfragments/frontendHeader.jsp"%>

	<%@include file="./frontendfragments/carouselFragment.jsp"%>

	<%@include file="./frontendfragments/aboutusFragment.jsp"%>


	<%@include file="./frontendfragments/productFradments.jsp"%>

<%-- 	<%@include file="./frontendfragments/serviceFragment.jsp"%> --%>

<%-- 	<%@include file="./frontendfragments/testimonialFragment.jsp"%> --%>

	
	<%@include file="./frontendfragments/promoFragment.jsp"%>

	<%@include file="./frontendfragments/infographix.jsp"%>
	<%@include file="./frontendfragments/ourClientFragment.jsp"%>
	
	
	<%@include file="./frontendfragments/modelFragment.jsp"%>
	
	<%@include file="./frontendfragments/frontEndFooter.jsp"%>
	<jsp:include page="./includes/web_js.jsp" />
</body>
<script type="text/javascript">
	$( document ).ready(function() {
		
		var allProduct = $("#allProductcount").attr('val');
		for (var i = 0; i < allProduct; i++) {
			var id = "#product_description"+i;
			var comboId = id+' *';
		$(id).html($(id).attr('desc'))
		$(id).attr('style','');
		$(comboId).attr('style','');
		}
	});
	</script>
</html>

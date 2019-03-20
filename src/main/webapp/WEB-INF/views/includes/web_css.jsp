
<%@include file="../fragments/includetags.jsp"%>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">

<title>Digify</title>


<!-- Google Fonts -->
<link href="https://fonts.googleapis.com/css?family=Merriweather+Sans:400,700" rel="stylesheet">
<link href='https://fonts.googleapis.com/css?family=Merriweather:400,300,300italic,400italic,700,700italic'
	rel='stylesheet' type='text/css'>

<!-- Plugin CSS -->
<link href="${digify}/static/frontEnd/vendor/magnific-popup/magnific-popup.css" rel="stylesheet">
<!-- Font Awesome Icons -->
<link href="${digify}/static/frontEnd/vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">



<!-- Theme CSS - Includes Bootstrap -->
<link href="${digify}/static/frontEnd/css/creative.css" rel="stylesheet">
<link href="${digify}/static/frontEnd/css/custom_css.css" rel="stylesheet">

<!-- Owl carousel -->
<link href="${digify}/static/frontEnd/vendor/owlcarousel/owl.carousel.min.css" rel="stylesheet" type="text/css">
<c:if test="${ fromcontact eq true }">
	<link href="${digify}/static/frontEnd/css/contactus.css" rel="stylesheet">
	<link href="${digify}/static/frontEnd/vendor/fontawesome-free/css/fontawesome.min.css" rel="stylesheet">
</c:if>

<c:if test="${ faq eq true }">
<link href="${digify}/static/frontEnd/css/contactus.css" rel="stylesheet">
	<link href="${digify}/static/frontEnd/css/faq.css" rel="stylesheet">
	<link href="${digify}/static/frontEnd/vendor/fontawesome-free/css/fontawesome.min.css" rel="stylesheet">
</c:if>
<c:if test="${ team eq true }">
<link href="${digify}/static/frontEnd/css/contactus.css" rel="stylesheet">
	<link href="${digify}/static/frontEnd/css/team.css" rel="stylesheet">
	<link href="${digify}/static/frontEnd/vendor/fontawesome-free/css/fontawesome.min.css" rel="stylesheet">
</c:if>

<c:if test="${ amc eq true }">
<link href="${digify}/static/frontEnd/css/contactus.css" rel="stylesheet">
	<link href="${digify}/static/frontEnd/css/team.css" rel="stylesheet">
	<link href="${digify}/static/frontEnd/vendor/fontawesome-free/css/fontawesome.min.css" rel="stylesheet">
</c:if>
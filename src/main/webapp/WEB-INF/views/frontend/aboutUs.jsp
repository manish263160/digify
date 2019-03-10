<%@include file="../fragments/includetags.jsp"%>

<!DOCTYPE html>
<html lang="en">

<head>
<jsp:include page="../includes/web_css.jsp" />
</head>

<body id="page-top">
	<%@include file="../frontendfragments/frontendHeader.jsp"%>

	<div class="wrapper">

		<section class="inner-banner inner-banner-about ">
			<div class="thm-container clearfix">
				<h2 class="pull-left">Contact Us</h2>
				<ul class="breadcumb pull-right">
					<li><a href="${digify }" class="home-fa"><i class="fa fa-home"></i> Home</a></li>
					<li><span>About Us</span></li>
				</ul>
			</div>
		</section>



		<section class="services-section sec-padding contact-page-content ">
			<div class="thm-container">
				<div class="sec-title">
					<h2>
						<span>About Us</span>
					</h2>
				</div>
				
				<div class="row" style="padding-bottom: 20px;">
					<div class="col-md-6 col-sm-6 col-xs-12 pull-left">
						<div class="left-box">
					<div class="img-box shadow">
						<img src="${digify}/static/frontEnd/img/pedstrian.jpg" alt="Awesome Image" width="100%">
					</div>
				</div>
					</div>
					<div class="col-md-6 col-sm-6 col-xs-12 pull-rigth">
						<div class="right-box">
					<div class="sec-title text-justify">
						<p style="color:#3c9c45;"><b>Digify Engineering Pvt. Ltd. maintain optimal security is an integral part of any business that not only protects your employees, but your assets as well.</b></p>
						<p>In any  industrial building or a multi-campus Building, you need a customised designed and technologically advanced security system . Make sure you get that with Digify Engineering Pvt. Ltd., a leading provider of premium security solutions dedicated to protecting all sizes of  Infrastructure with the latest products and services designed to control access, reduce potential theft and implement an effective risk management system.</p>
						<p>Each and every customer receives a needs assessment to ensure you get exactly what you need to protect your assets. Plus, with tailor-made maintenance and service plans for every type of business, you'll have peace of mind, knowing your security.</p>
						
					</div>
				</div>
					</div>
				</div>
			</div>
		</section>
	</div>
		<jsp:include page="../frontendfragments/frontEndFooter.jsp" />
	<jsp:include page="../includes/web_js.jsp" />
	
</body>
</html>

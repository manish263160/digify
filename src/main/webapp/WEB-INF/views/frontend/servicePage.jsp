<%@include file="../fragments/includetags.jsp"%>

<!DOCTYPE html>
<html lang="en">

<head>
<jsp:include page="../includes/web_css.jsp" />
<link href="${digify}/static/frontEnd/css/product_service.css" rel="stylesheet" type="text/css">
</head>

<body id="page-top">
<%@include file="../frontendfragments/frontendHeader.jsp"%>

<div class="container product-page">	
            <div class="row row-margin-bottom">
            <div class="col-md-12 no-padding lib-item" data-category="view">
                <div class="lib-panel">
                    <div class="row box-shadow">
                        <div class="col-md-6">
                            <img class="lib-img-show" src="${service.imageUrl }">
                        </div>
                        <div class="col-md-6 row-margin-bottom">
                            <div class="lib-row lib-header">
                               ${service.serviceName }
                                <div class="lib-header-seperator"></div>
                            </div>
                            <div class="lib-row lib-desc">
                                ${service.serviceDescription}
                            </div>
                        </div>
                    </div>
                </div>
            </div>
<!--             <div class="col-md-1"></div>             -->
        </div>
        <div class="row">
        <div class="col-md-4 ml-5 lib-item" data-category="ui">
               	<div class="request-form">
						<div class="request-form-header">
							<div class="row">
								<div class="col-lg-3 col-sm-3 col-md-3 col-xs-2">
									<img src="${digify}/static/frontEnd/img/request_quote.png" class="infodivimg" />
								</div>
								<div class="col-lg-9 col-sm-9 col-md-9 col-xs-10">
									<h3 class="inquirydivheading request-form-inner">Don't Hesitate To Ask</h3>
									<h2 class="inquirydivheadingtext">Request A Quote</h2>
								</div>
							</div>
						</div>
						<div class="request-form-content col-lg-12 col-sm-12 col-md-12 col-xs-12">
							<form  id="quotes_form" action="#">
								<div class="form-group">
									<input type="text" placeholder="Your Name" class="form-control1" id="personName" name="personName">
								</div>
								<div class="form-group">
									<input type="email" placeholder="Your Email" class="form-control1" id="personEmail" name="personEmail">
								</div>
								<div class="form-group">
									<input type="number" placeholder="Telephone" class="form-control1" id="mobile" name="mobile">
								</div>
								<div class="form-group">
									<textarea type="text" placeholder="Quote Details" class="form-control1" id="quoteDetails" name="quoteDetails"></textarea>
								</div>
								<input type="hidden" name="inquiryForId" value="${service.id }">
								<input type="hidden" name="inquiryFor" value="${table}">
								<input type="hidden" name="inquiryForName" value="${service.serviceName}">
								<button type="button" onclick="submitQuotes('${digify}','$event')" class="btn btn-primary">Submit Inquiry</button>
							</form>
						</div>
					</div>
            </div>
        </div>
</div>
	<%@include file="../frontendfragments/modelFragment.jsp"%>
	<%@include file="../frontendfragments/frontEndFooter.jsp"%>
	<jsp:include page="../includes/web_js.jsp" />
</body>
</html>

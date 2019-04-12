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
                            <div class="lib-row lib-desc" id="description">
                            ${service.serviceDescription}
                            </div>
                        </div>
                    </div>
                </div>
            </div>
<!--             <div class="col-md-1"></div>             -->
        </div>
        <div class="row">
        <div class="float-right offset-4 ">
               <button class="btn btn-primary" style="width: 100%;"  onclick="openModel('${service}','$event')">Get Quotes</button>
            </div>
        </div>
        
        <div class="modal fade" id="request-form" role="dialog">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<h2 class="modal-title">Request A Quote</h2>
			</div>
			<div class="modal-body">
				<form id="quotes_form" action="#">
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
					<input type="hidden" name="inquiryForId" value="${service.id }"> <input type="hidden" name="inquiryFor"
						value="${table}"> 
						<input type="hidden" name="inquiryForName" value="${service.serviceName}">
					<button type="button" onclick="submitQuotes('${digify}','$event')" class="btn btn-primary">Submit Inquiry</button>
				</form>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
			</div>
		</div>
	</div>
</div>
</div>
	<%@include file="../frontendfragments/modelFragment.jsp"%>
	<%@include file="../frontendfragments/frontEndFooter.jsp"%>
	<jsp:include page="../includes/web_js.jsp" />
	<script type="text/javascript">
	$( window ).on( "load", function() { $('#description, #description *').removeAttr('style'); })
	</script>
</body>
</html>

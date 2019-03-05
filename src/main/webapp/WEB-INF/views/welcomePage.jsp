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

	<%@include file="./frontendfragments/infographix.jsp"%>

	<%@include file="./frontendfragments/productFradments.jsp"%>

	<%@include file="./frontendfragments/serviceFragment.jsp"%>

	<%@include file="./frontendfragments/testimonialFragment.jsp"%>

	<%@include file="./frontendfragments/promoFragment.jsp"%>

	<%@include file="./frontendfragments/ourClientFragment.jsp"%>
	<jsp:include page="./frontendfragments/frontEndFooter.jsp" />
	<jsp:include page="./includes/web_js.jsp" />
</body>
<script type="text/javascript">
/**
 * 
 */
"use strict"; 

	$("#quotes_form").submit(function(e){
	  
		if($("#quotes_form").valid()){
		console.log("submitQuotes::");
	  
	  event.preventDefault();
	  var form_data = $("#quotes_form").serialize();
	  $.ajax({
		    url: '${digify}/requestQuotes',
		    type: 'POST',
		    data : form_data,
		    success: function(result) {
		        // Do something with the result
		        console.log("result=="+result)
/*     		      	  $(".progress").hide();	 */
		        if(result){

		        	$("#quotesSuccess").modal()
		        }
		    }
		});
	}
});


$("#quotes_form").validate({
    rules: {
    	personEmail :{
        	required: true,
        	email: true
        },
        mobile :{
        	required: true,
        	maxlength: 10
        },
        quoteDetails :{
        	required: true,
        }
   },
    //For custom messages
    messages: {

    },
    errorElement : 'div',
    errorPlacement: function(error, element) {
      var placement = $(element).data('error');
      if (placement) {
        $(placement).append(error)
      } else {
        error.insertAfter(element);
      }
    }
 });	
</script>
</html>

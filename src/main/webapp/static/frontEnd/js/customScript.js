/**
 * 
 */
"use strict"; 

	function submitQuotes(digify,e){
	  
		if($("#quotes_form").valid()){
		console.log("submitQuotes::", digify);
	  
	  event.preventDefault();
	  var form_data = $("#quotes_form").serialize();
	  $.ajax({
		    url: digify+'/requestQuotes',
		    type: 'POST',
		    data : form_data,
		    success: function(result) {
		        // Do something with the result
		        console.log("result=="+result)
/*     		      	  $(".progress").hide();	 */
		        if(result){

		        	$("#quotesSuccess").modal();
		        	$("#quotes_form")[0].reset();
		        }
		    }
		});
	}
};


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

var slideIndex = 0;
showproductSlides();

function showproductSlides() {
  var i;
  var slides = document.getElementsByClassName("mySlides");
  var dots = document.getElementsByClassName("dots");
  /*for (i = 0; i < slides.length; i++) {
    slides[i].style.display = "none";  
  }*/
  slideIndex++;
  if (slideIndex > slides.length) {slideIndex = 1}
  for (i = 0; i < slides.length; i++) {
	  slides[i].className = slides[i].className.replace(" show active", "");
	  }
  for (i = 0; i < dots.length; i++) {
    dots[i].className = dots[i].className.replace(" active", "");
  }
  slides[slideIndex-1].className +=  " show active";  
  dots[slideIndex-1].className += " active";
  setTimeout(showproductSlides, 2000); // Change image every 2 seconds
}

var serviceSlideIndex = 0;
showserviceSlides();

function showserviceSlides() {
  var i;
  var slides = document.getElementsByClassName("serviceSlide");
  var dots = document.getElementsByClassName("servicedot");
  /*for (i = 0; i < slides.length; i++) {
    slides[i].style.display = "none";  
  }*/
  serviceSlideIndex++;
  if (serviceSlideIndex > slides.length) {serviceSlideIndex = 1}
  for (i = 0; i < slides.length; i++) {
	  slides[i].className = slides[i].className.replace(" show active", "");
	  }
  for (i = 0; i < dots.length; i++) {
    dots[i].className = dots[i].className.replace(" active", "");
  }
  slides[serviceSlideIndex-1].className +=  " show active";  
  dots[serviceSlideIndex-1].className += " active";
  setTimeout(showserviceSlides, 2000); // Change image every 2 seconds
}
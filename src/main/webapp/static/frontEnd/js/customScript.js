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
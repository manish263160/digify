/**
 * 
 */
"use strict";

function submitQuotes(digify, e) {

	if ($("#quotes_form").valid()) {
		console.log("submitQuotes::", digify);

//		event.preventDefault();
		var form_data = $("#quotes_form").serialize();
		$.ajax({
			url : digify + '/requestQuotes',
			type : 'POST',
			data : form_data,
			success : function(result) {
				// Do something with the result
				console.log("result==" + result)
				/*     		      	  $(".progress").hide();	 */
				if (result) {
					$("#request-form").modal('hide');
					$("#quotesSuccess").modal();
					$("#quotes_form")[0].reset();
				}
			}
		});
	}
};

$("#quotes_form").validate({
	rules : {
		personEmail : {
			required : true,
			email : true
		},
		mobile : {
			required : true,
			number: true,
			maxlength : 10
		},
		quoteDetails : {
			required : true,
		}
	},
	//For custom messages
	messages : {

	},
	errorElement : 'div',
	errorPlacement : function(error, element) {
		var placement = $(element).data('error');
		if (placement) {
			$(placement).append(error)
		} else {
			error.insertAfter(element);
		}
	}
});
// -----------------------------open request model--------------------------------
function openModel(obj , event){
	
	$("#quotesSuccess").modal('hide');
	$("#request-form").modal();
}


// -----------------------owl carousel js changes-------------------------------------
$('.prodserviceCarousel').owlCarousel({
	  loop: true,
	  margin: 10,
	  nav: true,
	  dots: false,
	  navText: [
	    "<i class='fa fa-caret-left'></i>",
	    "<i class='fa fa-caret-right'></i>"
	  ],
	  autoplay: true,
	  autoplayHoverPause: true,
	  responsive: {
	    0: {
	      items: 1
	    },
	    600: {
	      items: 3
	    },
	    1000: {
	      items: 5
	    }
	  }
	});

$('#testimonials').owlCarousel({
	  loop: true,
	  margin: 0,
	  nav: false,
	  dots: false,
	  navText: [
	    "<i class='fa fa-caret-left'></i>",
	    "<i class='fa fa-caret-right'></i>"
	  ],
	  autoplay: true,
	  autoplayHoverPause: true,
	  responsive: {
	    0: {
	      items: 1
	    },
	    600: {
	      items: 2
	    },
	    1000: {
	      items: 3
	    }
	  }
	});

$('.our-client').owlCarousel({
	  loop: true,
	  margin: 10,
	  nav: false,
	  dots: false,
	  autoplay: true,
	  autoplayHoverPause: true,
	  responsive: {
	   400: {
		   items: 2
	   },
	    600: {
	      items: 3
	    },
	    1000: {
	      items: 6
	    }
	  }
	});

//---------------------------code start for auto slider---------------------------------------
function showContnet(size,index){
	console.log('index::',size,'' ,index);
	
	for (var i = 0; i < size; i++) {
		$("#productDesc_"+i).removeClass('show active');
	}
	$("#productDesc_"+index).addClass("show active");
}

function showContnetServ(size,index){

	for (var i = 0; i < size; i++) {
		$("#serviceDesc_"+i).removeClass('show active');
	}
	$("#serviceDesc_"+index).addClass("show active");
}
$(document).ready(function() {
    $('.datepicker').datepicker();
 
});

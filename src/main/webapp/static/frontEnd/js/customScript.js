/**
 * 
 */
"use strict";


$("#quotes_form").validate({
	rules : {
		personEmail : {
			required : true,
			email : true
		},
		mobile : {
			required : true,
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
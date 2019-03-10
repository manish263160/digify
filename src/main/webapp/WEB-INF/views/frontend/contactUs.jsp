<%@include file="../fragments/includetags.jsp"%>

<!DOCTYPE html>
<html lang="en">

<head>
<jsp:include page="../includes/web_css.jsp" />
</head>

<body id="page-top">
	<%@include file="../frontendfragments/frontendHeader.jsp"%>

	<div class="wrapper">

		<section class="inner-banner ">
			<div class="thm-container clearfix">
				<h2 class="pull-left">Contact Us</h2>
				<ul class="breadcumb pull-right">
					<li><a href="${digify }" class="home-fa"><i class="fa fa-home"></i> Home</a></li>
					<li><span>Contact Us</span></li>
				</ul>
			</div>
		</section>



		<section class="services-section sec-padding contact-page-content ">
			<div class="thm-container">
				<div class="sec-title">
					<h2>
						<span>Get in touch</span>
					</h2>
				</div>
				<div class="row">
					<div class="col-md-4">
						<div class="contact-info">
							<ul>
								<li>
									<div class="icon-box">
										<i class="fa fa-map-marker"></i>
									</div>
									<div class="content">
										<p>
											660/2119, Shivaji Park <br>Gurgaon-122001
										</p>
									</div>
								</li>
							</ul>
						</div>
					</div>
					<div class="col-md-4">
						<div class="contact-info">
							<ul>
								<li>
									<div class="icon-box">
										<i class="fa fa-envelope-o"></i>
									</div>
									<div class="content">
										<p>
											[Web] : <a href="mailto:www.digify.net.in" target="_blank">www.digify.net.in</a> <br>[Email]: <a
												href="mailto:info@digify.net.in" target="_blank">info@digify.net.in</a>
										</p>
									</div>
								</li>
							</ul>
						</div>
					</div>
					<div class="col-md-4">
						<div class="contact-info">
							<ul>
								<li>
									<div class="icon-box">
										<i class="fa fa-phone"></i>
									</div>
									<div class="content">
										<p>[Tel] : 0124-6512448</p>
									</div>
								</li>
							</ul>
						</div>
					</div>
				</div>
				<div class="row" style="padding-bottom: 20px;">
					<div class="col-md-6 col-sm-6 col-xs-12">
						<form  method="post" class="contact-form contact-page" id="contact_form" >
							<div>
								<input type="text" placeholder="Name" name="name">
							</div>
							<div>
								<input type="text" placeholder="Email" name="email">
							</div>
							<div>
								<input type="text" placeholder="Subject" name="subject">
							</div>
							<div>
								<textarea name="message" placeholder="Message"></textarea>
							</div>
							<button type="button" onclick="submitContactForm('$event')" class="thm-btn">
								Submit Now <i class="fa fa-arrow-right"></i>
							</button>
						</form>
					</div>
					<div class="col-md-6 col-sm-6 col-xs-12">
						<iframe
							src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d13238.854480712705!2d77.01760520298848!3d28.494015126621985!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x390d19e9173dc0b5%3A0x81e7afe494cf5f09!2sAshok+Vihar+Phase+III+Extension%2C+Gurugram%2C+Haryana+122017!5e0!3m2!1sen!2sin!4v1490943648557"
							width="600" height="450" frameborder="0" style="border: 0" allowfullscreen=""></iframe>
					</div>
				</div>
			</div>
		</section>


		<footer class="bg-light">
			<div class="container-fluid p-0">
				<div id="cshero-footer" class="cshero-footer1">

					<div id="cshero-footer-top">
						<div class="container">
							<div class="row">
								<div class="col-xs-12 col-sm-6 col-md-7 col-lg-7">
									<div class="row">
										<div class="cshero-footer-top1 col-xs-12 col-sm-12 col-md-7 col-lg-7">
											<aside id="text-5" class="widget widget_text">
												<div class="textwidget">
													<div class="media">
														<div class="media-left">
															<img class="logo-img" src="/digify/static/frontEnd/img/logo.png">
															<div class="media-body">DIGIFY Engineering Pvt. Ltd. Maintain optimal security is an Integral part
																of any business that not only protects your employees, but your assets as well as.</div>
														</div>
													</div>
												</div>
											</aside>
										</div>
										<div class="cshero-footer-top2 col-xs-12 col-sm-12 col-md-5 col-lg-5">
											<aside id="nav_menu-2" class="widget widget_nav_menu">
												<h3 class="widget-title">Navigation</h3>
												<div class="menu-navigation-container">
													<ul id="menu-navigation" class="menu p-0">
														<li id="menu-item-121"
															class="menu-item menu-item-type-custom menu-item-object-custom no_group menu-item-121" data-depth="0">
															<a href="#" class=""><span class="menu-title">About Us</span></a>
														</li>
														<li id="menu-item-122"
															class="menu-item menu-item-type-custom menu-item-object-custom no_group menu-item-122" data-depth="0">
															<a href="#" class=""><span class="menu-title">Product</span></a>
														</li>
														<li id="menu-item-123"
															class="menu-item menu-item-type-custom menu-item-object-custom no_group menu-item-123" data-depth="0">
															<a href="#" class=""><span class="menu-title">Careers</span></a>
														</li>
														<li id="menu-item-124"
															class="menu-item menu-item-type-custom menu-item-object-custom no_group menu-item-124" data-depth="0">
															<a href="#" class=""><span class="menu-title">Team</span></a>
														</li>
														<li id="menu-item-126"
															class="menu-item menu-item-type-custom menu-item-object-custom no_group menu-item-126" data-depth="0">
															<a href="#" class=""><span class="menu-title">FAQs</span></a>
														</li>
													</ul>
												</div>
											</aside>
										</div>
									</div>
								</div>
								<div class="cshero-footer-top3 col-xs-12 col-sm-6 col-md-5 col-lg-5">
									<aside id="nav_menu-3" class="widget widget_nav_menu">
										<h3 class="widget-title">Services</h3>
										<div class="menu-menu-services-footer-container">
											<ul id="menu-menu-services-footer" class="menu p-0">
												<li id="menu-item-497"
													class="menu-item menu-item-type-custom menu-item-object-custom no_group menu-item-497" data-depth="0">
													<a href="#" class=""><span class="menu-title">Access Control System</span></a>
												</li>
												<li id="menu-item-500"
													class="menu-item menu-item-type-custom menu-item-object-custom no_group menu-item-500" data-depth="0">
													<a href="#" class=""><span class="menu-title">Intrusion Detection</span></a>
												</li>
												<li id="menu-item-503"
													class="menu-item menu-item-type-custom menu-item-object-custom no_group menu-item-503" data-depth="0">
													<a href="#" class=""><span class="menu-title">Video Surveillance Systems</span></a>
												</li>
												<li id="menu-item-498"
													class="menu-item menu-item-type-custom menu-item-object-custom no_group menu-item-498" data-depth="0">
													<a href="#" class=""><span class="menu-title">Fire Alarm System</span></a>
												</li>
												<li id="menu-item-501"
													class="menu-item menu-item-type-custom menu-item-object-custom no_group menu-item-501" data-depth="0">
													<a href="#" class=""><span class="menu-title">Gate Management System</span></a>
												</li>
											</ul>
										</div>
									</aside>
								</div>
							</div>
						</div>
					</div>

					<div id="cs-copyright">
						<div class="container">
							<div class="row">
								<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12 text-center">Copyright © DIGIFY Engineering Pvt. Ltd.
									2017. All rights reserved.</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</footer>
	</div>
	<%@include file="../frontendfragments/modelFragment.jsp"%>
	<%-- 	<jsp:include page="../frontendfragments/frontEndFooter.jsp" /> --%>
	<jsp:include page="../includes/web_js.jsp" />
	<script type="text/javascript">
	function submitContactForm(e){
		if($("#contact_form").valid()){
			 event.preventDefault();
			  var form_data = $("#contact_form").serialize();
			  $.ajax({
				    url: '${digify }/frontendAction/contactUsSubmit',
				    type: 'POST',
				    data : form_data,
				    success: function(result) {
				        // Do something with the result
				        console.log("result=="+result)
		/*     		      	  $(".progress").hide();	 */
				        if(result){

				        	$("#contactussubmitSuccess").modal();
				        	$("#contact_form")[0].reset();
				        }
				    }
				});
		}
	}
	
	$("#contact_form").validate({
	    rules: {
	    	email :{
	        	required: true,
	        	email: true
	        },
	        name :{
	        	required: true
	        },
	        subject :{
	        	required: true
	        },
	        message :{
	        	required: true
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
</body>
</html>

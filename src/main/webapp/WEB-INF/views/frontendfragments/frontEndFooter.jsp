<!-- Contact Section -->
<section class="page-section page-section2" id="contact">
	<div class="container container9">

		<div class="row">
			<div class="col-lg-12 col-sm-12 col-md-12 col-xs-12 vc_row wpb_row vc_row-fluid">
				<div class="wpb_column vc_column_container vc_col-sm-12">
					<div class="vc_column-inner ">
						<div class="wpb_wrapper">
							<div class="cms-cta-wrapper cms-style-default clearfix cta-right row">
								<div class="col-lg-8 col-sm-8 col-md-8 col-xs-8 cms-cta-text text-center-md">
									<div class="subtext" style="color:;">Have Any Questions !</div>
									<div class="text" style="color:;">Don't Hesitate To Contact Us Any Time.</div>
								</div>
								<div class="col-lg-4 col-sm-4 col-md-4 col-xs-4 cms-cta-button text-center-md">
									<a href="${digify}/home/contactUs" class=" btn btn size-default"> Contact Us </a>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>

		</div>
	</div>
</section>

<!-- Footer -->
<footer class="bg-light">
	<div class="container-fluid p-0">
		<div id="cshero-footer" class="cshero-footer1">
			<div id="cshero-footer-feature-top">
				<div class="container">
					<div class="row">
						<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
							<div class="cshero-footer-feature-inner clearfix">
								<div class="feature-top-item">
									<div class="media">
										<div class="media-left">
											<span class="lnr lnr-map"><img class="footerimg" src="${digify}/static/frontEnd/img/visit.png" /></span>
										</div>
										
										<div class="media-body">
											<b><span>Visit Us</span></b> ${address }
										</div>
									</div>
								</div>
								<div class="feature-top-item">
									<div class="media">
										<div class="media-left">
											<span class="lnr lnr-envelope"><img class="footerimg" src="${digify}/static/frontEnd/img/email.png" /></span>
										</div>
										<div class="media-body">
											<b><span>Email Us</span></b> <a style="color: #828282; text-decoration: none;" href="mailto:info@digify.net.in">info@digify.net.in</a>
										</div>
									</div>
								</div>
								<div class="feature-top-item">
									<div class="media">
										<div class="media-left">
											<span class="lnr lnr-phone"><img class="footerimg" src="${digify}/static/frontEnd/img/call_0.png" /></span>
										</div>
										<div class="media-body">
											<b><span>Call Us</span></b> <a style="color: #828282; text-decoration: none;" href="tel:0124-6512448">0124-4014785, 9599662884</a>
										</div>

									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div id="cshero-footer-top">
				<div class="container">
<!-- 					<div class="row"> -->
<!-- 						<div class="col-xs-12 col-sm-6 col-md-8 col-lg-8"> -->
							<div class="row">
								<div class="cshero-footer-top1 col-xs-12 col-sm-12 col-md-3 col-lg-3">
									<aside id="text-5" class="widget widget_text">
										<div class="textwidget">
											<div class="media">
												<div class="media-left">
													<img class="logo-img" src="${digify}/static/frontEnd/img/logo.png">
													<div class="media-body">Digify Engineering Pvt. Ltd. maintains an optimal security which is an integral part of any business that not only protects your employees but your assets as well..</div>
												</div>
											</div>
										</div>
									</aside>
								</div>
								<div class="cshero-footer-top2 col-xs-12 col-sm-12 col-md-3 col-lg-3 offset-2">
									<aside id="nav_menu-2" class="widget widget_nav_menu">
										<h3 class="widget-title">Navigation</h3>
										<div class="menu-navigation-container">
											<ul id="menu-navigation" class="menu p-0">
												<li id="menu-item-121"
													class="menu-item menu-item-type-custom menu-item-object-custom no_group menu-item-121" data-depth="0">
													<a href="${digify }/home/aboutUs" class=""><span class="menu-title">About Us</span></a>
												</li>
												<li id="menu-item-122"
													class="menu-item menu-item-type-custom menu-item-object-custom no_group menu-item-122" data-depth="0">
													<a href="${digify }/home/dashBoard/products" class=""><span class="menu-title">Product</span></a>
												</li>
												<!-- <li id="menu-item-123"
													class="menu-item menu-item-type-custom menu-item-object-custom no_group menu-item-123" data-depth="0">
													<a href="#" class=""><span class="menu-title">Careers</span></a>
												</li> -->
												<%-- <li id="menu-item-124"
													class="menu-item menu-item-type-custom menu-item-object-custom no_group menu-item-124" data-depth="0">
													<a href="${digify }/home/team" class=""><span class="menu-title">Team</span></a>
												</li> --%>
												<li id="menu-item-126"
													class="menu-item menu-item-type-custom menu-item-object-custom no_group menu-item-126" data-depth="0">
													<a href="${digify }/home/faq" class=""><span class="menu-title">FAQs</span></a>
												</li>
											</ul>
										</div>
									</aside>
								</div>
							<div class="cshero-footer-top3 col-xs-12 col-sm-6 col-md-3 col-lg-3">
								<aside id="nav_menu-3" class="widget widget_nav_menu">
									<h3 class="widget-title">Products</h3>
									<div class="menu-menu-services-footer-container">
										<ul id="menu-menu-services-footer" class="menu p-0">
											<c:forEach items="${allServices }" var="services" varStatus="loop">
												<c:if test="${loop.count < 6 }">
													<li id="menu-item-${loop.count}"
														class="menu-item menu-item-type-custom menu-item-object-custom no_group menu-item-503"><a
														class="servicemenulink" href="${digify}/home/servicePage/${services.id}"><span
															class="menu-title">${services.serviceName}</span></a></li>
												</c:if>
											</c:forEach>
										</ul>
									</div>
								</aside>
							</div>
						</div>
<!-- 						</div> -->
					
<!-- 					</div> -->
				</div>
			</div>

			<div id="cs-copyright">
				<div class="container">
					<div class="row">
						<div class="col-xs-12 col-sm-12 col-md-6 col-lg-6 textalign-centre">Copyright � DIGIFY Engineering Pvt. Ltd.
							2019. All rights reserved.</div>
					<div class="col-xs-12 col-sm-12 col-md-6 col-lg-6 text-center">
					Powered by Anoman Digital Media Pvt. Ltd.
					</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</footer>

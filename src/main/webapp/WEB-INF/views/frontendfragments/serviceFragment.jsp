
<!-- Product Section -->
<section class="page-section" id="services">
	<div class="container-fluid">
		<div class="row justify-content-center">
			<div class="col-lg-8 text-center">
				<h3 class="serviceheading">What We Can Do?</h3>
				<div class="col-lg-12 col-sm-12 col-md-12 col-xs-12">
					<span class="ourstorydiv1">Our Service</span><span class="dot">.</span>
				</div>
				<p class="mb-4 aboutuscontent">
					Our Services department has been servicing clients for over 20 years. DIGIFY is a service provider to client such
					as The Department of defense,</br> Attorney Generals Department, Transdfield Westfield, Spotless and hundreds more.
				</p>
			</div>
			<div class="col-lg-12 col-sm-12 col-md-12 col-xs-12 p-0">
				<div class="row justify-content-center">
					<nav class="col-lg-12 col-sm-12 col-md-12 col-sm-12 navigationtab">
						<div class="col-lg-12 col-sm-12 col-md-12 col-sm-12 row justify-content-center nav nav-tabs nav-fill"
							style="margin: 0px; padding: 0px;" id="servc-nav-tab" role="tablist">
							<c:forEach items="${allServices }" var="servc" varStatus="todo">
									<a class="col-lg-2 col-sm-2 col-md-2 col-sm-2 nav-item nav-link servicedot"
										id="servc-nav-${todo.index}" data-toggle="tab"  role="tab"
										aria-controls="servc-nav-id-${todo.index}" aria-selected="${todo.index eq 0 ? 'true' : 'false' }">
										<div class="col-lg-12 col-sm-12 col-md-12 col-sm-12 text-center">
											<div class="margint-b servicediv">
												<div class="bgimgservice" style="background-image: url('${servc.iconImgUrl}');"></div>
												<h3 class="h4 mb-2 servicefonttext">${servc.serviceName }</h3>
											</div>
										</div>
									</a>
							</c:forEach>
						</div>
					</nav>
				</div>
				<div class="tab-content col-lg-12 col-sm-12 col-md-12 col-xs-12 bgcolor p-0" id="servc-nav-tabContent">
					<c:forEach items="${allServices }" var="servc" varStatus="todo">
							<div class="tab-pane fade serviceSlide" id="servc-nav-id-${todo.index }"
								role="tabpanel" aria-labelledby="servc-nav-${todo.index }">
								<div class="col-lg-12 col-sm-12 col-md-12 col-xs-12 p-0 row">
									<div class="col-lg-6 col-sm-6 col-md-6 col-xs-12 p-0">
										<img class="img-responsive" style="width: 100%; max-height: 30rem !important;" src="${servc.imageUrl }" />
									</div>
									<div class="col-lg-6 col-sm-6 col-md-6 col-xs-12">
										<p class="texthd">${servc.serviceName }</p>
										<div class="aboutuscontent text">${servc.serviceDescription }</div>
									</div>
								</div>
							</div>
					</c:forEach>

				</div>
			</div>
		</div>
</section>


<!-- Products Section -->
<section class="page-section" id="products">
	<div class="container-fluid">
		<div class="row justify-content-center">
			<div class="col-lg-8 text-center">
				<h3 class="serviceheading">What We Provide?</h3>
				<div class="col-lg-12 col-sm-12 col-md-12 col-xs-12">
					<span class="ourstorydiv1">Our Products</span><span class="dot">.</span>
				</div>
				<p class="mb-4 aboutuscontent">
					Here the product related text</br> This is fixed in html.
				</p>
			</div>
			<div class="col-lg-12 col-sm-12 col-md-12 col-xs-12 p-0">
				<div class="row justify-content-center">
					<nav class="col-lg-12 col-sm-12 col-md-12 col-sm-12 navigationtab">
						<div class="col-lg-12 col-sm-12 col-md-12 col-sm-12 row justify-content-center nav nav-tabs nav-fill"
							style="margin: 0px; padding: 0px;" id="nav-tab" role="tablist">
							<c:forEach items="${allProducts }" var="prod" varStatus="status">
								<c:if test="${status.index lt 4 }">
									<a class="col-lg-2 col-sm-2 col-md-2 col-sm-2 nav-item nav-link ${status.index eq 0 ? 'active' : '' }" 
									id="nav-${status.index}" data-toggle="tab"
										href="#nav-id-${status.index}" role="tab" aria-controls="nav-id-${status.index}"
										aria-selected="${status.index eq 0 ? 'true' : 'false' }">
										<div class="col-lg-12 col-sm-12 col-md-12 col-sm-12 text-center">
											<div class="margint-b servicediv">
												<div class="bgimgservice" style="background-image: url('${prod.iconImgUrl}');"></div>
												<h3 class="h4 mb-2 servicefonttext">${prod.productName }</h3>
											</div>
										</div>
									</a>
								</c:if>
							</c:forEach>
						</div>
					</nav>

				</div>





				<div class="tab-content col-lg-12 col-sm-12 col-md-12 col-xs-12 bgcolor p-0" id="nav-tabContent">
					<c:forEach items="${allProducts }" var="prod" varStatus="status">
						<c:if test="${status.index lt 4 }">
							<div class="tab-pane fade ${status.index eq 0 ? 'show active' : '' }" 
							id="nav-id-${status.index }" role="tabpanel"
								aria-labelledby="nav-${status.index }">
								<div class="col-lg-12 col-sm-12 col-md-12 col-xs-12 p-0 row">
									<div class="col-lg-6 col-sm-6 col-md-6 col-xs-12 p-0">
										<img class="img-responsive" style="width: 100%;" src="${prod.imageUrl }" />
									</div>
									<div class="col-lg-6 col-sm-6 col-md-6 col-xs-12">
										<p class="texthd">${prod.productName }</p>
										<div class="aboutuscontent text">${prod.productDescription }</div>
									</div>
								</div>
							</div>
						</c:if>
					</c:forEach>

				</div>
			</div>
		</div>
</section>

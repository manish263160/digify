
<!-- Products Section -->
<section class="page-section" id="products">
	<div class="container-fluid">
		<div class="row justify-content-center">
			<div class="col-lg-8 text-center">
				<h3 class="serviceheading">What We Provide?</h3>
				<div class="col-lg-12 col-sm-12 col-md-12 col-xs-12">
					<span class="ourstorydiv1">Our Products</span>
				</div>
				<p class="mb-4 aboutuscontent">
					Here the product related text</br> This is fixed in html.
				</p>
			</div>
			<div class="carousel-wrap">
				<div class="owl-carousel prodserviceCarousel" id="prodserviceCarousel">
					<c:forEach items="${allProducts }" var="prod" varStatus="status">
						<div class="item" onclick="showContnet('${fn:length(allProducts)}','${status.index}')">
							<div class="text-center">
								<div class="margint-b servicediv">
									<div class="bgimgservice" style="background-image: url('${prod.iconImgUrl}');"></div>
									<h3 class="h4 mb-2 servicefonttext">${prod.productName }</h3>
								</div>
							</div>
						</div>
					</c:forEach>
				</div>
			</div>
			
			<div class="tab-content col-lg-12 col-sm-12 col-md-12 col-xs-12 bgcolor p-0" id="nav-tabContent">
					<c:forEach items="${allProducts }" var="prod" varStatus="status">
							<div class="tab-pane fade mySlides ${status.index eq 0 ? 'show active' : '' }"    
							 id="productDesc_${status.index }" >
								<div class="col-lg-12 col-sm-12 col-md-12 col-xs-12 p-0 row">
									<div class="col-lg-6 col-sm-6 col-md-6 col-xs-12 p-0 productserviceBackground" style="background-image: url('${prod.imageUrl}');">
<%-- 										<img class="img-responsive" style="width: 100%; max-height: 25rem !important; " src="${prod.imageUrl }" /> --%>
									</div>
									<div class="col-lg-6 col-sm-6 col-md-6 col-xs-12">
										<p class="texthd">${prod.productName }</p>
										<div class="aboutuscontent text">${prod.productDescription }</div>
									</div>
								</div>
							</div>
					</c:forEach>
			
		</div>
	</div>
	</div>
		
</section>

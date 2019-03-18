  <!-- Testimonials Section -->
  <section class="testimonialsection page-section" id="">
    <div class="container">
      <div class="row justify-content-center">
	  <div class="col-lg-8 text-center">
		<h3 class="serviceheading">What People Says!</h3>
          <div class="col-lg-12 col-sm-12 col-md-12 col-xs-12"><span class="ourstorydiv1">Testimonials</span><span class="dot">.</span>
          </div>
        </div>
<!--         <div class="carousel-wrap"> -->
				<div class="owl-carousel" id="testimonials">
					<c:forEach begin="0"  end="9" var="prod" varStatus="status">
						<div class="item" >
							<figure class="snip1192">
								<blockquote>The company's experts have accumulated valuable experience in green development, being savvy to all aspects of the certification process and requirements.</blockquote>
								<div class="author">
									<img src="${digify}/static/frontEnd/img/testimonial.png" alt="sq-sample1" />
									<h5>
										Begha  <span> Art Director</span>
									</h5>
								</div>
							</figure>
						</div>
					</c:forEach>
				</div>
<!-- 			</div> -->
        
      </div>
    </div>
  </section>
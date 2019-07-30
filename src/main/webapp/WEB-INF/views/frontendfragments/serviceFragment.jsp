<!-- Product Section -->
<section class="page-section" id="services">
    <div class="container-fluid">
        <div class="row justify-content-center">
            <div class="col-lg-8 text-center">
                <h3 class="serviceheading">What We Can Do?</h3>
                <div class="col-lg-12 col-sm-12 col-md-12 col-xs-12">
                    <span class="ourstorydiv1">Our Service</span>
                </div>
                <p class="mb-4 aboutuscontent">
                    Our Services department has been servicing clients for over 20 years. DIGIFY is a service provider
                    to client such
                    as The Department of defense,</br> Attorney Generals Department, Transdfield Westfield, Spotless and
                    hundreds more.
                </p>
            </div>
            <div class="carousel-wrap">
                <div class="owl-carousel prodserviceCarousel" id="prodserviceCarousel">
                    <c:forEach items="${allServices }" var="prod" varStatus="status">
                        <div class="item" onclick="showContnetServ('${fn:length(allServices)}','${status.index}')">
                            <div class="text-center">
                                <div class="margint-b servicediv">
                                    <div class="bgimgservice"
                                         style="background-image: url('${prod.iconImgUrl}');"></div>
                                    <h3 class="h4 mb-2 servicefonttext">${prod.serviceName }</h3>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                </div>
            </div>

            <div class="tab-content col-lg-12 col-sm-12 col-md-12 col-xs-12 bgcolor p-0" id="nav-tabContent">
                <c:forEach items="${allServices }" var="prod" varStatus="status">
                    <div class="tab-pane fade mySlides ${status.index eq 0 ? 'show active' : '' }"
                         id="serviceDesc_${status.index }">
                        <div class="col-lg-12 col-sm-12 col-md-12 col-xs-12 p-0 row">
                            <div class="col-lg-6 col-sm-6 col-md-6 col-xs-12 p-0">
                                <img class="img-responsive" style="width: 100%; max-height: 25rem !important; "
                                     src="${prod.imageUrl }"/>
                            </div>
                            <div class="col-lg-6 col-sm-6 col-md-6 col-xs-12">
                                <p class="texthd">${prod.serviceName }</p>
                                <div class="aboutuscontent text">${prod.serviceDescription }</div>
                            </div>
                        </div>
                    </div>
                </c:forEach>


            </div>
        </div>
    </div>
</section>

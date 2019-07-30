<!-- Carousel section -->
<section id="intro">
    <div class="intro-container">
        <div id="introCarousel" class="carousel  slide carousel-fade" data-ride="carousel">

            <ol class="carousel-indicators">
                <c:forEach items="${carouselList }" var="carousel" varStatus="loop">
                    <li data-target="#introCarousel" data-slide-to="${loop.index }"
                        class="${loop.index eq 1 ?'active' : '' }"></li>

                </c:forEach>
            </ol>
            <div class="carousel-inner" role="listbox">
                <c:forEach items="${carouselList }" var="carousel" varStatus="loop">
                    <div class="carousel-item ${loop.index eq 1 ?'active' : '' }"
                         style="background-image: url('${carousel.imageUrl}');height: 727px;background-size: cover;"
                         id="">
                        <div class="carousel-container">
                            <div class="carousel-content">
                                <h1 class="text-white font-weight-bold">
                                    <!--                 DIGIFY Access Control System and security bollard range is India's largest and most comprehensive. -->
                                        ${carousel.contentDescription}
                                </h1>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>

            <a class="carousel-control-prev" href="#introCarousel" role="button" data-slide="prev">
                <span class="carousel-control-prev-icon ion-chevron-left" aria-hidden="true"></span>
                <span class="sr-only">Previous</span>
            </a>

            <a class="carousel-control-next" href="#introCarousel" role="button" data-slide="next">
                <span class="carousel-control-next-icon ion-chevron-right" aria-hidden="true"></span>
                <span class="sr-only">Next</span>
            </a>

        </div>
    </div>
</section>
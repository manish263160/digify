<%@include file="../fragments/includetags.jsp"%>
  <!-- Navigation -->
  <nav class="navbar navbar-expand-lg navbar-light fixed-top py-3 mainnav3" id="mainNav">
    <div class="container">
      <a class="navbar-brand js-scroll-trigger" href="${digify}/"><img class="logo-img" src="${digify}/static/frontEnd/img/logo.png"/></a> 
      <button class="navbar-toggler navbar-toggler-right" type="button" data-toggle="collapse" data-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
      </button>
      <div class="collapse navbar-collapse" id="navbarResponsive">
        <ul class="navbar-nav ml-auto my-2 my-lg-0">
          <li class="nav-item">
          <c:choose>
            <c:when test="${page eq 'home'}"><a class="nav-link js-scroll-trigger" href="#intro">Home</a></c:when>
            <c:otherwise><a class="nav-link js-scroll-trigger" href="${digify}/">Home</a></c:otherwise>
            </c:choose>
          </li>
          <li class="nav-item">
            <c:choose>
            <c:when test="${page eq 'home'}">
			<a class="nav-link js-scroll-trigger" href="#aboutus">About Us</a>
			</c:when>
            <c:otherwise><a class="nav-link js-scroll-trigger" href="${digify }/frontendAction/aboutUs">About Us</a></c:otherwise>
            </c:choose>
          </li>
          <li class="nav-item servicemenu">
          <c:choose>
            <c:when test="${page eq 'home'}">
			<a class="nav-link " href="#">Services <i class="fa fa-caret-down fa-lg caret_custom" aria-hidden="true" ></i></a>
			</c:when>
            <c:otherwise>
            <a class="nav-link " href="#">Services <i class="fa fa-caret-down fa-lg caret_custom" aria-hidden="true" ></i></a>
            </c:otherwise>
            </c:choose>
          <ul class="servicemenuli">
          <c:forEach items="${allProducts }" var="products" varStatus="loop">
		<c:if test="${loop.count lt 10 }">	<li class="servicemenuliin"><a class="servicemenulink" href="${digify}/frontendAction/productPage/${products.id}" ><span><img src="${products.iconImgUrl}"/></span>
		<c:choose>
			<c:when test="${ fn:length(products.productName) gt 20}">
			${ fn:substring(products.productName, 0, 20)}...
			</c:when>
			<c:otherwise>
			${products.productName}
			</c:otherwise>
			</c:choose>
			</a></li>
		</c:if>
          </c:forEach>
			<a style="text-decoration: underline; color: red;" href="${digify}/frontendAction/dashBoard/products">More ...</a>
			</ul>
		  </li>
          <li class="nav-item servicemenu">
          <c:choose>
            <c:when test="${page eq 'home'}">
			<a class="nav-link " href="#">Products
			<i class="fa fa-caret-down fa-lg caret_custom" aria-hidden="true" ></i></a>
			</c:when>
            <c:otherwise>
            <a class="nav-link " href="#">Products
			<i class="fa fa-caret-down fa-lg caret_custom" aria-hidden="true" ></i></a>
            </c:otherwise>
            </c:choose>           
          <ul class="servicemenuli">
			<c:forEach items="${allServices }" var="services" varStatus="loop">
			<c:if test="${loop.count < 10 }"><li class="servicemenuliin"><a class="servicemenulink" href="${digify}/frontendAction/servicePage/${services.id}"><span><img src="${services.iconImgUrl}"/></span>
			<c:choose>
			<c:when test="${ fn:length(services.serviceName) gt 20}">
			${ fn:substring(services.serviceName, 0, 20)}...
			</c:when>
			<c:otherwise>
			${services.serviceName }
			</c:otherwise>
			</c:choose>
			</a></li>
			</c:if>
          </c:forEach>
			<a style="text-decoration: underline; color: red;" href="${digify}/frontendAction/dashBoard/services">More ...</a>
			</ul>
		  </li>
		  <li class="nav-item">
            <a class="nav-link js-scroll-trigger" href="${digify}/frontendAction/amc">AMC</a>
          </li>
		  <li class="nav-item">
            <a class="nav-link js-scroll-trigger" href="${digify}/frontendAction/contactUs">Contact Us</a>
          </li>
		 <!--  <li class="nav-item">
            <a class="nav-link js-scroll-trigger" href="#contact"><div class="callimg"></div></a>
          </li> -->
        </ul>
      </div>
    </div>
  </nav>

  <!-- Masthead -->
  <!--<header class="masthead">
    <div class="container h-100">
      <div class="row h-100 align-items-center justify-content-center text-center">
        <div class="col-lg-10 ">
          <h1 class="text-white font-weight-bold">DIGIFY Access Control System and security bollard range
		  is India's largest and most comprehensive.</h1>
          
        </div>
        
      </div>
    </div>
  </header>-->
  
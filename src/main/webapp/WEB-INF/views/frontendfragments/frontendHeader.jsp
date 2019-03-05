
  <!-- Navigation -->
  <nav class="navbar navbar-expand-lg navbar-light fixed-top py-3" id="mainNav">
    <div class="container">
      <a class="navbar-brand js-scroll-trigger" href="#page-top"><img class="logo-img" src="${digify}/static/frontEnd/img/logo.png"/></a> <a href="${digify}/login">Admin Login</a>
      <button class="navbar-toggler navbar-toggler-right" type="button" data-toggle="collapse" data-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
      </button>
      <div class="collapse navbar-collapse" id="navbarResponsive">
        <ul class="navbar-nav ml-auto my-2 my-lg-0">
          <li class="nav-item">
            <a class="nav-link js-scroll-trigger" href="#intro">Home</a>
          </li>
          <li class="nav-item">
            <a class="nav-link js-scroll-trigger" href="#aboutus">About Us</a>
          </li>
          <li class="nav-item servicemenu">
            <a class="nav-link js-scroll-trigger" href="#products">Product
			<i class="fa fa-caret-down fa-lg" aria-hidden="true" style="padding-left: 10px; color:#716e6d;"></i></a>
          <ul class="servicemenuli">
          <c:forEach items="${allProducts }" var="products">
			<li class="servicemenuliin"><a class="servicemenulink"><span><img src="${products.iconImgUrl}"/></span>${products.productName}</a></li>
          </c:forEach>
			</ul>
		  </li>
          <li class="nav-item servicemenu">
            <a class="nav-link js-scroll-trigger" href="#services">Services
			<i class="fa fa-caret-down fa-lg" aria-hidden="true" style="padding-left: 10px; color:#716e6d;"></i></a>
          <ul class="servicemenuli">
			<c:forEach items="${allServices }" var="services">
			<li class="servicemenuliin"><a class="servicemenulink"><span><img src="${services.iconImgUrl}"/></span>${services.serviceName}</a></li>
          </c:forEach>
			</ul>
		  </li>
		  <li class="nav-item">
            <a class="nav-link js-scroll-trigger" href="#contact">AMC</a>
          </li>
		  <li class="nav-item">
            <a class="nav-link js-scroll-trigger" href="#contact">Contact Us</a>
          </li>
		  <li class="nav-item">
            <a class="nav-link js-scroll-trigger" href="#contact"><div class="callimg"></div></a>
          </li>
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
  
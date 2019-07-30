<%@include file="../fragments/includetags.jsp" %>
<security:authorize access="isAuthenticated()">
    <security:authentication property="principal.email" var="loggedInUser"/>
    <security:authentication property="principal.name" var="loggedInUserName"/>
    <security:authentication property="principal.id" var="loggedInUserId"/>
    <security:authentication property="principal.userCart" var="userCart"/>

    <input type="hidden" id="userName" name="userName" value="${loggedInUser}"/>
</security:authorize>
<%--<c:out value="${userCart.listOfService}"></c:out>--%>
<c:set var="userCart" value="${sessionScope.myCart }" ></c:set>
<c:set var="cartCount" value="0"></c:set>
<c:if test="${not empty userCart}">
    <c:if test="${ not empty userCart.listOfService && fn:length(userCart.listOfService) gt 0}">
        <c:set var="sizeOfservice" value="${fn:length(userCart.listOfService)}"></c:set>
        <c:set var="cartCount" value="${cartCount + sizeOfservice}"></c:set>
    </c:if>
    <c:if test="${not empty userCart.listOfProducts && fn:length(userCart.listOfProducts) gt 0}">
        <c:set var="sizeOfproducts" value="${fn:length(userCart.listOfProducts)}"></c:set>
        <c:set var="cartCount" value="${cartCount + sizeOfproducts}"></c:set>
    </c:if>
</c:if>
<!-- Navigation -->
<nav class="navbar navbar-expand-lg navbar-light fixed-top py-3 mainnav3" id="mainNav">
    <div class="container">
        <a class="navbar-brand js-scroll-trigger" href="${digify}/"><img class="logo-img"
                                                                         src="${digify}/static/frontEnd/img/logo.png"/></a>
        <button class="navbar-toggler navbar-toggler-right" type="button" data-toggle="collapse"
                data-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false"
                aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarResponsive">
            <ul class="navbar-nav ml-auto my-2 my-lg-0">
                <li class="nav-item">
                    <c:choose>
                        <c:when test="${page eq 'home'}"><a class="nav-link js-scroll-trigger"
                                                            href="#intro">Home</a></c:when>
                        <c:otherwise><a class="nav-link js-scroll-trigger" href="${digify}/">Home</a></c:otherwise>
                    </c:choose>
                </li>
                <li class="nav-item servicemenu">
                    <a class="nav-link trigger" href="#">About Us <i
                            class="fa fa-caret-down fa-lg caret_custom" aria-hidden="true"></i></a>
                    <ul class="servicemenuli about">
                        <li class="servicemenuliin about"><a class="servicemenulink about"
                                                             href="${digify }/home/aboutUs"><span>&nbsp;</span>Company
                            profile</a></li>
                        <li class="servicemenuliin about"><a class="servicemenulink about"
                                                             href="${digify }/home/areaofOperations"><span>&nbsp;</span>Area
                            of Operations</a></li>
                    </ul>
                </li>
                <li class="nav-item servicemenu">
                    <c:choose>
                        <c:when test="${page eq 'home'}">
                            <a class="nav-link trigger" href="#">Services <i class="fa fa-caret-down fa-lg caret_custom"
                                                                             aria-hidden="true"></i></a>
                        </c:when>
                        <c:otherwise>
                            <a class="nav-link trigger" href="#">Services <i class="fa fa-caret-down fa-lg caret_custom"
                                                                             aria-hidden="true"></i></a>
                        </c:otherwise>
                    </c:choose>
                    <ul class="servicemenuli">
                        <c:forEach items="${allProducts }" var="products" varStatus="loop">
                            <c:if test="${loop.count lt 10 }">
                                <li class="servicemenuliin"><a class="servicemenulink text_deco_none"
                                                               href="${digify}/home/productPage/${products.id}"><span><img
                                        src="${products.iconImgUrl}"/></span>
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
                        <a style="text-decoration: underline; color: red;" href="${digify}/home/dashBoard/products">More
                            ...</a>
                    </ul>
                </li>
                <li class="nav-item servicemenu">
                    <c:choose>
                        <c:when test="${page eq 'home'}">
                            <a class="nav-link trigger" href="#">Products
                                <i class="fa fa-caret-down fa-lg caret_custom" aria-hidden="true"></i></a>
                        </c:when>
                        <c:otherwise>
                            <a class="nav-link trigger" href="#">Products
                                <i class="fa fa-caret-down fa-lg caret_custom" aria-hidden="true"></i></a>
                        </c:otherwise>
                    </c:choose>
                    <ul class="servicemenuli">
                        <c:forEach items="${allServices }" var="services" varStatus="loop">
                            <c:if test="${loop.count < 10 }">
                                <li class="servicemenuliin"><a class="servicemenulink text_deco_none"
                                                               href="${digify}/home/servicePage/${services.id}"><span><img
                                        src="${services.iconImgUrl}"/></span>
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
                        <a style="text-decoration: underline; color: red;" href="${digify}/home/dashBoard/services">More
                            ...</a>
                    </ul>
                </li>
                <li class="nav-item">
                    <a class="nav-link js-scroll-trigger" href="${digify}/home/amc">AMC</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link js-scroll-trigger" href="${digify}/home/contactUs">Contact Us</a>
                </li>
                <!--  <li class="nav-item">
                   <a class="nav-link js-scroll-trigger" href="#contact"><div class="callimg"></div></a>
                 </li> -->
                <security:authorize access="hasRole('ROLE_USER')">
                    <li class="nav-item">
                        <a class="nav-link js-scroll-trigger col s3" href="${digify}/user/getUserOrders"> <span>My Order</span></a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link js-scroll-trigger col s3" href="${digify}/ecommerce/getUserCart/${loggedInUserId}"><i
                                class="fa fa-cart-plus cart-image" title="My Cart"></i> &#40;<span
                                id="cartNumber">${cartCount}</span>&#41;</a>
                    </li>
                    <li><a href="${digify}/logout" title="logout">Logout</a></li>
                </security:authorize>
                <security:authorize access="isAnonymous()">
                    <li class="nav-item">
                        <a class="nav-link js-scroll-trigger" href="${digify}/login">Login</a>
                    </li>
                </security:authorize>

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
  
<%@include file="../fragments/includetags.jsp" %>

<!DOCTYPE html>
<html lang="en">

<head>
    <jsp:include page="../includes/web_css.jsp"/>
</head>

<body id="page-top">
<%@include file="../frontendfragments/frontendHeader.jsp" %>

<div class="wrapper">

    <section class="inner-banner inner-banner-about">
        <div class="thm-container clearfix">
            <h2 class="pull-left">About Us</h2>
            <ul class="breadcumb pull-right">
                <li><a href="${digify }" class="home-fa"><i class="fa fa-home"></i> Home</a></li>
                <li><span>About Us</span></li>
            </ul>
        </div>
    </section>


    <section class="services-section sec-padding contact-page-content ">
        <div class="thm-container">
            <div class="sec-title">
                <h2>
                    <span>About Us</span>
                </h2>
            </div>
            <c:forEach items="${list }" var="loop">
                <div class="row" style="padding-bottom: 20px;">
                    <div class="col-md-6 col-sm-6 col-xs-12 pull-left">
                        <div class="left-box">
                            <div class="img-box shadow">
                                <img src="${loop.imageUrl }" alt="Awesome Image" width="100%">
                            </div>
                        </div>
                    </div>
                    <div class="col-md-6 col-sm-6 col-xs-12 pull-rigth">
                        <div class="right-box">
                            <div class="sec-title text-justify">
                                    ${loop.contentDescription }
                            </div>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>
    </section>
</div>
<%@include file="../frontendfragments/frontEndFooter.jsp" %>
<jsp:include page="../includes/web_js.jsp"/>

</body>
</html>

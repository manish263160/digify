<%@include file="../fragments/includetags.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1.0, user-scalable=no">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="msapplication-tap-highlight" content="no">
    <title>${heading} </title>
    <jsp:include page="../includes/admin_css.jsp"></jsp:include>
    <link href="${digify}/static/lib/css/page-center.css" type="text/css" rel="stylesheet" media="screen,projection">
</head>
<body class="${themecolor }">
<div id="alertmsg"></div>
<div class="container">
    <div class="row">
        <div class="col s12 m12 l12">
            <div class="card  ${colorClass }">
                <div class="card-content white-text">
                    <span class="card-title">${heading}</span>
                    <p>${msg}<p>
                </div>
                <div class="card-action">
                    <a href="${digify}/login" class="lime-text text-accent-1">Login</a>
                </div>
            </div>
        </div>
    </div>
</div>
<jsp:include page="../includes/admin_js.jsp"/>

</body>
</html>
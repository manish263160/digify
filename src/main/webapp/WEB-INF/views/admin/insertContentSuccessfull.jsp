<%@include file="../fragments/includetags.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Image Upload</title>
    <%@include file="../fragments/admin_header.jsp" %>
</head>
<body class="${themecolor }">

<div class="container">
    <div class="row">
        <div class="col s8 m6 l6 offset-l4">
            <div class="card">
                <%-- <div class="card-image">
                    <c:if test="${not empty imagepath }">
                        <img src="${imagepath }" alt="sample">
                    </c:if>

                </div> --%>
                <div class="card-content">
                    <p>The content uploaded successfully.</p>
                </div>
                <div class="card-action">
                    <a href="${digify}/admin/dashboard" class="btn waves-effect waves-red light-blue darken-4">Admin
                        Dash Board</a>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
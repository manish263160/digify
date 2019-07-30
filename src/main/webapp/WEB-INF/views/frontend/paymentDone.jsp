<%@include file="../fragments/includetags.jsp"%>

<!DOCTYPE html PUBLIC '-//W3C//DTD HTML 4.01 Transitional//EN' 'http://www.w3.org/TR/html4/loose.dtd'>
<html lang="en">

<head>
<%@include file="../includes/web_css.jsp"%>
<title>${msg}</title>
<style type="text/css">
.alert-message
{
    margin: 20px 0;
    padding: 20px;
    border-left: 3px solid #eee;
}
.alert-message h4
{
    margin-top: 0;
    margin-bottom: 5px;
}
.alert-message p:last-child
{
    margin-bottom: 0;
}
.alert-message code
{
    background-color: #fff;
    border-radius: 3px;
}
.alert-message-success
{
    background-color: #F4FDF0;
    border-color: #3C763D;
}
.alert-message-success h4
{
    color: #3C763D;
}
.alert-message-danger
{
    background-color: #fdf7f7;
    border-color: #d9534f;
}
.alert-message-danger h4
{
    color: #d9534f;
}
.alert-message-warning
{
    background-color: #fcf8f2;
    border-color: #f0ad4e;
}
.alert-message-warning h4
{
    color: #f0ad4e;
}
.alert-message-info
{
    background-color: #f4f8fa;
    border-color: #5bc0de;
}
.alert-message-info h4
{
    color: #5bc0de;
}
.alert-message-default
{
    background-color: #EEE;
    border-color: #B4B4B4;
}
.alert-message-default h4
{
    color: #000;
}
.alert-message-notice
{
    background-color: #FCFCDD;
    border-color: #BDBD89;
}
.alert-message-notice h4
{
    color: #444;
}
</style>
</head>
<body id="page-top">
	<%@include file="../frontendfragments/frontendHeader.jsp"%>
	<div class="container">
			<div class="row" style="margin: 14rem 0px 0px 19rem;">
				<div class="col-md-12 col-sm-6 col-xs-12">
					<div class="alert-message   ${status eq 0 ? 'alert-message-danger' : 'alert-message-success' }">
						<c:if test="${status eq 1 }">
								<h4>${msg}</h4>
								<p>Your payment has done</p>
								
							<strong> Order Id : ${orderId }</strong><br>
							<strong>payment Id : ${transactionId } </strong>
						</c:if>
						<c:if test="${status eq 0 }">
							<<h4>${msg}</h4>
							<p>Your payment has failed.</p>
						</c:if>

					</div>
				</div>
			</div>
		</div>
</body>
</html>
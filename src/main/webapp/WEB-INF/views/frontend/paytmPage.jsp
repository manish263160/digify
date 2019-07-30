<%@include file="../fragments/includetags.jsp"%>

<!DOCTYPE html PUBLIC '-//W3C//DTD HTML 4.01 Transitional//EN' 'http://www.w3.org/TR/html4/loose.dtd'>
<html lang="en">

<head>
<title>Title</title>
</head>
<body>
        <center><h1>Please do not refresh this page...</h1></center>
        <form method="post" action="${PAYTM_URL }" name="f1">
            <table border="1">
                <tbody>
                    <input type="hidden" name="MID" value="${MID }" /> 
                    <input type="hidden" name="WEBSITE" value="WEBSTAGING" />
                    <input type="hidden" name="ORDER_ID" value="${randomOrder }">
                    <input type="hidden" name="CUST_ID" value="${user.id }">
                    <input type="hidden" name="MOBILE_NO" value="${user.mobileNo }">
                    <input type="hidden" name="EMAIL" value="${user.email }">
                    <input type="hidden" name="INDUSTRY_TYPE_ID" value="Retail">
                    <input type="hidden" name="CHANNEL_ID" value="WEB">
                    <input type="hidden" name="TXN_AMOUNT" value="100.12">
                    <input type="hidden" name="CALLBACK_URL" value="${callbackUrl}">
                    <input type="hidden" name="CHECKSUMHASH" value="${paytmChecksum }">
                </tbody>
            </table>
        <script type="text/javascript">
            document.f1.submit();
        </script>
        </form>
    </body>
</html>
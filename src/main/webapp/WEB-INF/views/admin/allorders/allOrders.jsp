<%@include file="../../fragments/includetags.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Image Upload</title>
    <link href="${digify}/static/lib/css/magnific-popup.css" type="text/css"
          rel="stylesheet" media="screen,projection">
    <link href="http://cdn.datatables.net/1.10.6/css/jquery.dataTables.min.css" type="text/css" rel="stylesheet" media="screen,projection">
    <style type="text/css">
     select{
     display: block !important;
     }
    </style>
</head>
<body class="${themecolor }">
<jsp:include page="../../fragments/admin_header.jsp"/>
<div class="container">
 <div class="section">
  <p class="caption">All Orders</p>
   <div class="row">
               
                <div class="col s12 m8 l9">
                  <table id="data-table-simple" class="responsive-table display" cellspacing="0">
                    <thead>
                        <tr>
                           <th>User Id</th>
							<th>ORDERID</th>
							<th>BANKNAME</th>
							<th>BANKTXNID</th>
							<th>CURRENCY</th>
							<th>GATEWAYNAME</th>
							<th>MID</th>
							<th>PAYMENTMODE</th>
							<th>RESPCODE</th>
							<th>RESPMSG</th>
							<th>STATUS</th>
							<th>TXNAMOUNT</th>
							<th>TXNDATE</th>
							<th>TXNID</th>
							<th>CHECKSUMHASH</th>
							<th>BIN_NUMBER</th>
							<th>CARD_LAST_NUMS</th>
                        </tr>
                    </thead>
                 
                    <tfoot>
                        <tr>
                             <th>User Id</th>
							<th>ORDERID</th>
							<th>BANKNAME</th>
							<th>BANKTXNID</th>
							<th>CURRENCY</th>
							<th>GATEWAYNAME</th>
							<th>MID</th>
							<th>PAYMENTMODE</th>
							<th>RESPCODE</th>
							<th>RESPMSG</th>
							<th>STATUS</th>
							<th>TXNAMOUNT</th>
							<th>TXNDATE</th>
							<th>TXNID</th>
							<th>CHECKSUMHASH</th>
							<th>BIN_NUMBER</th>
							<th>CARD_LAST_NUMS</th>
                        </tr>
                    </tfoot>
                 
                    <tbody>
                        <c:forEach var="items" items="${userAllOrders}">
                        <tr>
                           <td>${items.userId}</td>
							<td><a href="${digify}/admin/getOrderByOrderId/${items.orderid}">${items.orderid}</a></td>
							<td>${items.bankname}</td>
							<td>${items.banktxnid}</td>
							<td>${items.currency}</td>
							<td>${items.gatewayname}</td>
							<td>${items.mid}</td>
							<td>${items.paymentmode}</td>
							<td>${items.respcode}</td>
							<td>${items.respmsg}</td>
							<td>${items.status}</td>
							<td>${items.txnamount}</td>
							<td>${items.txndate}</td>
							<td>${items.txnid}</td>
							<td>${items.checksumhash}</td>
							<td>${items.binNumber}</td>
							<td>${items.cardLastNums}</td>
                        </tr>
                        </c:forEach>
                    </tbody>
                  </table>
                </div>
                </div>
           
   </div>
   </div> 
<jsp:include page="../../includes/admin_js.jsp"/>
 <!--scrollbar-->
    <script type="text/javascript" src="${digify}/static/lib/js/jquery.dataTables.min.js"></script>
</body>
<script type="text/javascript">
$(document).ready(function(){
    $('#data-table-simple').DataTable();
    
  

    });
</script>
</html>

<%@include file="../fragments/includetags.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Image Upload</title>
<link href="${digify}/static/lib/css/magnific-popup.css" type="text/css" rel="stylesheet" media="screen,projection">
</head>
<body class="${themecolor }">
	<jsp:include page="../fragments/admin_header.jsp" />

	<div class="container">
		<div class="progress" style="display: none;">
			<div class="indeterminate"></div>
		</div>

		<div class="section">
			<p class="caption">All ${tableName} Images that uploaded by ${user.name }</p>
			<div class="row">
				<p class="col s3">
					<a href="${digify}/admin/product/add/addProductView/${tableName}"
						class="btn-floating btn-large waves-effect waves-light green accent-3 tooltipped" data-position="bottom"
						data-delay="50" data-tooltip="Upload product"> <i class="mdi-content-add ">Upload ${tableName} Image</i>
					</a>
				</p>
			</div>
			<!-- Modal Structure -->
			<div id="deleteAllCarausel" class="modal card pink lighten-5">
				<div class="modal-content ">
					<h4>Delete Confirmation</h4>
					<p>Your all product from data will be deleted permanently, and can't be undone.</p>
					<p>Are you sure you want to delete all news?</p>
				</div>
				<div class="modal-fixed-footer card-action pink lighten-4">
					<a href="#" class="waves-effect waves-red btn-flat modal-action modal-close pink-text">Disagree</a> <a href="#"
						class="waves-effect waves-green btn-flat modal-action modal-close pink-text" onclick="deleteAllImg('All','All')">Agree</a>
				</div>
			</div>

			<div class="divider"></div>
			<c:choose>
				<c:when test="${ not empty list && tableName eq 'products'}">

					<p class="caption">
						<b>product Image List</b>
					</p>
						<div class="row" >
					<c:forEach items="${list }" var="l" varStatus="loop">
							<div class="col s6 m3 l3">
								<div class="card">
									<div class="card-image">
										<img src="${l.imageUrl }" alt="sample" class="equal_height_img">
										 <span class="card-title"></span>
									</div>
									<div class="card-content">
										<p  class="tooltipped" data-position="bottom" data-delay="50"  >
										${l.productName}</p>
									</div>
									<div class="card-action">
										<a href="${digify}/admin/product/editProductServiceView/${tableName }/${l.id}">Edit</a> <a href="#"
											onclick="deleteAllImg('${l.id }','${l.productImage}' ,'${tableName }')">Delete</a>
									</div>
								</div>
							</div>

						<div class="divider"></div>
					</c:forEach>
						</div>
				</c:when>
				
				<c:when test="${ not empty list && tableName eq 'services'}">

					<p class="caption">
						<b>services Image List</b>
					</p>
						<div class="row">
					<c:forEach items="${list }" var="l" varStatus="loop">
							<div class="col s6 m3 l3">
								<div class="card">
									<div class="card-image">
										<img src="${l.imageUrl }" alt="sample" class="equal_height_img"> 
										<span class="card-title"></span>
									</div>
									<div class="card-content">
										<p class="tooltipped" data-position="bottom" data-delay="50" >
											${l.serviceName}</p>
									</div>
									<div class="card-action">
										<a href="${digify}/admin/product/editProductServiceView/${tableName}/${l.id}">Edit</a> <a href="#"
											onclick="deleteAllImg('${l.id }','${l.serviceImage}' ,'${tableName }')">Delete</a>
									</div>
								</div>
							</div>

						<div class="divider"></div>
					</c:forEach>
						</div>


				</c:when>
				<c:otherwise>
					<div class="row warningmodel">
						<div class="col s8 m6 l6 offset-l4">
							<div class="card">
								<%-- <div class="card-image">
						<c:if test="${not empty imagepath }">
							<img src="${imagepath }" alt="sample">
						</c:if>

					</div> --%>
								<div class="card-content">
									<p>There is no ${tableName}.Please click upload button to upload.</p>
								</div>

							</div>
						</div>
					</div>
					<div class="masonry-gallery-wrapper">
						<div class="popup-gallery"></div>
					</div>
				</c:otherwise>
			</c:choose>
		</div>


	</div>
	<jsp:include page="../includes/admin_js.jsp" />
	<script type="text/javascript" src="${digify}/static/lib/js/masonry.pkgd.min.js"></script>
	<script type="text/javascript" src="${digify}/static/lib/js/imagesloaded.pkgd.min.js"></script>
	<script type="text/javascript" src="${digify}/static/lib/js/jquery.magnific-popup.min.js"></script>
	<script type="text/javascript">
	
    $(document).ready(function(){
	    // the "href" attribute of .modal-trigger must specify the modal ID that wants to be triggered
    	$('.tooltipped').tooltip({delay: 50});
//     	$('#deleteAllCarausel').modal();
    	$('.datepicker').pickadate({
    	    selectMonths: true, 
    	    selectYears: 15,
    	    max: new Date(),
    		min : -7,
    		format : 'yyyy-mm-dd'

    	  });
	  });
      
      
      function deleteAllImg(contentId,imageName ,tableName ){
    	  console.log("contentId==="+contentId+" imageName=="+imageName+" tableName : "+tableName);
    	  imageName=imageName.trim();
    	  $(".progress").show();
    	  $.ajax({
    		    url: '${digify}/admin/product/deleteContent/'+contentId+'/'+tableName+'?imageName='+imageName,
    		    type: 'DELETE',
    		    success: function(result) {
    		        // Do something with the result
    		        console.log("result=="+result)
/*     		      	  $(".progress").hide();	 */
    		        if(result === 'success'){
    		        	location.reload();
    		        }
    		    }
    		});
    	  
      }
      
  
    </script>

</body>



</html>

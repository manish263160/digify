
<%@include file="../fragments/includetags.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Image Upload</title>
<link href="${digify}/static/lib/css/dropify.min.css" type="text/css"
	rel="stylesheet" media="screen,projection">
<style type="text/css">
#input_file-error {
	color: red !important;
	padding-top: 5% !important;
}

#categoryId {
	height: 100px;
}
</style>

</head>
<body class="${themecolor }">
	<jsp:include page="../fragments/admin_header.jsp" />
<c:if test="${not empty tableName }">
	<c:set var="productService" value="" scope="page"></c:set>
	<c:choose>
		<c:when test="${tableName eq 'products' }">
			<c:set var="productService" value="product"></c:set>
		</c:when>
		<c:when test="${tableName eq 'services' }">
			<c:set var="productService"  value="service"></c:set>
		</c:when>
	</c:choose>
	</c:if>
	<div class="container">
		<c:if test="${not empty error}">
			<div id="card-alert" class="card red">
				<div class="card-content white-text">
					<p>
						<i class="mdi-alert-error"></i> ${error}
					</p>
				</div>
				<button type="button" class="close white-text" data-dismiss="alert"
					aria-label="Close">
					<span aria-hidden="true">×</span>
				</button>
			</div>
		</c:if>
		<div id="card-alert" class="card red hide">
			<div class="card-content white-text">
				<p>
					<i class="mdi-alert-error"></i> This ${tableName} already exist.
				</p>
			</div>
			<button type="button" class="close white-text" data-dismiss="alert"
				aria-label="Close">
				<span aria-hidden="true">×</span>
			</button>
		</div>
		<form
			action="${digify}/admin/product/updateProductService?${_csrf.parameterName}=${_csrf.token}"
			method="post" modelAttribute="${tableName }"
			enctype="multipart/form-data" id="formupload">
			<div class="divider"></div>
			<div class="row section">
				<div class="col s12 m4 l3">
					<p>Select An Image to edit (Max 2MB)</p>
				</div>
				<div class="col s8 m4 l7">
					<input type="file" id="input_file" name="file" class="dropify"
						data-height="150" data-max-file-size="2M" />
				</div>
			</div>
			<div class="row section">
			<div class="col s12 m4 l3">
			<p>Old Image</p>
			</div>
				<img alt="old_img" src="${obj.imageUrl}" class="responsive-img valign" height="200" width="200">
				<input name="oldImageURL" type="hidden" value="${tableName eq 'products' ? obj.productImage : obj.serviceImage }">
			</div>
			<div class="row section">
				<div class="col s12 m4 l3">
					<p> Image External Link</p>
				</div>

				<div class="input-field col s8 m4 l4">
					<input id="externalLink" type="text" class="validate" value="${obj.externalLink}" onchange="checkImageLink(this)"
						autocomplete="off" name="externalLink" maxlength="250"> <label for="email" class="">Image External Link</label>
				</div>
			</div>
			<div class="row section">
				<div class="col s12 m4 l3">
					<p>${productService} Name</p>
				</div>

				<div class="input-field col s8 m4 l7">
					<input id="${productService}name" type="text" class="materialize-textarea" maxlength="250" autocomplete="off"
					 value="${tableName eq 'products' ? obj.productName : obj.serviceName}"
						name="${productService}Name"></input> <label for="${productService}name" class="">${productService} Name</label>
				</div>
			</div>
			<div class="row section">
				<div class="col s12 m4 l3">
					<p>${productService} Description</p>
				</div>

				<div class="input-field col s8 m4 l7">
					<textarea id="description" type="text" class="materialize-textarea" maxlength="2000" autocomplete="off"
						name="${productService}Description">${tableName eq 'products' ? obj.productDescription : obj.serviceDescription}</textarea>
					<label for="description" class="">${productService} Description</label>
				</div>
			</div>
			<div class="row section">
				<input type="hidden" value="${contentId}" name="contentId">
				<input name="tableName" value="${tableName }" type="hidden">
				<div class="col s12 m8 l9  center" style="padding-right: 193px;">
					<button class="btn btn-large waves-effect waves-light red darken-4"
						type="submit">Edit</button>
				</div>
			</div>
		</form>
	</div>


	<jsp:include page="../includes/admin_js.jsp" />
	<script type="text/javascript"
		src="${digify}/static/lib/js/dropify.min.js"></script>
	<script type="text/javascript">
	        $(document).ready(function(){
	            // Basic
	            $('.dropify').dropify();
	
	            // Translated
	            $('.dropify-fr').dropify({
	                messages: {
	                    default: 'Glissez-déposez un fichier ici ou cliquez',
	                    replace: 'Glissez-déposez un fichier ou cliquez pour remplacer',
	                    remove:  'Supprimer',
	                    error:   'Désolé, le fichier trop volumineux'
	                }
	            });
	
	            // Used events
	            var drEvent = $('.dropify-event').dropify();
	
	            drEvent.on('dropify.beforeClear', function(event, element){
	                return confirm("Do you really want to delete \"" + element.filename + "\" ?");
	            });
	
	            drEvent.on('dropify.afterClear', function(event, element){
	                alert('File deleted');
	            });
	        });
	        
	        $("#formupload").validate({
	            rules: {
	            	/* imageLink: {
	                    required: true,
	                   
	                }, */
	               
	                productName :{
	                	required: true,
	                },
	                productDescription :{
	                	required: true,
	                },
	                serviceName :{
	                	required: true,
	                },
	                serviceDescription :{
	                	required: true,
	                },
	           },
	            //For custom messages
	            messages: {

	            },
	            errorElement : 'div',
	            errorPlacement: function(error, element) {
	              var placement = $(element).data('error');
	              if (placement) {
	                $(placement).append(error)
	              } else {
	                error.insertAfter(element);
	              }
	            }
	         });	        
	       
	        function checkImageLink(text){
	        	var textValue=text.value;
	        	$.post("${digify}/checkVideoLink", {urllink: textValue , from :'image'}, function(result){
	            	console.log(result)
	        		if(result){
	        			$("#card-alert").removeClass("hide");
	        			$("#imgLink").val("");
	        		}else{
	        			$("#card-alert").addClass("hide");
	        		}
	            });
	        	
	        }
	        
	        function changeSelect(){
	        	console.log("------",$("#categoryId").val());
	        }
	    </script>

</body>
</html>
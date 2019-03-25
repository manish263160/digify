
<%@include file="../fragments/includetags.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Edit Product and Services</title>
	<%@include file="../fragments/admin_header.jsp" %>
<link href="${digify}/static/lib/css/dropify.min.css" type="text/css"
	rel="stylesheet" media="screen,projection">
	<link rel="stylesheet" href="//netdna.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
<link href="${digify}/static/lib/richtext/richtext.min.css" type="text/css" rel="stylesheet" media="screen,projection">
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
<c:if test="${not empty tableName }">
	<c:set var="productService" value="" scope="page"></c:set>
	<c:set var="gms" value="" scope="page"></c:set>
	<c:choose>
		<c:when test="${tableName eq 'products' }">
			<c:set var="productService" value="product"></c:set>
			<c:set var="gms"  value="product"></c:set>
		</c:when>
		<c:when test="${tableName eq 'services' }">
			<c:set var="productService"  value="service"></c:set>
			<c:set var="gms"  value="Gate Management System"></c:set>
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
					<i class="mdi-alert-error"></i> This ${gms} already exist.
				</p>
			</div>
			<button type="button" class="close white-text" data-dismiss="alert"
				aria-label="Close">
				<span aria-hidden="true">×</span>
			</button>
		</div>
		<div class="row section">
			<div class="col s12 m12 l12">
				<h5>Edit form of ${gms} : ${tableName eq 'products' ? obj.productName : obj.serviceName}</h5>
			</div>
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
						data-height="150" data-max-file-size="5M" />
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
					<p>Select Icon image</p>
				</div>
				<div class="col s4 m4 l4">
					<input type="file" id="input_file" name="file" class="dropify"
						data-height="150" data-max-file-size="20K" />
				</div>
			</div>
			<div class="row section">
			<div class="col s12 m4 l3">
			<p>Old Icon Image</p>
			</div>
				<img alt="old_icon_img" src="${obj.iconImgUrl}" class="responsive-img valign" height="100" width="100">
				<input name="oldIconImageURL" type="hidden" value="${obj.iconImg }">
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
					<p>${gms} Name</p>
				</div>

				<div class="input-field col s8 m4 l7">
					<input id="${productService}name" type="text" class="materialize-textarea" maxlength="250" autocomplete="off"
					 value="${tableName eq 'products' ? obj.productName : obj.serviceName}"
						name="${productService}Name"></input> <label for="${productService}name" class="">${gms} Name</label>
				</div>
			</div>
			<div class="row section">
				<div class="col s12 m4 l3">
					<p>${gms} Description</p>
				</div>

				<div class="input-field col s8 m4 l7">
					<textarea id="description" type="text"  maxlength="2000" autocomplete="off"
						name="${productService}Description">${tableName eq 'products' ? obj.productDescription : obj.serviceDescription}</textarea>
<%-- 					<label for="description" class="">${productService} Description</label> --%>
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


	<%@include file="../includes/admin_js.jsp" %>
	<script type="text/javascript"
		src="${digify}/static/lib/js/dropify.min.js"></script>
		<script type="text/javascript" src="${digify}/static/lib/richtext/jquery.richtext.min.js"></script>
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
	        
	        $('#description').richText({
        		fonts: false,
        		fontSize: false,
        		fontColor: false,
        		 imageUpload: false,
        		 fileUpload: false,
        		 table: false,
        		 videoEmbed: false,
        	});
	    </script>

</body>
</html>
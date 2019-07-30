<%@include file="../fragments/includetags.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Image Upload</title>
    <link href="${digify}/static/lib/css/dropify.min.css" type="text/css"
          rel="stylesheet" media="screen,projection">
    <link rel="stylesheet" href="//netdna.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
    <link href="${digify}/static/lib/richtext/richtext.min.css" type="text/css" rel="stylesheet"
          media="screen,projection">
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
<jsp:include page="../fragments/admin_header.jsp"/>

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
                <span aria-hidden="true">�</span>
            </button>
        </div>
    </c:if>
    <div id="card-alert" class="card red hide">
        <div class="card-content white-text">
            <p>
                <i class="mdi-alert-error"></i> This carousel already exist.
            </p>
        </div>
        <button type="button" class="close white-text" data-dismiss="alert"
                aria-label="Close">
            <span aria-hidden="true">�</span>
        </button>
    </div>
    <form
            action="${digify}/admin/updateHomeContent?${_csrf.parameterName}=${_csrf.token}"
            method="post" modelAttribute="homeComponent"
            enctype="multipart/form-data" id="formupload">
        <div class="divider"></div>
        <div class="row section">
            <div class="col s12 m4 l3">
                <p>Select An Image to edit (Max 2MB)</p>
            </div>
            <div class="col s8 m4 l7">
                <input type="file" id="input_file" name="file" class="dropify"
                       data-height="150" data-max-file-size="5M"/>
            </div>
        </div>
        <div class="row section">
            <div class="col s12 m4 l3">
                <p>Old Image</p>
            </div>
            <img alt="old_img" src="${homeContentObj.imageUrl }" class="responsive-img valign" height="200" width="200">
            <input name="oldImageURL" type="hidden" value="${homeContentObj.imageName }">
        </div>
        <div class="row section">
            <div class="col s12 m4 l3">
                <p>Image Link</p>
            </div>

            <div class="input-field col s8 m4 l4">
                <input id="imgLink" type="text" class="validate" value="${homeContentObj.imageLink}"
                       onchange="checkImageLink(this)" autocomplete="off"
                       name="imageLink" maxlength="250"> <label for="email"
                                                                class="">Image External Link</label>
            </div>
        </div>

        <div class="row section">
            <div class="col s12 m4 l3">
                <p>Description</p>
            </div>

            <div class="input-field col s8 m4 l7">
					<textarea id="contentDescription" type="text" placeholder="PLease write a desription"
                              class="materialize-textarea" maxlength="2000" autocomplete="off"
                              name="contentDescription">${homeContentObj.contentDescription}</textarea>
                <!-- 					<label for="contentDescription" class="">Content Description</label> -->
            </div>
        </div>
        <div class="row section">
            <input type="hidden" value="${contentId}" name="contentId">
            <input type="hidden" value="${ viewFolder}" name="viewFolder">
            <div class="col s12 m8 l9  center" style="padding-right: 193px;">
                <button class="btn btn-large waves-effect waves-light red darken-4"
                        type="submit">Edit
                </button>
            </div>
        </div>
    </form>
</div>


<jsp:include page="../includes/admin_js.jsp"/>
<script type="text/javascript"
        src="${digify}/static/lib/js/dropify.min.js"></script>
<script type="text/javascript" src="${digify}/static/lib/richtext/jquery.richtext.min.js"></script>
<script type="text/javascript">
    $(document).ready(function () {
        // Basic
        $('.dropify').dropify();

        // Translated
        $('.dropify-fr').dropify({
            messages: {
                default: 'Glissez-d�posez un fichier ici ou cliquez',
                replace: 'Glissez-d�posez un fichier ou cliquez pour remplacer',
                remove: 'Supprimer',
                error: 'D�sol�, le fichier trop volumineux'
            }
        });

        // Used events
        var drEvent = $('.dropify-event').dropify();

        drEvent.on('dropify.beforeClear', function (event, element) {
            return confirm("Do you really want to delete \"" + element.filename + "\" ?");
        });

        drEvent.on('dropify.afterClear', function (event, element) {
            alert('File deleted');
        });
    });

    $("#formupload").validate({
        rules: {
            /* imageLink: {
                required: true,

            }, */

            /*  imageLink :{
                 required: true,
             }, */
            contentDescription: {
                required: true,
            },
        },
        //For custom messages
        messages: {},
        errorElement: 'div',
        errorPlacement: function (error, element) {
            var placement = $(element).data('error');
            if (placement) {
                $(placement).append(error)
            } else {
                error.insertAfter(element);
            }
        }
    });

    function checkImageLink(text) {
        var textValue = text.value;
        $.post("${digify}/checkVideoLink", {urllink: textValue, from: 'image'}, function (result) {
            console.log(result)
            if (result) {
                $("#card-alert").removeClass("hide");
                $("#imgLink").val("");
            } else {
                $("#card-alert").addClass("hide");
            }
        });

    }

    function changeSelect() {
        console.log("------", $("#categoryId").val());
    }

    $("#contentDescription").richText({
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
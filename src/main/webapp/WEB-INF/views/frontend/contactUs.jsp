<%@include file="../fragments/includetags.jsp" %>

<!DOCTYPE html>
<html lang="en">

<head>
    <jsp:include page="../includes/web_css.jsp"/>
    <link>
</head>

<body id="page-top">
<%@include file="../frontendfragments/frontendHeader.jsp" %>

<div class="wrapper">

    <section class="inner-banner ">
        <div class="thm-container clearfix">
            <h2 class="pull-left">Contact Us</h2>
            <ul class="breadcumb pull-right">
                <li><a href="${digify }" class="home-fa"><i class="fa fa-home"></i> Home</a></li>
                <li><span>Contact Us</span></li>
            </ul>
        </div>
    </section>

    <section class="services-section sec-padding contact-page-content ">
        <div class="thm-container">
            <div class="sec-title">
                <h2>
                    <span>Get in touch</span>
                </h2>
            </div>
            <div class="row">
                <div class="col-md-4 col-sm-12 col-xs-12">
                    <div class="contact-info">
                        <ul>
                            <li>
                                <div class="icon-box">
                                    <i class="fa fa-map-marker"></i>
                                </div>
                                <div class="content">
                                    <p>
                                        40-C, Ground Floor, Amrapali Corporate Hub, IMT Manesar, Sector-2,
                                        Gurugram-122050
                                    </p>
                                </div>
                            </li>
                        </ul>
                    </div>
                </div>
                <div class="col-md-4">
                    <div class="contact-info">
                        <ul>
                            <li>
                                <div class="icon-box">
                                    <i class="fa fa-envelope-o"></i>
                                </div>
                                <div class="content">
                                    <p>
                                        [Web] : <a href="mailto:www.digify.net.in" target="_blank">www.digify.net.in</a>
                                        <br>[Email]: <a
                                            href="mailto:info@digify.net.in" target="_blank">info@digify.net.in</a>
                                    </p>
                                </div>
                            </li>
                        </ul>
                    </div>
                </div>
                <div class="col-md-4">
                    <div class="contact-info">
                        <ul>
                            <li>
                                <div class="icon-box">
                                    <i class="fa fa-phone"></i>
                                </div>
                                <div class="content">
                                    <p>[Tel] : 0124-4014785, 9599662884</p>
                                </div>
                            </li>
                        </ul>
                    </div>
                </div>
            </div>
            <div class="row" style="padding-bottom: 20px;">
                <div class="col-md-6 col-sm-6 col-xs-12">
                    <form method="post" class="contact-form contact-page" id="contact_form">
                        <div>
                            <input type="text" placeholder="Name" name="name">
                        </div>
                        <div>
                            <input type="text" placeholder="Email" name="email">
                        </div>
                        <div>
                            <input type="text" placeholder="Mobile Number" name="mobileNo">
                        </div>
                        <div>
                            <input type="text" placeholder="Date" class="datepicker" name="dateOfContact">
                        </div>
                        <div>
                            <input type="text" placeholder="Subject" name="subject">
                        </div>
                        <div>
                            <textarea name="message" placeholder="Message"></textarea>
                        </div>
                        <button type="button" onclick="submitContactForm('$event')" class="thm-btn">
                            Submit Now <i class="fa fa-arrow-right"></i>
                        </button>
                    </form>
                </div>
                <div class="col-md-6 col-sm-6 col-xs-12">
                    <!-- 						<iframe src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d13238.854480712705!2d77.01760520298848!3d28.494015126621985!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x390d19e9173dc0b5%3A0x81e7afe494cf5f09!2sAshok+Vihar+Phase+III+Extension%2C+Gurugram%2C+Haryana+122017!5e0!3m2!1sen!2sin!4v1490943648557" -->
                    <!-- 							class="contact_map" frameborder="0" style="border: 0" allowfullscreen=""></iframe> -->
                    <iframe src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d3510.8611232308795!2d76.93260451549135!3d28.36304800297632!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x390d3c338fe0e69b%3A0xdd3f980b882c5858!2sDigify+Enginnering+Pvt.Ltd.!5e0!3m2!1sen!2sin!4v1554979647870!5m2!1sen!2sin"
                            class="contact_map" frameborder="0" style="border:0" allowfullscreen></iframe>
                </div>
            </div>
        </div>
    </section>


    <%@include file="../frontendfragments/frontEndFooter.jsp" %>
</div>
<%@include file="../frontendfragments/modelFragment.jsp" %>
<%-- 	<jsp:include page="../frontendfragments/frontEndFooter.jsp" /> --%>
<jsp:include page="../includes/web_js.jsp"/>
<script type="text/javascript">
    function submitContactForm(e) {
        if ($("#contact_form").valid()) {
            event.preventDefault();
            var form_data = $("#contact_form").serialize();
            $.ajax({
                url: '${digify }/home/contactUsSubmit',
                type: 'POST',
                data: form_data,
                success: function (result) {
                    // Do something with the result
                    console.log("result==" + result)
                    /*     		      	  $(".progress").hide();	 */
                    if (result) {

                        $("#contactussubmitSuccess").modal();
                        $("#contact_form")[0].reset();
                    }
                }
            });
        }
    }

    $("#contact_form").validate({
        rules: {
            email: {
                required: true,
                email: true
            },
            name: {
                required: true
            },
            mobileNo: {
                required: true,
                number: true
            },
            dateOfContact: {
                required: true,
            },
            subject: {
                required: true
            },
            message: {
                required: true
            }
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
</script>
</body>
</html>

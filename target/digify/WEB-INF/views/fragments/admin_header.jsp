<jsp:include page="../includes/admin_css.jsp"></jsp:include>
<%@include file="includetags.jsp"%>

<!--Mobile Collapse Button-->
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<security:authorize access="isAuthenticated()">
	<security:authentication property="principal.email"
		var="loggedInUser" />
	<security:authentication property="principal.name"
		var="loggedInUserName" />
	
	<input type="hidden" id="userName" name="userName"
		value="${loggedInUser}" />
	</security:authorize>

<header id="header" class="page-topbar">
<div class="navbar-fixed">
      <nav class="navbar-color">
    <div class="nav-wrapper">
      <h1 class="logo-wrapper"><a href="#!" class="brand-logo darken-1"><img alt="abatar" src="${digify}/static/images/logo-512.png" > </a></h1>
      <a href="#" data-activates="mobile-demo" class="button-collapse"><i class="mdi-navigation-menu"></i></a>
      <ul class="side-nav" id="mobile-demo">
      <security:authorize access="hasRole('ROLE_ADMIN')">
       <li><a href="${digify}/user/homepage">Home</a></li>
       <li><a href="${digify}/user/news/getAllFile">All News</a></li>
        <li><a href="${digify}/user/image/getAllFile">All Images</a></li>
        <li><a href="${digify}/user/video/getAllFile">All Videos</a></li>
        </security:authorize>
       <security:authorize access="isAuthenticated()">
        <li><a href="${digify}/logout">Logout</a></li>
        </security:authorize>
       <!--  <li><a href="badges.html">Components</a></li>
        <li><a href="collapsible.html">Javascript</a></li>
        <li><a href="mobile.html">Mobile</a></li> -->
      </ul>
    </div>
  </nav>
  </div>
       </header>
<div id="main">
        <!-- START WRAPPER -->
        <div class="wrapper">

            <!-- START LEFT SIDEBAR NAV-->
            <aside id="left-sidebar-nav">
                <ul id="slide-out" class="side-nav fixed leftside-navigation ps-container ps-active-y" style="width: 240px; height: 100%">
                <li class="user-details cyan darken-2">
                <div class="row">
                   <!--  <div class="col col s4 m4 l4">
                        <img src="images/avatar.jpg" alt="" class="circle responsive-img valign profile-image">
                    </div> -->
                    <div class="col col s8 m8 l8">
                        
                        <a class="btn-flat dropdown-button waves-effect waves-light white-text profile-btn" href="#" data-activates="profile-dropdown">${user.name }<i class="mdi-navigation-arrow-drop-down right"></i></a><ul id="profile-dropdown" class="dropdown-content">
                            <li class="divider"></li>
                            <security:authorize access="isAuthenticated()">
                            <li><a href="${digify}/logout"><i class="mdi-hardware-keyboard-tab"></i> Logout</a>
                            </li>
                            </security:authorize>
                        </ul>
                        <p class="user-roal">Administrator</p>
                    </div>
                </div>
                </li>
               <security:authorize access="hasRole('ROLE_ADMIN')">
               <li class="no-padding">
                    <ul class="collapsible collapsible-accordion">
                        <li class="bold ${active eq 'admin'? 'active':'' }"><a class="collapsible-header waves-effect waves-cyan"><i class="mdi-action-account-box"></i> Admin Control</a>
                            <div class="collapsible-body" style="">
                                <ul>
                                <c:forEach var = "item" items="${listHomePgcontMaster }">
                                    <li><a href="${imgvids}/admin/home/${item.name}/${item.id}"><i class="${item.icon}"></i>${item.displayName}</a>
                                    </li>
                                    <%-- <li><a href="${imgvids}/admin/home/aboutUs"><i class="mdi-action-account-box"></i>About Us</a>
                                    </li>
                                    <li><a href="${imgvids}/admin/home/contactUs"><i class="mdi-communication-quick-contacts-dialer"></i>Contact Us</a>
                                    </li> --%>
                                    </c:forEach>
                                </ul>
                            </div>
                        </li>
                    </ul>
                </li>
                
                <li class="bold ${active eq 'products'? 'active':'' }"><a href="${digify}/product" class="waves-effect waves-cyan"><i class="mdi-editor-format-list-numbered"></i> Product </a>
                </li>
                <li class="bold ${active eq 'services'? 'active':'' }"><a href="${digify}/services" class="waves-effect waves-cyan"><i class="mdi-maps-local-offer"></i>Service</a>
                </li>
                <%-- <li class="bold ${active eq 'callBackList'? 'active':'' }"><a href="${digify}/user/callBackList" class="waves-effect waves-cyan"><i class="mdi-av-video-collection"></i>Call Back List</a>
                </li>
                <li class="bold ${active eq 'customerList'? 'active':'' }"><a href="${digify}/user/customerList" class="waves-effect waves-cyan"><i class="mdi-av-video-collection"></i>Customer List</a>
                </li>
                <li class="bold ${active eq 'toBeaPartner'? 'active':'' }"><a href="${digify}/user/toBeaPartner" class="waves-effect waves-cyan"><i class="mdi-av-video-collection"></i>To Be a partner</a>
                </li> --%>
               </security:authorize>
                
            </ul>
            </aside>
            <!-- END LEFT SIDEBAR NAV-->

        </div>
        <!-- END WRAPPER -->

    </div>

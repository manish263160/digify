package com.digify.auth;

import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

@Component("refererRedirectionAuthenticationSuccessHandler")
public class RefererRedirectionAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler
        implements AuthenticationSuccessHandler {


    public RefererRedirectionAuthenticationSuccessHandler() {
        super();
        setUseReferer(true);
    }

//	 @Override
//	    public void onAuthenticationSuccess(HttpServletRequest request,
//	            HttpServletResponse response, Authentication authentication)
//	            throws ServletException, IOException {
//	        // TODO Auto-generated method stub
//	        String ctoken = (String) request.getSession().getAttribute(WebConstants.CSRF_TOKEN);
//	        DefaultSavedRequest defaultSavedRequest = (DefaultSavedRequest) request.getSession().getAttribute("SPRING_SECURITY_SAVED_REQUEST_KEY");
//	        if( defaultSavedRequest != null && ctoken != null ) {
//	            String requestUrl = defaultSavedRequest.getRequestURL() + "?" + defaultSavedRequest.getQueryString();
//	            requestUrl = UrlTool.addParamToURL(requestUrl, WebConstants.CSRF_TOKEN, ctoken, true);
//	            getRedirectStrategy().sendRedirect(request, response, requestUrl);
//	        } else {
//	            super.onAuthenticationSuccess(request, response, authentication);
//	        }
//	    }
}

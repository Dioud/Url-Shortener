package com.supinfo.project.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


/**
 * Filtre plac� sur les servlets dont l'acc�s n�cessite d'�tre logg�.
 */
@WebFilter(urlPatterns="/auth/*")
public class AuthenticateFilter implements Filter {

	/** Nom du param�tre username dans le scope session. */
	private static final String USERNAME_PARAMETER_NAME = "username";
	/** Page HTML de login. */
	private static final String LOGIN_VIEW 				= "/login.jsp";
    
	public void init(FilterConfig config) throws ServletException {
		// Do nothing
	}
	
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// Cast des Request/Response en HttpRequest/HttpResponse pour avoir acc�s � la session
		// (L'id�al serait de verifier la l�gitimit� du cast via "instanceof")
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		
		final HttpSession session = httpRequest.getSession();
		final Object usernameParameter = session.getAttribute(USERNAME_PARAMETER_NAME);
		
		// Si un utilisateur est logg�, on redirige la requ�te vers le prochain �l�ment
		if(usernameParameter != null) {
			chain.doFilter(request, response);
		} else {
			// Sinon, l'utilisateur est envoy� vers la page de login
			httpResponse.sendRedirect(httpRequest.getContextPath() + LOGIN_VIEW);
		}
	}

	public void destroy() {
		// Do nothing
	}

}
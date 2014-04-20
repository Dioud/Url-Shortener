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
 * Filtre placé sur les servlets dont l'accès nécessite d'être loggé.
 */
@WebFilter(urlPatterns="/auth/*")
public class AuthenticateFilter implements Filter {

	/** Nom du paramètre username dans le scope session. */
	private static final String USERNAME_PARAMETER_NAME = "username";
	/** Page HTML de login. */
	private static final String LOGIN_VIEW 				= "/login.jsp";
    
	public void init(FilterConfig config) throws ServletException {
		// Do nothing
	}
	
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// Cast des Request/Response en HttpRequest/HttpResponse pour avoir accès à la session
		// (L'idéal serait de verifier la légitimité du cast via "instanceof")
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		
		final HttpSession session = httpRequest.getSession();
		final Object usernameParameter = session.getAttribute(USERNAME_PARAMETER_NAME);
		
		// Si un utilisateur est loggé, on redirige la requête vers le prochain élément
		if(usernameParameter != null) {
			chain.doFilter(request, response);
		} else {
			// Sinon, l'utilisateur est envoyé vers la page de login
			httpResponse.sendRedirect(httpRequest.getContextPath() + LOGIN_VIEW);
		}
	}

	public void destroy() {
		// Do nothing
	}

}
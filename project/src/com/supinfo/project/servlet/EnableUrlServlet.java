package com.supinfo.project.servlet;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.supinfo.project.dao.DaoFactory;
import com.supinfo.project.dao.UrlDao;
import com.supinfo.project.entity.Url;

/**
 * Servlet permettant l'affichage d'un produit sp�cifique selon un ID pass� en param�tre 
 * par l'utilisateur dans la requ�te.
 */
public class EnableUrlServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	/** Param�tre � r�cup�rer dans la requ�te. */
    private static final String ID_PARAMETER_NAME = "id";
    private static final String ENABLE_PARAMETER_NAME = "enable"; 
	
    /** Vue. */
    private static final String SHOW_Url_VIEW = "/auth/listUrl";
	
	/** Gestionnaire d'entity Url. */
    private UrlDao UrlDao;
	
	/**
	 * R�cup�ration du DAO Url.
	 */
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		UrlDao = DaoFactory.getUrlDao();
	}
    

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Param�tre ID r�cup�r� depuis la requ�te
		final String idParameter = request.getParameter(ID_PARAMETER_NAME);
		final String enableParameter = request.getParameter(ENABLE_PARAMETER_NAME);
		Url requestedUrl = new Url();
		
		// R�cup�ration du produit depuis la base
		if(idParameter != null) {
			try {
				final Long idLong = Long.parseLong(idParameter);
				requestedUrl = UrlDao.findUrlById(idLong);
				System.out.println(enableParameter);
				if (enableParameter.equals("1"))	{
					System.out.println("ok");
					UrlDao.enabledisableById(idLong, true);
				}
				else	{
					UrlDao.enabledisableById(idLong, false);
				}
			} catch(NumberFormatException nfe) {
				System.out.println("ERROR: l'id entr� est n'est pas un nombre.");
			}
		}
		
		// Cr�ation d'un attribut contenant le produit demand� qui va etre utilis� par la vue
		request.setAttribute("Url", requestedUrl);
		
		// Redirection vers la vue
		request.getRequestDispatcher(SHOW_Url_VIEW).forward(request, response);
	}
}

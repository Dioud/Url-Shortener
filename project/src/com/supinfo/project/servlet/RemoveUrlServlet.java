package com.supinfo.project.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.supinfo.project.dao.DaoFactory;
import com.supinfo.project.dao.StatDao;
import com.supinfo.project.dao.UrlDao;

/**
 * Servlet de suppression de produit en m�moire.
 */
public class RemoveUrlServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	/** Param�tre � r�cup�rer dans la requ�te. */
    private static final String ID_PARAMETER_NAME = "id";   

	/** Servlet vers laquelle on va rediriger le client apr�s la suppression. */
	private static final String LIST_Url_VIEW = "/auth/listUrl";
       
	/** Gestionnaire d'entity Url. */
	private UrlDao UrlDao;
	private StatDao StatDao;
	
	/**
	 * R�cup�ration du DAO Url.
	 */
	@Override
	public void init() throws ServletException {
		super.init();
		UrlDao = DaoFactory.getUrlDao();
		StatDao = DaoFactory.getStatDao();
	}
	
	 /**
	  * M�thode appel�e lors de requ�tes HTTP de type GET. <br>
	  * Dans cette servlet, permet de supprimer le produit associ� � l'ID en param�tre (URL).
	  * 
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Param�tre ID r�cup�r� depuis la requ�te
		final String idParameter = request.getParameter(ID_PARAMETER_NAME);
		
		// Suppression en m�moire
		if(idParameter != null) {
			try {
				final Long idLong = Long.parseLong(idParameter);
				StatDao.removeStat(idLong);
				UrlDao.removeUrl(idLong);
			} catch(NumberFormatException nfe) {
				System.out.println("ERROR: l'id entr� est n'est pas un nombre.");
			}
		}
		
		// Redirection vers la liste
		response.sendRedirect(request.getServletContext().getContextPath()+LIST_Url_VIEW);
	}
}

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
 * Servlet de suppression de produit en mémoire.
 */
public class RemoveUrlServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	/** Paramètre à récupérer dans la requête. */
    private static final String ID_PARAMETER_NAME = "id";   

	/** Servlet vers laquelle on va rediriger le client après la suppression. */
	private static final String LIST_Url_VIEW = "/auth/listUrl";
       
	/** Gestionnaire d'entity Url. */
	private UrlDao UrlDao;
	private StatDao StatDao;
	
	/**
	 * Récupération du DAO Url.
	 */
	@Override
	public void init() throws ServletException {
		super.init();
		UrlDao = DaoFactory.getUrlDao();
		StatDao = DaoFactory.getStatDao();
	}
	
	 /**
	  * Méthode appelée lors de requêtes HTTP de type GET. <br>
	  * Dans cette servlet, permet de supprimer le produit associé à l'ID en paramètre (URL).
	  * 
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Paramètre ID récupéré depuis la requête
		final String idParameter = request.getParameter(ID_PARAMETER_NAME);
		
		// Suppression en mémoire
		if(idParameter != null) {
			try {
				final Long idLong = Long.parseLong(idParameter);
				StatDao.removeStat(idLong);
				UrlDao.removeUrl(idLong);
			} catch(NumberFormatException nfe) {
				System.out.println("ERROR: l'id entré est n'est pas un nombre.");
			}
		}
		
		// Redirection vers la liste
		response.sendRedirect(request.getServletContext().getContextPath()+LIST_Url_VIEW);
	}
}

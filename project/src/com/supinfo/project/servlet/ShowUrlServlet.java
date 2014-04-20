package com.supinfo.project.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.supinfo.project.dao.DaoFactory;
import com.supinfo.project.dao.StatDao;
import com.supinfo.project.dao.UrlDao;
import com.supinfo.project.entity.Stat;
import com.supinfo.project.entity.Url;

/**
 * Servlet permettant l'affichage d'un produit sp�cifique selon un ID pass� en param�tre 
 * par l'utilisateur dans la requ�te.
 */
public class ShowUrlServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/** Param�tre � r�cup�rer dans la requ�te. */
	private static final String ID_PARAMETER_NAME = "id";   

	/** Vue. */
	private static final String SHOW_Url_VIEW = "/auth/showUrl.jsp";

	/** Gestionnaire d'entity Url. */
	private UrlDao UrlDao;
	private StatDao StatDao;

	/**
	 * R�cup�ration du DAO Url.
	 */
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		UrlDao = DaoFactory.getUrlDao();
		StatDao = DaoFactory.getStatDao();

	}

	/**
	 * M�thode appel�e par la m�thode Service de HttpServlet (classe m�re) dans le cas de requ�tes
	 * HTTP de type GET. <br>
	 * R�cup�re le param�tre "id" dans la requ�te pour afficher dans le flux de sortie (r�ponse au client)
	 * les donn�es sur le produit ayant l'id sp�cifi�. <br>
	 * 
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Param�tre ID r�cup�r� depuis la requ�te
		final String idParameter = request.getParameter(ID_PARAMETER_NAME);
		Url requestedUrl = new Url();

		// R�cup�ration du produit depuis la base
		if(idParameter != null) {
			try {
				final Long idLong = Long.parseLong(idParameter);
				requestedUrl = UrlDao.findUrlById(idLong);
				List<Stat> requestedStat = StatDao.findStatByUrlId(idLong);

				request.setAttribute("url", requestedUrl);
				request.setAttribute("stat", requestedStat);
			} catch(NumberFormatException nfe) {
				System.out.println("ERROR: l'id entr� est n'est pas un nombre.");
			}
		}

		// Cr�ation d'un attribut contenant le produit demand� qui va etre utilis� par la vue
		request.setAttribute("connected", true);
		// Redirection vers la vue
		request.getRequestDispatcher(SHOW_Url_VIEW).forward(request, response);
	}
}

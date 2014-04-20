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
 * Servlet permettant l'affichage d'un produit spécifique selon un ID passé en paramètre 
 * par l'utilisateur dans la requête.
 */
public class ShowUrlServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/** Paramètre à récupérer dans la requête. */
	private static final String ID_PARAMETER_NAME = "id";   

	/** Vue. */
	private static final String SHOW_Url_VIEW = "/auth/showUrl.jsp";

	/** Gestionnaire d'entity Url. */
	private UrlDao UrlDao;
	private StatDao StatDao;

	/**
	 * Récupération du DAO Url.
	 */
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		UrlDao = DaoFactory.getUrlDao();
		StatDao = DaoFactory.getStatDao();

	}

	/**
	 * Méthode appelée par la méthode Service de HttpServlet (classe mère) dans le cas de requêtes
	 * HTTP de type GET. <br>
	 * Récupère le paramètre "id" dans la requête pour afficher dans le flux de sortie (réponse au client)
	 * les données sur le produit ayant l'id spécifié. <br>
	 * 
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Paramètre ID récupéré depuis la requête
		final String idParameter = request.getParameter(ID_PARAMETER_NAME);
		Url requestedUrl = new Url();

		// Récupération du produit depuis la base
		if(idParameter != null) {
			try {
				final Long idLong = Long.parseLong(idParameter);
				requestedUrl = UrlDao.findUrlById(idLong);
				List<Stat> requestedStat = StatDao.findStatByUrlId(idLong);

				request.setAttribute("url", requestedUrl);
				request.setAttribute("stat", requestedStat);
			} catch(NumberFormatException nfe) {
				System.out.println("ERROR: l'id entré est n'est pas un nombre.");
			}
		}

		// Création d'un attribut contenant le produit demandé qui va etre utilisé par la vue
		request.setAttribute("connected", true);
		// Redirection vers la vue
		request.getRequestDispatcher(SHOW_Url_VIEW).forward(request, response);
	}
}

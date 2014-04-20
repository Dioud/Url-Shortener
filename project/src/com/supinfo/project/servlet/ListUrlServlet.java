package com.supinfo.project.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.supinfo.project.dao.DaoFactory;
import com.supinfo.project.dao.UrlDao;
import com.supinfo.project.entity.Url;

/**
 * Servlet permettant de lister l'ensemble des produits enregistrés en mémoire.
 */
public class ListUrlServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/** Paramètres de la requête. */
	private static final String LIST_Url_PARAMETER = "urls";

	/** Vue de listing. */
	private static final String LIST_Url_VIEW = "listUrl.jsp";

	private static final String ID_USER = "";

	/** Gestionnaire d'entity Url. */
	private UrlDao UrlDao;

	/**
	 * Récupération du DAO Url.
	 */
	@Override
	public void init() throws ServletException {
		super.init();
		UrlDao = DaoFactory.getUrlDao();
	}


	/**
	 * Méthode appelée quelque soit le type de requête HTTP. <br>
	 * Dans cette servlet, permet de récupérer l'ensemble des Url en base.
	 * La liste est enregistrée dans le scope Request. La requete est ensuite redirigée vers
	 * une JSP qui affiche la liste/<br>
	 * 
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Liste de l'ensemble des produits en base

		HttpServletRequest httpRequest = (HttpServletRequest) request;
		final HttpSession session = httpRequest.getSession();
		final Object idUserParameter = session.getAttribute(ID_USER);
		final Long idUser = (Long) idUserParameter;

		//final User user = userDao.findUserById(idUser);

		//final List<Url> allUrls = UrlDao.getAllUrls();
		final List<Url> allUrls = UrlDao.getUrlsByUser(idUser);

		//final List<Url> allUrls2 = user.getUrls();
		//System.out.println(idUser);

		// Création d'un attribut contenant la liste qui va etre utilisée par la vue
		request.setAttribute(LIST_Url_PARAMETER, allUrls);
		request.setAttribute("connected", true);
		// Redirection vers la JSP
		request.getRequestDispatcher(LIST_Url_VIEW)
		.forward(request, response);
	}
}

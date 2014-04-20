package com.supinfo.project.servlet;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.supinfo.project.dao.DaoFactory;
import com.supinfo.project.dao.UrlDao;
import com.supinfo.project.dao.UserDao;
import com.supinfo.project.entity.Url;
import com.supinfo.project.entity.User;

/**
 * Servlet d'ajout de produit via formulaire.
 */
public class AddUrlServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/** Paramètres à récupérer dans la requête. */
	private static final String ID_USER = "";
	private static final String NAME_PARAMETER 			= "name";
	private static final String URL_PARAMETER 		= "url";

	
	/** Gestionnaire d'entity Url. */
	private UrlDao UrlDao;
	/** Gestionnaire d'entity CATEGORY. */
	private UserDao userDao;

	/**
	 * Récupération du DAO Url & du DAO Category.
	 */
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);

		UrlDao = DaoFactory.getUrlDao();
		userDao = DaoFactory.getUserDao();
	}

	/**
	 * Méthode appelée lors de requêtes HTTP de type POST. <br>
	 * Dans cette servlet, permet de gerer les données saisies par l'utilisateur
	 * dans le formulaire d'ajout de produit.
	 * 
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Récuépration des paramètres
		final String name = request.getParameter(NAME_PARAMETER);
		final String url = request.getParameter(URL_PARAMETER);
		
		int lower = 1;
		int higher = 10000;
		int random = (int)(Math.random() * (higher-lower)) + lower;
		
		final String urlGenere = String.valueOf(random) ;

		// Création du SupUrl
		final Url Url = new Url();
		Url.setName(name);
		Url.setUrl(url);
		Url.setUrlGenere(urlGenere);
		Url.setEnable(true);

		HttpServletRequest httpRequest = (HttpServletRequest) request;
		final HttpSession session = httpRequest.getSession();
		final Object idUserParameter = session.getAttribute(ID_USER);
		final Long idUser = (Long) idUserParameter;
		final User user = userDao.findUserById(idUser);
		Url.setUser(user);
		
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date();
		Url.setDate(dateFormat.format(date));

		// Ajout en base
		UrlDao.addUrl(Url);

		// Redirection vers /showUrl?id=X
//		response.sendRedirect(request.getServletContext().getContextPath()+	SHOW_Url_SERVLET+"?"+ID_PARAMETER+"="+Url.getId());
		response.sendRedirect(request.getServletContext().getContextPath()+	"/auth/listUrl");
	}

	/**
	 * Méthode appelée lors de requêtes HTTP de type GET. <br>
	 * Dans cette servlet, permet de rediriger l'utilisateur vers la JSP formulaire en passant en paramètre
	 * la liste des catégories qu'on peut associer à un produit.
	 * 
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 
	}
}

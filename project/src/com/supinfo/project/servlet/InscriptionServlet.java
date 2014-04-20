package com.supinfo.project.servlet;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import com.supinfo.project.dao.DaoFactory;
import com.supinfo.project.dao.UserDao;
import com.supinfo.project.entity.User;


/**
 * Servlet dédié au traitement du login saisi par l'utilisateur (enregistrement en session).
 */
public class InscriptionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private UserDao userDao;
	private static final String USERNAME_PARAMETER_NAME = "username";
	private static final String PASSWORD_PARAMETER_NAME = "password";
	private static final String PASSWORD2_PARAMETER_NAME = "password2";
	private static final String ID_USER = "";
	private static final String LIST_PRODUCT_SERVLET	= "/auth/listUrl";
	private static final String LOGIN_VIEW 				= "/inscription.jsp";


	private static String encode(String password, String algorithm)
			throws NoSuchAlgorithmException {
		byte[] hash = null;
		try {
			MessageDigest md = MessageDigest.getInstance(algorithm);
			hash = md.digest(password.getBytes());
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < hash.length; ++i) {
			String hex = Integer.toHexString(hash[i]);
			if (hex.length() == 1) {
				sb.append(0);
				sb.append(hex.charAt(hex.length() - 1));
			} else {
				sb.append(hex.substring(hex.length() - 2));
			}
		}
		return sb.toString();
	}


	public void init() throws ServletException {
		// On conserve le fonctionnement de l'init initial
		super.init();

		// Valorisation des DAO via la DaoFactory
		userDao = DaoFactory.getUserDao();
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Récupération du paramètre dans la requête (formulaire)
		final String usernameParameter = request.getParameter(USERNAME_PARAMETER_NAME);
		final String passwordParameter = request.getParameter(PASSWORD_PARAMETER_NAME);
		final String password2Parameter = request.getParameter(PASSWORD2_PARAMETER_NAME);
		
		if (passwordParameter.equals(password2Parameter) )	{
			final User user = new User();
			user.setEmail(usernameParameter);

			try {
				user.setPassword(encode(passwordParameter, "MD5"));
			} catch (NoSuchAlgorithmException e) {
				e.printStackTrace();
			}

			userDao.addUser(user);
			
			final HttpSession session = request.getSession();
			session.setAttribute(USERNAME_PARAMETER_NAME, usernameParameter);
			session.setAttribute(ID_USER, user.getId());


			response.sendRedirect(request.getServletContext().getContextPath()+LIST_PRODUCT_SERVLET);
		}
		else {
			response.sendRedirect(request.getServletContext().getContextPath()+LOGIN_VIEW);
			
		}
		

	}


	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.sendRedirect(request.getServletContext().getContextPath()+LOGIN_VIEW);
	}


}



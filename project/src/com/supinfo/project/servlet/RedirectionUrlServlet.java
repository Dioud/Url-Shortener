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

import com.supinfo.project.dao.DaoFactory;
import com.supinfo.project.dao.StatDao;
import com.supinfo.project.dao.UrlDao;
import com.supinfo.project.entity.Stat;
import com.supinfo.project.entity.Url;

/**
 * Servlet permettant l'affichage d'un produit spécifique selon un ID passé en paramètre 
 * par l'utilisateur dans la requête.
 */
public class RedirectionUrlServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String ID_PARAMETER_NAME = "id";   
	private UrlDao UrlDao;
	private StatDao StatDao;


	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		UrlDao = DaoFactory.getUrlDao();
		StatDao = DaoFactory.getStatDao();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Paramètre ID récupéré depuis la requête
		final String idParameter = request.getParameter(ID_PARAMETER_NAME);
		String referer = request.getHeader("referer");
		Url requestedUrl = new Url();
		final Stat stat = new Stat();
		

		// Récupération du produit depuis la base
		if(idParameter != null) {
			requestedUrl = UrlDao.findUrlByLink(idParameter);
			if (requestedUrl != null)	{
				if (referer==null)	{
					referer="Direct";
				}
				stat.setReferer(referer);
				stat.setUrl(requestedUrl);
				stat.setPays("France");
				DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
				Date date = new Date();
				stat.setDate(dateFormat.format(date));
				StatDao.addStat(stat);
				response.sendRedirect(requestedUrl.getUrl());
			}
			else	{
				response.sendRedirect(request.getServletContext().getContextPath());
			}
		}
		else	{
			response.sendRedirect(request.getServletContext().getContextPath());
		}
	}
}

package com.supinfo.project.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.supinfo.project.util.PersistenceManager;

/**
 * Application Lifecycle Listener implementation class PersistenceListener
 *
 */
public class PersistenceListener implements ServletContextListener {

	/**
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     */
    public void contextInitialized(ServletContextEvent arg0) {
        // TODO Auto-generated method stub
    }

	/**
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     */
    public void contextDestroyed(ServletContextEvent arg0) {
        PersistenceManager.closeEntityManagerFactory();
    }
	
}

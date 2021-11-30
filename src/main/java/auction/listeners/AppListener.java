/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package auction.listeners;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.ServletContextAttributeEvent;
import javax.servlet.ServletContextAttributeListener;

//import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionAttributeListener;
//import java.util.logging.Level;
import java.util.logging.Logger;
import auction.util.TimerBean;


public final class AppListener implements
ServletContextListener,
HttpSessionAttributeListener,
ServletContextAttributeListener,
HttpSessionListener
{
	private ServletContext context = null;
	private  Logger logger = null;

	public AppListener()
	{
              logger = Logger.getLogger((AppListener.class.getName()));
              logger.info("Application initialized");		
	}

	//CONTEXT SECTION///////////////////////////////////////////////////////////////////////////

	public void contextInitialized(ServletContextEvent event)
	{
		this.context = event.getServletContext();
		logger.info("Context Initialized()");
                TimerBean t = new TimerBean();
                this.context.setAttribute("timer",t);
                t.init();
                t.loadTasks();
	}

	public void contextDestroyed(ServletContextEvent event)
	{
		this.context = null;
		logger.info("Context Destroyed()");
	}

	public void attributeAdded(ServletContextAttributeEvent event)
	{
		logger.info("Context Attribute Added('" + event.getName() + "', '" +
		event.getValue() + "')");
	}

	public void attributeRemoved(ServletContextAttributeEvent event)
	{
		logger.info("Context Attribute Removed('" + event.getName() + "', '" +
		event.getValue() + "')");
	}

	public void attributeReplaced(ServletContextAttributeEvent event)
	{
		logger.info("Context Attribute Replaced('" + event.getName() + "', '" +
		event.getValue() + "')");
	}

	//SESSION SECTION//////////////////////////////////////////////////////////////////

	public void sessionCreated(HttpSessionEvent event)
	{
		//NDC.push(event.getSession().getId());
		logger.info("Session Created('" + event.getSession().getId() + "')");		
	}

	public void sessionDestroyed(HttpSessionEvent event)
	{	
		logger.info("Session Destroyed('" + event.getSession().getId() + "')");
	}

	public void attributeAdded(HttpSessionBindingEvent event)
	{
		logger.info("Session Attribute Added('" + event.getSession().getId() + "', '" +
		event.getName() + "', '" + event.getValue() + "')");
	}

	public void attributeRemoved(HttpSessionBindingEvent event)
	{
		logger.info("Session Attribute Removed('" + event.getSession().getId() + "', '" +
		event.getName() + "', '" + event.getValue() + "')");
	}

	public void attributeReplaced(HttpSessionBindingEvent event)
	{
		logger.info("Session Attribute Replaced('" + event.getSession().getId() + "', '" +
		event.getName() + "', '" + event.getValue() + "')");
	}
}
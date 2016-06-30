package ULS;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import MRC.MessageManager;

/**
 * Application Lifecycle Listener implementation class ListenR
 *
 */
@WebListener
public class ListenR implements ServletContextListener {

    /**
     * Default constructor. 
     */
    public ListenR() {
        // TODO Auto-generated constructor stub
    }

	/**
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     */
    public void contextDestroyed(ServletContextEvent arg0)  { 
         // TODO Auto-generated method stub
    }

	/**
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     */
    public void contextInitialized(ServletContextEvent arg0)  { 
    	// pass null 
    	AccountManager am = new AccountManager(null);

		ServletContext sc = arg0.getServletContext();
		sc.setAttribute("AccMan", am);
    }
	
}

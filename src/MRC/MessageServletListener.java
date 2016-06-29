package MRC;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * Application Lifecycle Listener implementation class MessageServletListener
 *
 */
@WebListener
public class MessageServletListener implements ServletContextListener {

    /**
     * Default constructor. 
     */
    public MessageServletListener() {
        // TODO Auto-generated constructor stub
    }

	/**
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     */
    public void contextInitialized(ServletContextEvent arg0)  { 
         // TODO Auto-generated method stub
    	MessageManager mm = new MessageManager(null);
    	ServletContext sc = arg0.getServletContext();
		sc.setAttribute("MessMan", mm);
    }

	/**
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     */
    public void contextDestroyed(ServletContextEvent arg0)  { 
         // TODO Auto-generated method stub
    }
	
}

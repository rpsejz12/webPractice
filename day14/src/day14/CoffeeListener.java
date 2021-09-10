package day14;

import java.util.ArrayList;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * Application Lifecycle Listener implementation class CoffeeListener
 *
 */
@WebListener
public class CoffeeListener implements ServletContextListener {

    /**
     * Default constructor. 
     */
    public CoffeeListener() {
        // TODO Auto-generated constructor stub
    }

	/**
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     */
    public void contextDestroyed(ServletContextEvent sce)  { 
         // TODO Auto-generated method stub
    }

	/**
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     */
    public void contextInitialized(ServletContextEvent sce)  { 
         // TODO Auto-generated method stub
    	  	
    	ServletContext context= sce.getServletContext();
    	
    	ArrayList<CoffeeVO> datas = new ArrayList<CoffeeVO>();
    	CoffeeVO data1 = new CoffeeVO();
    	CoffeeVO data2 = new CoffeeVO();
    	CoffeeVO data3 = new CoffeeVO();
    	
    	data1.setName("아메리카노");
    	data1.setPrice(3000);
    	
    	data2.setName("카페라떼");
    	data2.setPrice(4000);
    	
    	data3.setName("카페모카");
    	data3.setPrice(5000);
    	
    	datas.add(data1);
    	datas.add(data2);
    	datas.add(data3);
    	
    	
    	context.setAttribute("coffeeAL", datas);   			
    }	
}

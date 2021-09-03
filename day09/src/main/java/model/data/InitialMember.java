package model.data;

import java.util.ArrayList;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;


@WebListener //필수
public class InitialMember implements ServletContextListener{

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		// TODO Auto-generated method stub
		
		ArrayList<Member> datas = new ArrayList<Member>();
		
		for(int i = 0 ;i <5 ; i++) {
			Member data = new Member("티모" +i,"timo"+i+"@naver.com");
			datas.add(data);
		}
		
		datas.add(new Member("아리",null));
		datas.add(new Member("아무무",null));
		
		ServletContext context = sce.getServletContext();
		context.setAttribute("members", datas);
		context.setAttribute("member", new Member());
		
		
		
		
		
	}
	
	
	
	
	

}

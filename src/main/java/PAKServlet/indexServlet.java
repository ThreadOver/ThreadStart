package PAKServlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import JDBC.Perform;
import instance.User;

public class indexServlet extends HttpServlet{

	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		String name = request.getParameter("user");
		String password = request.getParameter("password");
		System.out.println(name+password);
		User user = new User();
		user.setUser(name);
		user.setPassword(password);
		
		Perform pf = new Perform();
		pf.save(user);
		System.out.println("×¢²á³É¹¦");
	}

	
	
}

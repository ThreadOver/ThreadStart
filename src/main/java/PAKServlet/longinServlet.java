package PAKServlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import JDBC.Perform;
import instance.User;

public class longinServlet extends HttpServlet{

	
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		resp.setContentType("text/html;charset=utf-8");
		String name = req.getParameter("user");
		String password = req.getParameter("password");
		System.out.println(name+password);
		HttpSession session = req.getSession();
		session.setAttribute("user", name);
		Object sei = session.getAttribute("user");
		System.out.println(sei);
		Perform pf = new Perform();
		int count = pf.readpuser(name, password);
		System.out.println(count);
		if(count>0) {
			System.out.println("µÇÂ¼³É¹¦");
			resp.sendRedirect("dontai/index.jsp");
		}else {
			resp.sendRedirect("longin.jsp");
		}
		
	}

	
}

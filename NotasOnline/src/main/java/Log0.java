

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Log0
 */
@WebServlet("/Log0")
public class Log0 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Log0() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		LocalDateTime date = LocalDateTime.now();
		String dni = request.getParameter("dni");
		String pass = request.getParameter("pass");
		String uri = request.getContextPath() + request.getServletPath() + request.getPathInfo() + request.getQueryString();
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<!DOCTYPE html>\n<html>\n<head>\n"+ "<meta http-equiv=\"Content-type\" content=\"text/html; charset=utf-8\" />"+"<title>Información Log0</title></head><body>");
		out.println("<h1>Información Log0</h1>");
		out.println("<p>Date & Time: "+date+" <br/> DNI: "+dni+" <br/>Password: "+pass+"<br/> IP: "+request.getRemoteAddr()+"<br/> Method: "+request.getMethod()+" </p>");
		out.println("<p>URI: "+uri+" </p>");
		out.println("</body></html>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		LocalDateTime date = LocalDateTime.now();
		String dni = request.getParameter("dni");
		String pass = request.getParameter("pass");
		String uri = request.getContextPath() + request.getServletPath() + request.getPathInfo() + request.getQueryString();
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<!DOCTYPE html>\n<html>\n<head>\n"+ "<meta http-equiv=\"Content-type\" content=\"text/html; charset=utf-8\" />"+"<title>Información Log0</title></head><body>");
		out.println("<h1>Información Log0</h1>");
		out.println("<p>Date & Time: "+date+" <br/> DNI: "+dni+" <br/>Password: "+pass+"<br/> IP: "+request.getRemoteAddr()+"<br/> Method: "+request.getMethod()+" </p>");
		out.println("<p>URI: "+uri+" </p>");
		out.println("</body></html>");
	}

}

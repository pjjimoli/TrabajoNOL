

import java.io.IOException;
import java.io.File;
import java.io.FileWriter;
import java.time.LocalDateTime;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Log1
 */
@WebServlet("/Log1")
public class Log1 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Log1() {
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
		response.getWriter().println("<!DOCTYPE html>\n<html>\n<head>\n"+ "<meta http-equiv=\"Content-type\" content=\"text/html; charset=utf-8\" />"+"<title>Información Log1</title></head><body>");
		response.getWriter().println("<h1>Información Log1</h1>");
		response.getWriter().println("<p>Date & Time: "+date+" <br/> DNI: "+dni+" <br/>Password: "+pass+"<br/> IP: "+request.getRemoteAddr()+"<br/> Method: "+request.getMethod()+" </p>");
		response.getWriter().println("<p>URI: "+uri+" </p>");
		response.getWriter().println("</body></html>");
		response.getWriter().println(System.getProperty("user.home")+"\\Desktop/LogForm.txt");
		
		File log1 = new File(System.getProperty("user.home")+File.separator+"Escritorio/LogForm.txt");
		
		//Excepción
		try {
			log1.createNewFile();
		} catch (IOException e) {  
			System.out.println("La creación del archivo no fue posible");
		}
		
		FileWriter log1Write = new FileWriter(log1, true);
		log1Write.write("Date & Time: "+date+" DNI: "+dni+" Password: "+pass+" IP: "+request.getRemoteAddr()+" Method: "+request.getMethod()+"\nURI: "+uri+ "\n");
		log1Write.close();
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
		response.getWriter().println("<!DOCTYPE html>\n<html>\n<head>\n"+ "<meta http-equiv=\"Content-type\" content=\"text/html; charset=utf-8\" />"+"<title>Información Log1</title></head><body>");
		response.getWriter().println("<h1>Información Log1</h1>");
		response.getWriter().println("<p>Date & Time: "+date+" <br/> DNI: "+dni+" <br/>Password: "+pass+"<br/> IP: "+request.getRemoteAddr()+"<br/> Method: "+request.getMethod()+" </p>");
		response.getWriter().println("<p>URI: "+uri+" </p>");
		response.getWriter().println("</body></html>");
		
		File log1 = new File("C:\\Users\\persi\\Desktop/DatosLog1.txt");
		//Excepción
		try {
			log1.createNewFile();
		} catch (IOException e) {  
			System.out.println("La creación del archivo no fue posible");
		}
		FileWriter log1Write = new FileWriter(log1, true);
		log1Write.write("Date & Time: "+date+" DNI: "+dni+" Password: "+pass+" IP: "+request.getRemoteAddr()+" Method: "+request.getMethod()+"\nURI: "+uri+ "\n");
		log1Write.close();
	}

}

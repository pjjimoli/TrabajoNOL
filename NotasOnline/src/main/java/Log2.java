

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
 * Servlet implementation class Log2
 */
@WebServlet("/Log2")
public class Log2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Log2() {
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
		response.getWriter().println("<h1>Información Log2</h1>");
		response.getWriter().println("<p>Date & Time: "+date+" <br/> DNI: "+dni+" <br/>Password: "+pass+"<br/> IP: "+request.getRemoteAddr()+"<br/> Method: "+request.getMethod()+" </p>");
		response.getWriter().println("<p>URI: "+uri+" </p>");
		response.getWriter().println("</body></html>");
		response.getWriter().println("<p>El archivo del Log se ha creado en tu escritorio :) </p>");


        //Log2 -> configurar la ruta del archivo mediante web.xml
		File log2 = new File(this.getServletConfig().getInitParameter("logFilePath"));

        //Excepción
        try {
			log2.createNewFile();
		} catch (IOException e) {  
			System.out.println("La creación del archivo no fue posible");
        }

		FileWriter log2Write = new FileWriter(log2, true);
		log2Write.write("Date & Time: "+date+" DNI: "+dni+" Password: "+pass+" IP: "+request.getRemoteAddr()+" Method: "+request.getMethod()+"\nURI: "+uri+ "\n");
		log2Write.close();
        

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
		response.getWriter().println("<h1>Información Log2</h1>");
		response.getWriter().println("<p>Date & Time: "+date+" <br/> DNI: "+dni+" <br/>Password: "+pass+"<br/> IP: "+request.getRemoteAddr()+"<br/> Method: "+request.getMethod()+" </p>");
		response.getWriter().println("<p>URI: "+uri+" </p>");
		response.getWriter().println("</body></html>");
		response.getWriter().println("<p>El archivo del Log se ha creado en tu escritorio :) </p>");


        //Log2 -> configurar la ruta del archivo mediante web.xml
		File log2 = new File(this.getServletConfig().getInitParameter("logFilePath"));

        //Excepción
        try {
			log2.createNewFile();
		} catch (IOException e) {  
			System.out.println("La creación del archivo no fue posible");
        }

		FileWriter log2Write = new FileWriter(log2, true);
		log2Write.write("Date & Time: "+date+" DNI: "+dni+" Password: "+pass+" IP: "+request.getRemoteAddr()+" Method: "+request.getMethod()+"\nURI: "+uri+ "\n");
		log2Write.close();

        
	}

}
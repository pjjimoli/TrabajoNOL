

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.io.BufferedReader;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Scanner;

/**
 * Servlet implementation class login
 */
@WebServlet("/login")
public class login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public login() {
        super();
        // TODO Auto-generated constructor stub
        
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		String dni = request.getParameter("dni");
		String pass = request.getParameter("pass");
	    String key = "";
		
		HttpSession session = request.getSession(true);
		session.setMaxInactiveInterval(1000);
		response.setContentType("text/html");

		URL CentroEducativo = new URL("http://localhost:9090/CentroEducativo/login");
		HttpURLConnection connection = (HttpURLConnection) CentroEducativo.openConnection();

		connection.setDoInput(true);
		connection.setDoOutput(true);
		connection.setRequestMethod("POST");
		connection.setRequestProperty("Accept", "*/*");
		connection.setRequestProperty("content-Type", "application/json");

		try (Writer w = new OutputStreamWriter(connection.getOutputStream(), "UTF-8")) {

			w.write("{\"dni\": " + "\"" + dni + "\",\n\"password\": " + "\"" + pass + "\"}");
		}
		if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
		    List<String> cookies = connection.getHeaderFields().get("Set-Cookie");

		    try (Scanner scanner = new Scanner(connection.getInputStream(), "UTF-8")) {
		        String responseLine;

		        while (scanner.hasNextLine()) {
		            responseLine = scanner.nextLine();
		            key += responseLine; // Concatenamos cada línea a la cadena key
		        }
		    }
		}
		response.setContentType("text/html");
		response.getWriter()
				.println("<!DOCTYPE html>\n<html>\n<head>\n"
						+ "<meta http-equiv=\"Content-type\" content=\"text/html; charset=utf-8\" />"
						+ "<title>Información Log1</title></head><body>");
		response.getWriter().println("<h1>Información</h1>");
		response.getWriter().println("<p>token:" +  key + "</p>");
	}

}

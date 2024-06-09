

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpSession;

/**
 * Servlet Filter implementation class LoginControl
 */
@WebFilter("/LoginControl")
public class LoginControl extends HttpFilter implements Filter {
       
    private static final long serialVersionUID = 1L;

	/**
     * @see HttpFilter#HttpFilter()
     */
    public LoginControl() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	} 

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here
		System.out.println("Pasa filtro");
        HttpServletRequest http_req = (HttpServletRequest) request;
        HttpServletResponse http_resp = (HttpServletResponse) response;
        
		String dni = http_req.getParameter("dni");
		String pass = http_req.getParameter("pass");
	    String key = ""; 
	    List<String> cookies = new ArrayList<>();
		HttpSession session = http_req.getSession(true);

		if(session.getAttribute("key") == null) {
	        session.setAttribute("dni", dni);
	        session.setAttribute("pass", pass);
	        
			String nombreMaquina = request.getServerName();
			
			URL CentroEducativo = new URL("http://"+ nombreMaquina +":9090/CentroEducativo/login");
			HttpURLConnection connection = (HttpURLConnection) CentroEducativo.openConnection();
			connection.setDoInput(true);
			connection.setDoOutput(true);
			connection.setRequestMethod("POST");
			connection.setRequestProperty("Accept", "*/*");
			connection.setRequestProperty("content-Type", "application/json");
			
			for (String cookie : cookies) {
				connection.addRequestProperty("Cookie", cookie.split(";", 2)[0]);
			}
			
			try (Writer w = new OutputStreamWriter(connection.getOutputStream(), "UTF-8")) {

				w.write("{\"dni\": " + "\"" + dni + "\",\n\"password\": " + "\"" + pass + "\"}");
			} 
			
			if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
			    cookies = connection.getHeaderFields().get("Set-Cookie");
			    if (cookies != null) {
			        session.setAttribute("cookies", cookies);
			    }
			    System.out.println("HTTP_OK");
			    try (Scanner scanner = new Scanner(connection.getInputStream(), "UTF-8")) {
			        String responseLine;
	 
			        while (scanner.hasNextLine()) {
			            responseLine = scanner.nextLine();
			            key += responseLine;
			        }
			        session.setAttribute("key", key);
			        session.setAttribute("cookies", cookies);
			        
			        if(http_req.isUserInRole("rolalu")) {
			        	System.out.println("Alumno");
						http_resp.sendRedirect(http_req.getContextPath() + "/AlumnoDetail.html");
			        	return; } else if (http_req.isUserInRole("rolpro")) {System.out.println("Prof"); http_resp.sendRedirect(http_req.getContextPath() + "/ProfeDetail.html"); return;}
			    }
			} else {
				System.out.println("Parece que no ha sido posible establecer la conexion con el servidor");
				http_req.getSession().invalidate();
				http_resp.sendRedirect("index.html");
			}
			// pass the request along the filter chain
		
		} else { if(http_req.isUserInRole("rolalu")) {
			System.out.println("Alumno");
			http_resp.sendRedirect(http_req.getContextPath() + "/AlumnoDetail.html");
        	return; } else if (http_req.isUserInRole("rolpro")) {System.out.println("Prof");http_resp.sendRedirect(http_req.getContextPath() + "/ProfeDetail.html"); return;}}
		// pass the request along the filter chain
		chain.doFilter(request, response);
	} 

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}

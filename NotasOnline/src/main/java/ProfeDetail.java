

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Servlet implementation class ProfeDetail
 */
@WebServlet("/ProfeDetail")
public class ProfeDetail extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProfeDetail() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String nombreServer = request.getServerName();
		HttpSession session = request.getSession();
    	String dni = session.getAttribute("dni").toString();
    	String key = session.getAttribute("key").toString();
    	List<String> cookies = (List<String>) session.getAttribute("cookies");
    	
		if(request.isUserInRole("rolpro")) {
	    	
			  
		} else {
				response.setStatus(401);
				response.getWriter().append("No tienes permitido realizar esta accion!");
				return;
				}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	private JSONObject getProf(String dni,
			String key, List<String> cookies, String nombreServer) throws IOException {
		HttpURLConnection connection = null;

		String auxiliar = "";
		String jsonString = "";

		
		connection = (HttpURLConnection) new URL(
					"http://"+ nombreServer +":9090/CentroEducativo/profesores/" + dni + "?key=" + key).openConnection();
		connection.setDoInput(true);
		connection.setRequestMethod("GET");
		connection.setRequestProperty("Accept", "*/*");
		
		for (String cookie : cookies) {
			connection.addRequestProperty("Cookie", cookie.split(";", 2)[0]);
		}
		
		BufferedReader buff = new BufferedReader(new InputStreamReader(connection.getInputStream()));

		while ((auxiliar = buff.readLine()) != null) { 
			jsonString += auxiliar;
		}

		    JSONObject Profesor = new JSONObject(jsonString);

		return Profesor;
	}
	
	private JSONArray getAsignProf(String dni, String key, List<String> cookies, String nombreServer)
			throws MalformedURLException, IOException {
		String auxiliar = "";
		String jsonString = "";
		
		HttpURLConnection connection = (HttpURLConnection) new URL(
				"http://"+ nombreServer +":9090/CentroEducativo/profesores/" + dni + "/asignaturas?key=" + key).openConnection();

		connection.setDoInput(true);
		connection.setRequestMethod("GET");
		connection.setRequestProperty("Accept", "*/*");
		for (String cookie : cookies) {
			connection.addRequestProperty("Cookie", cookie.split(";", 2)[0]);
		}
		
		BufferedReader buff = new BufferedReader(new InputStreamReader(connection.getInputStream()));
 
		while ((auxiliar = buff.readLine()) != null) {
			jsonString += auxiliar;
		}
		
		JSONArray Asignaturas = new JSONArray(jsonString);
		
		return Asignaturas;
	}
	
	private JSONArray getAlumnosProf(String dni, String key, List<String> cookies, String nombreServer)
			throws MalformedURLException, IOException {
		String auxiliar = "";
		String jsonString = "";
		
		HttpURLConnection connection = (HttpURLConnection) new URL(
				"http://"+ nombreServer +":9090/CentroEducativo/profesores/" + dni + "/asignaturas?key=" + key).openConnection();

		connection.setDoInput(true);
		connection.setRequestMethod("GET");
		connection.setRequestProperty("Accept", "*/*");
		for (String cookie : cookies) {
			connection.addRequestProperty("Cookie", cookie.split(";", 2)[0]);
		}
		
		BufferedReader buff = new BufferedReader(new InputStreamReader(connection.getInputStream()));
 
		while ((auxiliar = buff.readLine()) != null) {
			jsonString += auxiliar;
		}
		
		JSONArray Asignaturas = new JSONArray(jsonString);
		
		return Asignaturas;
	}
	
	

}

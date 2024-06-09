

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;  

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.net.MalformedURLException;


import org.json.*;  
 
/**
 * Servlet implementation class AlumnoDetail
 */
@WebServlet("/AlumnoDetail")  
public class AlumnoDetail extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AlumnoDetail() { 
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
    	
		if(request.isUserInRole("rolalu")) {
	    	String action = request.getParameter("action");
			response.setContentType("application/json");
			
			if (action.equals("getAlumno")) { 
				 
	            response.setCharacterEncoding("UTF-8");
	            response.getWriter().write(getAlu(dni, key, cookies, nombreServer).toString());
			} 
			else if (action.equals("getAsignAlumn")) {
				
	            response.setCharacterEncoding("UTF-8");
	            System.out.println("Array: "+ getAsignAlumn(dni, key, cookies, nombreServer).toString());
	            response.getWriter().write(getAsignAlumn(dni, key, cookies, nombreServer).toString());
			} else if (action.equals("getAvatar")) {

                String carpeta = getServletContext().getRealPath("/WEB-INF/img");
                response.setContentType("text/plain");
                response.setCharacterEncoding("UTF-8");

                BufferedReader origen = new BufferedReader(new FileReader(carpeta+"/"+ dni + ".pngb64" ));
                PrintWriter out = response.getWriter();
                out.print("{ \"dni \": \""+ dni +"\",\"img\": \""); // Hay complicaciones con las comillas 
                String linea = origen.readLine(); out.print(linea); // Y con los saltos de línea!!
                while ((linea = origen.readLine()) != null) {out.print("\n"+linea);}
                out.print("}");origen.close(); 
           }
	
			  
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
	
	private JSONObject getInfoAsignatura(String acronimo, String key, List<String> cookies, String nombreServer, JSONObject Asignas)
			throws MalformedURLException, IOException {
		HttpURLConnection connection = (HttpURLConnection) new URL(
				"http://"+ nombreServer +":9090/CentroEducativo/asignaturas/" + acronimo + "?key=" + key).openConnection();
		
		String auxiliar = ""; 
		String jsonString = ""; 
		
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
	    JSONObject asignaturaInfo = new JSONObject(jsonString.toString());
	    
	    // Añadir la información de la asignatura al JSONArray Asignas
	    //Asignas.put(asignaturaInfo);
	 // Merge the new information into the existing JSON object
	    for (String k : asignaturaInfo.keySet()) {
	        Asignas.put(k, asignaturaInfo.get(k ));
	    }
	    return Asignas;
	}
	 
	private JSONArray getAsignAlumn(String dni, String key, List<String> cookies, String nombreServer)
			throws MalformedURLException, IOException {
		String auxiliar = "";
		String jsonString = "";
		
		HttpURLConnection connection = (HttpURLConnection) new URL(
				"http://"+ nombreServer +":9090/CentroEducativo/alumnos/" + dni + "/asignaturas?key=" + key).openConnection();

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
		
		JSONArray Asignas = new JSONArray(jsonString);
		
		for (int i = 0; i < Asignas.length(); i++ ){
			JSONObject jsonObject = Asignas.getJSONObject(i);
		    String acronimo = jsonObject.getString("asignatura");
			//Asignas = getInfoAsignatura(acronimo, key, cookies, nombreServer, Asignas);
	        JSONObject updatedObject = getInfoAsignatura(acronimo, key, cookies, nombreServer, jsonObject);
			Asignas.put(i, updatedObject); // Update the existing object at index i
		    
		} 
		return Asignas;
	}
	
	private JSONObject getAlu(String dni,
			String key, List<String> cookies, String nombreServer) throws IOException {
		HttpURLConnection connection = null;

		String auxiliar = "";
		String jsonString = "";

		
		connection = (HttpURLConnection) new URL(
					"http://"+ nombreServer +":9090/CentroEducativo/alumnos/" + dni + "?key=" + key).openConnection();
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

		    JSONObject Alumno = new JSONObject(jsonString);

		return Alumno;
	}
}

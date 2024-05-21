

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.ArrayList;
import java.io.BufferedReader;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.json.*; 
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
		request.getSession().invalidate();
		response.sendRedirect("index.html");
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
	    List<String> cookies = new ArrayList<>();
	    
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
		    cookies = connection.getHeaderFields().get("Set-Cookie");

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
		response.getWriter().println("<p>lista de asig:" + getAsignAlumn(dni, key, cookies).get(1).getNombre()  + "datos asignatura:" + getInfoAsignatura("DEW", key, cookies).getNombre() + "</p>");
	}

	private List<AsignaturasAlumn> getAsignAlumn(String dni, String key, List<String> cookies)
			throws MalformedURLException, IOException {
		String auxiliar = "";
		String jsonString = "";
		
		HttpURLConnection connection = (HttpURLConnection) new URL(
				"http://localhost:9090/CentroEducativo/alumnos/" + dni + "/asignaturas?key=" + key).openConnection();

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
		
		JSONArray jsonArray = new JSONArray(jsonString);
		List<AsignaturasAlumn> asignaturas = new ArrayList<>();
		
		for (int i = 0; i < jsonArray.length(); i++) {
		    JSONObject jsonObject = jsonArray.getJSONObject(i);
		    String nombre = jsonObject.getString("asignatura");
		    String nota = jsonObject.getString("nota");

		    AsignaturasAlumn asignatura = new AsignaturasAlumn();
		    asignatura.setNombre(nombre);
		    asignatura.setNota(nota);

		    asignaturas.add(asignatura);
		}
		
		return asignaturas;
	}
	
	private Asignaturas getInfoAsignatura(String acronimo, String key, List<String> cookies)
			throws MalformedURLException, IOException {
		HttpURLConnection connection = (HttpURLConnection) new URL(
				"http://localhost:9090/CentroEducativo/asignaturas/" + acronimo + "?key=" + key).openConnection();
		
		String auxiliar = "";
		String jsonString = "";
	    Asignaturas asignatura = new Asignaturas();
		
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
		

		    JSONObject jsonObject = new JSONObject(jsonString);
		    String acronimos = jsonObject.getString("acronimo");
		    int creditos = jsonObject.getInt("creditos");
		    String cuatrimestre = jsonObject.getString("cuatrimestre");
		    String nombre = jsonObject.getString("nombre");
		    int curso = jsonObject.getInt("curso");

		    asignatura.setAcronimo(acronimos);
		    asignatura.setCreditos(creditos);
		    asignatura.setCuatrimestre(cuatrimestre);
		    asignatura.setNombre(nombre);
		    asignatura.setCurso(curso);


		return asignatura;
	}
	
	/*
	 * Objeto AsignaturasAlumno
	 */
	public class AsignaturasAlumn {

		private String asignatura;
		private String nota;

		public AsignaturasAlumn() {
		}

		public String getNombre() {
			return asignatura;
		}

		public String getNota() {
			return nota;
		}
		
		public void setNombre(String asignatura) {
			this.asignatura = asignatura;
		}

		public void setNota(String nota) {
			this.nota = nota;
		}
	}
	
	/*
	 * Objeto Asignaturas
	 */
	
	public class Asignaturas {

		private String acronimo;
		private int creditos;
		private String cuatrimestre;
		private String nombre;
		private int curso;

		public Asignaturas() {
		}

		public String getAcronimo() {
			return acronimo;
		}

		public int getCreditos() {
			return creditos;
		}

		public String getCuatrimestre() {
			return cuatrimestre;
		}

		public String getNombre() {
			return nombre;
		}

		public int getCurso() {
			return curso;
		}
		
		public void setAcronimo(String acronimo) {
			this.acronimo = acronimo;
		}
		
		public void setCreditos(int creditos) {
			this.creditos = creditos;
		}
		
		public void setCuatrimestre(String cuatrimestre) {
			this.cuatrimestre = cuatrimestre;
		}
		
		public void setNombre(String nombre) {
			this.nombre = nombre;
		}
		
		public void setCurso(int curso) {
			this.curso = curso;
		}
	}
}

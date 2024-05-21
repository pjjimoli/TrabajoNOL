

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
		List<AsignaturasAlumn> asignas = getAsignAlumn(dni, key, cookies);
		response.setContentType("text/html");
		response.getWriter()
		.println("<!DOCTYPE html>\n<html>\n<head>\n"
				+ "<meta http-equiv=\"Content-type\" content=\"text/html; charset=utf-8\" />\n"
				+ "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\n"
				+ "<link href=\"https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css\" rel=\"stylesheet\" integrity=\"sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH\" crossorigin=\"anonymous\">\n"
				+ "<link href=estilo.css rel=\"stylesheet\">\n"
				+ "<script src=\"https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js\"></script>\n"
				+ "<script src=\"https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js\"></script>"
				+ "<title>Alumno - Notas Online</title></head>\n");
		response.getWriter()
		.println("<body>\r\n"
				+ "<form action=\"login\" method=\"get\">\r\n"
				+ "              <input class=\"logout\" type=\"submit\" value=\"Cerrar Sesión\">\r\n"
				+ "          </form>\r\n"
				+ "          \r\n"
				+ "	<main class=\"container-fluid\">\r\n"
				+ "		<div class=\"p-4 p-md-5 mb-4 main\">\r\n"
				+ "			<div class=\"mx-auto p-1 center\">\r\n"
				+ "\r\n"
				+ "\r\n"
				+ "\r\n"
				+ "				<h1 class=\"display-4 fw-bold\">NOTAS ONLINE</h1>\r\n"
				+ "				<p class=\"lead my-1\">"+ getAlu(dni, key, cookies).getNombre() +" "+ getAlu(dni, key, cookies).getApellidos()+ "</p>\r\n"
				+ "				<p class=\"lead my-3\">Aquí se muestran las asignaturas en las que\r\n"
				+ "					estás matriculado junto a sus notas</p>\r\n"
				+ "			</div>\r\n"
				+ "		</div>\r\n"
				+ "		<div class=\"row g-5\">\r\n"
				+ "			<div class=\"col-md-8\">\r\n"
				+ "			\r\n"
				+ "				<div class=\"card text-center tabs\">\r\n"
				+ "    <div class=\"card-header \">\r\n"
				+ "    <ul class=\"nav nav-tabs card-header-tabs \">\r\n");
				response.getWriter()
				.println("<li class=\"nav-item \">\r\n"
				+ "        <a class=\"nav-link active unselected-tab\" id=\"a0-tab\" data-toggle=\"tab\" href=\"#a0\" role=\"tab\" aria-controls=\"a0\" aria-selected=\"true\">" + asignas.get(0).getNombre() + "</a>\r\n"
				+ "      </li>\r\n");
				for (int i = 1; i < asignas.size(); i++) {
					response.getWriter().println("<li class=\"nav-item\">\r\n"
							+ "        <a class=\"nav-link unselected-tab\" id=\"a " + i + "-tab\" data-toggle=\"tab\" href=\"#a"+ i +"\" role=\"tab\" aria-controls=\"a"+ i +"\" aria-selected=\"false\">"+asignas.get(i).getNombre()+"</a>\r\n"
							+ "      </li>\r\n");
				}
				Asignaturas primera = getInfoAsignatura(asignas.get(0).getNombre(), key, cookies);
				response.getWriter().println("    </ul>\r\n"
				+ "  </div>\r\n"
				+ "  \r\n"
				+ "  \r\n"
				+ "  <div class=\"card-body\">\r\n"
				+ "                    <div class=\"tab-content\" id=\"myTabContent\">\r\n"
				+ "                        <div class=\"tab-pane fade show active\" id=\"a0\" role=\"tabpanel\" aria-labelledby=\"a0-tab\">\r\n"
				+ "                          <table>\r\n"
				+ "                            <tr><th>Nombre</th><th>Nota</th><th>Curso</th><th>Cuatrimestre</th><th>Créditos</th></tr>\r\n"
				+ "                            <tr><td>"+primera.getNombre()+"</td><td>"+asignas.get(0).getNota()+"</td><td>"+primera.getCurso()+"</td><td>"+primera.getCuatrimestre()+"</td><td>"+primera.getCreditos()+"</td></tr>\r\n"
				+ "                        </table>\r\n"
				+ "                        </div>\r\n");
				for (int i = 1; i < asignas.size(); i++) {
					Asignaturas asi = getInfoAsignatura(asignas.get(i).getNombre(), key, cookies);
					response.getWriter().println(
					"                        <div class=\"tab-pane fade show active\" id=\"a"+i+"\" role=\"tabpanel\" aria-labelledby=\"a"+i+"-tab\">\r\n"
					+ "                          <table>\r\n"
					+ "                            <tr><th>Nombre</th><th>Nota</th><th>Curso</th><th>Cuatrimestre</th><th>Créditos</th></tr>\r\n"
					+ "                            <tr><td>"+asi.getNombre()+"</td><td>"+asignas.get(i).getNota()+"</td><td>"+asi.getCurso()+"</td><td>"+asi.getCuatrimestre()+"</td><td>"+asi.getCreditos()+"</td></tr>\r\n"
					+ "                        </table>\r\n"
					+ "                        </div>\r\n");
				}
				response.getWriter().println( "                    </div>\r\n"
				+ "                </div>\r\n"
				+ "  \r\n"
				+ "  \r\n"
				+ "</div>\r\n"
				+ "				\r\n"
				+ "			</div>\r\n"
				+ "			<div class=\"col-md-4\">\r\n"
				+ "				<div class=\"p-4 mb-3 grupo\">\r\n"
				+ "					<h4 class=\"center\">GRUPO 3TI21_G2</h4>\r\n"
				+ "					<ul>\r\n"
				+ "						<li>Blauvac Brea, Adrián Pierre</li>\r\n"
				+ "						<li>García Bartolomé, Javier</li>\r\n"
				+ "						<li>Jiménez Olivares, Pedro José</li>\r\n"
				+ "						<li>Moris Puig, Yvan</li>\r\n"
				+ "						<li>Rea Mejia, Maria Carmen</li>\r\n"
				+ "						<li>Trull Martí, Andreu</li>\r\n"
				+ "					</ul>\r\n"
				+ "				</div>\r\n"
				+ "			</div>\r\n"
				+ "		</div>\r\n"
				+ "	</main>\r\n"
				+ "	<script src=\"https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js\" integrity=\"sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz\" crossorigin=\"anonymous\"></script>\r\n"
				+ "</body>\r\n"
				+ "\r\n"
				+ "\r\n"
				+ "</html>");
	}

	private Alumno getAlu(String user,
			String key, List<String> cookies) throws IOException {
		HttpURLConnection connection = null;

		String auxiliar = "";
		String jsonString = "";
	    Alumno alumno = new Alumno();

		
		connection = (HttpURLConnection) new URL(
					"http://localhost:9090/CentroEducativo/alumnos/" + user + "?key=" + key).openConnection();
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
		    String nombre = jsonObject.getString("nombre");
		    String apellidos = jsonObject.getString("apellidos");
		    String dni = jsonObject.getString("dni");

		    alumno.setNombre(nombre);
		    alumno.setApellidos(apellidos);
		    alumno.setDni(dni);


		return alumno;
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
	 * Objeto Alumno
	 */
	public class Alumno {

		private String apellidos;
		private String dni;
		private String nombre;

		public Alumno() {
		}

		public String getNombre() {
			return nombre;
		}

		public String getApellidos() {
			return apellidos;
		}
		
		public String getDni() {
			return dni;
		}
		
		public void setNombre(String nombre) {
			this.nombre = nombre;
		}

		public void setApellidos(String apellidos) {
			this.apellidos = apellidos;
		}
		
		public void setDni(String dni) {
			this.dni = dni;
		}
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


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
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
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String nombreServer = request.getServerName();
        HttpSession session = request.getSession();
        String dni = session.getAttribute("dni").toString();
        String key = session.getAttribute("key").toString();
        List<String> cookies = (List<String>) session.getAttribute("cookies");

        if (request.isUserInRole("rolpro")) {
            String action = request.getParameter("action");
            String asign = request.getParameter("asign");
            System.out.println("asignatura: " + asign);
            System.out.println("action: " + action);
            if (action != null) {
                if (action.equals("getProfe")) {
                    response.setContentType("application/json");
                    response.setCharacterEncoding("UTF-8");
                    response.getWriter().write(getProf(dni, key, cookies, nombreServer).toString());
                } else if (action.equals("getAsignProfe")) {
                    response.setContentType("application/json");
                    response.setCharacterEncoding("UTF-8");
                    response.getWriter().write(getAsignProf(dni, key, cookies, nombreServer).toString());
                } else if (action.equals("getAlumnAsign")) {
                    response.setContentType("application/json");
                    response.setCharacterEncoding("UTF-8");
                    response.getWriter().write(getAlumnosAsign(asign, key, cookies, nombreServer).toString());
                } else if (action.equals("getAvatar")) {
    				
                    String carpeta = getServletContext().getRealPath("/WEB-INF/img");
                    System.out.println("Carpeta: " + carpeta);
                    response.setContentType("text/plain");
                    response.setCharacterEncoding("UTF-8");
                    
                    BufferedReader origen = new BufferedReader(new FileReader(carpeta+"/"+ asign+ ".pngb64" ));
                    PrintWriter out = response.getWriter();
                    out.print("{\"dni\": \""+asign+"\", \"img\": \""); // Hay complicaciones con las comillas 
                    String linea = origen.readLine(); out.print(linea); // Y con los saltos de línea!!
                    while ((linea = origen.readLine()) != null) {out.print("\n"+linea);}
                    out.print("\"}");origen.close(); 
               } else { System.out.println("No lo pilla");}
            }

        } else {
            response.setStatus(401);
            response.getWriter().append("No tienes permitido realizar esta accion!");
            return;
        } 
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    private JSONObject getProf(String dni,
            String key, List<String> cookies, String nombreServer) throws IOException {
        HttpURLConnection connection = null;

        String auxiliar = "";
        String jsonString = "";

        connection = (HttpURLConnection) new URL(
                "http://" + nombreServer + ":9090/CentroEducativo/profesores/" + dni + "?key=" + key).openConnection();
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
        System.out.println("getAsignProf");
        HttpURLConnection connection = (HttpURLConnection) new URL(
                "http://" + nombreServer + ":9090/CentroEducativo/profesores/" + dni + "/asignaturas?key=" + key)
                .openConnection();

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

        //for (int i = 0; i < Asignaturas.length(); i++) {
         //   JSONObject asignatura = Asignaturas.getJSONObject(i);
         //   String acronimo = asignatura.getString("asignatura");
         //   JSONObject updatedAsignatura = getAlumnosAsigna(acronimo, key, cookies, nombreServer, asignatura);
         //   
         //  Asignaturas.put(i, updatedAsignatura); // Update the existing object at index i
       // }

        return Asignaturas;
    }

    private JSONArray getAlumnosAsign(String acronimo, String key, List<String> cookies, String nombreServer)
            throws MalformedURLException, IOException {
        String auxiliar = "";
        String jsonString = "";
       
        HttpURLConnection connection = (HttpURLConnection) new URL(
                "http://" + nombreServer + ":9090/CentroEducativo/asignaturas/" + acronimo + "/alumnos?key=" + key)
                .openConnection();
        System.out.println("getAlumnosAsign");
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

        JSONArray alumnosArray = new JSONArray(jsonString);

        // Crear un JSONArray vacío para almacenar los alumnos actualizados


        // Actualizar el objeto de asignatura con el JSONArray de alumnos actualizado
	    //for (String k : alumnosArray.keySet()) {
	    //    asignatura.put(k, alumnosArray.get(k ));
	    //}
      //for (int i = 0; i < alumnosArray.length(); i++) {
        //   JSONObject alumno = alumnosArray.getJSONObject(i);
        //   String dni = alumno.getString("dni");
        //   JSONObject updatedalumno= getAlu(dni, key, cookies, nombreServer, alumno);
        //   
        //  alumnosArray.put(i, updatedAlumno); // Update the existing object at index i
      // }

        return alumnosArray ;

    }

    private JSONObject getAlu(String dniAlumno,
            String key, List<String> cookies, String nombreServer, JSONObject Alumno) throws IOException {
        HttpURLConnection connection = null;

        String auxiliar = "";
        String jsonString = "";

        connection = (HttpURLConnection) new URL(
                "http://" + nombreServer + ":9090/CentroEducativo/alumnos/" + dniAlumno + "?key=" + key)
                .openConnection();
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

        JSONObject AlumnoInfo = new JSONObject(jsonString.toString());

        
        // Merge the new information into the existing JSON object
        for (String a : AlumnoInfo.keySet()) {
            Alumno.put(a, AlumnoInfo.get(a));
        }

        return Alumno;
    }

}

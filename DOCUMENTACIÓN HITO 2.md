<h1>DOCUMENTACIÓN HITO 2 </h1>


<h2>PÁGINA DE ENTRADA Y ENLACE A LA OPERACIÓN</h2>

Aquí nos encontramos en la situación de hacer un página html que sirva de index para posteriormente entrar a un supuesto login tanto para alumno como para profesor. Hemos creado este html siguiendo un poco el diseño que se proporciona en los documentos explicativos del trabajo, aparte de darle unos retoques con un archivo css que hemos creado. Este es el html en cuestión: 

    <!doctype html>
    <html lang="es-es">
    <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <link href=estilo.css rel="stylesheet">
    <title>Notas Online</title>


    </head>
    <body>
    <main class="container-fluid">
    <div class="p-4 p-md-5 mb-4 main">
    <div class="mx-auto p-2 center">
    <h1 class="display-4 fw-bold">NOTAS ONLINE</h1>
    <p class="lead my-3">Bienvenid@, aquí podrás comprobar tus notas
    si eres un alumno o gestionar a tus alumnos si eres un profesor</p>
    </div>
    </div>
    <div class="row g-5">
    <div class="col-md-8">
    <div class="p-4 mb-3 login center">
    <h3 class="center"><a href="login.html" class="center"><input type="button" value="ACCESO ALUMNO" class="main-button"></a></h3> 

    <p>
    Podrás consultar tus calificaciones
    </p>


    <h3 class="center"><a href="proximamente.html" class="center"><input type="button" value="ACCESO PROFESOR" class="main-button"></a></h3>
    <p>
    Podrás consultar o modificar las
    calificaciones en tus asignaturas
    </p>
    </div>
    </div>
    <div class="col-md-4">
    <div class="p-4 mb-3 grupo">
    <h4 class="center">GRUPO 3TI21_G2</h4>
    <ul>
    <li>Blauvac Brea, Adrián Pierre</li>
    <li>García Bartolomé, Javier</li>
    <li>Jiménez Olivares, Pedro José</li>
    <li>Moris Puig, Yvan</li>
    <li>Rea Mejia, Maria Carmen</li>
    <li>Trull Martí, Andreu</li>
    </ul>
    </div>
    </div>
    </div>
    </main>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
    </body>


    </html>

Como enlace para las posteriores operaciones del alumno tenemos este botón que redirigirá a un login.html:

    <a href="login.html" class="center"><input type="button" value="ACCESO ALUMNO" class="main-button"></a>

(También se ha creado otro para profesor, pero no lo comentamos ya que como no pertenece a este hito, no lo vemos necesario.)

<h2>AUTENTICACIÓN WEB</h2>

Ahora, hemos incorporado estas líneas dentro del archivo tomcat-user.xml, para guardar estos usuarios y contraseñas y vincularlos a su correspondiente rol. Hay dos roles, rolpro para profesores y rolalu para alumnos.

    <tomcat-users xmlns="http://tomcat.apache.org/xml"
    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    xsi:schemaLocation="http://tomcat.apache.org/xml tomcat-users.xsd"
    version="1.0">
    <role rolename="rolpro"/>
    <user username="23456733H" password="123456" roles="rolpro"/>
    <user username="10293756L" password="123456" roles="rolpro"/>
    <user username="06374291A" password="123456" roles="rolpro"/>
    <user username="65748923M" password="123456" roles="rolpro"/>
    <role rolename="rolalu"/>
    <user username="12345678W" password="123456" roles="rolalu"/>
    <user username="23456387R" password="123456" roles="rolalu"/>
    <user username="34567891F" password="123456" roles="rolalu"/>
    <user username="93847525G" password="123456" roles="rolalu"/>
    <user username="37264096W" password="123456" roles="rolalu"/>
    </tomcat-users>

Y también hemos puesto estas líneas en el web.xml para controlar la seguridad y autenticidad de los roles.

    <security-constraint>
        <web-resource-collection>
            <web-resource-name>Roles</web-resource-name>
            <url-pattern>/*</url-pattern>
        </web-resource-collection>
            <auth-constraint>
                <role-name>rolpro</role-name>
                <role-name>rolalu</role-name>
            </auth-constraint>
        </security-constraint>
        <security-role>
            <role-name>rolpro</role-name>
        </security-role>
        <security-role>
            <role-name>rolalu</role-name>
        </security-role>
        
        <login-config>
            <auth-method>BASIC</auth-method>
            <realm-name>Protegido</realm-name>
        </login-config>


<h2>LOGIN CON CENTROEDUCATIVO Y MANTENIMIENTO DE LA SESIÓN</h2>

Para el inicio de sesión hemos creado este html, básico para insertar dni, contraseña y un botón para iniciar la sesión. 

    <!doctype html>
    <html lang="es-es">
    <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <link href=estilo.css rel="stylesheet">
    <title>Notas Online</title>


    </head>
    <body>

    <main class="container-fluid">
    <div class="p-4 p-md-5 mb-4 main">
    <div class="mx-auto p-2 center">
    <h1 class="display-4 fw-bold">NOTAS ONLINE</h1>
    <p class="lead my-3">Bienvenid@, aquí podrás comprobar tus notas
    si eres un alumno o gestionar a tus alumnos si eres un profesor</p>
    </div>
    </div>
    <div class="row g-5">
    <div class="panel">
    <div class="p-4 mb-10 login center">
    <h2>Iniciar Sesión</h2>
    <br>
    <form action="login" method="post" class="form-signin">
            
        
            <div class="form-floating">
                <input type="text" name="dni" class="form-control" id="floatingInput" placeholder="DNI" required="required">
                <label for="floatingInput">DNI</label>
            </div>
            <div class="form-floating">
                <input type="password" name="pass" class="form-control" id="floatingPassword" placeholder="Password" required="required">
                <label for="floatingPassword">Contraseña</label>
            </div>
            <a href="alumno.html"><button class="w-25 btn custom-btn" type="submit"><div class="flecha"><i class="arrow right"></i></div></button></a>
            </form>
            <br>
            <p>Utilice sus credenciales para iniciar sesión</p>
    </div>

    </div>

    </div>
    </main>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
    </body>


    </html>

Aqui nos encontramos con la creación de la sesión y cookies con las lineas:

            List<String> cookies = new ArrayList<>();
            
            HttpSession session = request.getSession(true);
            session.setMaxInactiveInterval(1000);
            response.setContentType("text/html");

Mediante la librería javax.servlet.http.HttpSession; hemos creado un objeto session, que se mantendrá activo hasta que cumpla el intervalo de 1000 que le hemos dado con session.setMaxInactiveInterval(1000); o hasta que cierre la sesión manualmente con el método doGet() en posteriores campos de la documentación.
Como dijimos antes, el método doPost() se activará en el momento de dar click en el botón:

    <a href="alumno.html"><button class="w-25 btn custom-btn" type="submit"><div class="flecha"><i class="arrow right"></i></div></button></a>  

Y este es nuestro método doPost, gracias a diversas librerias podemos hacer uso de diversos métodos aqui. Primero creamos un objeto URL para provocar el Login con la url "http://localhost:9090/CentroEducativo/login", apartir de ese objeto URL, abrimos una conexión a esta con un objeto llamado connection, después le pasamos los parámetros necesarios para realizar la petición POST del login. Lo demás pertenece a otros apartados de la documentación. Esto nos devuelve la key, que la leemos mediante un Scanner, la guardamos en la variable key, ya que tendrá uso durante toda la sesión.



    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

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
		    if (!key.equals("-1")) {
				if (request.isUserInRole("rolpro")) {
					htmlProf(response, dni, key, cookies);
				} else if (request.isUserInRole("rolalu")) {
					htmlAlu(response, dni, key, cookies);
				} else {
					response.sendError(401);
				}
			}
		}

	}

En el método doGet() hemos hecho que el botón cerrar sesión, el cual aparece en las páginas detalle para alumnos y profesores mas posteriores, cierre la sesión de manera manual con .invalidate() y tras ello, redirija de nuevo a index.html.

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.getSession().invalidate();
		response.sendRedirect("index.html");
	}


<h2>CONSTRUCCION Y ENVÍO DE LAS PETICIONES A CENTROEDUCATIVO</h2>

Ahora utilizaré de ejemplo el método getAsigAlumn() que tenemos para mostrar como realizamos las peticiones a CentroEducativo, en este caso, el objetivo es obtener una lista de asignaturas. El proceso es igual que en el apartado anterior para solicitar la petición login, pero esta vez los parámetros son diferentes, ya que ahora realizamos una petición GET, y el url de petición es diferente.

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

<h2>INTERPRETACIÓN DE LAS RESPUESTAS DE CENTROEDUCATIVO
</h2>

Ahora usaremos la continuación del código del apartado anterior.
Usaremos la libreria de BufferedReader, para crear un objeto buff que lea el resultado de la respuesta al GET a CentroEducativo, este viene en formato de bits, pero gracias a estas librerias podemos transformarlo en Strings. Estos Strings los leeremos mediante buff.readLine(), como lee línea a línea necesitamos una variable auxiliar dentro de un bucle while para que vaya sumando estas líneas a otra variable jsonString.

Tras obtener los datos de respuesta en formato JSON y transformarlos a String, ahora creamos un objeto JSONArray, proporcionado por la libreria org.json.*; que se nos da en la documentación explicativa del proyecto. Al crear este objeto jsonArray, creamos una ArrayList<>(); para guardar posteriormente las asignaturas. Creamos un bucle para recorrer todo este jsonArray y poder leer los objetos en formato json, gracias a JSONObject, los guardamos en unas variables que luego usaremos para escribir en el ArrayList del tipo AsignaturasAlumn, objeto que hemos creado para que se pueda interactuar con su interfaz y escribir los parámetros en el ArrayList. Aqui proporcionamos el código de ambos:

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

Creación de la clase AsignaturasAlumn:

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

<h2>CONSTRUCCIÓN Y RETORNO DE LAS PÁGINAS HTML DE RESPUESTA
</h2>
 Por último comentar sobre la página html de respuesta de forma dinámica que se crea al hacer login como un alumno determinado. Se llamará a este método htmlAlu() en el doPost si a la hora de hacer el login el usuario se identifica como alumno, posteriormente, recibimos los parámetros response, dni, key y las cookies.
 
 Se creará una página en la que tenemos una descripción con nombre y apellidos del alumno arriba, a la derecha nuestro grupo y nombres y por último y más importante las pestañas de cada asignatura junto a la descripción de cada una y la nota del alumno.

 Creamos una ArrayList del tipo AsignaturasAlumn que llame al método getAsignAlumn y asi recuperar las asignaturas, posteriormente recuperamos el nombre y apellidos mediante getAlu.getNombre, Alumno es otro objeto creado que no se ha mencionado porque la estructura y el uso es para el mismo que AsignaturasAlumn que mencionamos antes.

 Dentro de la primera pestaña, que empieza activa llamamos obtenemos su acronimo con la lista creada anteriormente con las asignaturas, haciendo referencia al primer elemento del Array mediante get(0).
Luego haremos algo parecido pero en un bucle con las siguientes asignaturas, ya que no sabemos la cantidad de asignaturas a la que está matriculado el alumno o alumna.

Por último creamos otro bucle para llamar al método getInfoAsignatura(), que nos proporciona la información de la asignatura seleccionada y asi poder mostrarla dinámicamente dentro de la pestaña de la asignatura seleccionada, esta vinculación a la pestaña la realizamos asegurandonos que el id de la pestaña y de la tabla que muestra los datos coincidan.


    private void htmlAlu(HttpServletResponse response,String dni, String key, List<String> cookies) throws MalformedURLException, IOException{
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
                    + "        <a class=\"nav-link active unselected-tab\" id=\"a1-tab\" data-toggle=\"tab\" href=\"#a1\" role=\"tab\" aria-controls=\"a1\" aria-selected=\"true\">" + asignas.get(0).getNombre() + "</a>\r\n"
                    + "      </li>\r\n");
                    for (int i = 1; i < asignas.size(); i++) {
                        response.getWriter().println("<li class=\"nav-item\">\r\n"
                                + "        <a class=\"nav-link unselected-tab\" id=\"a " + (i+1) + "-tab\" data-toggle=\"tab\" href=\"#a"+ (i+1) +"\" role=\"tab\" aria-controls=\"a"+ (i+1) +"\" aria-selected=\"false\">"+asignas.get(i).getNombre()+"</a>\r\n"
                                + "      </li>\r\n");
                    }
                    Asignaturas primera = getInfoAsignatura(asignas.get(0).getNombre(), key, cookies);
                    response.getWriter().println("    </ul>\r\n"
                    + "  </div>\r\n"
                    + "  \r\n"
                    + "  \r\n"
                    + "  <div class=\"card-body\">\r\n"
                    + "                    <div class=\"tab-content\" id=\"myTabContent\">\r\n"
                    + "                        <div class=\"tab-pane fade show active\" id=\"a1\" role=\"tabpanel\" aria-labelledby=\"a1-tab\">\r\n"
                    + "                          <table>\r\n"
                    + "                            <tr><th>Nombre</th><th>Nota</th><th>Curso</th><th>Cuatrimestre</th><th>Créditos</th></tr>\r\n"
                    + "                            <tr><td>"+primera.getNombre()+"</td><td>"+asignas.get(0).getNota()+"</td><td>"+primera.getCurso()+"</td><td>"+primera.getCuatrimestre()+"</td><td>"+primera.getCreditos()+"</td></tr>\r\n"
                    + "                        </table>\r\n"
                    + "                        </div>\r\n");
                    
                    for (int i = 1; i < asignas.size(); i++) {
                    
                        Asignaturas asi = getInfoAsignatura(asignas.get(i).getNombre(), key, cookies);
                        response.getWriter().println(
                        "                        <div class=\"tab-pane fade show active\" id=\"a"+(i+1)+"\" role=\"tabpanel\" aria-labelledby=\"a"+(i+1)+"-tab\">\r\n"
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
        
<h1>MEMORIA DEL TRABAJO NOTAS ON-LINE</h1>

Realizado por:
  - Blauvac Brea, Adrián Pierre <br>
  - García Bartolomé, Javier <br>
  - Jiménez Olivares, Pedro José <br>
  - Moris Puig, Yvan <br>
  - Rea Mejia, Maria Carmen <br>
  - Trull Martí, Andreu <br>
  
 Grupo:  L1-3TI21

<h2>Índice</h2>

  - 1. Contexto del proyecto<br>
  - 2. Guía de navegación por NotasOnline<br>
  - 3. Explicacíon de la implementación del proyecto<br>
    - 3.1 Inicio de sesión y filtro<br>
    - 3.2 Servlets detalle<br>
    - 3.3 Htmls detalle<br>
  - 4. Actas de reunión<br>

<h2>1. Contexto del proyecto</h2>

El proyecto parte de un esquema muy simple, tenemos un formulario de login que contendrá datos de DNI y de contraseña para un inicio de sesión, el cual pasará por un filtro para comprobar que esas credenciales son correctas y poder crear una sesión.

Gracias a esa sesión, nos mandará un html dependiendo si somos Alumno o Profesor, cada uno con su diferente contenido, mediante solicitudes AJAX, conseguimos conectar a sus respectivos servlets que se ocuparán de hacer las peticiones HTTP a CentroEducativo.



<h2>2. Guía de navegación por NotasOnline</h2>

Esta es nuestra página de bienvenida, en la que podemos elegir si somos profesor o alumno. Nos redigirá al login.

![IMAGEN LOGFORM](https://github.com/pjjimoli/TrabajoNOL/blob/master/images/index.png)

Aquí el login, tanto para profesor como alumno:

![IMAGEN LOGFORM](https://github.com/pjjimoli/TrabajoNOL/blob/master/images/login.png)

Si nos logeamos como alumno tendremos esta página detalle, en cada pestaña aparecen los detalles de las demás asignaturas:

![IMAGEN LOGFORM](https://github.com/pjjimoli/TrabajoNOL/blob/master/images/detalleAlu.png)

Este es el certificado imprimible del alumno:

![IMAGEN LOGFORM](https://github.com/pjjimoli/TrabajoNOL/blob/master/images/certificado.png)

Si nos logeamos como profesor esta sera su página detalle, junto a sus asignaturas y alumnos:

![IMAGEN LOGFORM](https://github.com/pjjimoli/TrabajoNOL/blob/master/images/detailProfe.png)

Y esta es la ventana para dar nota:

![IMAGEN LOGFORM](https://github.com/pjjimoli/TrabajoNOL/blob/master/images/modificarNota.png)

<h2>3.Explicacíon de la implementación del proyecto</h2>
<h3>3.1 Inicio de sesión e identificador de un usuario</h3>
Vamos a ver aspectos relacionados con el inicio de sesión y la identificación de un usuario. Para ello vamos a ver el documento index.html (inicio de la página), este nos mostrará 2 formas de acceder al login, como profesor y como alumno.

<h4>Documento index.html</h4>
Primera página a la que se accede. Fragmento de código html que define el aspecto:

```html

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

<h3 class="center"><a href="login.html" class="center"><input type="button" value="ACCESO PROFESOR" class="main-button"></a></h3>
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
```
<h4>Documento login.html</h4>
Página donde los usuarios inician sesión. Aquí se ingresarán los credenciales y mediante el formulario solicitaremos el paso de estos datos por el filtro LoginControl. 
Fragmento html que define su aspecto:

```html

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

<form action="LoginControl" class="form-signin">
          
      
          <div class="form-floating">
            <input type="text" name="dni" class="form-control" id="floatingInput" placeholder="DNI" required="required">
            <label for="floatingInput">DNI</label>
          </div>
          <div class="form-floating">
            <input type="password" name="pass" class="form-control" id="floatingPassword" placeholder="Password" required="required">
            <label for="floatingPassword">Contraseña</label>
          </div>
          <button class="w-25 btn custom-btn" type="submit"><div class="flecha"><i class="arrow right"></i></div></button></a>
        </form>
        <br>
        <p>Utilice sus credenciales para iniciar sesión</p>
        </div>

</div>

</div>
</main>

```

<h4>LoginControl.java</h4>
Filtro asociado al inicio de sesión que, dependiendo de si el usuario que se conecta tiene rol de alumno o de profesor, el usuario acabará en una página u otra con distintas funcionalidades. Método doFilter de LoginControl.java:

```java

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

```
Primero al pasar el filtro crearemos una sesión (si no la hay) mediante una solicitud HTTP POST login a CentroEducativo, guardaremos la key y las cookies para su posterior comprobación de sesión en otros htmls.

En caso de que el código de respuesta no se un 200, se invalidará la sesión.

También, si ya hay una sesión creada, no volverá hacer la petición POST, si no que será directamente redirigido.

La comprobación de rol se realiza mediante http_req.isUserInRole. Si el usuario es un alumno se entrará en if(http_req.isUserInRole("rolalu")), mientras que si el usuario tiene el rol de profesor se entrará en if (http_req.isUserInRole("rolpro")). Podemon observar que dependiendo de en que if se entre, se cargará una página html distinta. AlumnoDetail.html si se posee el rol de alumno y ProfeDetail.html si se posee el rol de professor.

<h3>3.2 Explicación de los servlets</h3>
En este apartado explicaremos la funcionalidad de los servlets que componen nuestro proyecto.
<h4>AlumnoDetail.java</h4>
Solo se puede acceder a este servlet si nuestro rol es alumno. El servlet AlumnoDetail.java es el encargado de comunicarse con CentroEducativo, para dar los datos JSON necesario para la solicitud AJAX del html correspondiente.
Fragmentos de código más importantes de  AlumnoDetail.java:

```java

public class AlumnoDetail extends HttpServlet {
	private static final long serialVersionUID = 1L;

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
	

```
El código está situado en un doGet(). En caso de lanzar otra operació (doPost), esta lanzará un doGet(). Podemos observar en el código que, dependiendo del valor que tome action, el código se meterá en un if o en otro, y se llamará a distintos métodos (presentes en la misma clase). En el caso de action.equals("getAvatar") se obtendrá la imagen (por el valor del dni), estas fotos estan insertadas en una carpeta en WEB-INF llamada img.

Como se puede ver hemos creado métodos distintos para que cada uno haga su petición HTTP correspondiente.

Aquí tenemos el método para obtener la información del Alumno:

	JSONObject getAlu(String dni,
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

Como podemos comprobar, transformamos la respuesta en un String, para luego darle forma de JSONObject, que será el formato que manejará el AJAX.

Aquí tenemos el método para obtener la información de las asignaturas del alumno:

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
		
	        JSONObject updatedObject = getInfoAsignatura(acronimo, key, cookies, nombreServer, jsonObject);
			Asignas.put(i, updatedObject); // Update the existing object at index i
		    
		} 
		return Asignas;
	}

Como podemos comprobar aquí, el funcionamiento es parecido al anterior, con la diferencia que aquí devolvera un objeto JSONArray, y no solo eso si no que ese JSONArray contendrá los datos de las propias asignaturas, ya que lo anidamos con la llamada al método getInfoAsignatura(), aquí su código:

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

Como se ve, este método recibe el JSONArray Asignaturas y le añadimos los datos de las mismas.

<h4>ProfeDetail.java</h4>
Solo se puede acceder a este servlet su nuestro rol es profesor. El servlet ProfeDetail.java es el encargado de comunicarse con CentroEducativo.
Fragmentos de código más importantes de  AlumnoDetail.java:

```java

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String nombreServer = request.getServerName();
        HttpSession session = request.getSession();
        String dni = session.getAttribute("dni").toString();
        String key = session.getAttribute("key").toString();
        List<String> cookies = (List<String>) session.getAttribute("cookies");

        if (request.isUserInRole("rolpro")) {
            String action = request.getParameter("action");
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


```

Como es de esperar tiene un funcionamiento similar a AlumnoDetail.java, pero adaptadas a las operaciones permitidas a un profesor.

Aquí tendremos la llamada a getProf(), para recibir los datos de nuestro usuario, similar al getAlumno() anterior:

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



<h3>3.3 Páginas detalle de alumno y de profesor</h3>
En este apartado explicaremos la página principal a la que acceden un alumno o un profesor después de iniciar sesión de forma correcta.
<h4>AlumnoDetail.html</h4>
Página a la cual accede un usuario con el rol alumno después de logearse.
Parte AJAX del documento:

```
$(document).ready(function() {
		//Nombres y dni
		$.ajax({
			url:'AlumnoDetail',
			type:'POST',
			dataType:'json',
			data: { action: 'getAlumno'},
			async:true,
			success: function(datos){
				$('#nombre').text(datos.nombre+" "+datos.apellidos);
				$('#dni').text(datos.dni);
			},
			error: function(xhr, status, error) {
			    console.error(xhr.responseText);
			    alert("Error!!! No se encuentra alumno");
			}

		})



		// Avatar
        $.getJSON("AlumnoDetail?action=getAvatar")
            .done(function(response){
                 $("#esto").text(response.dni);
                 $("#aqui").attr("src", "data:image/png;base64,"+response.img);
            })
            .fail(function(jqxhr, textStatus, error ) {
                 var err = jqxhr.response.replace(",", "\n"); // Pequeños ajustes
                 alert("Algo mal: "+error);
        });
		
		// Asignaturas
		$.ajax({
			url:'AlumnoDetail',
			type:'POST',
			dataType:'json',
			async:true,
			data: { action: 'getAsignAlumn' },
			error: function(xhr, status, error) {
			    console.error(xhr.responseText);
			    alert("Error!!! No se encuentran asignaturas");
			}
,
			success: function(datos){
				$.each(datos, function(index, asignatura){ // Utiliza $.each() para iterar sobre las asignaturas
                // Crear la estructura HTML para cada pestaña y tabla
            var tabPane = $('<div>').addClass('tab-pane fade').attr('id', 'a' + (index + 1)).attr('role', 'tabpanel').attr('aria-labelledby', 'a' + (index + 1) + '-tab');
            var table = $('<table>');
            var tableHead = $('<tr>').append($('<th>').text('Nombre')).append($('<th>').text('Nota')).append($('<th>').text('Curso')).append($('<th>').text('Cuatrimestre')).append($('<th>').text('Créditos'));
            var tableBody = $('<tr>').append($('<td>').text(asignatura.nombre)).append($('<td>').text(asignatura.nota)).append($('<td>').text(asignatura.curso)).append($('<td>').text(asignatura.cuatrimestre)).append($('<td>').text(asignatura.creditos));
            table.append(tableHead).append(tableBody);
            tabPane.append(table);

            // Agregar la pestaña y la tabla al contenedor de contenido de la pestaña
            $('#myTabContent').append(tabPane);

            // Crear el enlace de la pestaña en la lista de pestañas
            var navItem = $('<li>').addClass('nav-item');
            var navLink = $('<a>').addClass('nav-link').addClass('unselected-tab').attr('id', 'a' + (index + 1) + '-tab').attr('data-toggle', 'tab').attr('href', '#a' + (index + 1)).attr('role', 'tab').attr('aria-controls', 'a' + (index + 1)).attr('aria-selected', 'false').text(asignatura.asignatura);
            navItem.append(navLink);
            $('.nav-tabs').append(navItem);
        });

        // Activar la primera pestaña
        $('.nav-link:first').addClass('active');
        $('.tab-pane:first').addClass('show active');
    }		
  })
})

```
Se usa para la generación del documento y la obtención de los datos. En los comentarios del código se nombra la funcionalidad de cada bloque de código.
Seguido del AJAX se encuentra el resto del documento html que ayuda e definir el diseño entre otras cosas. Mostramos una parte:

```
<form action="Logout">
              <input class="logout" type="submit" value="Cerrar Sesión">
          </form>
          
	<main class="container-fluid">
		<div class="p-4 p-md-5 mb-4 main">
			<div class="mx-auto p-1 center">

				<h1 class="display-4 fw-bold">NOTAS ONLINE</h1>
				<p id="nombre" class="lead my-1">Nombre, Alumno Aquí</p>
				<p class="lead my-3">Aquí se muestran las asignaturas en las que
					estás matriculadx junto a sus notas</p>
			</div>
		</div>
		<div class="row g-5">
			<div class="col-md-8">
			
				<div class="card text-center tabs">
  <div class="card-header ">
    <ul class="nav nav-tabs card-header-tabs ">
      <li class="nav-item ">
      </li>
      <li class="nav-item">

      </li>
      <li class="nav-item">

      </li>
    </ul>
  </div>
  
  
  <div class="card-body">
                    <div class="tab-content" id="myTabContent">

                </div>
  
  
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
			
			
              <a href="CertificadoAlu.html"><input class="certificado2" type="button" value="Crear Certificado"></a>
          
			
		</div>
	</main>

```

Podemos destacar el formulario que permite al usuario cerrar sesión y el botón que nos permite crear un certificado de tamaño A4 para impresión.
Para la generación del certificado usamos el html CertificadoAlu.html, sus peticiones AJAX tienen el mismo funcionamiento que en Alumno.Detail.html, con la diferencia de que aqui solicitará el avatar del alumno. Procedemos a describir su código acontinuación:
Parte AJAX:

```

let fechaActual = new Date();

let dia = fechaActual.getDate(); // Día del mes
let mes = fechaActual.getMonth() + 1; // Los meses en JavaScript empiezan desde 0
let año = fechaActual.getFullYear(); // Año




// Asignaturas

$(document).ready(function() {
		//Nombres y dni
		$.ajax({
			url:'AlumnoDetail',
			type:'POST',
			dataType:'json',
			data: { action: 'getAlumno'},
			async:true,
			success: function(datos){
				$('#nombre').text(datos.nombre+" "+datos.apellidos);
				$('#dni').text(datos.dni);
				$('#dia').text(dia);
				$('#mes').text(mes);
				$('#año').text(año);
			},
			error: function(xhr, status, error) {
			    console.error(xhr.responseText);
			    alert("Error!!! No se encuentra alumno");
			}

		})


		// Avatar
		$.ajax({
			url:'AlumnoDetail',
			type:'POST',
			dataType:'json',
			async:true,
			data: { action: 'getAsignAlumn' },
			error: function(xhr, status, error) {
			    console.error(xhr.responseText);
			    alert("Error!!! No se encuentran asignaturas");
			}
,
			success: function(datos){
				$.each(datos, function(index, asignatura){ // Utiliza $.each() para iterar sobre las asignaturas
                // Crear la estructura HTML para la tabla
            var table = $('#tablaCertificado');
            var tableHead = $('<tr>').append($('<th>').text('Acrónimo')).append($('<th>').text('Asignatura')).append($('<th>').text('Calificación'));
            var tableBody = $('<tr>').append($('<th>').text(asignatura.asignatura)).append($('<td>').text(asignatura.nombre)).append($('<td>').text(asignatura.nota));
            table.append(tableHead).append(tableBody);

          

          
        });

    }		
  })
})

```

Generación del documento y conseguir los datos. Html:

```
<div class="p-4 p-md-5 main center">
            <div>
              <h1>DEW - CentroEducativo</h1>
            </div>
        </div>
</header>
<h2 align="center" style="margin: 30px 30px;">Certificado sin validez académica</h2>
<div style="margin: 5px;">
<div style="float: left; width: 70%;">
<p>
<b>DEW - Centro Educativo</b> certifica que D/Dª <b><span id="nombre"> </span></b>,
con DNI <span id="dni"> </span>, matriculado/a en el curso 2023/2024, ha obtenido las calificaciones que se muestran en la siguiente tabla.
</p>
</div>
<div style="float: right; width: 27%;">
<img src="https://t4.ftcdn.net/jpg/02/29/75/83/360_F_229758328_7x8jwCwjtBMmC6rgFzLFhZoEpLobB6L8.jpg" style="width: 150px; height: 150px;"></img>
</div>
<table class="tablaCertificado" id="tablaCertificado" style="border:0px; width: 580px; border-collapse: separate; border-spacing: 2px 2px; font-size:17px;">



</table>
<div>
<p align="right">En Valencia, a <span id="dia"></span> de <span id="mes"></span> de <span id="año"></span></p>
</div>

</div>

```

<h4>ProfeDetail.html</h4>
Página a la cual accede un usuario con el rol profesor después de iniciar sesión.
Parte AJAX del documento:
FALTA
Seguido del AJAX se encuentra el resto del documento html que ayuda e definir el diseño entre otras cosas. Mostramos una parte:
FALTA


<h2>4.Actas de reuniones</h2>

La máquina virtual prototipo con la que probamos el código es del compañero PEDRO: dew-pjjimoli-2324.dsicv.upv.es

<h2>ACTA REUNIÓN 1</h2>


Fecha: 27/5/2024  
Horario: 20:20  - 22:00
Tipo de Reunión: Videoconferencia Discord   
Grupo: L1-3TI21
Asistentes: 
- Blauvac Brea, Adrián Pierre  
- García Bartolomé, Javier  
- Jiménez Olivares, Pedro José  
- Moris Puig, Yvan  
- Rea Mejia, Maria Carmen  
- Trull Martí, Andreu  
 
(Nota: Un asterisco (*) indica los asistentes ausentes) 


<h3>Desarrollo:</h3>
 
Se fijan los objetivos para la reunión, estos se basan en las Tareas a realizar en la entrega final. 
El compañero Yvan resuelva una explicación breve de lo que consiste Jquery y AJAX para el mejor entendimiento y por herencia mejor desarrollo de la aplicación. Así posteriormente llevamos a cabo un pequeño "brainstorm" para implementar codigo AJAX en las paginas del profesor, como se pide en la tarea. 
Además, el compañero Adrian desarrollo un croquis de la pagina para construir las ideas a llevar a cabo en esta.

![Imagen en : https://github.com/pjjimoli/TrabajoNOL/blob/master/images/croquis.png ](https://github.com/pjjimoli/TrabajoNOL/blob/master/images/croquis.png)


<h3>Puntos tratados:</h3>  
 
* Realización y definición de nuevas tareas y objetivos de la entrega Final
* Expliación de Jquery y AJAX
  


<h3>Resumen de Nuevas Tareas:</h3>  
 
1. Añadir a alumno 
2. Inicio de sesio para rol profesor
3. Consultar la lista de asignaturas12 que imparte. Se necesitará recorrer todas las asignaturas y anotar aquellas en las que este profesor aparezca.
4. Consultar la lista de alumnos en una de las asignaturas que imparte
5. Consultar o modificar la nota obtenida por uno de los alumnos en una de las asignaturas que imparte
    * Operación derivada: calcular la nota media de una de las asignaturas que imparte
       * Operación derivada AJAX: interacción ágil para consultar o modificar la nota obtenida de cada uno de los alumnos en una de las asignaturas que imparte. 
          - Debe cargar todos los datos en el navegador, facilitando el recorrido entre los alumnos y volcando las modificaciones (o todas las calificaciones) al servidor
          - Debe incluir la fotografía del alumno visualizado
6. Finalizar la sesión

<h3>Asignación y calendario:</h3>   

Todos los miembros del grupo deben de , antes de la siguiente reunión x/5/2024.

<h3>Intervención del Profesor:</h3>

 No se requiere intervención del profesor en esta sesión.
 
 
 
 
 
 
 <h2>ACTA REUNIÓN 2</h2>


Fecha: 2/6/2024  
Horario: 20:20  - 22:00
Tipo de Reunión: Videoconferencia Discord   
Grupo: L1-3TI21
Asistentes: 
- Blauvac Brea, Adrián Pierre  
- García Bartolomé, Javier  
- Jiménez Olivares, Pedro José  
- Moris Puig, Yvan  
- Rea Mejia, Maria Carmen  
- Trull Martí, Andreu  
 
(Nota: Un asterisco (*) indica los asistentes ausentes) 


<h3>Desarrollo:</h3>
 Se han realizado todas las correcciones mencionadas por el profesor. En primer lugar, se identificó que el servidor indicado en la documentación (pjjimoli) no contiene la aplicación, así que se ha añadido a la carpeta webapps. 
 A continuación, se implementó la autenticación (web=CE) para la página principal, que debe llevar a un formulario para autenticarse. Para ello, se ha creado un filtro de servlet llamado LoginControl que intercepta solicitudes HTTP para gestionar la autenticación de usuarios:
     
```java
public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
    System.out.println("Pasa filtro");
    HttpServletRequest http_req = (HttpServletRequest) request;
    HttpServletResponse http_resp = (HttpServletResponse) response;
``` 
    
Y poder así gestionar la sesión, se realiza una conexión HTTP POST a un
    servidor externo (CentroEducativo) para autenticar al usuario y se envían los
    datos dni y password en formato JSON al servidor. 
    
```java
try (Writer w = new OutputStreamWriter(connection.getOutputStream(), "UTF-8")) {
    w.write("{\"dni\": " + "\"" + dni + "\",\n\"password\": " + "\"" + pass + "\"}");
}
```

Seguidamente, se procesa la respuesta del servidor. Si la autenticación es exitosa, se almacenan las cookies y la clave (key) en la sesión. Y por último, se redirige al usuario a diferentes páginas según su rol (rolalu para alumnos y rolpro para profesores).
    
```java
    if(http_req.isUserInRole("rolalu")) {
        System.out.println("Alumno");
        http_resp.sendRedirect(http_req.getContextPath() + "/AlumnoDetail.html");
        return; 
    } else if (http_req.isUserInRole("rolpro")) {
        System.out.println("Prof");
        http_resp.sendRedirect(http_req.getContextPath() + "/ProfeDetail.html");
        return;
    }
```
 
 
Por otro lado, se ha modificado el archivo web.xml para que contenga los servlets y filtros requeridos:

```xml  
  <filter>
    <display-name>LoginControl</display-name>
    <filter-name>LoginControl</filter-name>
    <filter-class>LoginControl</filter-class>
  </filter>
  <filter-mapping>
    <filter-name>LoginControl</filter-name>
    <url-pattern>/LoginControl</url-pattern>
  </filter-mapping>
```

Se revisaron y corrigieron las menciones a versiones no admitidas de Bootstrap y jQuery y se modifico las actas, ya que había demasiado código.



<h3>Puntos tratados:</h3>  
- Todas las correcciones mencionadas por el profesor
  
  

<h3>Resumen de Nuevas Tareas:</h3>  
 
  Finalizar las correcciones de manera adecuada y proceder con la entrega final a parti de ahí.


<h3>Asignación y calendario:</h3>   

Todos los miembros del grupo deben de organizarse y repartirse las tareas, antes de la siguiente reunión 7/6/2024.

<h3>Intervención del Profesor:</h3>

Sí hizo falta intervención del profesor en esta sesión, se ha preguntado varias dudas mediante el webmail sobre las correcciones a tratar.


 
 
 <h2>ACTA REUNIÓN 3</h2>


Fecha: 7/6/2024  
Horario: 20:20  - 22:00
Tipo de Reunión: Videoconferencia Discord   
Grupo: L1-3TI21
Asistentes: 
- Blauvac Brea, Adrián Pierre  
- García Bartolomé, Javier  
- Jiménez Olivares, Pedro José  
- Moris Puig, Yvan  
- Rea Mejia, Maria Carmen  
- Trull Martí, Andreu  
 
(Nota: Un asterisco (*) indica los asistentes ausentes) 


<h3>Desarrollo:</h3>

Se ha creado un nuevo servlet llamado Logout que maneja el proceso de cierre de sesión de un usuario en la aplicación web. Contiene el método doGet que maneja las solicitudes GET:
    - request.getSession().invalidate();: Invalida la sesión actual del usuario,
    cerrando efectivamente la sesión.
    -mresponse.sendRedirect("index.html");: Redirige al usuario a la página
    index.html, generalmente la página de inicio o de login de la aplicación.
    
Además, el método `doPost` maneja las solicitudes POST delegando el manejo a doGet. Esto permite que tanto las solicitudes GET como POST sean manejadas de la misma manera, cerrando la sesión del usuario y redirigiéndolo a la página de inicio.

Por otra parte, se han implementado varios servlets más como el de ProfeDetail que maneja las solicitudes relacionadas con los detalles de un profesor en una aplicación web. En su método doGet maneja dichas solicitudes, donde obtiene el nombre del servidor, dni, key y las cookies de la sesión. Verifica si el usuario tiene el rol rolpro (profesor). Si no lo tiene, se devuelve un estado 401 (No autorizado) y un mensaje de error.

Además, el método `getProf` realiza una solicitud HTTP GET para obtener los detalles de un profesor, poder así abrir una conexión HTTP a la URL específica y añade las cookies a la solicitud. Lee la respuesta del servidor y la convierte en un objeto JSON que representa al profesor.

El método `getAsignProf` realiza una solicitud HTTP GET para obtener las asignaturas de un profesor, similar al método anterior, abre una conexión HTTP y añade las cookies. Lee la respuesta del servidor y la convierte en un arreglo JSON que representa las asignaturas del profesor.

También se ha añadido el método `getAlumnosProf para obtener los alumnos asignados a un profesor, similar a los otros métodos de solicitud GET. La respuesta del servidor se convierte en un arreglo JSON que representa a los alumnos del profesor.

Y por lo tanto, se ha creado su respectivo ProfeDetail.html.

<h3>Puntos tratados:</h3>  
- Creación de nuevos servlets
  
  

<h3>Resumen de Nuevas Tareas:</h3>  
 
  Continuar con el las requeridas por el profesor.


<h3>Asignación y calendario:</h3>   

Todos los miembros del grupo deben de organizarse y repartirse las tareas, antes de la siguiente reunión 8/6/2024.

<h3>Intervención del Profesor:</h3>

Sí hizo falta intervención del profesor en esta sesión, se ha preguntado varias dudas mediante el webmail sobre las correcciones a tratar.


 
 


 <h2>ACTA REUNIÓN 4</h2>


Fecha: 8/6/2024  
Horario: 17:20  - 21:00
Tipo de Reunión: Videoconferencia Discord   
Grupo: L1-3TI21
Asistentes: 
- Blauvac Brea, Adrián Pierre  
- García Bartolomé, Javier  
- Jiménez Olivares, Pedro José  
- Moris Puig, Yvan  
- Rea Mejia, Maria Carmen  
- Trull Martí, Andreu  
 
(Nota: Un asterisco (*) indica los asistentes ausentes) 


<h3>Desarrollo:</h3>

Hemos continuado con la creación del servlet llamado `AlumnoDetail`, y contiene varios métodos que mencionares a continuación sus diferentes usos. El método `doGet` obtiene el nombre del servidor, dni, key y las cookies de la sesión. Verifica si el usuario tiene el rol `rolalu` (alumno). Si no lo tiene, se devuelve un estado 401 (No autorizado) y un mensaje de error. Si el usuario es un alumno, se verifica el parámetro `action de la solicitud y se realizan diferentes acciones:
    `getAlumno`: Obtiene los detalles del alumno.
    `getAsignAlumn`: Obtiene las asignaturas del alumno.
    `getAvatar: Obtiene el avatar del alumno en formato base64.
 
Además contine otros métodos como `getInfoAsignatura` donde Este método realiza una solicitud HTTP GET para obtener la información de una asignatura y añade la información obtenida al objeto JSON `Asignas`. El método `getAsignAlumn` realiza una solicitud HTTP GET para obtener las asignaturas de un alumno. Luego, para cada asignatura, obtiene información adicional utilizando `getInfoAsignatura`.

Por último, `getAlu` que realiza una solicitud HTTP GET para obtener los detalles de un alumno y devuelve un objeto JSON con esa información.

Y por lo tanto, se ha creado su respectivo AlumnoDetail.html.

Se ha realizado la implementación del diseño CSS para el certificado y la creación del CertificadoAlu.html.


<h3>Puntos tratados:</h3>  
- Creación de nuevos servlets
  
  

<h3>Resumen de Nuevas Tareas:</h3>  
 
Se discuten los aspectos a considerar para la entrega final que deben incluirse en las actas y se empieza a repartir las tareas entre los miembros..


<h3>Asignación y calendario:</h3>   

Todos los miembros del grupo deben de organizarse y repartirse las tareas, antes de la siguiente reunión 9/6/2024.

<h3>Intervención del Profesor:</h3>

No hizo falta intervención del profesor en esta sesión.
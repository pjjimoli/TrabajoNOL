<h1>TrabajoNOL - DEW - ACTA HITO 2 </h1>

La máquina virtual prototipo con la que probamos el código es del compañero PEDRO: dew-pjjimoli-2324.dsicv.upv.es

<h2>ACTA REUNIÓN 1</h2>


Fecha: 08/5/2024  
Horario: 17:00 - 20:30  
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
 
Se fijan los objetivos para la reunión, estos se basan en las Tareas a realizar en el Hito 2. 
Durante esta reunión creamos:
* login.html, que desarrollaremos de forma simple para su posterior evolución:

      <body>
        <h1>LOGIN</h1>
        <form action="login" method="post">
          <label>DNI:</label> <input type="text" required="required" name="dni"/><br/>
          <label>Contraseña:</label> <input type="password" required="required" name="pass"/>
          <input type="submit" />
        </form>
      </body>


* index.html,que el compañero Adrian se ofreció a desarrollarla usando la librería bootstrap en CSS, version de librería /bootstrap@5.3.3.
   - Se importo la fuente 'Monserrat'...
  
            @import  url('https://fonts.googleapis.com/css2?family=Montserrat:ital,wght@0,100..900;1,100..900&display=swap') ;

   - Para el cual se definieron distintas clases ademas del body...
  
            .main {
                    background-color: #0a4f6b;
                    color: #F8F5DF;
                    border-radius: 25px;
                    margin-top: 30px;
            }
            .grupo { ... }
            .center { ... }
            .login { ...  }
            body { ... }

    - Además de dos apartados en distintos &lt;div&gt; anidados, para llevar a cabo el inicio de sesión según seas profesor o alumno...

            <article class="blog-post login">
                    <h2>Si eres un alumn@...</h2>
                    <p>
                            Podrás <a href="login.html">consultar</a> tus calificaciones...
                            Debes contar con tus datos identificativos para acceder.
                    </p>
                    <h2>Si eres un profesor@...</h2>
                    <p>
                            Podrás <a href="login.html">consultar o modificar</a> las calificaciones en tus asignaturas... Debes contar con tus datos identificativos para acceder.
                    </p>
            </article>
       

<h3>Puntos tratados:</h3>  
 
* Realización y fijación de nuevas tareas y objetivos del hito 2
* Punto 1: Pag de entrada y enlace a la operación 
* Construccion pagina HTML de Alumno


<h3>Resumen de Nuevas Tareas:</h3>  
 
1. Página de entrada y enlace a la operación 
2. Autenticación web
3. Login con CentroEducativo y mantenimiento de la sesión (no es necesariamente un paso separado)
4. Construcción y envío de la petición a CentroEducativo
5. Interpretación de la respuesta de CentroEducativo
6. Construcción y retorno de la página HTML de respuesta

<h3>Asignación y calendario:</h3>   

Todos los miembros del grupo deben de entender el código realizado y pensar una forma de iniciar sesión en CentroEducativo, antes de la siguiente reunión 12/5/2024.

<h3>Intervención del Profesor:</h3>

 No se requiere intervención del profesor en esta sesión.
 







<h2>ACTA REUNIÓN 2</h2>

Fecha: 12/5/2024  
Horario: 17:30 - 21:30  
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

En esta reunión llevamos a cabo la construcción del Servlet que se encarga de el método Post que realiza el formulario de la página Login.html. 


* el servlet login.java,que al “postear” la información de login, en formato JSON, en el servidor que en su respuesta nos envía un token key que usaremos para mantener sesión...

      protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

              String dni = request.getParameter("dni");
              String pass = request.getParameter("pass");
              String key = "";
  
              ...

     * Se conecta a la base de datos...

              URL CentroEducativo = new URL("http://localhost:9090/CentroEducativo/login");
              HttpURLConnection connection = (HttpURLConnection) CentroEducativo.openConnection();
       
     * Construimos el envio del POST...

              try (Writer w = new OutputStreamWriter(connection.getOutputStream(), "UTF-8")) {
                      w.write("{\"dni\": " + "\"" + dni + "\",\n\"password\": " + "\"" + pass + "\"}");
              }
  
              
 
     * Esperamos respuesta (si envio es correcto)...

              if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                  cookies = connection.getHeaderFields().get("Set-Cookie");
  
                  try (Scanner scanner = new Scanner(connection.getInputStream(), "UTF-8")) {
                      String responseLine;
                      while (scanner.hasNextLine()) {
  
                          responseLine = scanner.nextLine();
                          key += responseLine; // Concatenamos cada línea a la cadena key
                  }
              }

       De este último código la complicación surgió de cómo actuar con esta key(string).  Pues es necesaria para hacer consultas a la base de datos. 
  Posteriormente se desarrolla el código HTML de la pagina de respuesta

    
 

<h3>Puntos tratados:</h3>
 
* Realización y fijación de nuevas tareas y objetivos del hito 2
* Punto 2: Autenticación web
* Punto 3a: Login con CentroEducativo
* Uso de Swagger-IU




<h3>Resumen de Nuevas Tareas:</h3>
 
1. Página de entrada y enlace a la operación 
2. Autenticación web
3. Login con CentroEducativo y mantenimiento de la sesión 
4. Construcción y envío de la petición a CentroEducativo
5. Interpretación de la respuesta de CentroEducativo
6. Construcción y retorno de la página HTML de respuesta

 
<h3>Asignación y calendario:</h3>
 
Todos los miembros del grupo deben de entender el código realizado y pensar una forma usar la key para mantener la sesión, antes de la siguiente reunión 12/5/2024.

 
<h3>Intervención del Profesor:</h3>
 
No se requiere intervención del profesor en esta sesión.









 <h2>ACTA REUNIÓN 3</h2>


Fecha: 21/5/2024  
Horario: 17:00 - 22:00
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
 
Usamos la key que devuelve el login para hacer llamadas al CentroEducativo para solicitar la información necesaria para la construcción de la Página web.
Además, el compañero Adrian separa el CSS de index.html para introducirlo en un nuevo archivo estilo.css que servirá para definir el estilo de otras páginas html.
Para iniciar sesión enviamos el email y la password en formato JSON al servidor para recibir una key, así con esa key hacemos llamadas a la BBDD
Para construir el Servlet ‘login.java’ tuvimos que importar distintas librerías:

    import java.io. ...
                   IOException;
                   InputStreamReader;
                   OutputStreamWriter;
                   Writer;
    import java.net. ...
                   HttpURLConnection;
                   MalformedURLException;
                   URL;
    import java.util. ...
                   List;
                   ArrayList;
                   Scanner;
    import java.io.BufferedReader;
    import javax.servlet. ...
                   ServletException;
                   annotation.WebServlet;
                   http. ...
                        HttpServlet;
                        HttpServletRequest;
                        HttpServletResponse;
                        HttpSession;
    import org.json.*;


Durante el desarrollo nos dimos cuenta de que el doGet() no contenía nada pues habíamos comentado las líneas predeterminadas del método. Lo aprovechamos de manera para que doGet() sea llamado por el botón de cierre de sesión, gracias a este código acabamos la sesión actual mediante invalidate(), acto después te redirige de nuevo a index. 


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
                    request.getSession().invalidate();
                    response.sendRedirect("index.html");
            }
 
A continuación, seguimos con el método doPost() que en adición a lo que ya contenía de la reunión anterior empezamos a trabajar. 

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
                    ...

* Para construir la pagina HTML de respuesta...
                           
                    response.setContentType("text/html");
                    response.getWriter()
                                    .println("<!DOCTYPE html>\n<html>\n<head>\n"
                                                    + "<meta http-equiv=\"Content-type\" content=\"text/html; charset=utf-8\" />"
                                                    + "<title>Información Log1</title></head><body>");
                    response.getWriter().println("<h1>Información</h1>");


A partir de aquí empezamos a trabajar en esta reunión. Donde definimos y tratamos la información recibida del servidor. A su vez incorporamos nuevos métodos para el tratamiento de esta información.
Le mostramos al usuario las Asignaturas y sus datos.


                response.getWriter().println("<p>lista de asig:" + getAsignAlumn(dni, key, cookies).get(1).getNombre()  + "datos asignatura:" + getInfoAsignatura("DEW", key, cookies).getNombre() + "</p>");
       


Pero para tratar de esta formas los datos hay que familiarizar el ambiente con la data a tratar. Y así, desarrollamos nuevos métodos para conseguir las asignaturas relacionadas a un alumno, y su información pertinente. 


Primero tenemos que recibir y relacionar las asignaturas del alumno pertinente en nuestra aplicación web...

       private List<AsignaturasAlumn> getAsignAlumn(String dni, String key, List<String> cookies) throws MalformedURLException, IOException {
               String auxiliar = "";
               String jsonString = "";
               ...



   * Se conecta con un URL especifico de la base de datos para enviar las asignaturas del alumno...
               
                new URL("http://localhost:9090/CentroEducativo/alumnos/" + dni + "/asignaturas?key=" + key).openConnection();
     
   * Tras declarar el buffer con las asignaturas del alumno que recibimos, instanciar las con su información...

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
               
               return asignaturas;}
       

   * Para organizar esta información construimos un objeto AsignaturasAlumn a tratar en getAsignAlumn()...

         public class AsignaturasAlumn {
     
                  private String asignatura;
                  y sus get();set();
     
                  private String nota;
                  y sus get();set();
     
          }


Segundo tenemos que manejar la inf
        
        private Asignaturas getInfoAsignatura(String acronimo, String key, List<String> cookies)
                        throws MalformedURLException, IOException {
                HttpURLConnection connection = (HttpURLConnection) new URL(
                                "http://localhost:9090/CentroEducativo/asignaturas/" + acronimo + "?key=" + key).openConnection();
                
                String auxiliar = "";
                String jsonString = "";
            Asignaturas asignatura = new Asignaturas();

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
 


Aquí no solamente definimos nuevos métodos para tratar la información, más bien creamos objetos de tipo AsignaturasAlum y Asignaturas con sus pertinentes get();set(); para sus atributos. 




Objeto Asignaturas a tratar en getInfoAsignatura()

        public class Asignaturas {
                private String acronimo;
                private int creditos;
                private String cuatrimestre;
                private String nombre;
                private int curso;

                 y sus get();set();
        }




<h3>Puntos tratados:</h3>
 
* Realización y fijación de nuevas tareas y objetivos del hito 2
* Punto 3b: mantenimiento de la sesión 
* Punto 4: Construcción y envío de la petición a CentroEducativo
* Punto 5: Interpretación de la respuesta de CentroEducativo




<h3>Resumen de Nuevas Tareas:</h3>
 
1. Página de entrada y enlace a la operación 
2. Autenticación web
3. Login con CentroEducativo y mantenimiento de la sesión 
4. Construcción y envío de la petición a CentroEducativo
5. Interpretación de la respuesta de CentroEducativo
6. Construcción y retorno de la página HTML de respuesta


 
<h3>Asignación y calendario:</h3>
 
Todos los miembros del grupo deben de entender el código realizado y pensar una forma de construir la página de retorno de forma dinámica, antes de la siguiente reunión 22/5/2024.




 
<h3>Intervención del Profesor:</h3>
 
No se requiere intervención del profesor en esta sesión.



 <h2>ACTA REUNIÓN 4</h2>


Fecha: 22/5/2024  
Horario: 17:30 - 22:00
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
Una vez ya tenemos la aplicación desarrollada con todos sus componentes definidos y con los pertinentes metodos para administrar los datos enviados y recibidos, solo nos queda acabar de tratar la información recibida para construir, de forma dinamica durante la ejecucion, la pagina de retorno de HTML.
Para ello creamos un metodo llamado htmlAlu que reciba los datos del alumno para construir una pagina donde podra acceder a dicha información sobre sus asignaturas.

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
       + "<main class=\"container-fluid\">\r\n"
       + "<div class=\"p-4 p-md-5 mb-4 main\">\r\n"
       + "<div class=\"mx-auto p-1 center\">\r\n"
       + "\r\n"
       + "\r\n"
       + "\r\n"
       + "<h1 class=\"display-4 fw-bold\">NOTAS ONLINE</h1>\r\n"
       + "<p class=\"lead my-1\">"+ getAlu(dni, key, cookies).getNombre() +" "+ getAlu(dni, key, cookies).getApellidos()+ "</p>\r\n"
       + "<p class=\"lead my-3\">Aquí se muestran las asignaturas en las que\r\n"
       + "estás matriculado junto a sus notas</p>\r\n"
       + "</div>\r\n"
       + "</div>\r\n"
       + "<div class=\"row g-5\">\r\n"
       + "<div class=\"col-md-8\">\r\n"
       + "\r\n"
       + "<div class=\"card text-center tabs\">\r\n"
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
       + "\r\n"
       + "</div>\r\n"
       + "<div class=\"col-md-4\">\r\n"
       + "<div class=\"p-4 mb-3 grupo\">\r\n"
       + "<h4 class=\"center\">GRUPO 3TI21_G2</h4>\r\n"
       + "<ul>\r\n"
       + "<li>Blauvac Brea, Adrián Pierre</li>\r\n"
       + "<li>García Bartolomé, Javier</li>\r\n"
       + "<li>Jiménez Olivares, Pedro José</li>\r\n"
       + "<li>Moris Puig, Yvan</li>\r\n"
       + "<li>Rea Mejia, Maria Carmen</li>\r\n"
       + "<li>Trull Martí, Andreu</li>\r\n"
       + "</ul>\r\n"
       + "</div>\r\n"
       + "</div>\r\n"
       + "</div>\r\n"
       + "</main>\r\n"
       + "<script src=\"https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js\" integrity=\"sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz\" crossorigin=\"anonymous\"></script>\r\n"
       + "</body>\r\n"
       + "\r\n"
       + "\r\n"
       + "</html>");
       }

Sin embargo el resultado obtenido no era el esperado. Pues en cada inicio de sesión, donde en cada subventana de la pagina aparece el acrónimo de la asignatura, al darle deberiamos ver la información de la asignatura en concreto. Sin embargo, en la primera carga de esta pagina, aporta la información de todas la asignaturas. Esto al cambiar de pestaña "se arregla".

* Resultado 1a iteracion
![](https://github.com/pjjimoli/TrabajoNOL/blob/master/images/image.png)

* Resultado del resto de iteraciones
  
![](https://github.com/pjjimoli/TrabajoNOL/blob/master/images/image2.png)


A su vez, tuvimos que aplicar un control escrito para conocer el rol de los usuarios que inician sesión.

    if (!key.equals("-1")) {
                if (request.isUserInRole("rolpro")) {
                    htmlProf(response, dni, key, cookies);
                } else if (request.isUserInRole("rolalu")) {
                    htmlAlu(response, dni, key, cookies);
                } else {
                    response.sendError(401);
                }
            }

<h3>Puntos tratados:</h3>
 
* Realización y fijación de nuevas tareas y objetivos del hito 2
* Punto 6: Construcción y retorno de la pagina HTML de respuesta



<h3>Resumen de Nuevas Tareas:</h3>
 
1. Página de entrada y enlace a la operación 
2. Autenticación web
3. Login con CentroEducativo y mantenimiento de la sesión 
4. Construcción y envío de la petición a CentroEducativo
5. Interpretación de la respuesta de CentroEducativo
6. Construcción y retorno de la página HTML de respuesta


 
<h3>Asignación y calendario:</h3>
 
Todos los miembros del grupo deben de entender el código realizado y pensar una forma de construir la página de retorno de forma dinámica, antes de la siguiente reunión 22/5/2024.


 
<h3>Intervención del Profesor:</h3>
 
No se requiere intervención del profesor en esta sesión.



<h2>Estado Actual del Grupo</h2>

No se han identificado conflictos entre los miembros del grupo.
La colaboración y comunicación entre estos miembros es efectiva a la par que eficiente. Además, de recalcar que se ha respirado un ambiente de bienestar y comprensión en el grupo.
Todos los miembros coinciden en el objetivo principal de obtener una buena calificación, con una inclinación ambiciosa a la maxima nota.
Hasta la fecha, la unica dificultad técnica con la que distintos miembros hemos tenido problemas fue tras la segunda reunión. Estos problemas surgían a la hora de acceder a las paginas web del proyecto, ya que en la base de datos resultaba haber un error.
Este error provocaba un bloqueo de acceso a la página y para atravesar esta seguridad debías iniciar sesión con el login (dni y contraseña) de un usuario de la aplicación. Sin embargo, ciertos compañeros siguieron con el problema, aunque, este no impidio la continuación pues no había problema para el desarrolo del proyecto debido a que la MV prototipo principal no sufría de este problema.

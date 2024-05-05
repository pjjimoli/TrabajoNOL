# **TrabajoNOL - DEW | ACTA HITO 1**



## ACTA REUNIÓN 1
**Fecha:** 31/4/2024  
**Horario:** 18:00 - 19:30  
**Tipo de Reunión:** Videoconferencia Discord   
**Grupo:** L1-3TI21

**Asistentes:** 
- Blauvac Brea, Adrián Pierre  
- García Bartolomé, Javier  
- Jiménez Olivares, Pedro José  
- Moris Puig, Yvan  
- Rea Mejia, Maria Carmen  
- Trull Martí, Andreu  
 
*(Nota: Un asterisco indica los asistentes ausentes)*
 

**Resumen:**
 
Se presentó el documento PDF "Aspectos esenciales para el trabajo en equipo", que detalla temas agrupados en tres áreas principales:  
 
1. Comunicación, Objetivos y Resolución de problemas 
    
2. Expectativas y Brainstorming  
   
3. Conflictos y Gestión de Conflictos  
 
Se asignaron los temas por orden alfabético tal que:


- **AGRUPACIÓN 1:** Comunicación, Objetivos y Resolución de problemas
  - Blauvac Brea, Adrián Pierre
  - García Bartolomé, Javier
  ---
- **AGRUPACIÓN 2:** Expectativas, Brainstorming 
   - Jiménez Olivares, Pedro José
    - Moris Puig, Yvan
  ---
- **AGRUPACIÓN 3:** Conflictos, Gestión de Conflictos
    - Rea Mejia, Maria Carmen
    - Trull Martí, Andreu
  ---
 
 
**Puntos tratados:**
 
1. Presentación del documento "Aspectos esenciales para el trabajo en equipo"
   
2. Designación del secretario para el proyecto: Yvan Moris Puig. 
 
 
**Resumen de Nuevas Tareas:**
 
1. Familiarizarse con Eclipse para implantar un proyecto web dinámico en la MV del portal.
   
2. Preparar el acta de la sesión actual.
 
 
**Asignación y calendario:**
 
Todos los miembros del grupo deben de realizar las nuevas tareas antes de la próxima reunión, 1/5/2024.
 
 
**Intervención del Profesor:**
 
No se requiere intervención del profesor en esta sesión.

---

## ACTA REUNIÓN 2

**Fecha:** 1/5/2024  
**Horario:** 18:00 - 20:45  
**Tipo de Reunión:** Videoconferencia Discord  
**Grupo:** L1-3TI21

**Asistentes:** 
- Blauvac Brea, Adrián Pierre  
- García Bartolomé, Javier  
- Jiménez Olivares, Pedro José  
- Moris Puig, Yvan  
- Rea Mejia, Maria Carmen  
- Trull Martí, Andreu  

 *(Nota: Un asterisco indica los asistentes ausentes)*
 
**Resumen:**
 
Se ha creado el proyecto en Eclipse y se configuró un repositorio en GitHub para que podamos trabajar todos con el mismo repositorio remoto. Tras configurarlos todos en sus respectivas aplicaciones de Eclipse, se desarrolló un documento HTML con los formularios Log0, Log1 y Log2 de manera que se tengan que ingresar los datos de dni y contraseña y dandole a un botón se envie el formulario.
 
 
**Puntos tratados:**
 
1. Creación del proyecto en Eclipse y configuración del repositorio en GitHub.  
2. Desarrollo de los formularios Log0, Log1 y Log2.
 
 
**Resumen de Nuevas Tareas:**
1. Completar la implementación de Log0, Log1, Log.
2. Todos: Revisar y probar los formularios desarrollados.
3. Preparar el acta de la sesión actual
 
 
**Asignación y calendario:**
 
Todos los miembros del grupo deben de realizar las nuevas tareas.
La próxima reunión se celebrará el 4/5/2024 a las 18:00.
 
 
**Desarrollo:**

- **Log0:** Se ha creado un servlet con su configuración predefinida, dentro de sus métodos doGet y doPost hemos hecho 4 variables ”date”(LocalDateTime date = LocalDateTime.now()) para obtener la fecha actual, ”dni” (String dni = request.getParameter("dni")) para obtener el usuario de la casilla de formulario, ”pass” (String pass = request.getParameter("pass")) para obtener la contraseña de la casilla de formulario y ”uri” (String uri = request.getContextPath() + request.getServletPath() + request.getPathInfo() + request.getQueryString()) para obtener la uri. Por último creamos la respuesta de estos datos mediante otro html con el método response.getWriter().println().


- **Log1:** Se ha creado un servlet con su configuración predefinida, la implementación es la misma que en Log0, pero añadiendo un objeto llamado File, que guardará la ruta para crear un archivo LogForm.txt para que salve la información del formulario en forma de archivo de texto. Tan solo hay que crear otro objeto FileWriter llamado log1Write que tenga como parámetros el objeto File log1. Ahora solo tenemos que llamar a log1Write y hacer uso del método write() para pasarle los parámetros que queramos escribir.
Además, se ha agregado un try-catch para crear dicho archivo. Si la creación falla, se imprime un mensaje indicando el problema. El archivo se crea en el escritorio del usuario en forma de .txt.

- **Log2:** Se ha creado un servlet con su configuración predefinida, la implementación es la misma que en Log1, sin embargo, se ha configurado la ruta del archivo de registro a través de un parámetro en el archivo web.xml (File log1 = new File(getServletContext().getInitParameter("logFilePath"))) 
Manteniendo todo lo demás igual.
 
 
**Intervención del Profesor:**
 
No se requiere intervención del profesor en esta sesión.

---

## ACTA REUNIÓN 3

**Fecha:** 4/5/2024  
**Horario:** 17:00 - 20:30  
**Tipo de Reunión:** Videoconferencia Discord  
**Grupo:** L1-3TI21

**Asistentes:** 
- Blauvac Brea, Adrián Pierre  
- García Bartolomé, Javier  
- Jiménez Olivares, Pedro José  
- Moris Puig, Yvan  
- Rea Mejia, Maria Carmen  
- Trull Martí, Andreu  
 
*(Nota: Un asterisco indica los asistentes ausentes)*
 
 
**Resumen:**
 
 Hemos acabado de implementar el código de los logs restantes desviando la salida hacia un documento de texto. A su vez, dimos los primeros pasos con ‘curl’.
 
**Puntos tratados:**
 
1. Acabar log1 y log2  
2. Análisis de orden ‘curl’ en CentroEducativo-0.2.0 
3. Preparación de Proyecto para entrega de Hito 1
 
**Resumen de Nuevas Tareas:**
1. Todos: Revisar y probar los formularios desarrollados.
2. Conectar con CentroEducativo y probar curl
3. Preparar el acta de la sesión actual
 
**Asignación y calendario:**
 
Todos los miembros del grupo deben de realizar las nuevas tareas.
La próxima reunión se celebrará el 5/5/2024 a las 18:00.
 
 
**Desarrollo:**
 En la anterior sesión implementamos el código para copiar la información saliente en un documento de texto, LogForm.txt.Sin embargo, la creación de este documento supuso un pequeño problema en la anterior sesión, por lo que en esta sesión nos centramos en esta parte.De esta forma, resolvimos ambos logs

- **Log1:**
File log1 = new File(System.getProperty("user.home") + File.separator + nombreEscritorio + "/LogForm.txt");

- **Log2:** Este log, supuso un poco mas de tiempo, pues a pesar de estar configurado en Web.xml: 
<init-param>   
<param-name>logFilePath</param-name>     <param-value>/home/user/Escritorio/LogForm.txt</param-value> 
</init-param>
No identificamos la errata hasta después de un tiempo de pruebas, donde identificamos el error en la línea 48 y la línea 84 donde 
File log1 = new File(getServletContext().getInitParameter("logFilePath"));
debía de ser reemplazado por, 
File log2 = new File(this.getServletConfig().getInitParameter("logFilePath"));
Esto se debe al metodo this.getServletConfig() que proporciona información de configuración sobre el servlet actual(“this”)

Y manteniendo todo lo demás igual.

Más tarde Adrián y Pedro se pusieron a probar las órdenes curl iniciando el servidor CentroEducativo mediante su jar correspondiente, al acceder a su interfaz en Swagger UI, vemos que parámetros solicita cada petición http de sus métodos.
Estas son las órdenes utilizadas con sus respectivos resultados:  

Creamos una key mediante el login para el rol de Administrador

>curl -s --data '{"dni":"111111111","password":"654321"}' -X POST -H "content-type: application/json" http://localhost:9090/CentroEducativo/login -c cucu -b cucu

obtenemos la key: 1oqk08o1fe044grgg1g4f0dlmt

Ahora procedemos a consultar todos los alumnos y asignaturas, haciendo uso de la key de acceso que acabamos de recibir.

>curl -s -X GET 'http://localhost:9090/CentroEducativo/alumnosyasignaturas?key=1oqk08o1fe044grgg1g4f0dlmt' -H "accept: application/json" -c cucu -b cucu

y tenemos de resultado:
 
>[{"apellidos":"Garcia Sanchez","password":"123456","nombre":"Pepe", "asignaturas":  ["IAP","DCU","DEW"],"dni":"12345678W"},
	 {"apellidos":"Fernandez Gómez","password":"123456","nombre":"Maria","asignaturas":["DCU","DEW"],"dni":"23456387R"},
	 {"apellidos":"Hernandez Llopis","password":"123456","nombre":"Miguel","asignaturas":["DCU","IAP"],"dni":"34567891F"},
	 {"apellidos":"Benitez Torres","password":"123456","nombre":"Laura","asignaturas":["IAP","DEW"],"dni":"93847525G"},
	 {"apellidos":"Alonso Pérez","password":"123456","nombre":"Minerva","asignaturas":[],"dni":"37264096W"}]

Ahora como hemos iniciado sesión con el rol de Admin, intentamos crear una asignatura

>curl -s --data '{"acronimo": "MAT", "creditos": "6", "cuatrimestre": "B", "curso": "4", "nombre": "Matemáticas"}' -X POST -H "content-type: application/json" 'http://localhost:9090/CentroEducativo/asignaturas?key=1oqk08o1fe044grgg1g4f0dlmt' c cucu -b cucu

obtenemos por la consola un “OK” pero igualmente solicitaremos la asignatura para asegurarnos que existe, mediante esta orden get:

>curl -s -X GET 'http://localhost:9090/CentroEducativo/asignaturas/MAT?key=1oqk08o1fe044grgg1g4f0dlmt' -H "accept: application/json" -c cucu -b cucu

Efectivamente está todo correcto porque de resultado obtenemos:

>{"acronimo":"MAT","nombre":"Matemáticas","curso":4,"cuatrimestre":"B","creditos":6.0}

Ahora probaremos a eliminarla con una solicitud DELETE:

>curl -s -X DELETE -H "content-type: application/json" 'http://localhost:9090/CentroEducativo/asignaturas/MAT?key=1oqk08o1fe044grgg1g4f0dlmt' -H -c cucu -b cucu

Y obtenemos de respuesta un OK.

Intentamos obtener las asignaturas de un alumno dentro de la base de datos mediante:

>curl -s -X GET -H "content-type: application/json" 'http://localhost:9090/CentroEducativo/alumnos/12345678W/asignaturas?key=1oqk08o1fe044grgg1g4f0dlmt' -H -c cucu -b cucu

Y el resultado es:

>[{"asignatura":"DCU","nota":""},{"asignatura":"DEW","nota":""},{"asignatura":"IAP","nota":""}]

Bien, ahora queremos dar la nota a un alumno pero no podemos con el rol de Admin, por lo que cambiamos de rol de Admin a rol de profe:

>curl -s --data '{"dni":"23456733H","password":"123456"}' -X POST -H "content-type: application/json" http://localhost:9090/CentroEducativo/login -c cucu -b cucu

obtenemos esta key: b53krohr2npe2kflsge507sgjc

Y le damos una nota:

>curl -s --data '8' -X PUT -H "content-type: application/json" 'http://localhost:9090/CentroEducativo/alumnos/12345678W/asignaturas/DEW?key=b53krohr2npe2kflsge507sgjc' -H -c cucu -b cucu

y el resultado es un OK, por lo que confirmamos que todo ha ido correctamente.

Por último, al comprobar que todas las órdenes iban bien, Adrián ha procedido a crear el Shell solicitado.



**Intervención del Profesor:**
 
No se requiere intervención del profesor en esta sesión.

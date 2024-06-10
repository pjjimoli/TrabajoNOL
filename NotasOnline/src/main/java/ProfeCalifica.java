

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class ProfeCalifica
 */
@WebServlet("/ProfeCalifica")
public class ProfeCalifica extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProfeCalifica() {
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

        if (request.isUserInRole("rolpro")) {
        	
        	//Enviar a servidor, recibido en data....
        	
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

}



import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet Filter implementation class SesionControl
 */
@WebFilter("/SesionControl")
public class SesionControl extends HttpFilter implements Filter {
       
    /**
     * @see HttpFilter#HttpFilter()
     */
    public SesionControl() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
	    // place your code here
	    System.out.println("Pasa filtro Sesion");
	    HttpServletRequest http_req = (HttpServletRequest) request;
	    HttpServletResponse http_resp = (HttpServletResponse) response;
	    
	    HttpSession session = http_req.getSession();
	    List<String> cookies = (List<String>) session.getAttribute("cookies");
	    
	    if (http_req.isUserInRole("rolalu")) {
	        http_resp.sendRedirect(http_req.getContextPath() + "/CertificadoAlu.html");
	        return; // Agrega esta línea para asegurarte de que no se continúe procesando
	    }
	    
	    // pass the request along the filter chain
	    chain.doFilter(request, response);
	}


	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}

package imta.controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import imta.utils.SessionType;

/**
 * Servlet that manage home (/) redirection
 */
public class HomeServlet  extends AssociationServlet {

    @Override
    public void init() throws ServletException {
        System.out.println("Initializing imta.controllers.HomeServlet");
    }

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException
    {
    	super.doGet(req, resp);
    	
    	req.getRequestDispatcher("WEB-INF/pages/home.jsp").forward(req, resp);
    }

}

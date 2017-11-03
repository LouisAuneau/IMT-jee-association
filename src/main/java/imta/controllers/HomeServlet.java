package imta.controllers;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet that manage home (/) redirection
 */
public class HomeServlet  extends HttpServlet {

    @Override
    public void init() throws ServletException {
        System.out.println("Initializing imta.controllers.HomeServlet");
    }

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException
    {
        resp.sendRedirect("login");
    }

}

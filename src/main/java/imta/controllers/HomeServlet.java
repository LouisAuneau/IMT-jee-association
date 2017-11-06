package imta.controllers;

import imta.utils.SessionType;

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
        //Si la session n'existe pas ou si la session n'est pas du type LOGGED_IN_SESSION
        if(req.getSession() == null || req.getSession().getAttribute("sessionType") != SessionType.LOGGED_IN_SESSION)
        {
            //On redirige vers la page login
            resp.sendRedirect("login");
        } else {
            //Sinon on forward la page d'acceuil
            req.getRequestDispatcher("WEB-INF/pages/home.jsp").forward(req, resp);
        }
    }

}

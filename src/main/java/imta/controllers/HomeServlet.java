package imta.controllers;

import imta.utils.SessionType;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
        if(req.getSession() != null
                && req.getSession().getAttribute("sessionType") == SessionType.LOGGED_IN_SESSION)
        {
            req.getRequestDispatcher("WEB-INF/pages/home.jsp").forward(req, resp);
        } else {
            //Invalidate old session
            if(req.getSession() != null) {
                req.getSession(false).invalidate();
            }
            req.getRequestDispatcher("WEB-INF/pages/login.jsp").forward(req, resp);
        }
    }

}

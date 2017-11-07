package imta.controllers;

import imta.utils.Routes;
import imta.utils.SessionType;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LogoutServlet extends HttpSecureServlet {

    @Override
    public void init() throws ServletException {
        System.out.println("Initializing imta.controllers.LogoutServlet");
    }

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException
    {
        //We can access logout services only if we are logged in
        if(!verifyLoggedIn(req)) {
            resp.sendRedirect(Routes.HOME.getRoutePath());
            return;
        }

        //Invalidate old session and create a new one
        if(req.getSession() != null) {
            req.getSession(false).invalidate();
        }
        HttpSession session = req.getSession(true);
        session.setAttribute("sessionType", SessionType.LOGIN_SESSION);
        //Redirect on login page
        resp.sendRedirect(Routes.LOGIN.getRoutePath());
    }

}

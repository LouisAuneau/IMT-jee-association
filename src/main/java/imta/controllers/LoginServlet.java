package imta.controllers;

import imta.modele.bean.jpa.UtilisateurEntity;
import imta.modele.persistence.services.jpa.UtilisateurPersistenceJPA;
import imta.utils.Routes;
import imta.utils.SessionType;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

/**
 * Servlet that manage login page
 */
public class LoginServlet extends HttpSecureServlet {

    @Override
    public void init() throws ServletException {
        System.out.println("Initializing imta.controllers.LoginServlet");
    }

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException
    {
        //We can access login services only if we are not logged in
        if(!verifyNotLogged(req)) {
            resp.sendRedirect(Routes.HOME.getRoutePath());
            return;
        }
         //Set encoding of request and response
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");

        //Invalidate old session if sessionType != login
        if(req.getSession()!= null
                && req.getSession().getAttribute("sessionType") != SessionType.LOGIN_SESSION)
        {
            req.getSession(false).invalidate();
        }

        //Forward request
        req.getRequestDispatcher("WEB-INF/pages/login.jsp").forward(req, resp);
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException
    {
        //We can access login services only if we are not logged in
        if(!verifyNotLogged(req)) {
            resp.sendRedirect(Routes.HOME.getRoutePath());
            return;
        }
        //Set encoding of request and response
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        boolean loginSuccess = false;
        //Invalidate old session and create a new one
        if(req.getSession() != null) {
            req.getSession(false).invalidate();
        }
        HttpSession session = req.getSession(true);
        session.setAttribute("sessionType", SessionType.LOGIN_SESSION); //Session type is login

        //Get login connexion form user inputs
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        //Check if the username is given
        if(username == null || username.isEmpty()) {
            session.setAttribute("noUsernameGiven",true);
        }
        //Check if the password is given
        else if(password == null || password.isEmpty()) {
            session.setAttribute("noPasswordGiven",true);
        }
        //Check if connexion works
        else if(checkConnexion(username, password)) {
            loginSuccess = true;
        }

        //If the connexion success, redirect to home page
        if(loginSuccess) {
            session.setAttribute("sessionType", SessionType.LOGGED_IN_SESSION); //Session type is login
            session.setAttribute("username", username);
            resp.sendRedirect(Routes.HOME.getRoutePath());
        }
        //If the connexion fails, forward the login page.
        else {
            //Save user input in request in case of forward on login page
            session.setAttribute("username", username);
            session.setAttribute("connexionFails",true);
            resp.sendRedirect(Routes.LOGIN.getRoutePath());
        }
    }

    private boolean checkConnexion(String username, String password) {
        UtilisateurPersistenceJPA userDao = new UtilisateurPersistenceJPA();
        UtilisateurEntity user = userDao.load(username);
        try {
            if(user != null && user.checkPassword(password)) {
                return true;
            }
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
            return false;
        }
        return false;
    }

}

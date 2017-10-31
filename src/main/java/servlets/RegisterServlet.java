package servlets;

import utils.CountryList;
import utils.SessionType;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Servlet that manage register page
 */
public class RegisterServlet extends HttpServlet {

    @Override
    public void init() throws ServletException {
        System.out.println("Initializing servlets.RegisterServlet");
    }

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException
    {
        //Set encoding of request and response
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");

        //Invalidate old session if sessionType != register
        if(req.getSession()!= null
                && req.getSession().getAttribute("sessionType") != SessionType.REGISTER_SESSION)
        {
            req.getSession(false).invalidate();
        }

        //Retreive list of countries
        ArrayList<String> countryList = CountryList.getCountryList();
        req.setAttribute("countryList", countryList);

        //Forward request
        req.getRequestDispatcher("pages/register.jsp").forward(req, resp);
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException
    {
        //Set encoding of request and response
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");

        //Invalidate old session and create a new one
        if(req.getSession() != null) {
            req.getSession(false).invalidate();
        }
        HttpSession session = req.getSession(true);

        //Get register form user inputs
        boolean registerSuccess = false;
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String password2 = req.getParameter("password2");
        String secondName = req.getParameter("secondName");
        String firstName = req.getParameter("firstName");
        String address = req.getParameter("address");
        String postalCode = req.getParameter("postalCode");
        String city = req.getParameter("city");
        String country = req.getParameter("country");

        //Check if the username is given
        if(username == null || username.isEmpty()) {
            session.setAttribute("noUsernameGiven",true);
        }
        //Check if the password is given
        else if(password == null || password.isEmpty()) {
            session.setAttribute("noPasswordGiven",true);
        }
        //Check if the password2 is given
        else if(password2 == null || password2.isEmpty()) {
            session.setAttribute("noPassword2Given",true);
        }
        //Check if the secondName is given
        else if(secondName == null || secondName.isEmpty()) {
            session.setAttribute("noSecondNameGiven",true);
        }
        //Check if the firstName is given
        else if(firstName == null || firstName.isEmpty()) {
            session.setAttribute("noFirstNameGiven", true);
        }
        else if(!password.equals(password2)) {
            session.setAttribute("noPasswordMatch", true);
        }
        else {
            //TODO register user !!
            registerSuccess = true;
        }

        if(registerSuccess) {
            session.setAttribute("sessionType", SessionType.LOGIN_SESSION); //Session type is login
            session.setAttribute("registerSucceed", true); //Session type is login
            resp.sendRedirect("login");
        }
        else {
            session.setAttribute("sessionType", SessionType.REGISTER_SESSION); //Session type is register
            //Save user input in request in case of forward on register page
            session.setAttribute("username", username);
            session.setAttribute("secondName", secondName);
            session.setAttribute("firstName", firstName);
            session.setAttribute("address", address);
            session.setAttribute("postalCode", postalCode);
            session.setAttribute("city", city);
            session.setAttribute("country", country);
            resp.sendRedirect("register");
        }
    }

}

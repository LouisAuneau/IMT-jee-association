package imta.controllers;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

import imta.utils.SessionType;

public abstract class HttpSecureServlet extends HttpServlet {

	protected boolean verifyLoggedIn(HttpServletRequest req) {
	    if(req.getSession() == null) {
	        return false;
        } else if(req.getSession().getAttribute("sessionType") != SessionType.LOGGED_IN_SESSION) {
	        return false;
        }
        return true;
    }

    protected boolean verifyNotLogged(HttpServletRequest req) {
	    return !verifyLoggedIn(req);
    }

}

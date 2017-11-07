package imta.controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import imta.utils.SessionType;

/**
 * Extension of HttpServlet to add a session logged check in each doGet and doPost of
 * the servlets which will inherit from this class
 * @author Emeric G
 *
 */
public abstract class HttpSecureServlet extends HttpServlet {

	@Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException
    {
		//this.process(req, resp);
    }
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException
    {
		//this.process(req, resp);
    }

	/**
	 * Check if somebody is logged. If not, redirects to login page
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	private void process(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException
    {
		// check if an user is logged
		if(req.getSession() == null
				|| req.getSession().getAttribute("sessionType") != SessionType.LOGGED_IN_SESSION)
		{
			resp.sendRedirect("login");
		}
    }
}

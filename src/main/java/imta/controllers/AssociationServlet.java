package imta.controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import imta.utils.SessionType;

public abstract class AssociationServlet extends HttpServlet {

	@Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException
    {
		// check if an user is logged
		if(req.getSession() == null
				|| req.getSession().getAttribute("sessionType") != SessionType.LOGGED_IN_SESSION)
        {
			resp.sendRedirect("login");
			return;
        }
    }
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException
    {
		// check if an user is logged
		if(req.getSession() == null
				|| req.getSession().getAttribute("sessionType") != SessionType.LOGGED_IN_SESSION)
        {
			resp.sendRedirect("login");
			return;
        }
    }
}

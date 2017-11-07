package imta.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import imta.modele.bean.jpa.AchatEntity;
import imta.modele.persistence.services.AchatPersistence;
import imta.modele.persistence.services.jpa.AchatPersistenceJPA;

/**
 * Servlet implementation class CommandeServlet
 */
public class CommandeServlet extends AssociationServlet {
	private static final long serialVersionUID = 1L;
    
	private List<AchatEntity> achats;
	private AchatPersistence achaPers;
	
    @Override
    public void init() throws ServletException {
        System.out.println("Initializing imta.controllers.CommandeServlet");
    }
	
    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
    	
    	// first checks
    	super.doGet(request,response);
    	
    	HttpSession session = request.getSession();
    	String username = (String) session.getAttribute("username");
    	
    	this.loadAchats(username);
    	request.setAttribute("achats", this.achats);
    	
    	// Forward request
        request.getRequestDispatcher("WEB-INF/pages/commande.jsp").forward(request, response);
	}

    @Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
    	
    	// first checks
    	super.doPost(request, response);
    	
	}

    private void loadAchats(String username) {
    	
    	if (achats == null) {
    		achats = new ArrayList<>();
    	}
    	else {
    		achats.clear();
    	}
    	
    	this.achaPers = new AchatPersistenceJPA();
    	this.achats = achaPers.loadByUser(username);
    }
}

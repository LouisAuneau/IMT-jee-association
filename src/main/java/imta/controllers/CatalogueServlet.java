package imta.controllers;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import imta.modele.bean.jpa.ArticleEntity;
import imta.modele.persistence.services.ArticlePersistence;
import imta.modele.persistence.services.jpa.ArticlePersistenceJPA;
import imta.utils.SessionType;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class CatalogueServlet extends HttpServlet {

	private List<ArticleEntity> articles;
	private ArticlePersistence artiPers;
	
    @Override
    public void init() throws ServletException {
        System.out.println("Initializing imta.controllers.CatalogueServlet");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException
    {
    	this.loadArticles();
    	req.setAttribute("articles", this.articles);
    	
    	// Forward request
        req.getRequestDispatcher("WEB-INF/catalogue.jsp").forward(req, resp);
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException
    {
        //Set encoding of request and response
//        req.setCharacterEncoding("UTF-8");
//        resp.setCharacterEncoding("UTF-8");
//        boolean loginSuccess = false;
//        //Invalidate old session and create a new one
//        if(req.getSession() != null) {
//            req.getSession(false).invalidate();
//        }
//        HttpSession session = req.getSession(true);
//        session.setAttribute("sessionType", SessionType.LOGIN_SESSION); //Session type is login
//
//        //Get login connexion form user inputs
//        String username = req.getParameter("username");
//        String password = req.getParameter("password");
//
//        //Check if the username is given
//        if(username == null || username.isEmpty()) {
//            session.setAttribute("noUsernameGiven",true);
//        }
//        //Check if the password is given
//        else if(password == null || password.isEmpty()) {
//            session.setAttribute("noPasswordGiven",true);
//        }
//        //Check if connexion works
//        else if(checkConnexion(username, password)) {
//            loginSuccess = true;
//        }
//
//        //If the connexion success, redirect to home page
//        if(loginSuccess) {
//            resp.sendRedirect("hello");
//        }
//        //If the connexion fails, forward the login page.
//        else {
//            //Save user input in request in case of forward on login page
//            session.setAttribute("username", username);
//            session.setAttribute("connexionFails",true);
//            resp.sendRedirect("login");
//        }
    	
    	this.loadArticles();
    	req.setAttribute("articles", this.articles);
    	
    	// Forward request
        req.getRequestDispatcher("WEB-INF/catalogue.jsp").forward(req, resp);
    }
    
    
    private void loadArticles() {
    	ArticleEntity arti;
    	
    	if (articles == null) {
    		articles = new ArrayList<>();
    	}
    	else {
    		articles.clear();
    	}
    	
//    	for (int i = 1; i < 11; i++) {
//    		arti = new ArticleEntity();
//    		arti.setDescription("description de l'article dont le code est i" + i);
//    		arti.setCode("i" + i);
//    		arti.setNom("Batte de baseball n°" + i);
//    		arti.setQuantite(15);
//    		articles.add(arti);
//    	}
    	
    	this.artiPers = new ArticlePersistenceJPA();
    	this.articles = artiPers.loadAll();
    }
}

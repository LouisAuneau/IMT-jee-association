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
        req.getRequestDispatcher("WEB-INF/pages/catalogue.jsp").forward(req, resp);
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException
    {
    	this.loadArticles();
    	req.setAttribute("articles", this.articles);
    	
    	// Forward request
        req.getRequestDispatcher("WEB-INF/pages/catalogue.jsp").forward(req, resp);
    }
    
    private void loadArticles() {
    	
    	if (articles == null) {
    		articles = new ArrayList<>();
    	}
    	else {
    		articles.clear();
    	}
    	
    	this.artiPers = new ArticlePersistenceJPA();
    	this.articles = artiPers.loadAll();
    }
}

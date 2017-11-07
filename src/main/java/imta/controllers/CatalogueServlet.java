package imta.controllers;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import imta.modele.bean.jpa.AchatEntity;
import imta.modele.bean.jpa.ArticleEntity;
import imta.modele.bean.jpa.UtilisateurEntity;
import imta.modele.persistence.services.AchatPersistence;
import imta.modele.persistence.services.ArticlePersistence;
import imta.modele.persistence.services.UtilisateurPersistence;
import imta.modele.persistence.services.jpa.AchatPersistenceJPA;
import imta.modele.persistence.services.jpa.ArticlePersistenceJPA;
import imta.modele.persistence.services.jpa.UtilisateurPersistenceJPA;
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
    	// check if an user is logged
    	if(req.getSession() == null
    			|| req.getSession().getAttribute("sessionType") != SessionType.LOGGED_IN_SESSION)
    	{
    		resp.sendRedirect("login");
    		return;
    	}    	
    	
    	this.loadArticles();
    	req.setAttribute("articles", this.articles);
    	
    	// Forward request
        req.getRequestDispatcher("WEB-INF/pages/catalogue.jsp").forward(req, resp);
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException
    {
    	// check if an user is logged
    	if(req.getSession() == null
    			|| req.getSession().getAttribute("sessionType") != SessionType.LOGGED_IN_SESSION)
    	{
    		resp.sendRedirect("login");
    		return;
    	}  
    	
    	AchatPersistence achaPers;
    	UtilisateurPersistence userPers;
    	ArticleEntity article;
    	AchatEntity achat;
    	UtilisateurEntity user;
    	
    	String username = (String) req.getSession().getAttribute("username");
    	String codeArticle = (String) req.getParameter("id");
    	Integer quantiteCommande = Integer.parseInt(req.getParameter("quantiteComm"));
    	Integer stock = Integer.parseInt(req.getParameter("stock"));
    	
    	if (username != null && codeArticle != null && quantiteCommande != null && stock != null) {
    		achaPers = new AchatPersistenceJPA();
    		userPers = new UtilisateurPersistenceJPA();
    		
    		if (artiPers == null) {
    			artiPers = new ArticlePersistenceJPA();
    		}
    		
    		article = artiPers.load(codeArticle);
    		article.setQuantite(stock - quantiteCommande);
    		
    		user = userPers.load(username);
    		
    		achat = new AchatEntity();
    		achat.setQuantite(quantiteCommande);
    		achat.setArticle2(article);
    		achat.setUtilisateur2(user);

    		// TODO utiliser count all pour incrémenter l'id ou utiliser insert
    		achat.setId(2);
    		
    		artiPers.save(article);
    		achaPers.insert(achat);
    	}
    	resp.sendRedirect("catalogue");
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

package imta.controllers;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import imta.modele.bean.jpa.AchatEntity;
import imta.modele.bean.jpa.ArticleEntity;
import imta.modele.bean.jpa.UtilisateurEntity;
import imta.modele.persistence.services.AchatPersistence;
import imta.modele.persistence.services.ArticlePersistence;
import imta.modele.persistence.services.UtilisateurPersistence;
import imta.modele.persistence.services.jpa.AchatPersistenceJPA;
import imta.modele.persistence.services.jpa.ArticlePersistenceJPA;
import imta.modele.persistence.services.jpa.UtilisateurPersistenceJPA;
import imta.utils.Routes;

import java.io.IOException;
import java.util.List;

public class CatalogueServlet extends HttpSecureServlet {

    private static final String TITRE_CATALOGUE_VIDE = "Notre catalogue est vide...";
    private static final String TITRE = "Voici notre catalogue";

	@Override
    public void init() throws ServletException {
        System.out.println("Initializing imta.controllers.CatalogueServlet");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException
    {
        //We can access catalogue services only if we are logged in
        if(!verifyLoggedIn(req)) {
            resp.sendRedirect(Routes.HOME.getRoutePath());
            return;
        }
    	
    	Boolean catalogueVide;
    	String header;
        List<ArticleEntity> articles = this.loadArticles();
    	
    	// on regarde si le catalogue est vide
    	if (articles.isEmpty()) {
    		catalogueVide = true;
    		header = TITRE_CATALOGUE_VIDE;
    	}
    	else {
    		catalogueVide = false;
    		header = TITRE;
    	}
    	req.setAttribute("catalogueVide", catalogueVide);
    	req.setAttribute("header", header);
    	req.setAttribute("articles", articles);
    	
    	// Forward request
        req.getRequestDispatcher("WEB-INF/pages/catalogue.jsp").forward(req, resp);
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException
    {
        //We can access catalogue services only if we are logged in
        if(!verifyLoggedIn(req)) {
            resp.sendRedirect(Routes.HOME.getRoutePath());
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
    	
    	// Creation d'un achat à partir d'un article
    	if (username != null && codeArticle != null && quantiteCommande != null) {
    		achaPers = new AchatPersistenceJPA();
    		userPers = new UtilisateurPersistenceJPA();

            ArticlePersistence artiPers = new ArticlePersistenceJPA();

    		article = artiPers.load(codeArticle);

            //Verify that we have enouth products
            if(article.getQuantite() - quantiteCommande < 0) {
                req.setAttribute("isNotEnougthProducts", true);
                resp.sendRedirect(Routes.CATALOG.getRoutePath());
                return;
            }

    		article.setQuantite(article.getQuantite() - quantiteCommande);

    		achat = achaPers.loadByUserAndArticle(username, codeArticle);

    		// si un achat de cet article n'existait pas, on le crée
    		if (achat == null) {
    			achat = new AchatEntity();
        		achat.setQuantite(quantiteCommande);
        		achat.setArticle2(article);
        		user = userPers.load(username);
        		achat.setUtilisateur2(user);
    		}
    		// sinon on augmente juste sa quantité
    		else {
    			achat.setQuantite(achat.getQuantite() + quantiteCommande);
    		}

    		artiPers.save(article);
    		achaPers.save(achat);
    	}
    	resp.sendRedirect(Routes.CATALOG.getRoutePath());
    }
    
    /**
     * Récupère les articles présents dans le catalogue de l'association
     */
    private List<ArticleEntity> loadArticles() {
        ArticlePersistence artiPers = new ArticlePersistenceJPA();
    	return artiPers.loadAll();
    }
}

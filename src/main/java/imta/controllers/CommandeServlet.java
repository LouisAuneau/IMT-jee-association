package imta.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import imta.modele.bean.jpa.AchatEntity;
import imta.modele.bean.jpa.ArticleEntity;
import imta.modele.persistence.services.AchatPersistence;
import imta.modele.persistence.services.ArticlePersistence;
import imta.modele.persistence.services.jpa.AchatPersistenceJPA;
import imta.modele.persistence.services.jpa.ArticlePersistenceJPA;
import imta.utils.Routes;

/**
 * Servlet implementation class CommandeServlet
 */
public class CommandeServlet extends HttpSecureServlet {
	private static final long serialVersionUID = 1L;
    private static final String RETRAIT_ACHAT = "0";
    private static final String ABANDON_COMMANDE = "1";
    private static final String TITRE_COMMANDE_VIDE = "Votre commande est vide...";
    private static final String TITRE = "Votre commande";
	
	private List<AchatEntity> achats;
	private AchatPersistence achaPers;

    @Override
    public void init() throws ServletException {
        System.out.println("Initializing imta.controllers.CommandeServlet");
    }
	
    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException
    {
        //We can access command services only if we are logged in
        if(!verifyLoggedIn(request)) {
            response.sendRedirect(Routes.HOME.getRoutePath());
            return;
        }
    	
    	HttpSession session = request.getSession();
    	String username = (String) session.getAttribute("username");
    	Boolean commandeNonVide;
    	String header;
    	
    	this.loadAchats(username);
    	
    	// On regarde si l'utilisateur a des achats
    	if (achats.isEmpty()) {
    		commandeNonVide = false;
    		header = TITRE_COMMANDE_VIDE;
    	}
    	else {
    		commandeNonVide = true;
    		header = TITRE;
    	}
    	request.setAttribute("commandeNonVide", commandeNonVide);
    	request.setAttribute("header", header);
    	request.setAttribute("achats", this.achats);
    	
    	// Forward request
        request.getRequestDispatcher("WEB-INF/pages/commande.jsp").forward(request, response);
	}

    @Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException
    {
        //We can access command services only if we are logged in
        if(!verifyLoggedIn(request)) {
            response.sendRedirect(Routes.HOME.getRoutePath());
            return;
        }
    	
    	String typeAction = request.getParameter("typeAction");
    	Integer idAchat;
    	String codeArticle;
    	Integer quantiteArticle;
    	String username;
    	
    	// cas o� on souhaite juste retirer un Achat de la commande
    	if (RETRAIT_ACHAT.equals(typeAction)) {
    		idAchat = Integer.parseInt(request.getParameter("idAchat"));
    		quantiteArticle = Integer.parseInt(request.getParameter("quantiteArticle"));
    		codeArticle = request.getParameter("codeArticle");
    		
    		this.remettreDansCatalogue(idAchat, codeArticle, quantiteArticle);
    	}
    	// cas o� on veut supprimer tout le contenu de la commande
    	else if (ABANDON_COMMANDE.equals(typeAction)) {
    		username = (String) request.getSession().getAttribute("username");
//    		achaPers.deleteByUser(username);
    		
    		for (AchatEntity achat : this.achats) {
    			this.remettreDansCatalogue(
    					achat.getId(), 
    					achat.getArticle2().getCode(), 
    					achat.getQuantite());
    		}
    	}
    	
    	response.sendRedirect("commande");
	}

    /**
     * Recupere les achats de la commande de l'utilisateur
     * @param username
     */
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
    
    /**
     * Vide la commande et remet les stockes des achats dans le catalogue
     * @param idAchat
     * @param codeArticle
     * @param quantite
     */
    private void remettreDansCatalogue(Integer idAchat, String codeArticle, Integer quantite) {
    	
    	if (achaPers == null) {
    		achaPers = new AchatPersistenceJPA();
    	}
    	ArticlePersistence artiPers = new ArticlePersistenceJPA();
    	ArticleEntity article = artiPers.load(codeArticle);
    	
    	if (article == null) {
    		return;
    	}
    	
    	article.setQuantite(article.getQuantite() + quantite);
    	artiPers.save(article);
    	
    	achaPers.delete(idAchat);
    }
}

package imta.controllers;

import imta.modele.bean.jpa.UtilisateurEntity;
import imta.modele.persistence.services.jpa.AchatPersistenceJPA;
import imta.modele.persistence.services.jpa.UtilisateurPersistenceJPA;
import imta.utils.Routes;
import org.junit.*;
import imta.utils.MyHttpSessionMock;
import imta.utils.SessionType;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyBoolean;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class LoginServletTest {

    private HttpServletRequest request;
    private HttpServletResponse response;
    private HttpSession session;
    private LoginServlet loginServlet;

    @BeforeClass
    public static void beforeClass() throws UnsupportedEncodingException, NoSuchAlgorithmException {
        AchatPersistenceJPA achatDao = new AchatPersistenceJPA();
        achatDao.deleteAll();
        UtilisateurPersistenceJPA userDao = new UtilisateurPersistenceJPA();
        UtilisateurEntity user = new UtilisateurEntity("mguyot", "1234", "mat", "guy", "","","","");
        userDao.deleteAll();
        userDao.insert(user);
    }

    @AfterClass
    public static void afterClass() {
        UtilisateurPersistenceJPA userDao = new UtilisateurPersistenceJPA();
        userDao.deleteAll();
    }

    @Before
    public void setUp() {
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
        RequestDispatcher dispatcher = mock(RequestDispatcher.class);
        session = new MyHttpSessionMock();
        loginServlet = new LoginServlet();
        when(request.getRequestDispatcher(anyString())).thenReturn(dispatcher);
        when(request.getSession()).thenReturn(session);
        when(request.getSession(anyBoolean())).thenReturn(session);
    }

    @Test
    public void doGetTestOkSessionDeleted() throws Exception {
        session.setAttribute("sessionType", SessionType.REGISTER_SESSION);
        loginServlet.doGet(request, response);
        //Check that the session is deleted
        assertEquals(null, session.getAttribute("sessionType"));
        //Verify that doGet forwards on pages/login.jsp
        verify(request).getRequestDispatcher("WEB-INF/pages/login.jsp");
    }

    @Test
    public void doGetTestOkSessionNotDeleted() throws Exception {
        session.setAttribute("sessionType", SessionType.LOGIN_SESSION);
        loginServlet.doGet(request, response);
        //Check that the session is not deleted
        assertEquals(SessionType.LOGIN_SESSION, session.getAttribute("sessionType"));
        //Verify that doGet forwards on pages/login.jsp
        verify(request).getRequestDispatcher("WEB-INF/pages/login.jsp");
    }

    @Test
    public void doPostTestOk() throws Exception {
        when(request.getParameter("username")).thenReturn("mguyot");
        when(request.getParameter("password")).thenReturn("1234");
        loginServlet.doPost(request, response);
        //Verify that doPost redirect on hello page
        verify(response).sendRedirect(Routes.HOME.getRoutePath());
    }

    @Test
    public void doPostTestFailsNoUsername() throws Exception {
        loginServlet.doPost(request, response);
        assertEquals(SessionType.LOGIN_SESSION, request.getSession().getAttribute("sessionType"));
        assertEquals(true, request.getSession().getAttribute("noUsernameGiven"));
        //Verify that doPost redirect on login page
        verify(response).sendRedirect(Routes.LOGIN.getRoutePath());
    }

    @Test
    public void doPostTestFailsNoPassword() throws Exception {
        when(request.getParameter("username")).thenReturn("mguyot");
        loginServlet.doPost(request, response);
        assertEquals(SessionType.LOGIN_SESSION, request.getSession().getAttribute("sessionType"));
        assertEquals(true, request.getSession().getAttribute("noPasswordGiven"));
        //Verify that doPost redirect on login page
        verify(response).sendRedirect(Routes.LOGIN.getRoutePath());
    }

    @Test
    public void doPostTestFailsInvalidPassword() throws Exception {
        when(request.getParameter("username")).thenReturn("mguyot");
        when(request.getParameter("password")).thenReturn("12345");
        loginServlet.doPost(request, response);
        assertEquals(SessionType.LOGIN_SESSION, request.getSession().getAttribute("sessionType"));
        assertEquals(true, request.getSession().getAttribute("connexionFails"));
        //Verify that doPost redirect on login page
        verify(response).sendRedirect(Routes.LOGIN.getRoutePath());
    }

}

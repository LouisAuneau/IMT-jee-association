import org.junit.Before;
import org.junit.Test;
import servlets.RegisterServlet;
import utils.MyHttpSessionMock;
import utils.SessionType;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyBoolean;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class RegisterServletTest {

    private HttpServletRequest request;
    private HttpServletResponse response;
    private HttpSession session;
    private RegisterServlet registerServlet;

    @Before
    public void setUp() {
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
        RequestDispatcher dispatcher = mock(RequestDispatcher.class);
        session = new MyHttpSessionMock();
        registerServlet = new RegisterServlet();
        when(request.getRequestDispatcher(anyString())).thenReturn(dispatcher);
        when(request.getSession()).thenReturn(session);
        when(request.getSession(anyBoolean())).thenReturn(session);
    }

    @Test
    public void doGetTestOkSessionDeleted() throws Exception {
        session.setAttribute("sessionType", SessionType.LOGIN_SESSION);
        registerServlet.doGet(request, response);
        //Check that the session is deleted
        assertEquals(null, session.getAttribute("sessionType"));
        //Check that the doGet method forward on pages/register.jsp
        verify(request).getRequestDispatcher("pages/register.jsp");
    }

    @Test
    public void doGetTestOkSessionNotDeleted() throws Exception {
        session.setAttribute("sessionType", SessionType.REGISTER_SESSION);
        registerServlet.doGet(request, response);
        //Check that the session is not deleted
        assertEquals(SessionType.REGISTER_SESSION, session.getAttribute("sessionType"));
        //Check that the doGet method forward on pages/register.jsp
        verify(request).getRequestDispatcher("pages/register.jsp");
    }

    public void doPostTestOkAddressGiven() throws Exception {
        //TODO : To implement
    }

    @Test
    public void doPostTestOkAddressNotGiven() throws Exception {
        when(request.getParameter("username")).thenReturn("testUser");
        when(request.getParameter("password")).thenReturn("1234");
        when(request.getParameter("password2")).thenReturn("1234");
        when(request.getParameter("firstName")).thenReturn("mike");
        when(request.getParameter("secondName")).thenReturn("mikel");
        registerServlet.doPost(request, response);
        assertEquals(SessionType.LOGIN_SESSION, request.getSession().getAttribute("sessionType"));
        assertEquals(true, request.getSession().getAttribute("registerSucceed"));
        //Verify that doPost redirect on login page
        verify(response).sendRedirect("login");
    }

    @Test
    public void doPostTestFailsNoUsernameGiven() throws Exception {
        registerServlet.doPost(request, response);
        assertEquals(true, request.getSession().getAttribute("noUsernameGiven"));
        //Verify that doPost redirect on login page
        verify(response).sendRedirect("register");
    }

    @Test
    public void doPostTestFailsNoPassword1Given() throws Exception {
        when(request.getParameter("username")).thenReturn("mguyot");
        registerServlet.doPost(request, response);
        assertEquals(true, request.getSession().getAttribute("noPasswordGiven"));
        //Verify that doPost redirect on login page
        verify(response).sendRedirect("register");
    }

    @Test
    public void doPostTestFailsNoPassword2Given() throws Exception {
        when(request.getParameter("username")).thenReturn("testUser");
        when(request.getParameter("password")).thenReturn("1234");
        registerServlet.doPost(request, response);
        assertEquals(true, request.getSession().getAttribute("noPassword2Given"));
        //Verify that doPost redirect on login page
        verify(response).sendRedirect("register");
    }

    @Test
    public void doPostTestFailsNoFirstNameGiven() throws Exception {
        when(request.getParameter("username")).thenReturn("testUser");
        when(request.getParameter("password")).thenReturn("1234");
        when(request.getParameter("password2")).thenReturn("1234");
        when(request.getParameter("secondName")).thenReturn("mike");
        registerServlet.doPost(request, response);
        assertEquals(true, request.getSession().getAttribute("noFirstNameGiven"));
        //Verify that doPost redirect on login page
        verify(response).sendRedirect("register");
    }

    @Test
    public void doPostTestFailsNoSecondNameGiven() throws Exception {
        when(request.getParameter("username")).thenReturn("testUser");
        when(request.getParameter("password")).thenReturn("1234");
        when(request.getParameter("password2")).thenReturn("1234");
        when(request.getParameter("firstName")).thenReturn("mike");
        registerServlet.doPost(request, response);
        assertEquals(true, request.getSession().getAttribute("noSecondNameGiven"));
        //Verify that doPost redirect on login page
        verify(response).sendRedirect("register");
    }

    @Test
    public void doPostTestFailsPasswordsNotEquals() throws Exception {
        when(request.getParameter("username")).thenReturn("testUser");
        when(request.getParameter("password")).thenReturn("1234");
        when(request.getParameter("password2")).thenReturn("12345");
        when(request.getParameter("firstName")).thenReturn("mike");
        when(request.getParameter("secondName")).thenReturn("mikel");
        registerServlet.doPost(request, response);
        assertEquals(true, request.getSession().getAttribute("noPasswordMatch"));
        //Verify that doPost redirect on login page
        verify(response).sendRedirect("register");
    }

    public void doPostTestFailsPartialAddressGiven() throws Exception {
        //TODO : To implement
    }

}

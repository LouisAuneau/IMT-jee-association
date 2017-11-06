package imta.controllers;

import org.junit.Before;
import org.junit.Test;
import imta.utils.MyHttpSessionMock;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static org.mockito.Matchers.anyBoolean;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class HomeServletTest {
    private HttpServletRequest request;
    private HttpServletResponse response;
    private HomeServlet homeServlet;

    @Before
    public void setUp() {
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
        RequestDispatcher dispatcher = mock(RequestDispatcher.class);
        HttpSession session = new MyHttpSessionMock();
        homeServlet = new HomeServlet();
        when(request.getRequestDispatcher(anyString())).thenReturn(dispatcher);
        when(request.getSession()).thenReturn(session);
        when(request.getSession(anyBoolean())).thenReturn(session);
    }

    @Test
    public void doGetTestNoSession () throws Exception {
        homeServlet.doGet(request, response);
        //Verify that doGet redirects on login
        verify(response).sendRedirect("login");
    }

}

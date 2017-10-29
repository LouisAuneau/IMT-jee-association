import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //Get login connexion form user inputs
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        //Save user input in request in case of forward on login page
        req.setAttribute("username", username);
        req.setAttribute("password", password);

        //Check if the username is given
        if(username.isEmpty()) {
            req.setAttribute("noUsernameGiven",true);
        }
        //Check if the password is given
        else if(password.isEmpty()) {
            req.setAttribute("noPasswordGiven",true);
        }
        //Check if connexion works
        else if(checkConnexion(username, password)) {
            resp.sendRedirect("/hello");
            return;
        }
        //If the connexion fails, forward the login page.
        else {
            req.setAttribute("connexionFails",true);
        }
        req.getRequestDispatcher("login").forward(req, resp);
    }

    private boolean checkConnexion(String username, String password) {
        return Objects.equals(username, "mguyot") && Objects.equals(password, "1234");
    }

}

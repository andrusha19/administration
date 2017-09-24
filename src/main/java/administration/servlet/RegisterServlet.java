
package administration.servlet;

import administration.data.User;
import administration.sql.UserDAOImpl;
import java.io.IOException;
import static java.nio.charset.StandardCharsets.ISO_8859_1;
import static java.nio.charset.StandardCharsets.UTF_8;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/register.ftl").forward(request, response);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        String error;

        if (username == null || username.isEmpty()) {
            error = "Login cannot be empty.";
            request.setAttribute("error", error);
            request.getRequestDispatcher("/register.ftl").forward(request, response);
            return;
        }

        if (password == null || password.isEmpty()) {
            error = "Password cannot be empty.";
            request.setAttribute("error", error);
            request.getRequestDispatcher("/register.ftl").forward(request, response);
            return;
        }
        
        if (email == null || email.isEmpty()) {
            error = "E-mail cannot be empty.";
            request.setAttribute("error", error);
            request.getRequestDispatcher("/register.ftl").forward(request, response);
            return;
        }

        byte[] ptext = username.getBytes(ISO_8859_1); 
        String value = new String(ptext, UTF_8); 
        User user = new UserDAOImpl().getUser(value);
        if(user != null){
            error = "Such login is already in use.";
            request.setAttribute("error", error);
            request.getRequestDispatcher("/register.ftl").forward(request, response);
        }else{
            user = new User();
            ptext = username.getBytes(ISO_8859_1); 
            value = new String(ptext, UTF_8); 
            user.setName(value);
            ptext = password.getBytes(ISO_8859_1); 
            value = new String(ptext, UTF_8); 
            user.setPassword(value);
            ptext = email.getBytes(ISO_8859_1); 
            value = new String(ptext, UTF_8); 
            user.setEmail(value);
            user.setRegistration_time(new Date(System.currentTimeMillis()));
            user.setLast_edit_time(new Date(System.currentTimeMillis()));
            user.setRole("user");
            error = new UserDAOImpl().addUser(user);
            if(error.equals("OK")){
                request.getSession(true).setAttribute("user", user);
                response.sendRedirect(request.getContextPath() + "/browse");
            }else{
                request.setAttribute("error", error);
                request.getRequestDispatcher("/register.ftl").forward(request, response);
            }
        }
    }
}

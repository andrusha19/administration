
package administration.servlet;

import administration.data.User;
import administration.sql.UserDAOImpl;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import static java.nio.charset.StandardCharsets.ISO_8859_1;
import static java.nio.charset.StandardCharsets.UTF_8;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(request.getSession(false) == null || request.getSession().getAttribute("user") == null)
            request.getRequestDispatcher("/login.ftl").forward(request, response);
        else{
            String error = "You are already logged in.";
            request.setAttribute("error", error);
            User user = (User) request.getSession(false).getAttribute("user");
            if(user.getRole().equals("user")){
                request.getSession(false).setAttribute("error", error);
                response.sendRedirect(request.getContextPath() + "/browse");
            }
            else if(user.getRole().equals("admin")){
                request.getSession(false).setAttribute("error", error);
                response.sendRedirect(request.getContextPath() + "/manage");
            }
            else{
                error = "Your role is not specified in database.";
                request.setAttribute("error", error);
                request.getRequestDispatcher("/login.ftl").forward(request, response);
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String error;
        
        if(username == null || username.isEmpty() || password == null || password.isEmpty()){
            error = "Login and password cannot be empty.";
            request.setAttribute("error", error);
            request.getRequestDispatcher("/login.ftl").forward(request, response);
            return;
        }
        
        byte[] ptext = username.getBytes(ISO_8859_1); 
        String value = new String(ptext, UTF_8); 
        User user = new UserDAOImpl().getUser(value);
        if(user == null){
            error = "There is no such user in database.";
            request.setAttribute("error", error);
            request.getRequestDispatcher("/login.ftl").forward(request, response);
        }else{
            MessageDigest md5 = null;
            String pass = "";
            try {
                md5 = MessageDigest.getInstance("MD5");
            } catch (NoSuchAlgorithmException ex) {
                error = "Your system does not support MD5 encryption.";
                request.setAttribute("error", error);
                request.getRequestDispatcher("/login.ftl").forward(request, response);
            }
            ptext = password.getBytes(ISO_8859_1); 
            value = new String(ptext, UTF_8); 
            md5.update(StandardCharsets.UTF_8.encode(value));
            pass = String.format("%032x", new BigInteger(1, md5.digest()));
            
            if(user.getRole().equals("user")){
                if(pass.equals(user.getPassword())){
                    request.getSession(true).setAttribute("user", user);
                    response.sendRedirect(request.getContextPath() + "/browse");
                }else{
                    error = "Your password is incorrect.";
                    request.setAttribute("error", error);
                    request.getRequestDispatcher("/login.ftl").forward(request, response);
                }
            }
            else if(user.getRole().equals("admin")){
                if(pass.equals(user.getPassword())){
                    request.getSession(true).setAttribute("user", user);
                    response.sendRedirect(request.getContextPath() + "/manage");
                }else{
                    error = "Your password is incorrect.";
                    request.setAttribute("error", error);
                    request.getRequestDispatcher("/login.ftl").forward(request, response);
                }
            }
            else{
                error = "Your role is not specified in database.";
                request.setAttribute("error", error);
                request.getRequestDispatcher("/login.ftl").forward(request, response);
            }
        }
    }

}

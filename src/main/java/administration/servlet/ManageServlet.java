
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
import javax.servlet.http.HttpSession;

@WebServlet("/manage/*")
public class ManageServlet extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if(session == null){
            response.sendRedirect(request.getContextPath() + "/login");
        }else{
            User user = (User) session.getAttribute("user");
            if(user == null){
                session.invalidate();
                response.sendRedirect(request.getContextPath() + "/login");
                return;
            }
            if(user.getRole().equals("user")){
                response.sendRedirect(request.getContextPath() + "/browse");
                return;
            }
            if(user.getRole().equals("admin")){
                String error = (String) session.getAttribute("error");
                session.setAttribute("error", null);
                if(request.getPathInfo() == null){
                    request.setAttribute("error", error);
                    request.setAttribute("user", user);
                    request.setAttribute("users", new UserDAOImpl().getUsers());
                    request.getRequestDispatcher("/browse.ftl").forward(request, response);
                }else{
                    User edituser=new UserDAOImpl().getUser(request.getPathInfo().substring(1));
                    if(edituser == null){
                        session.setAttribute("error", error);
                        response.sendRedirect(request.getContextPath() + "/manage");
                    }else{
                        request.setAttribute("error", error);
                        request.setAttribute("user", user);
                        request.setAttribute("edituser", edituser);
                        request.getRequestDispatcher("/edit.ftl").forward(request, response);
                    }
                }
            }else{
                session.invalidate();
                response.sendRedirect(request.getContextPath() + "/login");
            }
        }
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        String error;

        HttpSession session = request.getSession(false);
        if(session == null){
            response.sendRedirect(request.getContextPath() + "/login");
        }else{
            User user = (User) session.getAttribute("user");
            if(user == null){
                session.invalidate();
                response.sendRedirect(request.getContextPath() + "/login");
                return;
            }
            if(user.getRole().equals("user")){
                response.sendRedirect(request.getContextPath() + "/browse");
                return;
            }
            if(user.getRole().equals("admin")){
                if(request.getPathInfo() == null){
                    request.setAttribute("user", user);
                    request.setAttribute("users", new UserDAOImpl().getUsers());
                    request.getRequestDispatcher("/browse.ftl").forward(request, response);
                }else{
                    User edituser=new UserDAOImpl().getUser(request.getPathInfo().substring(1));
                    if(edituser == null){
                        response.sendRedirect(request.getContextPath() + "/manage");
                    }else{
                        if(request.getParameter("Cancel") != null){
                            session.setAttribute("error", null);
                            response.sendRedirect(request.getContextPath() + "/manage");
                            return;
                        }
                        if(request.getParameter("Delete") != null){
                            error = new UserDAOImpl().deleteUser(edituser.getName());
                            if(error.equals("OK"))
                                error = "User " + edituser.getName() + " was deleted.";
                            else
                                error = "Sorry, failed to delete user :" + error;
                            request.getSession(false).setAttribute("error", error);
                            response.sendRedirect(request.getContextPath() + "/manage");
                            return;
                        }
                        if(request.getParameter("Send") != null){
                            if (password == null || password.isEmpty()) {
                                password = null;
                            }
                            if (email == null || email.isEmpty()) {
                                error = "E-mail cannot be empty.";
                                request.setAttribute("error", error);
                                request.setAttribute("edituser", edituser);
                                request.getRequestDispatcher("/edit.ftl").forward(request, response);
                                return;
                            }
                            User newuser = new User();
                            newuser.setName(edituser.getName());
                            byte[] ptext;
                            String value;
                            if(password != null){
                                ptext = password.getBytes(ISO_8859_1); 
                                value = new String(ptext, UTF_8);
                                newuser.setPassword(value);
                            }else{
                                newuser.setPassword(password);
                            }
                            ptext = email.getBytes(ISO_8859_1); 
                            value = new String(ptext, UTF_8);
                            newuser.setEmail(value);
                            newuser.setRegistration_time(edituser.getRegistration_time());
                            newuser.setLast_edit_time(new Date(System.currentTimeMillis()));
                            if(request.getParameter("role") != null)
                                newuser.setRole(request.getParameter("role"));
                            else
                                newuser.setRole("admin");
                            error = new UserDAOImpl().changeUser(newuser);
                            if(error.equals("OK")){
                                error = "User " + edituser.getName() + " was edited.";
                            }else{
                                error = "Sorry, failed to edit user :" + error;
                            }
                            request.getSession(false).setAttribute("error", error);
                            response.sendRedirect(request.getContextPath() + "/manage");
                            return;
                        }else{
                            session.setAttribute("error", null);
                            response.sendRedirect(request.getContextPath() + "/manage");
                        }
                    }
                }
            }else{
                session.invalidate();
                response.sendRedirect(request.getContextPath() + "/login");
            }
        }
    }
}

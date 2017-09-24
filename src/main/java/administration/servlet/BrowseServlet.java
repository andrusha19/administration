
package administration.servlet;

import administration.data.User;
import administration.sql.UserDAOImpl;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/browse")
public class BrowseServlet extends HttpServlet{
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
            if(user.getRole().equals("admin")){
                response.sendRedirect(request.getContextPath() + "/manage");
                return;
            }
            if(user.getRole().equals("user")){
                request.setAttribute("user", user);
                request.setAttribute("users", new UserDAOImpl().getUsers());
                request.getRequestDispatcher("/browse.ftl").forward(request, response);
            }else{
                session.invalidate();
                response.sendRedirect(request.getContextPath() + "/login");
            }
        }
    }
}


package administration.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/logout")
public class LogOutServlet extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(request.getSession(false) == null || request.getSession().getAttribute("user") == null)
            response.sendRedirect(request.getContextPath() + "/login");
        else{
            request.getSession().invalidate();
            response.sendRedirect(request.getContextPath() + "/login");
        }
    }
}

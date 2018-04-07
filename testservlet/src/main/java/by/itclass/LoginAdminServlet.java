package by.itclass;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/loginadmin")
public class LoginAdminServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.getWriter().println("<html>");
        resp.getWriter().println("<head>");
        resp.getWriter().println("</head>");
        resp.getWriter().println("<body>");
        resp.getWriter().println("<form action=\"/hello/loginadmin\" method=\"POST\">\n" +
                "  First name:<br>\n" +
                "  <input type=\"text\" name=\"name\"><br>\n" +
                "  Last name:<br>\n" +
                "  <input type=\"password\" name=\"password\">\n" +
                "  <input type=\"submit\" name=\"submit\">\n" +
                "</form>");
        resp.getWriter().println("</body>");
        resp.getWriter().println("</html>");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String password = req.getParameter("password");

        if (name == null || password == null) {
            resp.setStatus(500);
            resp.getWriter().println("Empty login or password");
        } else {
            if (name.equals("admin") && password.equals("123456")) {
                resp.getWriter().println("Welcome");
                //resp.addCookie(new Cookie("admin", "true"));
                req.getSession().setAttribute("admin", true);
            } else {
                resp.setStatus(500);
                resp.getWriter().println("Invalid login or password");
            }
        }

    }
}

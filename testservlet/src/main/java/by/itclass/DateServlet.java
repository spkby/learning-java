package by.itclass;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

@WebServlet("/date")
public class DateServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        boolean isAdmin = false;
        Cookie[] cookies = req.getCookies();
        for (Cookie cookie :  cookies) {
            if (cookie.getName().equals("admin") &&
                    cookie.getValue().equals("true")) {
                isAdmin = true;
                break;
            }
        }

        if (isAdmin) {
            resp.getWriter().println(new Date());
        } else {
            resp.setStatus(401);
            resp.getWriter().println("No rights to view the page");
        }
    }
}

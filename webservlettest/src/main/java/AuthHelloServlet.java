import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/admin")
public class AuthHelloServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req,
                         HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession();
        Object admin = session.getAttribute("admin");
        if (admin != null && admin instanceof String) {
            if (admin.equals("yes")) {
                resp.getWriter().println("Hello, Servlet");
                return;
            }
        }

        resp.setStatus(403);
        resp.getWriter().println("Get out");

        /*
        Cookie[] cookies = req.getCookies();
        if (cookies != null) {
            for (Cookie cookie :
                    cookies) {
                if (cookie.getName().equals("admin")) {
                    if (cookie.getValue().equals("yes")) {
                        resp.getWriter().println("Hello, Servlet");
                    } else {
                        resp.setStatus(403);
                        resp.getWriter().println("Get out");
                    }
                    return;
                }
            }
        }

        resp.setStatus(403);
        resp.getWriter().println("Get out");
*/

    }
}

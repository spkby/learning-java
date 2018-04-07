import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.HttpURLConnection;

@WebServlet("/add")
public class AddServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String a = req.getParameter("a");
        String b = req.getParameter("b");

        try {
            Integer a_i = Integer.parseInt(a);
            Integer b_i = Integer.parseInt(b);

            if (a_i + b_i == 50) {
                req.getSession().setAttribute("admin", "yes");
                /*
                Cookie cookie = new Cookie("admin", "yes");
                resp.addCookie(cookie);
                */
            }

            //Добавляем к запросу аттрибут (может быть любой Java объект),
            //который виден только сервлетам и JSP-страницам
            //(клиенту он не попадает)
            req.setAttribute("sum", a_i + b_i);

            //Перенаправляем запрос на JSP-страницу
            req.getRequestDispatcher("/WEB-INF/add_post.jsp").forward(req, resp);


        } catch (NullPointerException | NumberFormatException e) {
            resp.setStatus(HttpURLConnection.HTTP_BAD_REQUEST);
            resp.getWriter().println("Wrong params");
        }


    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //Перенаправляем запрос на JSP-страницу
        req.getRequestDispatcher("/WEB-INF/add_get.jsp").forward(req, resp);
    }
}


package by.itclass;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.getWriter().println("<html>");
        resp.getWriter().println("<body>");

        resp.getWriter().println("<form action=\"/hello/login\" method=POST>\n" +
                "    <label><b>Username</b></label>\n" +
                "    <input type=\"text\" name=\"lgn\" required>\n" +
                "\n" +
                "    <label><b>Password</b></label>\n" +
                "    <input type=\"password\" name=\"psw\" required>\n" +
                "\n" +
                "    <button type=\"submit\">Login</button>\n" +
                "</form>");

        resp.getWriter().println("</body>");
        resp.getWriter().println("</html>");

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String login = req.getParameter("lgn");
        String password = req.getParameter("psw");

        if(login == null || password == null){
            resp.getWriter().println("Empty Login or Password");
        } else {
            if (login.equals("admin") && password.equals("123456")){
                resp.getWriter().println("OK");
                resp.addCookie(new Cookie("admin","true"));
                resp.sendRedirect("/hello/date");
            } else {
                resp.setStatus(500);
                resp.getWriter().println("Invalid login or password");
            }

        }


    }
}

package by.itclass;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;

public class HelloServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req,
                         HttpServletResponse resp) throws ServletException, IOException {

        Enumeration<String> parameterNames = req.getParameterNames();

        //resp.setStatus(404);
        resp.sendRedirect("https://google.com");

        resp.getWriter().println("<html>");
        resp.getWriter().println("<head>");
        resp.getWriter().println("</head>");
        resp.getWriter().println("<body>");
        resp.getWriter().println("<h1>Not found</h1>");
        resp.getWriter().println("</body>");
        resp.getWriter().println("</html>");


    }
}

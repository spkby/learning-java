package by.itclass;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;

public class WebApp extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      //  super.doGet(req, resp);

        resp.setStatus(401);

        resp.getWriter().println("<html>");
        resp.getWriter().println("<body>");
        resp.getWriter().println("<html>");

        Enumeration<String> params = req.getParameterNames();


        while (params.hasMoreElements()){

            String next = params.nextElement();
            resp.getWriter().println("<h1>"+next+"-"+"</h1>");

        }


        //resp.getWriter().println(req.getRemoteAddr());
    }
}

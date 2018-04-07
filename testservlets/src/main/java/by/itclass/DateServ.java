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
public class DateServ extends HttpServlet{


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        boolean isAdmin = false;
        Cookie[] cookies = req.getCookies();

        if(cookies == null){
            resp.getWriter().println("Invalid");
            return;
        }
        for (Cookie c: cookies) {

            if(c.getName().equals("admin") && c.getValue().equals("true")){
            isAdmin = true;
            }
        }

        if(isAdmin){
            resp.getWriter().println(new Date());
        }else {
            resp.setStatus(401);
            resp.getWriter().println("Invalid");
        }
    }
}

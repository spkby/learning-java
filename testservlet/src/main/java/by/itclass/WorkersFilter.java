package by.itclass;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.regex.*;

@WebFilter("/workers/*")
public class WorkersFilter extends HttpFilter {

    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
        String info = req.getRequestURI();
        System.out.println(info);
        Pattern p = Pattern.compile("/workers/[0-9]+/[0-9]+"); //TODO - проверить регулярное выражение

        Matcher matcher = p.matcher(info);
        if (matcher.find()) {
            String[] parts = info.split("/");
            System.out.println(parts);
            int last = parts.length - 1;
            int prelast = parts.length - 2;
            int from = Integer.parseInt(parts[prelast]);
            int count = Integer.parseInt(parts[last]);
            req.setAttribute("from", from);
            req.setAttribute("count", count);
            chain.doFilter(req, res);
        } else {
            chain.doFilter(req, res);
        }


    }
}

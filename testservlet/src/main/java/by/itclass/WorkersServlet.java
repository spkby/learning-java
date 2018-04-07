package by.itclass;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


@WebServlet("/workers")
public class WorkersServlet extends HttpServlet {

    private Connection connection;
    private PreparedStatement preparedStatement;

    @Override
    public void init() throws ServletException {

        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/company",
                        "postgres", "123456");
            preparedStatement = connection.prepareStatement("SELECT * FROM workers OFFSET ? LIMIT ?");

        } catch (SQLException e) {
           throw new ServletException(e.getMessage());
        } catch (ClassNotFoundException e) {
            throw new ServletException(e.getMessage());
        }

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {

            String from = req.getParameter("from");
            String count = req.getParameter("count");

            int from_i = 0;
            if (from != null)
                from_i = Integer.parseInt(from);
            else {
                from_i = (int)req.getAttribute("from");
            }
            int count_i = 0;
            if (count != null) {
                count_i = Integer.parseInt(count);
            } else {
                count_i = (int)req.getAttribute("count");
            }
                //preparedStatement.setString(2, "NULL");


            preparedStatement.setInt(1, from_i);
            preparedStatement.setInt(2, count_i);
            preparedStatement.execute();

            List<String> workers = new ArrayList<>();

            ResultSet resultSet = preparedStatement.getResultSet();

            while (resultSet.next()) {
                String name = resultSet.getString("name");
                String surname = resultSet.getString("surname");
                workers.add(name + " " + surname);
            }

            //String[] strings = new String[workers.size()];
            //workers.toArray(strings);

            req.setAttribute("workers", workers);

            req.getRequestDispatcher("/WEB-INF/workers.jsp").forward(req, resp);


        } catch (SQLException | NullPointerException | NumberFormatException e) {
            resp.setStatus(500);
            resp.getWriter().println("Ошибка запроса");
        }

    }
}

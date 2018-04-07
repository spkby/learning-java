package by.itclass;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;


@WebServlet("/add")
public class WorkersAddServlet extends HttpServlet {

    private Connection connection;
    private PreparedStatement preparedStatement;

    @Override
    public void init() throws ServletException {

        try {
            //Загрузка JDBC-драйвера базы данных
            Class.forName("org.postgresql.Driver");

            //Установка соединения с БД
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/company",
                        "postgres", "123456");

            //Подготовка запроса
            preparedStatement = connection.prepareStatement("INSERT INTO workers (name, surname, birthdate) VALUES (?, ?, ?)");

        } catch (SQLException e) {
           throw new ServletException(e.getMessage());
        } catch (ClassNotFoundException e) {
            throw new ServletException(e.getMessage());
        }

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //Перенаправление запроса на JSP-страницу
        req.getRequestDispatcher("/WEB-INF/workeradd.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String name = req.getParameter("name");
        String surname = req.getParameter("surname");

        System.out.println(name);
        System.out.println(surname);

        if (name != null && !name.isEmpty() &&
                surname != null && !surname.isEmpty()) {

            try {
                preparedStatement.setString(1, name);
                preparedStatement.setString(2, surname);
                preparedStatement.setDate(3, new Date(new java.util.Date().getTime()));

                preparedStatement.executeUpdate();

                resp.sendRedirect("/hello/workers");
                //req.getRequestDispatcher("/workers").forward(req, resp);

            } catch (SQLException e) {

                resp.setStatus(500);
                resp.getWriter().println(e.getMessage());
            }

        } else {
            resp.setStatus(500);
            resp.getWriter().println("Wrong name and/or surname");
        }


    }
}

import dao.Worker;

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
    private PreparedStatement getStatement;
    private PreparedStatement insertStatement;


    @Override
    public void init() throws ServletException {

        try {
            Class.forName("org.postgresql.Driver");

            connection = DriverManager
                    .getConnection("jdbc:postgresql://localhost:5432/company24",
                            "IT Class",
                            "123456");

            getStatement = connection.prepareStatement("SELECT name, surname, birthdate FROM workers");
            insertStatement = connection.prepareStatement("INSERT INTO workers (name, surname, birthdate) VALUES (?, ?, ?)");

        } catch (Exception e) {
            throw new ServletException(e.getMessage(), e);
        }


    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {
            ResultSet resultSet = getStatement.executeQuery();

            List<Worker> workers = new ArrayList<>();
            while (resultSet.next()) {
                String name = resultSet.getString(1);
                String surname = resultSet.getString(2);
                Date birthdate = resultSet.getDate(3);

                Worker worker = new Worker();
                worker.setName(name);
                worker.setSurname(surname);
                worker.setBirthdate(birthdate);

                workers.add(worker);

            }

            req.setAttribute("workers", workers);
            req.getRequestDispatcher("/WEB-INF/workers.jsp").forward(req,resp);

        } catch (SQLException e) {
            throw new ServletException(e.getMessage(), e);
        }


    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {
        String name = req.getParameter("name");
        String surname = req.getParameter("surname");
        Date birthdate = Date.valueOf(req.getParameter("birthdate"));

        insertStatement.setString(1, name);
        insertStatement.setString(2, surname);
        insertStatement.setDate(3, birthdate);

        insertStatement.executeUpdate();

        resp.sendRedirect("/webtest24/workers");

        } catch (SQLException e) {
            throw new ServletException(e.getMessage(), e);
        }
    }
}

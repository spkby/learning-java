package by.itclass;


import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

//Данный сервлет может принимать multipart-запросы (на загрузку файла)
@MultipartConfig
@WebServlet("/admin/upload")
public class FileUploadServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/upload.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Part part = req.getPart("file");
        try (InputStream is = part.getInputStream();
             OutputStream os = new FileOutputStream("C:/" + part.getSubmittedFileName())) {
            byte[] buffer = new byte[8192];

            int count = is.read(buffer);

            while (count != -1) {
                os.write(buffer, 0, count);
                os.flush();
                count = is.read(buffer);
            }
        }

        resp.sendRedirect("/hello/admin/upload");

    }
}

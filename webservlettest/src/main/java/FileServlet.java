import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

@WebServlet("/file")
@MultipartConfig    //данная аннотация включает поддержку загрузки файлов на сервер в данном сервлете
public class FileServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/file.jsp").forward(req, resp);
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Part part = req.getPart("file");
        part.getSubmittedFileName();

        File file = new File("C:/uploadedFiles",
                part.getSubmittedFileName());


        try (InputStream is = part.getInputStream();
             FileOutputStream fileOutputStream = new FileOutputStream(file)) {

            byte[] buffer = new byte[4096];

            int count = is.read(buffer);

            while (count != -1) {
                fileOutputStream.write(buffer, 0, count);
                count = is.read(buffer);
            }
        } catch (IOException e) {
            file.delete();
        }






    }
}

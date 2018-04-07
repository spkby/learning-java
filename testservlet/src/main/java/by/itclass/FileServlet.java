package by.itclass;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

@WebServlet("/music")
public class FileServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //Тип скачиваемого файла
        resp.setContentType("text/txt");

        try (InputStream is = this.getClass().getClassLoader().getResourceAsStream("music.mp3");
             OutputStream stream = resp.getOutputStream()) {

            byte[] buffer = new byte[8192];

            int count = is.read(buffer);

            while (count != -1) {
                stream.write(buffer, 0, count);
                stream.flush();
                count = is.read(buffer);
            }
        }

    }
}

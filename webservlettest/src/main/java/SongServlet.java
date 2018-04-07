import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.concurrent.atomic.AtomicInteger;

@WebServlet("/song")
public class SongServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setContentType("audio/mpeg");

        try (InputStream is = this.getClass().getClassLoader().getResourceAsStream("song.mp3");
             OutputStream outputStream = resp.getOutputStream()) {

            byte[] buffer = new byte[4096];

            int count = is.read(buffer);

            while (count != - 1) {
                outputStream.write(buffer, 0, count);
                count = is.read(buffer);
            }

        }






    }
}

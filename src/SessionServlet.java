import com.google.gson.JsonObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;

@WebServlet(name = "SessionServlet", urlPatterns = "/api/session")
public class SessionServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        HttpSession session = request.getSession(true);
        String sessionId = session.getId();
        long lastAccessTime = session.getLastAccessedTime();

        JsonObject responseJsonObj = new JsonObject();
        responseJsonObj.addProperty("sessionID", sessionId);
        responseJsonObj.addProperty("lastAccessTime", new Date(lastAccessTime).toString());

        response.getWriter().write(responseJsonObj.toString());

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String item = request.getParameter("id");
        HttpSession session = request.getSession();


        ArrayList<String> cart = (ArrayList<String>) session.getAttribute("cart");
        if (cart == null) {
            cart = new ArrayList<>();
            cart.add(item);
            session.setAttribute("cart", cart);
        } else {
            synchronized (cart) {
                cart.add(item);
            }
        }

        response.getWriter().write(String.join(",", cart));
    }
}

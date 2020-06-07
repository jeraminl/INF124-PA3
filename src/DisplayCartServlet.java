import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.codehaus.jackson.map.util.JSONPObject;
import org.glassfish.jersey.client.ClientConfig;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.UriBuilder;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URI;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

@WebServlet(name = "DisplayCartServlet", urlPatterns = "/api/display-cart")

public class DisplayCartServlet extends HttpServlet {
    @Resource(name = "jdbc/store_db")
    private DataSource dataSource;
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        // get cart
        HttpSession session = request.getSession();
        ArrayList<String> cart = (ArrayList<String>) session.getAttribute("cart");

        JsonArray jsonArray = new JsonArray();
        for (int i = 0; i < cart.size(); ++i) {
            String id = cart.get(i);
            //System.out.println(id);

            // loading database for the given product id

            ClientConfig config = new ClientConfig();
            Client client = ClientBuilder.newClient(config);

            WebTarget target = client.target(getBaseURI());

            String jsonResponse =
                    target.path(id).
                            request().
                            get(String.class);
            JsonParser parser = new JsonParser();
            JsonObject json = (JsonObject) parser.parse(jsonResponse);

            jsonArray.add(json);



        }
        //System.out.println("cart: " + jsonArray.toString());
        out.write(jsonArray.toString());
        response.setStatus(200);
        out.close();
    }

    private static URI getBaseURI() {

        //Change the URL here to make the client point to your service.
        return UriBuilder.fromUri("http://localhost:8081/keyboardClient_war/api/products/").build();
    }
}

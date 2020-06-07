import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.glassfish.jersey.client.ClientConfig;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;

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
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URI;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "SingleProductServlet", urlPatterns = "/api/single-product")
public class SingleProductServlet extends HttpServlet {

    @Resource(name = "jdbc/store_db")
    private DataSource dataSource;
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        //System.out.println("parameter: " + request.getParameter("id"));
        String id = request.getParameter("id");


        // adding product id to the last 5 visited items
        HttpSession session = request.getSession();
        ArrayList<String> visited = (ArrayList<String>) session.getAttribute("visited");
        if (visited == null) {
            visited = new ArrayList<>();
            visited.add(id);

        } else {
            synchronized (visited) {
                if (!visited.contains(id)){
                    visited.add(id);
                }
            }
        }
        if(visited.size() > 5){
            visited.remove(0);
        }
        session.setAttribute("visited", visited);

        ClientConfig config = new ClientConfig();
        Client client = ClientBuilder.newClient(config);

        WebTarget target = client.target(getBaseURI());

        String jsonResponse =
                target.path(id).
                        request().
                        accept(MediaType.APPLICATION_JSON).
                        get(String.class);

        System.out.println(jsonResponse);
        PrintWriter out = response.getWriter();

        out.write(jsonResponse);

        out.close();
    }

    private static URI getBaseURI() {

        //Change the URL here to make the client point to your service.
        return UriBuilder.fromUri("http://localhost:8081/keyboardClient_war/api/products/").build();
    }
}

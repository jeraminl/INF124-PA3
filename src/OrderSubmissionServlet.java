import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.codehaus.jackson.map.ObjectMapper;
import org.glassfish.jersey.client.ClientConfig;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URI;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@WebServlet(name = "OrderSubmissionServlet", urlPatterns = "/api/submit-order")
public class OrderSubmissionServlet extends HttpServlet {

    @Resource(name = "jdbc/store_db")
    private DataSource dataSource;
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();



        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String address = request.getParameter("address");
        String city = request.getParameter("city");
        String state = request.getParameter("state");
        String zip = request.getParameter("zip");
        String shipMeth = request.getParameter("shipMeth");
        String price = request.getParameter("finalPrice");
        String productCart = request.getParameter("productCart");

        Order order = new Order();

        order.setFirstName(firstName);
        order.setLastName(lastName);
        order.setAddress(address);
        order.setCity(city);
        order.setState(state);
        order.setZip(zip);
        order.setEmail(email);
        order.setPhone(phone);
        order.setProductCart(productCart);
        order.setShipMeth(shipMeth);
        order.setPrice(price);

        ObjectMapper mapper = new ObjectMapper();
        String jsonString = mapper.writeValueAsString(order);
        System.out.println(jsonString);

        ClientConfig config = new ClientConfig();

        Client client = ClientBuilder.newClient(config);

        WebTarget target = client.target(getBaseURI());


        client.target(getBaseURI())
                .request(MediaType.APPLICATION_JSON)
                .post(Entity.entity(order, MediaType.APPLICATION_JSON));

        System.out.println("submitted");


        RequestDispatcher rd = request.getRequestDispatcher("/api/confirm-order");
        rd.forward(request,response);


    }

    private static URI getBaseURI() {

        //Change the URL here to make the client point to your service.
        return UriBuilder.fromUri("http://localhost:8081/keyboardClient_war/api/products/").build();
    }


}

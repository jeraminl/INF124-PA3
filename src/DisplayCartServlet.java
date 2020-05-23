import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;
import java.io.IOException;
import java.io.PrintWriter;
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
            System.out.println(id);

            // loading database for the given product id

            try {
                Connection dbcon = dataSource.getConnection();

                String query = "SELECT * FROM products WHERE id=?";
                PreparedStatement statement = dbcon.prepareStatement(query);

                statement.setString(1, id);
                ResultSet rs = statement.executeQuery();

                while (rs.next()) {
                    String product_id = rs.getString("id");
                    String product_name = rs.getString("name");
                    float product_price = rs.getFloat("price");

                    JsonObject jsonObject = new JsonObject();

                    jsonObject.addProperty("product_id", product_id);
                    jsonObject.addProperty("product_name", product_name);
                    jsonObject.addProperty("product_price", product_price);
                    System.out.println(product_id + " " + product_name);
                    jsonArray.add(jsonObject);
                }
                System.out.println(jsonArray.toString());

                rs.close();
                statement.close();
                dbcon.close();

            } catch (Exception e) {
                JsonObject jsonObject = new JsonObject();
                jsonObject.addProperty("errorMessage", e.getMessage());
                out.write(jsonObject.toString());

                response.setStatus(500);
            }
        }
        out.write(jsonArray.toString());
        response.setStatus(200);
        out.close();
    }
}

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
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

@WebServlet(name = "SessionServlet", urlPatterns = "/api/session")
public class SessionServlet extends HttpServlet {
    @Resource(name = "jdbc/store_db")
    private DataSource dataSource;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        ArrayList<String> visited = (ArrayList<String>) session.getAttribute("visited");
        response.setContentType("application/json");
        //PrintWriter out = response.getWriter();

        try {
            Connection dbcon = dataSource.getConnection();
            Statement statement = dbcon.createStatement();
            String query = "SELECT * from products";
            ResultSet rs = statement.executeQuery(query);
            JsonArray jsonArray = new JsonArray();

            while (rs.next()){
                if (visited.contains(rs.getString("id"))){
                    String product_id = rs.getString("id");
                    String product_name = rs.getString("name");
                    float product_price = rs.getFloat("price");
                    String product_category = rs.getString("category");

                    JsonObject jsonObject = new JsonObject();

                    jsonObject.addProperty("product_id", product_id);
                    jsonObject.addProperty("product_name", product_name);
                    jsonObject.addProperty("product_price", product_price);
                    jsonObject.addProperty("product_category", product_category);

                    jsonArray.add(jsonObject);
                }

            }

            //out.write(jsonArray.toString());

            request.setAttribute("visitedData", jsonArray);
            response.setStatus(200);

            rs.close();
            statement.close();
            dbcon.close();

        } catch (Exception e){
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("errorMessage", e.getMessage());
            //out.write(jsonObject.toString());

            response.setStatus(500);
        }

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

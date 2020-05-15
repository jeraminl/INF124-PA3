import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

@WebServlet(name = "SingleProductServlet", urlPatterns = "/api/single-product")
public class SingleProductServlet extends HttpServlet {

    @Resource(name = "jdbc/store_db")
    private DataSource dataSource;
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        System.out.println("parameter: " + request.getParameter("id"));
        String id = request.getParameter("id");
        PrintWriter out = response.getWriter();


        try {
            Connection dbcon = dataSource.getConnection();

            String query = "SELECT * FROM products WHERE id=?";
            PreparedStatement statement = dbcon.prepareStatement(query);

            statement.setString(1,id);
            ResultSet rs = statement.executeQuery();
            JsonArray jsonArray = new JsonArray();

            while (rs.next()){
                String product_id = rs.getString("id");
                String product_description = rs.getString("description");
                String product_size = rs.getString("size");
                String product_name = rs.getString("name");
                float product_price = rs.getFloat("price");
                String product_switch = rs.getString("switch");
                String product_category = rs.getString("category");


                JsonObject jsonObject = new JsonObject();

                jsonObject.addProperty("product_id", product_id);
                jsonObject.addProperty("product_name", product_name);
                jsonObject.addProperty("product_description", product_description);
                jsonObject.addProperty("product_size", product_size);
                jsonObject.addProperty("product_price", product_price);
                jsonObject.addProperty("product_switch", product_switch);
                jsonObject.addProperty("product_category", product_category);

                jsonArray.add(jsonObject);
            }

            out.write(jsonArray.toString());
            response.setStatus(200);

            rs.close();
            statement.close();
            dbcon.close();

        } catch (Exception e){
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("errorMessage", e.getMessage());
            out.write(jsonObject.toString());

            response.setStatus(500);
        }
        out.close();
    }
}

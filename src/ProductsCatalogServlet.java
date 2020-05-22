import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "ProductCatalogServlet", urlPatterns = "/api/fullCatalog")
public class ProductsCatalogServlet extends HttpServlet {
    @Resource(name = "jdbc/store_db")
    private DataSource dataSource;


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession();
        //System.out.println("visited: " + (String)session.getAttribute("visited"));

        try {
            Connection dbcon = dataSource.getConnection();
            Statement statement = dbcon.createStatement();
            String query = "SELECT * from products";
            ResultSet rs = statement.executeQuery(query);
            RequestDispatcher rd = request.getRequestDispatcher("/api/session");
            rd.include(request,response);


            JsonArray total = new JsonArray();

            JsonArray jsonArray = new JsonArray();

            while (rs.next()){
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


            JsonArray visited = (JsonArray) request.getAttribute("visitedData");

            total.add(jsonArray);
            total.add(visited);

            out.write(total.toString());

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

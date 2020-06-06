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

@WebServlet(name = "ZipServlet", urlPatterns = "/api/zipcode")
public class ZipServlet extends HttpServlet {

    @Resource(name = "jdbc/store_db")
    private DataSource dataSource;
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        String zip = request.getParameter("zip");
        PrintWriter out = response.getWriter();
        //System.out.println("parameter HASJDKAHSBJKDSAJKH: " + request.getParameter("zip"));

        // loading database for the given product zipcode
        try {
            Connection dbcon = dataSource.getConnection();

            String query = "SELECT * FROM zipTable WHERE zip=?";
            PreparedStatement statement = dbcon.prepareStatement(query);

            statement.setString(1, zip);
            ResultSet rs = statement.executeQuery();
            JsonArray jsonArray = new JsonArray();

            while (rs.next()){
                String state = rs.getString("state");
                //System.out.println(state);
                JsonObject jsonObject = new JsonObject();
                jsonObject.addProperty("state", state);
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

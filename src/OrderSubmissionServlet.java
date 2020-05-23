import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
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


        try {
            Connection dbcon = dataSource.getConnection();


            if (dbcon != null) {
                System.out.println("Connection established!!");
                System.out.println();
            }

            String query = "INSERT INTO orders (firstName, lastName, email, phone, address, city, state, " +
                    "zip, productID, ship, total)" +
                    "VALUES (?,?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement statement = dbcon.prepareStatement(query);

            statement.setString(1,firstName);
            statement.setString(2,lastName);
            statement.setString(3,email);
            statement.setString(4,phone);
            statement.setString(5,address);
            statement.setString(6,city);
            statement.setString(7,state);
            statement.setString(8,zip);
            statement.setString(9,productCart);
            statement.setString(10,shipMeth);
            statement.setString(11,price);

            statement.execute();

            statement.close();
            dbcon.close();

        } catch (Exception e){
            System.err.println("Got an exception!");
            System.err.println(e.getMessage());

        }

        RequestDispatcher rd = request.getRequestDispatcher("/api/confirm-order");
        rd.forward(request,response);


    }


}

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "OrderConfirmationServlet", urlPatterns = "/api/confirm-order")
public class OrderConfirmationServlet extends HttpServlet {
    @Resource(name = "jdbc/store_db")
    private DataSource dataSource;
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");


        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String address = request.getParameter("address");
        String city = request.getParameter("city");
        String state = request.getParameter("state");
        String zip = request.getParameter("zip");
        String shipMeth = request.getParameter("shipMeth");

        String billingFirstName = request.getParameter("billingFirstName");
        String billingLastName = request.getParameter("billingLastName");
        String ccNum = request.getParameter("ccNum");

        String billingAddress = request.getParameter("billingAddress");
        String billingCity = request.getParameter("billingCity");
        String billingState = request.getParameter("billingState");
        String billingZip = request.getParameter("billingZip");

        String productCart = request.getParameter("productCart");
        String price = request.getParameter("finalPrice");





        PrintWriter out = response.getWriter();
        out.write("<html><body><div style='text-align: center;'>");
        out.write("<p style='color: green; font-size: large;'>Congratulations! <span style='text-transform: capitalize;'>" + firstName + "</span>, your order is complete!</p>");

        out.write("<p>First Name:" + firstName + "</p>");
        out.write("<p>Last Name:" + lastName + "</p>");
        out.write("<p>Email:" + email + "</p>");
        out.write("<p>Phone:" + phone + "</p>");
        out.write("<p>Address:" + address + "</p>");
        out.write("<p>City:" + city + "</p>");
        out.write("<p>State:" + state + "</p>");
        out.write("<p>Zip:" + zip + "</p>");
        out.write("<p>Shipping Method:" + shipMeth + "</p>");
        out.write("<p>Billing First Name:" + billingFirstName + "</p>");
        out.write("<p>Billing Last Name:" + billingLastName + "</p>");
        out.write("<p>CC:" + ccNum + "</p>");
        out.write("<p>Billing Address:" + billingAddress + "</p>");
        out.write("<p>Billing City:" + billingCity + "</p>");
        out.write("<p>Billing State:" + billingState + "</p>");
        out.write("<p>Billing Zip:" + billingZip + "</p>");


        out.write("<p>product IDs:" + productCart + "</p>");
        out.write("<p>Final Price:" + price + "</p>");


        out.write("</div></body></html>");
        out.close();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}

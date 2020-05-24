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

        out.write("<html class=\"no-js\" lang=\"\">\n" +
                "  <head>\n" +
                "    <meta charset=\"utf-8\" />\n" +
                "    <title>Order Details</title>\n" +
                "    <meta name=\"description\" content=\"\" />\n" +
                "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\" />\n" +
                "\n" +
                "    <link rel=\"stylesheet\" type=\"text/css\" href=\"../css/order_details.css\" />\n" +
                "    <link rel=\"stylesheet\" type=\"text/css\" href=\"../css/header.css\" />\n" +
                "    <meta name=\"theme-color\" content=\"#fafafa\" />\n" +
                "  </head>\n" +
                "  <body>\n" +
                "    <div class=\"flex-container sansserif\">\n" +
                "      <div class=\"website-header\">\n" +
                "        <ul>\n" +
                "          <li class=\"header-image\">\n" +
                "            <img\n" +
                "              src=\"../img/blank_keyboard.png\"\n" +
                "              alt=\"Hobby Logo\"\n" +
                "              height=\"160\"\n" +
                "              width=\"400\"\n" +
                "            />\n" +
                "          </li>\n" +
                "          <li class=\"header-text\">KEYBOARD<br />STORE</li>\n" +
                "        </ul>\n" +
                "      </div>\n" +
                "      <div>\n" +
                "        <h2 class=\"details-title\">Completed Order Details</h2>\n" +
                "      </div>\n" +
                "      <div>\n" +
                "        <div class=\"grid-container\">" +
                "<p>First Name: " + firstName + "</p>" +
                "<p>Last Name: " + lastName + "</p>" +
                "<p>Email: " + email + "</p>" +
                "<p>Phone: " + phone + "</p>" +
                "<p>Address: " + address + "</p>" +
                "<p>City: " + city + "</p>" +
                "<p>Zip:" + zip + "</p>" +
                "<p>Shipping Method:" + shipMeth + "</p>" +
                "<p>Billing First Name:" + billingFirstName + "</p>" +
                "<p>Billing First Name:" + billingFirstName + "</p>" +
                "<p>CC:" + ccNum + "</p>" +
                "<p>Billing Address:" + billingAddress + "</p>" +
                "<p>Billing City:" + billingCity + "</p>" +
                "<p>Billing State:" + billingState + "</p>" +
                "<p>Billing Zip:" + billingZip + "</p>" +
                "<p>product IDs:" + productCart + "</p>" +
                "<p>Final Price:" + price + "</p>" +
                "</div>\n" +
                "        <a href=\"../index.html\"><button>TO HOME PAGE</button></a>\n" +
                "      </div>\n" +
                "    </div>\n" +
                "  </body>\n" +
                "</html>");
        out.close();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}

<%--
  Created by IntelliJ IDEA.
  User: mattl
  Date: 5/27/2020
  Time: 11:31 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page import="java.sql.*,
    com.google.gson.JsonArray,
    com.google.gson.JsonObject,
    com.google.gson.JsonParser,
    org.glassfish.jersey.client.ClientConfig,
    javax.annotation.Resource,
    javax.servlet.ServletException,
    javax.servlet.annotation.WebServlet,
    javax.servlet.http.HttpServlet,
    javax.servlet.http.HttpServletRequest,
    javax.servlet.http.HttpServletResponse,
    javax.servlet.http.HttpSession,
    javax.sql.DataSource,
    javax.ws.rs.client.Client,
    javax.ws.rs.client.ClientBuilder,
    javax.ws.rs.client.WebTarget,
    javax.ws.rs.core.UriBuilder,
    java.io.IOException,
    java.io.PrintWriter,
    java.net.URI,
    java.util.ArrayList"

%>
<%@ page import="com.google.gson.JsonElement" %>

<%

    ArrayList<String> visited = (ArrayList<String>) session.getAttribute("visited");
    System.out.println("visited: " + visited);

    ClientConfig clientConfig = new ClientConfig();
    Client client = ClientBuilder.newClient(clientConfig);

    WebTarget target = client.target(getBaseURI());

    String jsonResponse =
            target.request().get(String.class);


    JsonParser parser = new JsonParser();
    JsonArray data = (JsonArray) parser.parse(jsonResponse);

    JsonArray office = new JsonArray();
    JsonArray gaming = new JsonArray();
    JsonArray professional = new JsonArray();
    JsonArray recent = new JsonArray();

    for (JsonElement el : data){
        JsonObject product = el.getAsJsonObject();
        //System.out.println((product.get("category").getAsString().equals("Office")));
        if (product.get("category").getAsString().equals("Office")){
            office.add(el);
        }
        else if (product.get("category").getAsString().equals("Gaming")){
            gaming.add(el);
        }
        else{
            professional.add(el);
        }
        if (visited != null){
            for ( String id : visited){
                if (product.get("id").getAsString().equals(id)){
                    recent.add(el);
                }
            }
        }

    }



    System.out.println("recent: " + recent);


%>

<head>
    <meta charset="utf-8" />
    <title></title>
    <meta name="description" content="" />
    <meta name="viewport" content="width=device-width, initial-scale=1" />

    <link rel="stylesheet" type="text/css" href="css/index.css" />
    <link rel="stylesheet" type="text/css" href="css/header.css" />
    <script src="js/index.js"></script>
    <meta name="theme-color" content="#fafafa" />
</head>

<body>
<div class="flex-container sansserif">
    <div class="website-header">
        <ul>
            <li class="header-image">
                <img
                        src="./img/blank_keyboard.png"
                        alt="Hobby Logo"
                        height="160"
                        width="400"
                />
            </li>
            <li class="header-text">KEYBOARD<br />STORE</li>
        </ul>
    </div>
    <div>
        <h2>Recently Viewed Items</h2>
        <div id="recentProduct" class="grid-container">
            <%  for (JsonElement el: recent) { %>
            <div class='grid-item'>
                <a href="product.html?Id=<%= el.getAsJsonObject().get("id").getAsString()%>"
                   name="<%= el.getAsJsonObject().get("id").getAsString()%>"
                   style="color:black;text-decoration:none;"
                >
                    <%= el.getAsJsonObject().get("name").getAsString() %>
                    <div class="grid-img">
                        <img src="./img/<%=el.getAsJsonObject().get("id").getAsString()%>/0.jpg" class="photo">
                    </div>
                    <p><%=el.getAsJsonObject().get("price").getAsString()%> </p>
                </a>
            </div>

            <% } %>
        </div>
    </div>
    <div>
        <h2>Our Products</h2>
        <h3>Office</h3>
        <div id="productListOffice" class="grid-container">

            <%  for (JsonElement el: office) { %>
                <div class='grid-item'>
                    <a href="product.html?Id=<%= el.getAsJsonObject().get("id").getAsString()%>"
                    name="<%= el.getAsJsonObject().get("id").getAsString()%>"
                       style="color:black;text-decoration:none;"
                    >
                        <%= el.getAsJsonObject().get("name").getAsString() %>
                        <div class="grid-img">
                            <img src="./img/<%=el.getAsJsonObject().get("id").getAsString()%>/0.jpg" class="photo">
                        </div>
                        <p><%=el.getAsJsonObject().get("price").getAsString()%> </p>
                    </a>
                </div>

            <% } %>



        </div>
        <h3>Gaming</h3>
        <div id="productListGaming" class="grid-container">
            <%  for (JsonElement el: gaming) { %>
            <div class='grid-item'>
                <a href="product.html?Id=<%= el.getAsJsonObject().get("id").getAsString()%>"
                   name="<%= el.getAsJsonObject().get("id").getAsString()%>"
                   style="color:black;text-decoration:none;"
                >
                    <%= el.getAsJsonObject().get("name").getAsString() %>
                    <div class="grid-img">
                        <img src="./img/<%=el.getAsJsonObject().get("id").getAsString()%>/0.jpg" class="photo">
                    </div>
                    <p><%=el.getAsJsonObject().get("price").getAsString()%> </p>
                </a>
            </div>

            <% } %>
        </div>

        <h3>Professional</h3>
        <div id="productListProfessional" class="grid-container">
            <%  for (JsonElement el: professional) { %>
            <div class='grid-item'>
                <a href="product.html?Id=<%= el.getAsJsonObject().get("id").getAsString()%>"
                   name="<%= el.getAsJsonObject().get("id").getAsString()%>"
                   style="color:black;text-decoration:none;"
                >
                    <%= el.getAsJsonObject().get("name").getAsString() %>
                    <div class="grid-img">
                        <img src="./img/<%=el.getAsJsonObject().get("id").getAsString()%>/0.jpg" class="photo">
                    </div>
                    <p><%=el.getAsJsonObject().get("price").getAsString()%> </p>
                </a>
            </div>

            <% } %>
        </div>
    </div>

    <div>
        <h1>About us</h1>
        <p>
            We are Gary Tan (50854525), Jeramin Leong (12768778), David Bailey
            Rodriguez (61923528) and we sell all different kinds of keyboards and
            accessories. No need to go to the store, especially when we should all
            be staying home. We have different keyboards like from mechanical,
            gaming, RGB, and even wireless keyboards with bluetooth.
        </p>
        <h1>product sources:</h1>
        <ul>
            <li>
                https://www.amazon.com/Dell-Keyboard-KB216-Multimedia-580-ADHK/dp/B01B8OSVCK/ref=sr_1_1?dchild=1&keywords=dell+kb216&qid=1587519627&sr=8-1
            </li>
            <li>
                https://www.amazon.com/Dell-KM636-Wireless-Keyboard-5WH32/dp/B01FM8H22S/ref=sr_1_1?dchild=1&keywords=km636&qid=1587519698&sr=8-1
            </li>
            <li>
                https://www.amazon.com/Redragon-K502-Keyboard-Illuminated-Improved/dp/B00LSGKEC4
            </li>
            <li>
                https://www.amazon.com/Razer-Cynosa-Chroma-Gaming-Keyboard/dp/B075KMZ4MX
            </li>
            <li>
                https://www.amazon.com/Redragon-K551-Mechanical-Keyboard-Construction/dp/B016M91SS0
            </li>
            <li>
                https://www.amazon.com/Redragon-K552-Mechanical-Keyboard-Equivalent/dp/B016MAK38U
            </li>
            <li>
                https://www.amazon.com/Redragon-S101-Keyboard-Ergonomic-Programmable/dp/B00NLZUM36
            </li>
            <li>
                https://www.amazon.com/CORSAIR-RAPIDFIRE-Mechanical-Gaming-Keyboard/dp/B01D8H09TS
            </li>
            <li>
                https://www.amazon.com/HELLO-GANSS-Mechanical-Keyboard-Programble/dp/B081W3RRSJ
            </li>
            <li>
                https://www.amazon.com/HyperX-Alloy-Elite-Software-Controlled-Customization/dp/B077YDJWZR
            </li>
            <li>
                https://www.amazon.com/HyperX-Alloy-FPS-Pro-Ultra-Compact/dp/B074F5L8GQ
            </li>
            <li>
                https://www.amazon.com/Cooler-Master-Tenkeyless-Mechanical-Removable/dp/B07N4G6NLF
            </li>
            <li>
                https://www.amazon.com/Das-Keyboard-Professional-Cherry-Mechanical/dp/B00JI2APZQ
            </li>
            <li>
                https://www.amazon.com/HELLO-GANSS-Mechanical-Gaming-Keyboard/dp/B07ZNY3L16
            </li>
            <li>
                https://www.amazon.com/Logitech-Backlit-Mechanical-Gaming-Keyboard/dp/B01CDYB8AG
            </li>
        </ul>

        <h1>website code guidance from:</h1>
        <ul>
            <li>https://www.w3schools.com/</li>
        </ul>
    </div>
</div>
</body>
<%!
    private static URI getBaseURI() {

        //Change the URL here to make the client point to your service.
        return UriBuilder.fromUri("http://localhost:8081/keyboardClient_war/api/products/").build();
    }

%>
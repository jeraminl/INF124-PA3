<%--
  Created by IntelliJ IDEA.
  User: mattl
  Date: 5/27/2020
  Time: 11:31 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page import="java.sql.*" %>

<%
    Class.forName("com.mysql.jdbc.Driver").newInstance();
    Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/store_db?useSSL=false&allowPublicKeyRetrieval=true",
            "mytestuser","mypassword");

    Statement selectOffice = connection.createStatement();
    ResultSet resultOffice = selectOffice.executeQuery("Select * from products where category = 'Office'");
    ResultSetMetaData metadataOffice = resultOffice.getMetaData();

    Statement selectGaming = connection.createStatement();
    ResultSet resultGaming = selectGaming.executeQuery("Select * from products where category = 'Gaming'");
    ResultSetMetaData metadataGaming = resultGaming.getMetaData();

    Statement selectPro = connection.createStatement();
    ResultSet resultPro = selectPro.executeQuery("Select * from products where category = 'Professional'");
    ResultSetMetaData metadataPro = resultPro.getMetaData();

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
        <div id="recentProduct" class="grid-container"></div>
    </div>
    <div>
        <h2>Our Products</h2>
        <h3>Office</h3>
        <div id="productListOffice" class="grid-container">

            <%  while (resultOffice.next()) { %>
                <div class='grid-item'>
                    <a href="product.html?Id=<%= resultOffice.getString("id")%>"
                    name="<%= resultOffice.getString("id")%>"
                       style="color:black;text-decoration:none;"
                    >
                        <%= resultOffice.getString("name") %>
                        <div class="grid-img">
                            <img src="./img/<%=resultOffice.getString("id")%>/0.jpg" class="photo">
                        </div>
                        <p><%=resultOffice.getString("price")%> </p>
                    </a>
                </div>

            <% } %>

        </div>
        <h3>Gaming</h3>
        <div id="productListGaming" class="grid-container">
            <%  while (resultGaming.next()) { %>
            <div class='grid-item'>
                <a href="product.html?Id=<%= resultGaming.getString("id")%>"
                   name="<%= resultGaming.getString("id")%>"
                   style="color:black;text-decoration:none;"
                >
                    <%= resultGaming.getString("name") %>
                    <div class="grid-img">
                        <img src="./img/<%=resultGaming.getString("id")%>/0.jpg" class="photo">
                    </div>
                    <p><%=resultGaming.getString("price")%> </p>
                </a>
            </div>

            <% } %>
        </div>

        <h3>Professional</h3>
        <div id="productListProfessional" class="grid-container">
            <%  while (resultPro.next()) { %>
            <div class='grid-item'>
                <a href="product.html?Id=<%= resultPro.getString("id")%>"
                   name="<%= resultPro.getString("id")%>"
                   style="color:black;text-decoration:none;"
                >
                    <%= resultPro.getString("name") %>
                    <div class="grid-img">
                        <img src="./img/<%=resultPro.getString("id")%>/0.jpg" class="photo">
                    </div>
                    <p><%=resultPro.getString("price")%> </p>
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

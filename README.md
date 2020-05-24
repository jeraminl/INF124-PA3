# PA3

Group Members:

- Gary Tan (50854525)
- Jeramin Leong (12768778)
- David Bailey Rodriguez (61923528)

URL: UNABLE TO DEPLOY ON OPENLAB

Layout: Our homepage features a logo at the top of the screen, followed by 3 sections that seperate the keyboards our retailer sells by category, Office, Gaming, and Professional. Each product we have available are displayed within their own product windows, and lists the name and price of the product. The product windows also hover when you mouse over a product in order to assist the user in identifying which product they will explore. Also, we have an About Us section at the bottom of the page.

When you click into any given product, you will be redirect to a product page that lists more information about the product, including more pictures that one can hover or click into. User can add items to cart with the button below and move to the checkout page or go back to the home page to add other products.

Satisfied Requirement Locations:

1. This requirement is achieved with SessionServlet and ProductsCatalogServlet. The ProductsCatalogServlet will call the database to load all the products into their respective category. At the same time, it will also use the "include" function of the request dispatcher to call the SessionServlet to get the info on up to 5 latest products that the user viewed in this session (it will appear in alphabetical order rather than order of visitation. However it will definitely only show the 5 latest ones).
2. The product details page are handled by the SingleProductServlet and Session Sevlet. The SingleProductServlet will load all the info necessary for the product. The SessionServlet will handle adding items to cart using httpsession when the user hits the "add to cart" button.
3. The checkout page can be accessed in any detailed product page with the 'checkout button' at the bottom of the page. The items in the shopping cart will display on the right column of the order form by using the DisplayCartServlet. The Tax and Zip servlet will handle the ajax functionality (updating shipping state based on zip and tax based on the state). Once all the info has been filled and submitted, the OrderSubmissionServlet will handle inserting the info into the database then it will use the requestDispatcher's forward function to bring the user to the OrderConfirmation Servlet which will print out a page will all the order details.

Other usability note:

1. clicking the logo at the top when in the product page will bring you back to index
2. clicking on photos in the product page will pop up a modal

Local Deployment requirement:

MySQL 5.7 - edit credentials in META-INF/context.xml
Tomcat 8.5.53
Maven
deployed through intellij tomcat integration

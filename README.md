# PA4

Group Members:

- Gary Tan (50854525)
- Jeramin Leong (12768778)
- David Bailey Rodriguez (61923528)

URL: UNABLE TO DEPLOY ON OPENLAB

Layout: Our homepage features a logo at the top of the screen, followed by 3 sections that seperate the keyboards our retailer sells by category, Office, Gaming, and Professional. Each product we have available are displayed within their own product windows, and lists the name and price of the product. The product windows also hover when you mouse over a product in order to assist the user in identifying which product they will explore. Also, we have an About Us section at the bottom of the page.

When you click into any given product, you will be redirect to a product page that lists more information about the product, including more pictures that one can hover or click into. User can add items to cart with the button below and move to the checkout page or go back to the home page to add other products.

Satisfied Requirement Locations:

1. This requirement is achieved in the index.jsp
2. This requirement is achieved through the webservice application in the Resource class. For our website, we only need to use the GET and POST method with different MEDIATYPEs to achieve all the functionality

3. We have replaced all direct database interactions to use the webservice instead (see SingleProductServlet for example.)

RESTful Documentation:

- Methods in the Resources class

1. GET -> produces json.

- path: {localhost}/{war}/api/products/
- sample response:
  [
  {
  "id": 0,
  "name": "Dell Wired Keyboard KB216",
  "description": null,
  "size": null,
  "price": 23.0,
  "keySwitch": null,
  "category": "Office"
  },...... ]

2. GET singe product -> produces json.

- path: {localhost}/{war}/api/products/{id}
- sample response:
  {
  "id": 1,
  "name": "Dell Wireless Keyboard and Mouse KM636",
  "description": "Contemporary wireless keyboard and mouse for office and personal environments.",
  "size": "Full",
  "price": 33.94,
  "keySwitch": "Membrane",
  "category": "Office"
  }

3. POST for order submission -> consumes form

- path: {localhost}/{war}/api/products/
- sample request:
  firstName:post
  lastName:man
  address:1234 ant
  city:irvine
  state:CA
  zip:92606
  email:hi@example.com
  phone:000-000-0000
  productCart:1
  shipMeth:6
  billingFirstName:post
  billingLastName:man
  ccNum:0000 0000 0000 0000
  billingAddress:1234 ant
  billingCity:irvine
  billingState:ca
  billingZip:92606
  finalPrice:"99.99"
- sample response:
  order added successfully

4. POST for order submission -> consumes JSON

- path: {localhost}/{war}/api/products/
- sample request: (order object converted to JSON)
- sample response: order added successfully

Other usability note:

1. clicking the logo at the top when in the product page will bring you back to index
2. clicking on photos in the product page will pop up a modal

Local Deployment requirement:

MySQL 5.7 - edit credentials in META-INF/context.xml
Tomcat 8.5.53
Maven
deployed through intellij tomcat integration

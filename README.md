# PA2

Group Members:

- Gary Tan (50854525)
- Jeramin Leong (12768778)
- David Bailey Rodriguez (61923528)

URL: http://circinus-42.ics.uci.edu:8081

Layout: Our homepage features a logo at the top of the screen, followed by 3 sections that seperate the keyboards our retailer sells by category, Office, Gaming, and Professional. Each product we have available are displayed within their own product windows, and lists the name and price of the product. The product windows also hover when you mouse over a product in order to assist the user in identifying which product they will explore. Also, we have an About Us section at the bottom of the page.

When you click into any given product, you will be redirect to a product page that lists more information about the product, including more pictures that one can hover or click into. At the bottom of the page there is an Order Form for users to submit orders. Once filled and all fields are entered appropriately, the user can submit the form and this will prompt an email window to appear with all of the appropriate fields filled out.

Satisfied Requirement Locations:

1. fill*product_info.php, get_product*\*.php
2. confirmation.php
3. confirmation.php
4. - Tax rate updates as zip code changes
   - shipping zip code automatically updates the shipping state

MySQL credentials:
dbname = 'store_db'
user = 'root'
password = 'mytest'

Other usability note:

1. clicking the logo at the top when in the product page will bring you back to index
2. clicking on photos in the product page will pop up a modal



### DataSource
- `WebContent/META-INF/context.xml` contains a DataSource, with database information stored in it.
`WEB-INF/web.xml` registers the DataSource to name jdbc/store_db, which could be referred to anywhere in the project.

- a private DataSource reference dataSource is created with `@Resource` annotation. It is a reference to the DataSource `jdbc/store_db` we registered in `web.xml`

- To use DataSource, you can create a new connection to it by `dataSource.getConnection()`, and you can use the connection as previous examples.

# BookStoreApp
The capstone project of the Nanodegree (keeping track of the inventory of a bookstore)

The app contains activities for the user to:

- Add Inventory
- See Product Details
- Edit Product Details
- See a list of all inventory from a Main Activity

The user navigates between the activities using Up/Back Navigation and Intents.

In the Main Activity/Fragment, each list item displays the Product Name, Price, and Quantity.

Each list item also contains a SaleButton that reduces the total quantity of that particular product by one (no negative quantities are displayed). 

The Product Detail Layout displays the Product Name, Price, Quantity, Supplier Name, and Supplier Phone Number that's stored in the database.
The Product Detail Layout also contains buttons that increase and decrease the available quantity displayed (zero is the lowest amount). 
The Product Detail Layout contains a button to delete the product record entirely and a button to order from the supplier (intent to a phone app using the Supplier Phone Number stored in the database).

When there is no information to display in the database, the layout displays a TextView with instructions on how to populate the database.


# RestaurantManager


This is a desktop aplication with a very basig Gui responsible for handling a restaurant . The aplication can handle 3 actors : waiter , administrator , chef , each one having different 
atributes .   
Model and Desing :   
Three lair architecture was used in order to manage the distribution of the classes in packages. The first layer consists of the Presentation Layer which defines the user interface . The next layer is the Business witch contains the classes that encapsulate the application logic. And finally a DataLayer responsible  for storing  the state of the menu in a serialized form and later load it back into the program memory.
   
Design patters that were used :   
Composite Pattern, when creating the menu. First of all, a menu item can be of two types, a BaseProduct which is a simple product by itself containing a name a quantity, and a price ( Exemple: Fries 300g  45 dollars). The second type is represented by the CompositeProduct which is formed from more BaseProducts  (for example Greek Salat which contains cheese, tomatoes, and salat).  
    
Observer Pattern: that notifies the chef when a client orders a CompositeProduct ( the logic is that only those require cooking ).
  
Other features: The program comes with a JavaDoc to explain the classes and a jar file for an easy run of the application. The menu is serialized each time the application closes ( that 
was a building requirement ).  
 
Administrator :   
It is the only one capable of modifying the menu. It can add a new product, or change an already existing one, or simply delete an item from the menu. 
The logic here was quite simple, if the product that was inserted already exists, the changes will be made to it and saved back in the file, if the product does not exist,
it will be added. // insert picture here

Waiter :  
First of all the waiter chooses a table number, and then it starts adding items to the order. When the waiter wants it can select
generate the bill, which will tell the total cost of all the items that were ordered and will also generate a text file with the details of the order.

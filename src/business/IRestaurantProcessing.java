package business;

import java.util.LinkedHashMap;
import java.util.LinkedList;

public interface IRestaurantProcessing {


    /**
     * @pre  item!= null
     *  list.size@ost = lise.size@pre+1
     */
    public void addItem(MenuItem item);

    /**
     * @pre  item!= null
     * @post item@pre.price != item@post.price || item@pre.getDescription != item@post.getDescription()
     * @param item
     */
    public void editItem(MenuItem item);

    /**
     * @pre  item!= null
     *  list.size@post = lise.size@pre-1
     * @param item
     */
    public void deleteItem(MenuItem item);


    /**
     * @pre  order!= null
     * @post orderMap.get(order)@post != ororderMap.get(order)@pre
     * @param order,items
     */
    public void addOrder(Order order, LinkedList<MenuItem> items);


    /**
     * @pre order!=null, menuItem!=null
     *
     * @post orderMap.get(order).size@post = orderMap.get(order).size@pre+1
     * @param order
     * @param menuItem
     */
    public void addToOrder(Order order , MenuItem menuItem);


    /**
     * @post Order!=null;
     * @pre returned price !=null;
     * @param order
     *
     */
    public double computePrice(Order order);

    /**
     * @post product !=null;
     * @pre BillIndexTabelnr.txt generated
     * @param product
     */


}

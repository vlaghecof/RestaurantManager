package business;

import presentation.ChefInterface;

import javax.swing.*;
import java.io.Serializable;
import java.security.PublicKey;
import java.util.*;


/**
 * @invariat menu is empty impies no menu yet set ;
 *
 */
public class Restaurant extends Observable implements IRestaurantProcessing, Serializable {


    public Observer observer ;
    public ChefInterface chefInterface;

    public static LinkedList<MenuItem> menu;
    public LinkedList<Order> orders;

    public static  Map<Order, LinkedList<MenuItem>> orderMap;

    private IRestaurantProcessing employee;

    public LinkedList<MenuItem> getMenu() {
        return menu;
    }

    public void setMenu(LinkedList<MenuItem> menu) {
        this.menu = menu;
    }

    public Restaurant() {
        orderMap = new HashMap<Order, LinkedList<MenuItem>>();
        //menu = new LinkedList<MenuItem>();
        observer= new ChefInterface();

    }

    // @Requires("item != null")
    //   @Ensures("menu.size() == old(menu.size()) +1")
    @Override
    public void addItem(MenuItem item) {
        assert item!=null;
        int preSize = menu.size();
        menu.add(item);
        int postSize =menu.size();
        assert postSize-preSize==1;

    }

    // @Requires("item != null")
    // @Ensures("item != old(item)")
    @Override
    public void editItem(MenuItem item) {
       assert item!=null;
        String itemName = null;
        StringBuilder itemDescription = null;

        for(int i = 0; i < menu.size(); i++) {
            if(menu.get(i).getInfo().equals(item.getInfo())) {
                 itemName= item.getProdName();//save them before the change
                 itemDescription = item.getDescription();
                menu.get(i).editItem(item);
            }
        }


        assert (itemName.equals(item.getInfo())||itemDescription.equals(item.getDescription()));
    }


    // @Requires("item != null")
    //   @Ensures("menu.size() == old(menu.size()) -1")
    @Override
    public void deleteItem(MenuItem item) {

        assert item!=null;
        int preSize=  menu.size();
        int postSize ;
        for(int i = 0; i < menu.size(); i++) {
            if(menu.get(i).getInfo().equals(item.getInfo())) {
                menu.remove(i);
            }
        }
        postSize=menu.size();
        assert postSize-preSize==1;

    }
    // @Requires("order != null")
    //   @Ensures(orderMap.get(order)!=null")
    @Override
    public void addOrder(Order order, LinkedList<MenuItem> items) {

        assert  order!=null;

         LinkedList<MenuItem> postCond = orderMap.get(order);
        orderMap.put(order, items);
        LinkedList<MenuItem> preCond = orderMap.get(order);

        assert postCond!=preCond;
    }
//@Requires("menuItem != null",order!=null)
 // @Ensures("orderMap.get(order).size() == old(orderMap.get(order).size() +1")
    @Override
    public void addToOrder(Order order, MenuItem menuItem) {

        assert order!=null;
        assert menuItem!=null;

        int postSize=0;
        int preSize;
        if(orderMap.get(order)!=null) {
             postSize = orderMap.get(order).size();
        }

        LinkedList<MenuItem> itemList = orderMap.get(order);
        itemList.add(menuItem);
        orderMap.put(order,itemList);
        if(menuItem instanceof CompositeProduct)
            notifyObservers(menuItem);

        preSize=orderMap.get(order).size();
        assert postSize-preSize==1;
    }

    public void notifyObservers(MenuItem item )
    {
        observer.update(this,item);

    }

    //@Requires("order!=null")
    @Override
    public double computePrice(Order order) {
       assert order!=null;

        double sum = 0;
        LinkedList<MenuItem> itemList = orderMap.get(order);
        for(MenuItem item : itemList) {
            sum += item.computePrice();
        }

        return sum;
    }




}

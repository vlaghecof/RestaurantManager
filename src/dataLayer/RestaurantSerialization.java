package dataLayer;

import business.IRestaurantProcessing;
import business.MenuItem;
import business.Restaurant;

import javax.print.attribute.standard.PresentationDirection;
import java.io.*;
import java.lang.reflect.Field;
import java.util.LinkedList;

public class RestaurantSerialization implements Serializable {


Restaurant restaurant =  new Restaurant();

    public void restaurantSerialization(String file,LinkedList<MenuItem> menuItems) throws IOException {
        FileOutputStream fileOutputStream= new FileOutputStream(file+".ser");
        ObjectOutputStream outputStream = new ObjectOutputStream(fileOutputStream);

        outputStream.writeObject(menuItems);

        outputStream.close();
        fileOutputStream.close();

    }

    public  LinkedList<MenuItem> restaurantDeserialization (String file,Restaurant restaurant) throws IOException, ClassNotFoundException {
        FileInputStream fileInputStreamle = new FileInputStream(file+".ser");
        ObjectInputStream inputStream= new ObjectInputStream(fileInputStreamle);

        LinkedList<MenuItem> menuItems= (LinkedList<MenuItem>)inputStream.readObject();


        restaurant.setMenu(menuItems);


        fileInputStreamle.close();
        inputStream.close();

        return menuItems;

    }



}

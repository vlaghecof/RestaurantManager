package business;

import dataLayer.RestaurantSerialization;
import presentation.WelcomePage;

import javax.swing.*;
import java.io.IOException;

public class AppMain {

    public static void main(String[] args) throws  ClassNotFoundException {

        String file="restaurant";

        Restaurant restaurant = new Restaurant();

        RestaurantSerialization restaurantSerialization= new RestaurantSerialization();

        try {
            restaurantSerialization.restaurantDeserialization(file,restaurant);
        } catch (IOException e) {
            e.printStackTrace();
        }


        JFrame frame21 = new JFrame("Welcome");
        frame21.setContentPane(new WelcomePage().welcomePanel);
        frame21.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame21.pack();
        frame21.setVisible(true);


        try {
            restaurantSerialization.restaurantSerialization(file,restaurant.getMenu());
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}

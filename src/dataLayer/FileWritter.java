package dataLayer;

import business.MenuItem;
import business.Order;
import business.Restaurant;

import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.LinkedList;

public class FileWritter {

    int billIndex=1;

    public void printBill(LinkedList<MenuItem> menuItems, Order order, int total){
        String title = String.format("BillNr%dTable%d.txt", billIndex, order.getTable());
        billIndex++;
        try {
            FileWriter fileWriter = new FileWriter(title);

            fileWriter.write("Order from date :" + "" + order.getDate() + "\n");
            fileWriter.write("Table : "+order.getTable()+"\n  \n");


            fileWriter.write("Ordered items: \n");

            menuItems.forEach((item) -> {


                try {
                    if(item.computePrice()>0)
                        fileWriter.write(((MenuItem)item).getInfo()+" costing "+item.computePrice() +"\n" );
                } catch (IOException e) {
                    e.printStackTrace();
                }


            });

            fileWriter.write("\n");
            fileWriter.write("Total: " + "" + total);
            fileWriter.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}

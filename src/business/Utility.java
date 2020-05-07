package business;

import javax.swing.*;
import javax.xml.validation.Validator;
import java.awt.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;

public class Utility {
    private static  int billIndex=1;

    public MenuItem inMenu(LinkedList<MenuItem> menu , String produtName) {
        Iterator iter = menu.iterator();
        MenuItem item;
        while(iter.hasNext())
        {
            item=(MenuItem) iter.next();
           if( item.getInfo().compareTo(produtName.strip())==0)
            {   if(item instanceof BaseProduct) {

               BaseProduct baseProduct = new BaseProduct((BaseProduct)item  );
                return baseProduct;


            }
            else {
               CompositeProduct compositeProduct = new CompositeProduct( (CompositeProduct)item  );
                return compositeProduct;
                //return null;
            }
            }
        }
        return null;
    }

    public void printBill(LinkedList<MenuItem> menuItems,Order order,int total){
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

    public boolean validator(String input)
    {
        if (input.isEmpty())
        {
            return true;
        }
        return false;
    }



}

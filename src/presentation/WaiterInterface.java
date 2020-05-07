package presentation;

import business.*;
import com.sun.source.tree.NewArrayTree;
import dataLayer.FileWritter;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.geom.RectangularShape;
import java.util.Currency;
import java.util.LinkedList;

import static java.lang.Integer.parseInt;
import static java.lang.Integer.reverseBytes;

public class WaiterInterface {

    public JPanel waiterPanel;
    private JButton btnComputePrice;
    private JButton btnAddToOrder;
    private JButton btngenerateBill;
    private JTextField textProduct;
    private JTextField textTableNumber;
    private JTextArea textOrder;
    private JLabel labTableNumber;
    private JLabel labProduct;
    public JTabbedPane waiter;
    private JButton btnStartOrder;
    private JComboBox comboBox1;

    Order curentOrder= new Order() ;

    LinkedList<MenuItem> itemList = new LinkedList<>();

    Restaurant restaurant = new Restaurant() ;

    Utility utility = new Utility();
    int price;

    public void setRestaurant(Restaurant restaurant)
    {
        this.restaurant= restaurant;
    }

    public boolean checkInput()
    {
        if(utility.validator(textProduct.getText().toString()))
        {
            JOptionPane.showMessageDialog(null,"Empty Product Name , please input a name" );
            return false;
        }

        if(utility.validator(textTableNumber.getText().toString()))
        {
            JOptionPane.showMessageDialog(null,"Empty Table Numbeber , please input a number" );
            return false;
        }



return true;
    }


    public WaiterInterface() {
        btnAddToOrder.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                if(curentOrder.getTable()==0)
                {
                    JOptionPane.showMessageDialog(null,"Please start an order first ");

                }

                if(!checkInput())
                {
                    return;
                }
                textOrder.setText("");

                String product = textProduct.getText().toString();


                    MenuItem item  = utility.inMenu(restaurant.getMenu(),product);

                    if(item==null)
                    {
                        JOptionPane.showMessageDialog(null,"Product " + product+" is not on the menu");
                    return;
                    }


                restaurant.addToOrder(curentOrder,item);


                itemList= restaurant.orderMap.get(curentOrder);

                itemList.forEach((items)->{
                    if(!items.getInfo().isEmpty())
                    if(items instanceof BaseProduct)
                    textOrder.append((items).getInfo()+  " with price "+( items).computePrice() +"\n");
                    else
                        textOrder.append((items).getInfo()+ " with price "+ items.computePrice()+ " \n");

                });

            }
        });
        btnComputePrice.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                    int price=0;

                itemList= restaurant.orderMap.get(curentOrder);

                for (MenuItem item: itemList) {
                    price+=item.computePrice();
                }


                JOptionPane.showMessageDialog(null,"Total Price is "+ price );


            }
        });
        btngenerateBill.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });


        btnStartOrder.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if(utility.validator(textTableNumber.getText().toString()))
                {
                    JOptionPane.showMessageDialog(null,"Empty Table number  , please input a number" );
                    return ;
                }


                int tableIndex =  Integer.parseInt(textTableNumber.getText().toString()) ;
                JOptionPane.showMessageDialog(null,"Started on order for table "+tableIndex);

                textOrder.setText("");

                curentOrder = new Order(tableIndex);

                LinkedList<MenuItem> test = new LinkedList<>();
                BaseProduct baseProduct = new BaseProduct();
                test.add(baseProduct);

                restaurant.addOrder(curentOrder,test);

                itemList= restaurant.orderMap.get(curentOrder);



            }
        });
        btngenerateBill.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                restaurant.addOrder(curentOrder,itemList);



                int price=0;

                itemList= restaurant.orderMap.get(curentOrder);

                for (MenuItem item: itemList) {
                    price+=item.computePrice();
                }

                FileWritter fileWritter = new FileWritter();
                fileWritter.printBill(itemList,curentOrder,price);

                JOptionPane.showMessageDialog(null,"Bill has been generated " );



            }
        });
    }
}

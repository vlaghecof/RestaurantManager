package presentation;

import business.*;
import dataLayer.RestaurantSerialization;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.LinkedList;

public class AdministratorInterface {
    public JPanel panelAdmin;
    private JTextField textName;
    private JTextField textDescription;
    private JTextField textPrice;
    private JButton btnAddProduct;
    private JButton btnEditProduct;
    private JButton btnDeleteProduct;
    private JLabel labName;
    private JLabel labDescription;
    private JLabel labPrice;

    String file="restaurant";
    Restaurant restaurant =  new Restaurant();
    Utility utility = new Utility();
    RestaurantSerialization restaurantSerialization =  new RestaurantSerialization();

    public AdministratorInterface() {
        btnAddProduct.addActionListener(new ActionListener() {
            @Override
                public void actionPerformed(ActionEvent e) {
                if(textDescription.getText().toString().matches(".*\\d.*")) {
                    JOptionPane.showMessageDialog(null, "Base Product");
                    restaurant.addItem(new BaseProduct(textName.getText().toString().strip(),Integer.parseInt(textDescription.getText().toString().strip()),Double.parseDouble(textPrice.getText().toString().strip())));

                }
                else {
                    JOptionPane.showMessageDialog(null, "Composite Product");

                    String name = textName.getText().toString().strip();
                    CompositeProduct compositeProduct =  new CompositeProduct(name);
                    String[] incredients = textDescription.getText().toString().split(",");
                    LinkedList<MenuItem> menuItems = new LinkedList<>();
                    menuItems= compositeProduct.getProductComponents();

                    for (int i = 0; i < incredients.length; i++)
                    {

                        MenuItem item  = utility.inMenu(restaurant.getMenu(),incredients[i]);

                        if(item==null)
                        {
                            BaseProduct baseProduct = new BaseProduct(incredients[i]);
                            menuItems.add(baseProduct);

                        }
                        else
                        {
                            menuItems.add(item);
                        }

                    }

                    restaurant.addItem(compositeProduct);
                  }


                try {
                    restaurantSerialization.restaurantSerialization(file,restaurant.getMenu());
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }


            }

        });
        btnEditProduct.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if(textDescription.getText().toString().matches(".*\\d.*")) {
                    JOptionPane.showMessageDialog(null, "Base Product");
                    restaurant.editItem(new BaseProduct(textName.getText().toString().strip(),Integer.parseInt(textDescription.getText().toString().strip()),Double.parseDouble(textPrice.getText().toString().strip())));


                }
                else
                {
                    JOptionPane.showMessageDialog(null, "Composite Product");

                    String name = textName.getText().toString().strip();
                    CompositeProduct compositeProduct =  new CompositeProduct(name);
                    String[] incredients = textDescription.getText().toString().split(",");
                    LinkedList<MenuItem> menuItems = new LinkedList<>();
                    menuItems= compositeProduct.getProductComponents();

                    for (int i = 0; i < incredients.length; i++)
                    {

                        MenuItem item  = utility.inMenu(restaurant.getMenu(),incredients[i]);

                        if(item==null)
                        {
                            BaseProduct baseProduct = new BaseProduct(incredients[i]);
                            menuItems.add(baseProduct);

                        }
                        else
                        {
                            menuItems.add(item);
                        }

                    }

                    restaurant.editItem(compositeProduct);
                }

                try {
                    restaurantSerialization.restaurantSerialization(file,restaurant.getMenu());
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }

            }
        });
        btnDeleteProduct.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                LinkedList<MenuItem> menuItems = new LinkedList<>();
                LinkedList<MenuItem> toDelete = new LinkedList<>();

                menuItems = restaurant.getMenu();
                String productName=textName.getText().toString().strip();

              menuItems.forEach((item)->{
                  if(item instanceof CompositeProduct)
                  {
                      if(item.getInfo().equals(productName))
                          toDelete.add(item);

                      LinkedList<MenuItem> composition =((CompositeProduct) item).getProductComponents();
                      composition.forEach((comp)->{
                          if(comp.getInfo().equals(productName))
                          {
                              MenuItem menuItem = item;
                              toDelete.add(menuItem);

                          }
                      });

                  }
                  else
                  {
                      if(item.getInfo().equals(productName))
                      {
                          MenuItem menuItem = item;
                          toDelete.add(menuItem);

                      }

                  }

              });

              toDelete.forEach((delet)->{
                            restaurant.deleteItem(delet);
                  JOptionPane.showMessageDialog(null, "item was deleted "+delet.getInfo());
              });


                try {
                    restaurantSerialization.restaurantSerialization(file,restaurant.getMenu());
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }



        });
    }
}

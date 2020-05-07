package presentation;

import business.IRestaurantProcessing;
import business.Order;
import business.Restaurant;
import business.MenuItem;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;

public class WelcomePage {
    private JButton adminViewButton;
    private JButton waiterViewButton;
    private JButton showMenuButton;
    public JPanel welcomePanel;
    private JButton button1;
    Restaurant restaurant = new Restaurant() ;

    public void setRestaurant(Restaurant restaurant)
    {
        this.restaurant= restaurant;
    }

    public WelcomePage() {
        waiterViewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                WaiterInterface waiterInterface = new WaiterInterface();
             //   waiterInterface.setRestaurant(restaurant);
                JFrame frame2 = new JFrame("chelner");
                frame2.setContentPane(waiterInterface.waiterPanel);
                frame2.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frame2.pack();


                JOptionPane.showMessageDialog(null,"Before placing any orders pleas first use Start Order with a table number ");
                frame2.setVisible(true);
            }
        });
        showMenuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String data[][]={};
                String column[]={"Name","Description","Price"};
                JPanel pane = new JPanel(new GridBagLayout());
                DefaultTableModel model = new DefaultTableModel(data, column);
                JTable menu  = new JTable(model);


                if(restaurant.getMenu().isEmpty()){
                    JOptionPane.showMessageDialog(null,"No items in the menu yet");
                    return;
                }



                Iterator iterator =   restaurant.getMenu().iterator();
                while(iterator.hasNext()) {

                    MenuItem item = (MenuItem)iterator.next();
                    Object[] objects = new  Object[3];

                    objects[0]= ((business.MenuItem)item).getProdName();
                    objects[1] = ((business.MenuItem)item).getDescription();
                    objects[2] = ((business.MenuItem)item).computePrice();
                    model.addRow(objects);

                }
                JScrollPane sp = new JScrollPane(menu);
                pane.add(sp);
                JFrame frame2 = new JFrame("Menu");
                frame2.setContentPane(pane);
                frame2.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE );
                frame2.pack();
                frame2.setVisible(true);




            }
        });
        adminViewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {


                JFrame frame2 = new JFrame("Admin");
                frame2.setContentPane(new AdministratorInterface().panelAdmin);
                frame2.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frame2.pack();
                frame2.setVisible(true);

            }
        });

    }
}

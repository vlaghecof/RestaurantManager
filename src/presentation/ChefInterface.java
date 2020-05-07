package presentation;

import business.MenuItem;

import javax.swing.*;
import java.util.Observable;
import java.util.Observer;

public class ChefInterface implements Observer {

public void Popa(MenuItem item)
{
    JOptionPane.showMessageDialog(null,"The chef will prepare " + item.getInfo());
}

    @Override
    public void update(Observable o, Object arg) {
        Popa((MenuItem)arg);
    }
}

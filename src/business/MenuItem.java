package business;

import java.io.Serializable;

public class MenuItem implements Serializable {

    public void add(MenuItem item) { }

    public void remove(MenuItem item) {
        throw new UnsupportedOperationException();
    }

    public double computePrice() {
        throw new UnsupportedOperationException();
    }

    public String getInfo() {
        throw new UnsupportedOperationException();
    }

    public StringBuilder getDescription() {
        throw new UnsupportedOperationException();
    }

    public void editItem(MenuItem item) {
        throw new UnsupportedOperationException();
    }

    public String getProdName() {
        throw new UnsupportedOperationException();
    }

    public String contains(String prodName){throw new UnsupportedOperationException();}
}

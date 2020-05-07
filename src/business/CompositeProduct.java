package business;

import java.util.Iterator;
import java.util.LinkedList;

public class CompositeProduct extends MenuItem{
    private String productName;
    private LinkedList<MenuItem> productComponents = new LinkedList();

    public CompositeProduct(String productName, LinkedList<MenuItem> productComponents) {
        this.productName = productName;
        this.productComponents = productComponents;
    }

    public CompositeProduct(CompositeProduct compositeProduct) {
        this.productName = compositeProduct.productName;
        this.productComponents = compositeProduct.productComponents;
    }


    public CompositeProduct(String productName) {
        this.productName = productName;

    }


    public StringBuilder getDescription() {

        StringBuilder stringBuilder = new StringBuilder();


        Iterator<MenuItem> productIterator = productComponents.iterator();
        while(productIterator.hasNext()) {

            MenuItem productInfo = (MenuItem) productIterator.next();
            if(productInfo instanceof CompositeProduct)
                continue;
                stringBuilder.append(productInfo.getInfo()+" ");
        }
        return stringBuilder;
    }



    public String getProdName()
    {
        return productName;
    }


    public LinkedList getProductComponents() {
        return productComponents;
    }

    public void setProductComponents(LinkedList productComponents) {
        this.productComponents = productComponents;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }


    public void add(CompositeProduct newComponent) {
        productComponents.add(newComponent);
    }

    public void remove(CompositeProduct component) {
        productComponents.remove(component);
    }

    @Override
    public String toString() {
        return "CompositeProduct{" +
                "productName='" + productName + '\'' +
                ", productComponents=" + productComponents +
                '}';
    }

    public String getInfo() {
        return productName;
    }

    public double computePrice() {
        double sum = 0;
        Iterator<MenuItem> productIterator = productComponents.iterator();
        while(productIterator.hasNext()) {

            MenuItem productInfo = (MenuItem) productIterator.next();
            if(productInfo instanceof CompositeProduct)
                continue;

           sum += productInfo.computePrice();
        }
        return sum;
    }

    public void editItem(MenuItem item) {
        CompositeProduct aux = (CompositeProduct) item;
        productName = aux.getProductName();
        productComponents = aux.getProductComponents();
    }



    public String contains(String prodName){

        for (MenuItem item : productComponents) {
            if(item.getInfo().equals(prodName))
                return this.productName;
        }

        return null;
    }


}

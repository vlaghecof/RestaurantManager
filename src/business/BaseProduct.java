package business;

public class BaseProduct extends MenuItem {

    private String productName;
    private int quantity;
    private double price;

    public BaseProduct() {
        this.productName = "";
        this.quantity = 0;
        this.price = 0;
    }

    public BaseProduct(BaseProduct baseProduct) {
        this.productName = baseProduct.productName;
        this.quantity = baseProduct.quantity;
        this.price = baseProduct.price;
    }

    public BaseProduct(String incredient) {
        this.productName = incredient;
        this.quantity = 100;
        this.price = 100;
    }
    public StringBuilder getDescription() {

        StringBuilder stringBuilder = new StringBuilder();
          stringBuilder.append(quantity+" grams");
          return stringBuilder;

    }


    public String getProdName()
    {
        return productName;
    }


    public BaseProduct(String productName, int quantity, double price) {
        this.productName = productName;
        this.quantity = quantity;
        this.price = price;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double computePrice() {
        return this.price;
    }

    public String getInfo() {
        return productName;
    }

    public void editItem(MenuItem item) {
        BaseProduct aux = (BaseProduct) item;
        productName = aux.getProductName();
        quantity = aux.getQuantity();
        price = aux.getPrice();
    }

    @Override
    public String toString() {
        return  productName + ' ' ;
    }

    public String contains(String prodName){
        if(this.productName.equals(prodName))
            return prodName;

        return null;
    }
}

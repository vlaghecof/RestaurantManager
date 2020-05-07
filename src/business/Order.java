package business;

import java.util.Date;

public class Order {

    private static int id=0;
    private Date date;
    private int table;

    @Override
    public String toString() {
        return "Order" +
                " " + id +
                ", on the date=" + date +
                ", for table table=" + table ;
    }

    public Order() {
        id ++;
        date = null;
        table = 0;
    }


    public static int getId() {
        return id;
    }

    public Date getDate() {
        return date;
    }

    public int getTable() {
        return table;
    }

    public Order(int table) {
      id++;
       this.date = new Date();
        this.table = table;
    }

    public Order(Date date, int table) {
        id++;
        this.date = date;
        this.table = table;
    }
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((date == null) ? 0 : date.hashCode());
        result = prime * result + id;
        result = prime * result + table;
        return result;
    }
    @Override
    public boolean equals(Object obj) {


        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Order other = (Order) obj;
        if (date == null) {
            if (other.date != null)
                return false;
        } else if (!date.equals(other.date))
            return false;
        if (id != other.id)
            return false;
        if (table != other.table)
            return false;
        return true;
    }

}

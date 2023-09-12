package product;
import javax.swing.*;
import java.awt.*;
import java.util.Date;


public class Product extends JFrame{
    private String product_name;
    private double price;
    private int product_id, category_id, amount;
    private Date date;


    Product()
    {



    }
    public Product(int id, String Name, double money, int amount, int category_id, Date date){
        product_name=Name;
        product_id=id;
        price=money;
        this.amount=amount;
        this.category_id=category_id;
        this.date=date;
    }

    public int getProduct_id() {
        return product_id;
    }

    public String getProduct_name() {
        return product_name;
    }

    public double getPrice() {
        return price;
    }

    public int getCategory_id() {
        return category_id;
    }

    public int getAmount() {
        return amount;
    }
    public Date getDate() {
        return date;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setCategory_id(int category_id) {
        this.category_id = category_id;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}


public class Customer {

    private String ID;
    private String name;
    private String phone;
    private int points;

    public Customer(String name, String phone) {
        this.name = name;
        this.phone = phone;
        this.points = 0;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }


    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public int getPoints() {
        return points;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public void addPoints(int x) {
        //x is amount of money or items
        //we add some sort of metric, for every 50ETB 5 points or for every 25 item, 5 points or somethhing
    }
    public String toString() {
        return "Customer: " + name + ", Phone: " + phone + ", Points: " + points;
    }//returns string representation of the customer object
}

public class Employee {

    private String name;
    private String phone;
    private double salary;


    public Employee(String name, String phone, double salary) {
        this.name = name;
        this.phone = phone;
        this.salary = salary;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getPhone() {
        return phone;
    }


    public void setPhone(String phone) {
        this.phone = phone;
    }


    public double getSalary() {
        return salary;
    }


    public void setSalary(double salary) {
        this.salary = salary;
    }


    public String toString() {
        return "Name: " + name + ", Phone: " + phone + ", Salary: $" +salary;
    } // returns a string representation of an employee object
}

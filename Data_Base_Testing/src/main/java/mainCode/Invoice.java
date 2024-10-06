package mainCode;

public class Invoice {
    public String customer;  // Name of the invoice (varchar, length 100)
    public double value; // Value of the invoice (double)

    // Constructor
    public Invoice(String name, double value) {
        this.customer = name;
        this.value = value;
    }
}

/**
 * Product class.
 * @author Zakaria Ismail
 */
public class Product {
    //  Define mystore.Product attributes
    private final String name;
    private final int id;
    private final double price;

    /**
     * Constructor for Product
     * @param iname String, name
     * @param iid   int, id
     * @param iprice    double, price
     */
    public Product(String iname, int iid, double iprice) {
        name = iname;
        id = iid;
        price = iprice;
    }

    /**
     * Get product name
     * @return  String, product name
     */
    public String getName() {
        return name;
    }

    /**
     * Get product ID
     * @return  int, id
     */
    public int getId() {
        return id;
    }

    /**
     * Get product price
     * @return  double, Produce price
     */
    public double getPrice() {
        return price;
    }
}

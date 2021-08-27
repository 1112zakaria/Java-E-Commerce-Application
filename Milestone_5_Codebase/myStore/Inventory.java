import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

/**
 * Class that is responsible for keeping track and maintaining
 * information relating to the products available in the store.
 * @author Zakaria Ismail
 */
public class Inventory {
    //  TODO: RE-MAKE addStock so that it takes a mystore.Product object as an input
    //      - I'm probably going to disregard this, to be honest...

    /*
        inventory: stores PID/mystore.Product object pair
        stockCount: stores PID/mystore.Product object pair

        Note: how do I make this better????
     */
    protected HashMap<Integer, Product> products;
    protected HashMap<Integer, Integer> stock;
    protected ArrayList<Integer> productIDs;

    //  Default items

    public Inventory() {
        products = new HashMap<>();
        stock = new HashMap<>();
        productIDs = new ArrayList<>();
    }

    public Inventory(boolean hasDefault) {

        this();

        if (hasDefault) {
            //  Default items and prices
            String[] DEFAULT_NAMES = {"Cookie", "Chocolate", "Muffin", "Sandwich"};
            double[] DEFAULT_PRICES = {4, 5, 6, 20};
            Integer[] DEFAULT_STOCK = {24, 55, 76, 2};

            //  Add default items to the hash maps
            for (int i = 0; i < DEFAULT_NAMES.length; i++) {
                products.put(i, new Product(DEFAULT_NAMES[i], i, DEFAULT_PRICES[i]));
                stock.put(i, DEFAULT_STOCK[i]);
            }

            productIDs = new ArrayList<>(products.keySet());
        }
    }



    /**
     * Removes a quantity of stock associated with a mystore.Product id
     * from inventory.
     * Returns a mystore.Product object for success and null for failure.
     * @param id int value for the id of a mystore.Product
     * @param quantity int value for the amount of mystore.Product to be removed from inventory
     * @return mystore.Product object of the product to be removed
     */
    public Product removeStock(int id, int quantity) {
        //   No error catching yet?
        //  Get current product stock
        Integer newStock,
                currentStock = stock.get(id);

        //  Subtract quantity from current item stock
        newStock = currentStock - quantity;

        //  Signal failure if newStock would be less than 0
        if (newStock < 0 || quantity < 0) {
            return null;
        } else {
            stock.put(id, newStock);
            //return products.get(id);
        }

        //  Place mystore.Product with 0 stock remaining at the end of the ArrayList (I'm sorting the empty Products at the end)
        //  Note: this is for the printing
        if (newStock == 0) {
            //  Will it place at the end? Test to see. IT IS! POGGERS
            //  Note to self; add places items at the end of the list
            productIDs.remove((Integer) id);
            productIDs.add(id);
        }

        return products.get(id);
    }

    /**
     * Adds a quantity of a mystore.Product to the mystore.Inventory
     * @param prod mystore.Product object to be added to inventory
     * @param quantity int value for the amount of mystore.Product to be added
     */
    public void addStock(Product prod, int quantity) {
        //  This adds stock to currently existing mystore.Product
        /*
        Integer newStock,
                currentStock = stock.get(id);
        if (currentStock != null) {
            newStock = currentStock + quantity;
            //  Does not check if ID
            stock.put(id, newStock);
        }

         */

        Integer new_stock, current_stock = stock.get(prod.getId());
        if (quantity >= 0) {
            if (current_stock != null) {
                //  Existing product
                new_stock = current_stock + quantity;
                stock.put(prod.getId(), new_stock);
            } else {
                //  New mystore.Product
                stock.put(prod.getId(), quantity);
                products.put(prod.getId(), prod);
                productIDs.add(prod.getId());
            }
        }
    }

    /**
     * Adds stock for a new Product.
     * @param iname     String, Product name
     * @param iprice    double, Product price
     * @param quantity  int, Product stock
     */
    public void addStock(String iname, double iprice, int quantity) {
        //  This creates a product and adds stock to the inventory
        //mystore.Product newProduct = new mystore.Product();
        int PID = generateNewPID();

        products.put(PID, new Product(iname, PID, iprice));
        stock.put(PID, quantity);
    }


    // Another problem: I don't check if stock is legitimate
    //  Make it so that you can add Products <- THIS IS IMPORTANT

    /**
     * Gets product info
     * @param id    int, id
     * @return  String, product info
     */
    public String getProductInfo(int id) {
        Product p = products.get(id);   //  Get product
        String info;
        Integer rs = stock.get(id);     //  Remaining stock

        //info = id + "\t" + p.getName()+ "\t" + p.getPrice() + "$\t" + rs;
        info = String.format("%d\t%s\t%.2f\t%d", id, p.getName(), p.getPrice(), rs);
        return info;
    }

    //  Functionality to check how much stock of a given mystore.Product is in the mystore.Inventory (mystore.StoreManager functionality)
    //  ASK IF THIS IS ALLOWED/CORRECT?

    /**
     * Get product stock.
     * @param id    int, product id
     * @return  int, product stock
     */
    public int getProductStock(int id) {
        return stock.get(id);
    }

    //  Non-requested function:

    /**
     * Get product IDs
     * @return  ArrayList<Integer>, all PIDs
     */
    public ArrayList<Integer> getAllPIDs() {
        return productIDs;
    }

    public String getProductName(int id) {
        return products.get(id).getName();
    }

    /**
     * Generates new PID
     * @return  int, new unique PID
     */
    private int generateNewPID() {
        //  Generates new PID not used
        ArrayList<Integer> allPIDs = getAllPIDs();
        int maxPID = Collections.max(allPIDs);
        return maxPID + 1;
    }

    /**
     * Checks if mystore.Inventory is empty.
     * @return  boolean, mystore.Inventory isEmpty?
     */
    public boolean isEmpty() {

        for (Integer PID : productIDs) {
            //  Loop through all stock values and search for non-zero quantity
            if (stock.get(PID) != 0) {
                return false;
            }
        }

        return true;
    }

    /**
     * Gets all Products
     * @return  HashMap<Integer, Product>, all products
     */
    public HashMap<Integer, Product> getAllProducts() {
        return products;
    }

    /**
     * Gets all stock
     * @return  HashMap<Integer, Integer>, all stock
     */
    public HashMap<Integer, Integer> getAllStock() {
        return stock;
    }


}

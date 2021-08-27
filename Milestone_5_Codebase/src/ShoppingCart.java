/**
 * mystore.ShoppingCart class that keeps track of the state of the user's shopping cart
 */
public class ShoppingCart extends Inventory {

    //  TODO: Determine whether to set access modifiers to private vs protected
    //private int cartID;
    //private HashMap<Integer, mystore.Product> products = new HashMap<>();
    //private HashMap<Integer, Integer> stock = new HashMap<>();
    //private ArrayList<Integer> productIDs = new ArrayList<>();
    //private mystore.StoreManager storeMgr;

    /**
     * Constructor for mystore.ShoppingCart.
     */
    public ShoppingCart() {
        super(false);
    }
    /*
        NOT SURE IF mystore.ShoppingCart will need its own ID in the future, but keeping this here

    public mystore.ShoppingCart(mystore.StoreManager sm, int id) {
        super(false);
        //cartID = id;
        //storeMgr = sm;

    }*/


    /**
     * Returns String data for all of the mystore.ShoppingCart's
     * Products and includes an option field for the user
     * to select.
     * @return  String, mystore.ShoppingCart data
     */
    public String toString(boolean hasOptions) {
        StringBuilder strData = new StringBuilder();
        int PID;

        for (int i = 0; i < productIDs.size(); i++) {
            PID = productIDs.get(i);
            strData.append(String.format("%d\t%s\t%d", PID, products.get(PID).getName(),
                    stock.get(PID)));
            if (hasOptions) {
                //  Add option indicator
                strData.append(String.format("\t%d", i));
            } else {
                //  Add mystore.Product Price
                strData.append(String.format("\t%.2f$", products.get(PID).getPrice()));
            }

            strData.append("\n");
        }

        return strData.toString();
    }

    /**
     * Removes a quantity of mystore.Product from mystore.ShoppingCart.
     * @param productID int, ID for mystore.Product
     * @param quantity int, value for the amount of mystore.Product to be removed from inventory
     * @return mystore.Product, removed mystore.Product object
     */
    public Product removeStock(int productID, int quantity) {

        Product removed_product = products.get(productID);
        //  FIXME: not sure if there is a problem with the method overriding but intelliJ is complaining
        if (stock.get(productID) - quantity >= 0) {
            //  Remove a quantity from mystore.ShoppingCart
            stock.put(productID, stock.get(productID) - quantity);
        }

        if (stock.get(productID) == 0) {
            //  If no more of product, get rid entirely
            stock.remove(productID);
            products.remove(productID);
            productIDs.remove((Integer) productID);
        }

        return removed_product;
    }

    /**
     * Gets the product price.
     * @param productID int, productID
     * @return  double, Product price
     */
    public double getProductPrice(int productID) {
        return products.get(productID).getPrice();
    }

    /**
     * Indicates if mystore.ShoppingCart is empty.
     * @return  boolean, mystore.ShoppingCart is empty
     */
    public boolean isEmpty() {
        return productIDs.size() == 0;
    }





}

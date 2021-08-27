/*
    Name: Zakaria Ismail
    S#: 101143497
 */

import java.util.*;


/**
 * Manages the mystore.Inventory, maintains ShoppingCarts,
 * and responds to queries, prompts, and inputs received from the user via the
 * mystore.StoreView class
 */
public class StoreManager {
    //  TODO: update getInventoryInfo Javadoc

    //  Define inventory
    private Inventory inventory = new Inventory(true);
    private HashMap<Integer, StoreView> storeViews = new HashMap<>();
    private HashMap<Integer, ShoppingCart> shoppingCarts = new HashMap<>();
    private ArrayList<Integer> cartIDs = new ArrayList<>();

    public StoreManager() {
        //  Initialize default storeView
        createStoreView();
    }

    /**
     * Creates and adds a mystore.StoreView object to the mystore.StoreManager object
     * along with its respective mystore.ShoppingCart object, identified by
     * the cartID
     * @return int value for the cartID of the new mystore.StoreView
     */
    public int createStoreView() {
        int cartID = generateCartID();
        StoreView sv = new StoreView(cartID, this);
        ShoppingCart sc = new ShoppingCart();

        storeViews.put(cartID, sv);
        shoppingCarts.put(cartID, sc);
        cartIDs.add(cartID);

        //System.out.println("New storeView created!");
        return cartID;
    }


    /**
     * Gets the mystore.StoreView object associated with id
     * @param id int value for the cartID of the desired mystore.StoreView object
     * @return mystore.StoreView object associated with the id
     */
    public StoreView getStoreView(int id) {
        int maxCartID = Collections.max(cartIDs);
        int newCartID;
        if (id <= maxCartID) {
            return storeViews.get(id);
        } else {
            newCartID = createStoreView();
            return storeViews.get(newCartID);
        }
        //return storeViews.get(id);
    }




    /**
     * Generates unique cartID
     * @return int value for unique cartID
     */
    private int generateCartID() {
        if (!cartIDs.isEmpty()) {
            int maxCartID = Collections.max(cartIDs);
            return maxCartID+1;
        } else {
            return 0;
        }
    }


    /**
     * Returns ALL Products in a mystore.ShoppingCart
     * back to the mystore.Inventory.
     * @param cartID    int, mystore.ShoppingCart ID
     */
    public void requestProductReturn(int cartID) {
        ShoppingCart cart = shoppingCarts.get(cartID);
        ArrayList<Integer> productIDs = new ArrayList<>(cart.getAllPIDs());
        Product removedProduct;
        int quantity;

        for (Integer ID : productIDs) {
            //  Remove all Products in mystore.ShoppingCart and place back in mystore.Inventory
            quantity = cart.getProductStock(ID);
            removedProduct = cart.removeStock(ID, quantity);
            inventory.addStock(removedProduct, quantity);
        }
    }

    /**
     * Gets all cartIDs
     * @return  ArrayList<Integer>, storeView keys
     */
    public ArrayList<Integer> getAllCartIDs() {
        /*
        ArrayList<Integer> keys = new ArrayList<>(storeViews.keySet());
        Collections.sort(keys);
        return keys;
         */
        return cartIDs;
    }

    public HashMap<Integer, Product> getAllProducts() {
        return inventory.getAllProducts();
    }

    public HashMap<Integer, Integer> getAllStock() {
        return inventory.getAllStock();
    }

    /**
     * Gets product stock.
     * @param prodID    int, productID
     * @return  int, product Stock
     */
    public int getProductStock(int prodID) {
        return inventory.getProductStock(prodID);
    }

    /**
     * Adds 1 of a Product to cart
     * @param cartID    int, cart ID
     * @param product   Product, added product
     */
    public void addToCart(int cartID, Product product) {
        shoppingCarts.get(cartID).addStock(product, 1);
        inventory.removeStock(product.getId(), 1);
    }

    /**
     * Removes 1 of a Product from a cart
     * @param cartID    int, cartID
     * @param product   Product, removed Product
     */
    public void removeFromCart(int cartID, Product product) {
        shoppingCarts.get(cartID).removeStock(product.getId(), 1);
        inventory.addStock(product, 1);
    }

    /**
     * Gets all cart stock
     * @param cartID    int, cartID
     * @return  HashMap<Integer, Integer>, cart stock
     */
    public HashMap<Integer, Integer> getAllCartStock(int cartID) {
        System.out.println(shoppingCarts.get(cartID));

        return shoppingCarts.get(cartID).getAllStock();
    }

    /**
     * Gets all of a cart's products
     * @param cartID    int, cartID
     * @return  HashMap<Integer, Product>, cart products
     */
    public HashMap<Integer, Product> getAllCartProducts(int cartID) {
        return shoppingCarts.get(cartID).getAllProducts();
    }

    /**
     * Get cart total balance
     * @param cartID    int, cart ID
     * @return  float, total balance
     */
    public float getTotalBalance(int cartID) {
        HashMap<Integer, Product> products = getAllCartProducts(cartID);
        HashMap<Integer, Integer> stock = getAllCartStock(cartID);
        float totalBalance = 0;

        for (Integer i : products.keySet()) {
            totalBalance += stock.get(i) * products.get(i).getPrice();
        }

        return totalBalance;
        //  TODO: total balance method that calculates total balance
        //  FIXME: make process transaction do this task
    }


    /**
     * User "takes out" all of the Products
     * @param cartID    int, cartID
     */
    public float processTransaction(int cartID) {
        ShoppingCart cart = shoppingCarts.get(cartID);
        HashMap<Integer, Product> products = getAllCartProducts(cartID);
        HashMap<Integer, Integer> stock = getAllCartStock(cartID);
        Set<Integer> keys = new HashSet<>(products.keySet());

        float totalBalance = getTotalBalance(cartID);

        for (Integer i : keys) {
            cart.removeStock(products.get(i).getId(), stock.get(i));
        }

        return totalBalance;

        //  TODO: process transaction method that calculates total balance and
        //      empties cart
    }



}

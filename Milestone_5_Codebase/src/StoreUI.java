/*
    Name: Zakaria Ismail
    S#: 101143497
 */

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * StoreUI interface acting as component for StorePage
 * @author Zakaria Ismail
 */
public class StoreUI extends JPanel {

    private ArrayList<ProductUI> productPanels;

    private StoreView storeView;
    private StoreManager storeMgr;
    private StorePage storePage;
    private CartUI cartUI;

    /**
     * Constructor for StoreUI
     * @param sv    StoreView
     * @param sm    StoreManager
     * @param sp    StorePage
     * @param cui   CartUI
     */
    public StoreUI(StoreView sv, StoreManager sm, StorePage sp, CartUI cui) {

        storeView = sv;
        storeMgr = sm;
        storePage = sp;
        productPanels = new ArrayList<>();
        cartUI = cui;

        //this.add(new JLabel("storeUI"));
        this.setLayout(new FlowLayout());

    }

    /**
     * Initializes storeUI Product panels
     */
    public void initializeUI() {
        // Create and populate ProductUIs and add to panel
        this.removeAll();
        productPanels.removeAll(productPanels);
        HashMap<Integer, Product> allProducts = storeMgr.getAllProducts();
        HashMap<Integer, Integer> allStock = storeMgr.getAllStock();

        for (Integer i : allProducts.keySet()) {
            //  Loop through each product and create a ProductUI
            Product prod = allProducts.get(i);
            int stock = allStock.get(i);
            ProductUI productUI = new ProductUI(prod, stock, storeView, storeMgr, cartUI);
            productPanels.add(productUI);
            this.add(productUI);
        }
    }
}

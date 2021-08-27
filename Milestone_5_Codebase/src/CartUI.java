/*
    Name: Zakaria Ismail
    S#: 101143497
 */

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Set;

/**
 * Cart User interface for StorePage.
 * @author Zakaria Ismail
 */
public class CartUI extends JPanel {

    private JButton logoutBtn;
    private JButton checkoutBtn;
    private JLabel cartContents;

    private StoreManager storeMgr;
    private StoreView storeView;

    private HashMap<Integer, Product> products;
    private HashMap<Integer, Integer> stock;

    /**
     * Constructor for CartUI
     * @param sv    StoreView
     * @param sm    StoreManager
     */
    public CartUI(StoreView sv, StoreManager sm) {
        storeMgr = sm;
        storeView = sv;

        // TODO: center the buttons
        logoutBtn = new JButton("LOGOUT");
        logoutBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                //System.out.println("Logout button pressed!");
                // Go to logout confirmation page
                storeView.togglePage(2);
            }
        });
        checkoutBtn = new JButton("CHECKOUT");
        checkoutBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                //  Go to checkout page
                //System.out.println("Checkout button pressed!");
                storeView.togglePage(3);
            }
        });

        cartContents = new JLabel("My cart:");

        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        //this.add(new JLabel("cartUI"));
        this.add(logoutBtn);
        this.add(checkoutBtn);
        this.add(cartContents);
    }

    /**
     * Updates the text shown on user's cart.
     */
    public void updateInterface() {
        stock = storeMgr.getAllCartStock(storeView.getCartID());
        products = storeMgr.getAllCartProducts(storeView.getCartID());
        Set<Integer> keys = stock.keySet();
        StringBuilder contents = new StringBuilder();

        contents.append("<html>My cart:<br>");

        for (Integer i : keys) {
            //contents.append(stock.get(i) + " | " + products.get(i).getName() +
              //      " | " + products.get(i).getPrice() + "$<br>");
            contents.append(String.format("%d | %s | %.2f$<br>", stock.get(i),
                    products.get(i).getName(), products.get(i).getPrice()));
        }
        contents.append(String.format("Total balance: %.2f$",
                storeMgr.getTotalBalance(storeView.getCartID())));
        contents.append("</html>");


        //System.out.println("CartUI has been updated.");

        cartContents.setText(contents.toString());

    }
}

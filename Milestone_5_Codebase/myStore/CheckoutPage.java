/*
    Name: Zakaria Ismail
    S#: 101143497
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Set;

/**
 * CheckoutPage for checkout confirmation
 * @author Zakaria Ismail
 */
public class CheckoutPage extends JPanel {

    private StoreManager storeMgr;
    private StoreView storeView;

    private HashMap<Integer, Integer> stock;
    private HashMap<Integer, Product> products;

    private JButton yesBtn;
    private JButton noBtn;
    private JLabel cartContents;

    private float totalBalance;

    /**
     * CheckoutPage constructor
     * @param sv    StoreView
     * @param sm    StoreManager
     */
    public CheckoutPage(StoreView sv, StoreManager sm) {
        storeView = sv;
        storeMgr = sm;
        cartContents = new JLabel("void");


        //setCartContents();

        this.setBackground(Color.lightGray);
        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        this.add(new JLabel("<html><b>CHECKOUT<b><br></html>"));
        this.add(cartContents);
        this.add(new JLabel("Please confirm your purchase."));

        yesBtn = new JButton("Yes.");
        yesBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                //  Clear current storeView cart and go to homepage
                //System.out.println("The user wants to checkout and go home!");
                // storeMgr.requestProductReturn(storeView.getCartID()); process transaction
                setTotalBalance();
                //storeMgr.processTransaction(storeView.getCartID());
                //  TODO: count total balance and make it an attribute that can be
                //      acquired by ProcessingPage
                storeView.hideAll();
                storeView.togglePage(4);
            }
        });
        noBtn = new JButton("No.");
        noBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                //  Restore and return to store, with no changes to cart
                //System.out.println("The user wants to go back to the store!");
                storeView.togglePage(1, true);
            }
        });

        this.add(yesBtn);
        this.add(noBtn);

    }

    /**
     * Set the label contents.
     */
    public void setCartContents() {
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

    /**
     * Set the total balance text label
     */
    public void setTotalBalance() {
        totalBalance = storeMgr.getTotalBalance(storeView.getCartID());
    }
}

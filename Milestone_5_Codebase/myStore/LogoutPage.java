/*
    Name: Zakaria Ismail
    S#: 101143497
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * LogoutPage for GUI interface
 * @author Zakaria Ismail
 */
public class LogoutPage extends JPanel {

    private StoreManager storeMgr;
    private StoreView storeView;

    private JButton yesBtn;
    private JButton noBtn;

    /**
     * LogoutPage constructor
     * @param sv    StoreView
     * @param sm    StoreManager
     */
    public LogoutPage(StoreView sv, StoreManager sm) {
        storeView = sv;
        storeMgr = sm;

        this.setBackground(Color.CYAN);
        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        this.add(new JLabel("<html><b>LOGOUT</b></html>"));
        this.add(new JLabel("<html>Please confirm that you wish to logout.<br>" +
                "All of the items in your cart will be returned.</html>"));

        yesBtn = new JButton("Yes.");
        yesBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                //  Clear current storeView cart and go to homepage
                //System.out.println("The user wants to go home!");
                storeMgr.requestProductReturn(storeView.getCartID());
                storeView.hideAll();
                storeView.togglePage(0);
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
}

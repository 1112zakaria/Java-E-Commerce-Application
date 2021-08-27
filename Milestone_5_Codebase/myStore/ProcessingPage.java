/*
    Name: Zakaria Ismail
    S#:101143497
 */

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * ProcessingPage for GUI interface
 * @author Zakaria Ismail
 */
public class ProcessingPage extends JPanel {

    private StoreView storeView;
    private StoreManager storeMgr;

    private JLabel totalBalance;
    private JButton returnBtn;

    /**
     * ProcessingPage constructor
     * @param sv    StoreView
     * @param sm    StoreManager
     */
    public ProcessingPage(StoreView sv, StoreManager sm) {
        storeMgr = sm;
        storeView = sv;

        totalBalance = new JLabel("void");
        returnBtn = new JButton("Return home.");
        returnBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                //System.out.println("Return home button has been pressed!");
                storeView.togglePage(0);
            }
        });

        this.add(totalBalance);
        this.add(returnBtn);

    }

    /**
     * Generates label for end of shopping experience
     */
    public void generateLabel() {
        float balance = storeMgr.processTransaction(storeView.getCartID());
        totalBalance.setText(String.format("<html>Thank you for shopping!<br>" +
                "Your total balance is %.2f$</html>", balance));
    }
}

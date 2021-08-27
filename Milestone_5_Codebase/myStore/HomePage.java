/*
    Name: Zakaria Ismail
    S#: 101143497
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * GUI homepage
 * @author Zakaria Ismail
 */
public class HomePage extends JPanel {

    private JComboBox dropdownList = new JComboBox();
    private StoreManager storeMgr;
    private int selectedIndex;
    private JButton button = new JButton("Enter store");

    public HomePage(StoreManager storeMgr) {
        this.storeMgr = storeMgr;
        this.setLayout(new BorderLayout());
        this.setBackground(Color.RED);
        this.add(new JLabel("<html><b>Welcome to the Food Store!</b><br>" +
                "Please select a user.</html>"), BorderLayout.PAGE_START);   // For testing purposes
        this.add(dropdownList, BorderLayout.CENTER);
        this.add(button, BorderLayout.SOUTH);

        //  FIXME: should adding actionlistener be before or after adding to panel
        dropdownList.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                selectedIndex = dropdownList.getSelectedIndex();
                //System.out.println("selected index updated. index =" + selectedIndex);
            }
        });

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                //System.out.println("Button pressed");
                StoreView targetSV = storeMgr.getStoreView(selectedIndex);
                //  Make page enter SV
                StoreView.setCurrentStoreView(targetSV);
                targetSV.togglePage(1);
            }
        });
    }

    /**
     * Updates dropdown list with
     * current valid options
     */
    public void updateInterface() {
        ArrayList<Integer> svKeys = storeMgr.getAllCartIDs();

        dropdownList.removeAllItems();
        //  The index will be used to identify the storeView. If +1
        //  key, then create new storeview
        for (Integer key : svKeys) {
            // Add items
            String str = "User #" + key;
            dropdownList.addItem(str);
        }
        // Add "create new storeview" option
        dropdownList.addItem("Create new User");

        System.out.println("Items updated");

    }


}

/*
    Name: Zakaria Ismail
    S#: 101143497
 */

import javax.swing.*;
import java.awt.*;

/**
 * Main page of Store application
 * @author Zakaria Ismail
 */
public class StorePage extends JPanel {

    private StoreView storeView;
    private StoreManager storeMgr;

    private StoreUI storeUI;
    private CartUI cartUI;
    private JSplitPane splitPane;
    private JScrollPane storeScrollPane;
    private JScrollPane cartScrollPane;

    //test
    //private JPanel splitter = new JPanel();


    /**
     * Constructor for StorePage
     * @param sv    StoreView
     * @param sm    StoreManager
     */
    public StorePage(StoreView sv, StoreManager sm) {
        storeView = sv;
        storeMgr = sm;

        cartUI = new CartUI(storeView, storeMgr);
        storeUI = new StoreUI(storeView, storeMgr, this, cartUI);

        this.setBackground(Color.PINK);
        this.setLayout(new BorderLayout());
        this.add(new JLabel("Welcome to the Food Store (User#"+storeView.getCartID() + ")"),
                BorderLayout.NORTH);

        storeUI.setBackground(Color.GREEN);
        storeUI.setPreferredSize(new Dimension(500, 100));  // adjust in order to modify
        cartUI.setBackground(Color.YELLOW);

        storeScrollPane = new JScrollPane(storeUI, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        cartScrollPane = new JScrollPane(cartUI, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);



        //  FIXME: find alternative to using split pane?
        splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, storeUI, cartUI);
        //splitPane.setDividerLocation(0.75);
        this.add(splitPane);


        //this.add(storeUI);
        //this.add(cartUI);


    }

    /**
     * Initializes user interface for StoreUI
     */
    public void initalizeUI() {
        // initalizes storeUI
        storeUI.initializeUI();
        //cartUI.updateInterface();
    }

    /**
     * Updates cartUI interface
     */
    public void updateCartInterface() {
        cartUI.updateInterface();
    }
}

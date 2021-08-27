import javax.swing.*;
import java.awt.*;

/**
 * StoreView class that manages the GUI for the system.
 * @author Zakaria Ismail
 */
public class StoreView {
    //  TODO: update displayStore Javadoc

    //  TODO: center GUI elements that need centering
    //  FIXME: add images to each product
    //  FIXME: appropriately re-color the GUI to something nicer

    private int cartID;
    //private static StoreManager storeMgr = new StoreManager();  THIS IS AN INFINITE LOOP
    private static StoreManager storeMgr;

    private static final JFrame frame = new JFrame("The Food Store!");                     // window
    //private static final HomePage homePage = new HomePage(storeMgr);                // page 0
    private static HomePage homePage;
    private StorePage storePage;              // page 1
    private LogoutPage logoutPage;                // page 2
    private CheckoutPage checkoutPage;
    private ProcessingPage processingPage;

    private static StoreView currentStoreView = null;

    /**
     * Constructor for the StoreView class. Initalizes
     * the associated mystore.StoreManager and cartID
     * @param icartID int value for the associated cartID
     */
    public StoreView(int icartID, StoreManager sm) {
        storeMgr = sm;
        cartID = icartID;
        //storeMgr = sm;
        //private final CheckoutPage checkoutPage;
        //frame = new JFrame();
        //homePage = new HomePage();
        storePage = new StorePage(this, storeMgr);
        logoutPage = new LogoutPage(this, storeMgr);
        checkoutPage = new CheckoutPage(this, storeMgr);
        processingPage = new ProcessingPage(this, storeMgr);
        //  TODO: shouldn't HomePage be static? since it's should allow
        //      to branch to other StoreViews and GUIs

        //frame.add(homePage);    // FIXME: will I get an error after "adding" homePage twice?
        //frame.add(storePage);
        //frame.add(quitPage);
        //frame.add(processingPage);
        frame.setPreferredSize(new Dimension(700,500));
        frame.pack();
        frame.setLayout(new BorderLayout());


        //  TODO: when adding each component, INITALIZE TO HIDDEN
        storePage.setVisible(false);
        logoutPage.setVisible(false);
    }

    /**
     * Gets the cartID of the StoreView object
     * @return int value for the cartID of the StoreView object
     */
    public int getCartID() {
        return cartID;
    }

    /**
     * Displays the initial state of the GUI,
     * which is the homepage.
     */
    public static void initializeGUI() {
        homePage.setVisible(true);
        frame.setVisible(true);
        homePage.updateInterface();
    }

    public static void initializeHomePage() {
        homePage = new HomePage(storeMgr);
        frame.add(homePage);
    }

    /**
     * Set the current storeview
     * @param sv    StoreView
     */
    public static void setCurrentStoreView(StoreView sv) {
        currentStoreView = sv;
    }

    /**
     * Goes to selected page by make page
     * visible and making all others invisible.
     * 0 - homepage
     * 1 - storepage
     * 2 - logoutpage
     * 3 - checkoutpage
     * 4 - processingpage
     * @param selectedPage  int, selected page
     */
    public void togglePage(int selectedPage) {
        //  TODO: it appears that I need to: 1. hide, 2. remove, 3. add
        hideAll();
        if (selectedPage == 0) {
            // Go to homepage
            //System.out.println("Homepage has been entered!");
            frame.add(homePage);
            homePage.updateInterface();
            homePage.setVisible(true);

        } else if (selectedPage == 1) {
            // Go to storePage
            frame.add(storePage);
            storePage.setVisible(true);
            //System.out.println("current storeView:" + currentStoreView.getCartID());
            storePage.initalizeUI();
            storePage.updateCartInterface();
        } else if (selectedPage == 2) {
            // Go to logoutpage
            frame.add(logoutPage);
            logoutPage.setVisible(true);
            //System.out.println("Entering logout page!");
        } else if (selectedPage == 3) {
            //  Go to checkout page
            frame.add(checkoutPage);
            checkoutPage.setVisible(true);
            checkoutPage.setCartContents();
            //System.out.println("Entering checkout page!");
        } else if (selectedPage == 4) {
            //  Processing page
            frame.add(processingPage);
            processingPage.setVisible(true);
            processingPage.generateLabel();
            //System.out.println("Entering processing page!");


        }
    }

    /**
     * Toggles to a page and restores
     * its current contents
     * @param selectedPage  int, selected Page
     * @param restore       boolean, data is restored
     */
    public void togglePage(int selectedPage, boolean restore) {
        hideAll();
        if (selectedPage == 1 && restore) {
            frame.add(storePage);
            storePage.setVisible(true);
            //System.out.println("restoring storeview " + currentStoreView.getCartID());
            storePage.updateCartInterface();

        }
    }

    /**
     * Hides all GUI pages.
     */
    public void hideAll() {
        homePage.setVisible(false);
        storePage.setVisible(false);
        logoutPage.setVisible(false);
        checkoutPage.setVisible(false);
        processingPage.setVisible(false);

        frame.remove(homePage);
        frame.remove(storePage);
        frame.remove(logoutPage);
        frame.remove(checkoutPage);
        frame.remove(processingPage);

    }


    /**
     * Entry point for the application.
     * @param args String array value for the command line inputs to the program
     */
    public static void main(String[] args) {
        //StoreManager sm = new StoreManager();
        //StoreView current_storeView = null;
        //StoreView.storeMgr = new StoreManager();    // solves the "infinite loop" exception error
        //StoreView.setStoreMgr(new StoreManager());
        StoreView.storeMgr = new StoreManager();
        StoreView.initializeHomePage();
        StoreView.initializeGUI();


    }
}

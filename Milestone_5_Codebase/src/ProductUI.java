/*
    Name: Zakaria Ismail
    S#: 101143497
 */

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

/**
 * ProductUI for StorePage component
 * @author Zakaria Ismail
 */
public class ProductUI extends JPanel {

    private StoreManager storeMgr;
    private StoreView storeView;
    private CartUI cartUI;

    private Product product;
    private int initialStock;
    private int currentStock;
    private String imageUrl;    // expected to follow "<productID>.png" format
    //  TODO: get images from separate directory?
    private JButton addBtn;
    private JButton removeBtn;
    private JPanel btnPanel;

    private JPanel headerPanel;
    private JLabel descLabel;
    private JLabel stockLabel;
    private JLabel imageLabel;
    private ImageIcon image;

    /**
     * ProductUI constructor
     * @param prod  Product
     * @param stk   Stock
     * @param sv    StoreView
     * @param sm    StoreManager
     * @param cui   CartUI
     */
    public ProductUI(Product prod, int stk, StoreView sv, StoreManager sm, CartUI cui) {
        //  TODO: disable buttons accordingly
        //      - Disable + & - when stock is 0 on init (initialStock == 0)
        //      - Disable + when stock is 0 during session (currentStock == 0)
        //      - Disable - when stock is at max on init and during session (initialStock == currentStock)

        //  TODO: display image of Products
        storeMgr = sm;
        storeView = sv;
        cartUI = cui;

        //imageUrl = "images/" + prod.getId() + ".png";


        product = prod;
        initialStock = stk;
        currentStock = stk;

        addBtn = new JButton("+");
        addBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                // Add 1 of item to cart
                //System.out.println(product.getName() + " ADD button has been pressed!");
                addToCart();
                updateInterface();
            }
        });
        if (initialStock == 0) {
            addBtn.setEnabled(false);
        }

        removeBtn = new JButton("-");
        removeBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                //   Remove 1 of item from cart
                //System.out.println(product.getName() + " REMOVE button has been pressed!");
                removeFromCart();
                updateInterface();
            }
        });
        //  FIXME: this is bad programming. I'll fix it later
        if (initialStock == 0) {
            removeBtn.setEnabled(false);
        } else if (initialStock == currentStock) {
            removeBtn.setEnabled(false);
        }

        descLabel = new JLabel(String.format("%s - %.2f$", product.getName(), product.getPrice()));
        //imageLabel = new JLabel();
        btnPanel = new JPanel();
        headerPanel = new JPanel();
        stockLabel = new JLabel(String.format("Stock: %d", initialStock));

        this.setLayout(new BorderLayout());
        this.setPreferredSize(new Dimension(200, 200));
        //this.add(new JLabel(product.getName()));

        // add image
        try {
            renderImage();
            //System.out.println("Image successfully rendered");
        } catch (IOException e) {
            //System.out.println("IMAGE FAILED TO RENDER!");
            try {
                renderPlaceholderImage();
                //System.out.println("Placeholder image successfully rendered");
            } catch (IOException ea) {
                //System.out.println("PLACEHOLDER FAILED TO RENDER");
            }
        }


        //  Add components
        headerPanel.add(descLabel);
        headerPanel.add(stockLabel);
        this.add(headerPanel, BorderLayout.PAGE_START);
        this.add(imageLabel, BorderLayout.CENTER);
        btnPanel.add(removeBtn);
        btnPanel.add(addBtn);
        this.add(btnPanel, BorderLayout.PAGE_END);

        //validate();


    }

    /**
     * Updates stock printed on Product label.
     */
    private void updateInterface() {
        currentStock = storeMgr.getProductStock(product.getId());
        stockLabel.setText(String.format("Stock: %d", currentStock));

        if (currentStock == 0) {
            addBtn.setEnabled(false);
        } else {
            addBtn.setEnabled(true);
        }

        if (currentStock == initialStock) {
            removeBtn.setEnabled(false);
        } else {
            removeBtn.setEnabled(true);
        }

        cartUI.updateInterface();
    }

    /**
     * Adds Product to Cart
     */
    private void addToCart() {
        storeMgr.addToCart(storeView.getCartID(), product);
    }

    /**
     * Removes Product from Cart
     */
    private void removeFromCart() {
        storeMgr.removeFromCart(storeView.getCartID(), product);
    }


    /**
     * Renders image for Product
     * @throws IOException
     */
    private void renderImage() throws IOException {
        imageLabel = new JLabel();
        imageUrl = "images/" + product.getId() + ".png";
        InputStream in = getClass().getResourceAsStream(imageUrl);
        BufferedImage img = ImageIO.read(in);
        Image scaledImg = img.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        imageLabel = new JLabel(new ImageIcon(scaledImg));
    }

    /**
     * Renders placeholder image if image not found
     * @throws IOException
     */
    private void renderPlaceholderImage() throws IOException {
        imageLabel = new JLabel();
        imageUrl = "images/imgNotFound.png";
        InputStream in = getClass().getResourceAsStream(imageUrl);
        BufferedImage img = ImageIO.read(in);
        Image scaledImg = img.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        imageLabel = new JLabel(new ImageIcon(scaledImg));
    }
}

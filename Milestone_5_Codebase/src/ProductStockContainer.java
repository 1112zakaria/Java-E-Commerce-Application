public interface ProductStockContainer {
    public int getProductQuantity(Product prod);
    public void addProductQuantity(Product prod, int quantity);
    public boolean removeProductQuantity(Product prod, int quantity);
    public int getNumOfProducts();
}

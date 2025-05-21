package Ass03.ShopManagement3;

import com.shopmanagement.dao.ProductDao;
import com.shopmanagement.model.ShopModel;

public class App {
    public static void main(String[] args) {
        System.out.println("Hello, Shop Management!");

        // Creating an instance of ProductDao
        ProductDao productDao = new ProductDao();

        // Creating a new product (Example: Chair)
        ShopModel product = new ShopModel("Chair", "Furniture", 120.50, 10);

        // Saving the product to the database
        productDao.saveProduct(product);

        // Printing the assigned product ID after saving
        System.out.println("Saved Product ID: " + product.getId());
    }
}

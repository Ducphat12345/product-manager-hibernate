package repository;

import model.Product;

import java.util.List;

public interface ProductRepository {
    List<Product> getAllProduct();
    List<Product> getProductByCategory(int id);
    Product getProductByID(int id);
    Product getProductByName(String name);
    boolean save(Product product);
    boolean update(Product product);
    boolean delete(Product product);
}

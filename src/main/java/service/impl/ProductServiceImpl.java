package service.impl;

import model.Product;
import repository.ProductRepository;
import repository.impl.ProductRepositoryImpl;
import service.ProductService;

import java.util.List;

public class ProductServiceImpl implements ProductService {
    private ProductRepository productRepository;
    public ProductServiceImpl(){
        this.productRepository = new ProductRepositoryImpl();
    }
    @Override
    public List<Product> getAllProduct() {
        return productRepository.getAllProduct();
    }

    @Override
    public List<Product> getProductByCategory(int id) {
        return productRepository.getProductByCategory(id);
    }

    @Override
    public Product getProductByID(int id) {
        return productRepository.getProductByID(id);
    }

    @Override
    public Product getProductByName(String name) {
        return productRepository.getProductByName(name);
    }

    @Override
    public boolean save(Product product) {
        return productRepository.save(product);
    }

    @Override
    public boolean update(Product product) {
        return productRepository.update(product);
    }

    @Override
    public boolean delete(Product product) {
        return productRepository.delete(product);
    }
}

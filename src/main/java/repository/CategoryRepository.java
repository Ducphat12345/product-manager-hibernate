package repository;

import model.Category;
import model.Product;

import java.util.List;

public interface CategoryRepository {
    List<Category> getAllCategory();
    Category getCategoryByID(int id);
    Category getCategoryByName(String name);
    boolean save(Category category);
    boolean update(Category category);
    boolean delete(Category category);
}

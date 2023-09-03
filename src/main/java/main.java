import model.*;
import service.CategoryService;
import service.ProductService;
import service.UserService;
import service.impl.CategoryServiceImpl;
import service.impl.ProductServiceImpl;
import service.impl.UserServiceImpl;

import java.util.*;

public class main {
    static List<Item> items;
    static Item item;
    static Cart cart;
    static CategoryService categoryService = new CategoryServiceImpl();
    static ProductService productService = new ProductServiceImpl();
    static UserService userService = new UserServiceImpl();
    static List<Category> categories;
    static List<Product> products;
    public static void main(String[] args) {
        login();
    }
    public static void login(){
        System.out.println("Vui lòng đăng nhập vào hệ thống");
        boolean check;
        do {
            System.out.println("Nhập username:");
            String username = new Scanner(System.in).nextLine();
            System.out.println("Nhập password:");
            String password = new Scanner(System.in).nextLine();
            User user = userService.findUserByUsername(username);
            if (user != null && user.getUsername().equalsIgnoreCase(username) && user.getPassword().equalsIgnoreCase(password)){
                if (user.getRole().equalsIgnoreCase("admin")){
                    System.out.println("Đăng nhập thành công với quyền " + user.getRole());
                    while (true){
                        adminManagement();
                        System.out.println("Mời bạn chọn chương trình:");
                        int choose = new Scanner(System.in).nextInt();
                        switch (choose){
                            case 1:
                                getAllCategory();
                                break;
                            case 2:
                                getAllProduct();
                                break;
                            case 3:
                                getCategoryByID();
                                break;
                            case 4:
                                getProductByID();
                                break;
                            case 5:
                                getCategoryByName();
                                break;
                            case 6:
                                getProductByName();
                                break;
                            case 7:
                                saveCategory();
                                break;
                            case 8:
                                updateCategory();
                                break;
                            case 9:
                                deleteCategory();
                                break;
                            case 10:
                                saveProduct();
                                break;
                            case 11:
                                updateProduct();
                                break;
                            case 12:
                                deleteProduct();
                                break;
                            case 13:
                                System.out.println("Kết thúc chương trình!");
                                System.exit(0);
                                break;
                            default:
                                System.out.println("Không có chương trình bạn chọn! Nhập lại:");
                                break;
                        }
                    }
                }
                else {
                    System.out.println("Đăng nhập thành công với quyền " + user.getRole());
                    while (true){
                        customerManagement();
                        System.out.println("Mời bạn chọn chương trình:");
                        int choose = new Scanner(System.in).nextInt();
                        switch (choose){
                            case 1:
                                getProductByCategory();
                                break;
                            case 2:
                                getProductByID();
                                break;
                            case 3:
                                getProductByName();
                                break;
                            case 4:
                                getAllProduct();
                                break;
                            case 5:
                                addToCart();
                                break;
                            case 6:
                                getAllProductOnCart();
                                break;
                            case 7:
                                System.out.println("Kết thúc chương trình!");
                                System.exit(0);
                                break;
                            default:
                                System.out.println("Không có chương trình bạn chọn! Nhập lại:");
                                break;
                        }
                    }
                }
            }
            else {
                System.out.println("Sai tên đăng nhập hoặc mật khẩu! Nhập lại:");
                check = true;
            }
        }
        while (check);
    }
    public static void getAllCategory(){
        categories = categoryService.getAllCategory();
        if (!(categories == null || categories.isEmpty())){
            System.out.println("Danh sách danh mục là:");
            categories.forEach(System.out::println);
        }
        else {
            System.out.println("Không có bản ghi nào!");
        }
    }
    public static void getAllProduct(){
        products = productService.getAllProduct();
        if (products != null){
            System.out.println("Danh sách sản phẩm là:");
            products.forEach(System.out::println);
        }
        else {
            System.out.println("Không có bản ghi nào!");
        }
    }
    public static void getCategoryByID(){
        System.out.println("Nhập id danh mục muốn tìm:");
        int id = new Scanner(System.in).nextInt();
        Category category = categoryService.getCategoryByID(id);
        if (category != null){
            System.out.println("Danh mục tìm thấy là: " + category);
        }
        else {
            System.out.println("Không tìm thấy danh mục mang mã" + id);
        }
    }
    public static void getCategoryByName(){
        System.out.println("Nhập tên danh mục muốn tìm:");
        String name = new Scanner(System.in).nextLine();
        Category category = categoryService.getCategoryByName(name);
        if (category != null){
            System.out.println("Danh mục tìm thấy là: " + category);
        }
        else {
            System.out.println("Không tìm thấy danh mục mang tên" + name);
        }
    }
    public static void getProductByID(){
        System.out.println("Nhập id sản phẩm muốn tìm kiếm:");
        int id = new Scanner(System.in).nextInt();
        Product product = productService.getProductByID(id);
        if (product != null){
            System.out.println("Sản phầm tìm thấy là: " + product);
        }
        else {
            System.out.println("Không có sản phẩm mang mã " + id);
        }
    }
    public static void getProductByName(){
        System.out.println("Nhập tên sản phẩm muốn tìm kiếm:");
        String name = new Scanner(System.in).nextLine();
        Product product = productService.getProductByName(name);
        if (product != null){
            System.out.println("Sản phầm tìm thấy là: " + product);
        }
        else {
            System.out.println("Không có sản phẩm mang tên " + name);
        }
    }
    public static void saveCategory(){
        System.out.println("Nhập thông tin danh mục:");
        System.out.println("Nhập tên danh mục:");
        String name = new Scanner(System.in).nextLine();
        boolean resultSave = categoryService.save(new Category(name));
        if (resultSave){
            System.out.println("Thêm mới thành công!");
        }
        else {
            System.out.println("Thêm mới thất bại!");
        }
    }
    public static void updateCategory(){
        System.out.println("Nhập id danh mục muốn tìm:");
        int id = new Scanner(System.in).nextInt();
        Category category = categoryService.getCategoryByID(id);
        boolean resultUpdate = false;
        if (category != null){
            System.out.println("Nhập tên danh mục:");
            category.setName(new Scanner(System.in).nextLine());
            resultUpdate = categoryService.update(category);
        }
        if (resultUpdate){
            System.out.println("Cập nhật thành công!");
        }
        else {
            System.out.println("Cập nhật thất bại!");
        }
    }
    public static void deleteCategory(){
        System.out.println("Nhập id danh mục muốn tìm:");
        int id = new Scanner(System.in).nextInt();
        Category category = categoryService.getCategoryByID(id);
        boolean resultDelete = false;
        if (category != null){
            resultDelete = categoryService.delete(category);
        }
        if (resultDelete){
            System.out.println("Xóa thành công!");
        }
        else {
            System.out.println("Xóa thất bại!");
        }
    }
    public static void saveProduct(){
        System.out.println("Nhập tên sản phẩm:");
        String name = new Scanner(System.in).nextLine();
        System.out.println("Nhập giá sản phẩm:");
        double price = new Scanner(System.in).nextDouble();
        System.out.println("Nhập id category:");
        int idCategory = new Scanner(System.in).nextInt();
        Category category = categoryService.getCategoryByID(idCategory);
        boolean resultSave = productService.save(new Product(name, price, category));
        if (resultSave){
            System.out.println("Thêm mới thành công!");
        }
        else {
            System.out.println("Thêm mới thất bại!");
        }
    }
    public static void updateProduct(){
        System.out.println("Nhập id sản phẩm muốn tìm kiếm:");
        int id = new Scanner(System.in).nextInt();
        Product product = productService.getProductByID(id);
        boolean resultUpdate = false;
        if (product != null){
            System.out.println("Nhập tên sản phẩm:");
            product.setName(new Scanner(System.in).nextLine());
            System.out.println("Nhập giá sản phẩm:");
            product.setPrice(new Scanner(System.in).nextDouble());
            resultUpdate = productService.update(product);
        }
        if (resultUpdate){
            System.out.println("Cập nhật thành công!");
        }
        else {
            System.out.println("Cập nhật thất bại!");
        }
    }
    public static void deleteProduct(){
        System.out.println("Nhập id sản phẩm muốn tìm kiếm:");
        int id = new Scanner(System.in).nextInt();
        Product product = productService.getProductByID(id);
        boolean resultDelete = false;
        if (product != null){
            resultDelete = productService.delete(product);
        }
        if (resultDelete){
            System.out.println("Xóa thành công!");
        }
        else {
            System.out.println("Xóa thất bại!");
        }
    }
    public static void getProductByCategory(){
        System.out.println("Nhập id danh mục muốn tìm kiếm:");
        int id = new Scanner(System.in).nextInt();
        Category category = categoryService.getCategoryByID(id);
        if (category != null){
            products = productService.getProductByCategory(category.getId());
            System.out.println("Danh sách sản phầm tìm thấy là:");
            products.forEach(System.out::println);
        }
        else {
            System.out.println("Không có sản phẩm mang danh mục " + id);
        }
    }
    public static void getAllProductOnCart(){
        System.out.println("Danh sách sản phẩm trong giỏ hàng là:");
        System.out.println(cart.toString() + "\n");
    }
    public static void addToCart(){
        System.out.println("Nhập tên sản phẩm thêm vào giỏ hàng:");
        String name = new Scanner(System.in).nextLine();
        Product product = productService.getProductByName(name);
        int quantity = 1;
        if (items == null){
            items = new ArrayList<>();
            item = new Item();
            item.setId(product.getId());
            item.setProduct(product);
            item.setQuantity(quantity);
            item.setPrice(product.getPrice());
            items.add(item);
            cart = new Cart();
            cart.setId(1);
            cart.setItems(items);
            System.out.println("Thêm mới vào giỏ hàng thành công!");
        }
        else {
            for (Item item1 : items){
                if (item1.getProduct().getId() == product.getId()){
                    item1.setQuantity(item1.getQuantity() + quantity);
                    item1.setPrice(product.getPrice() * item1.getQuantity());
                    System.out.println("Thêm sản phẩm vào giỏ hàng thành công!");
                    break;
                }
                else if (item1.getProduct().getId() != product.getId()){
                    item = new Item();
                    item.setId(product.getId());
                    item.setProduct(product);
                    item.setQuantity(quantity);
                    item.setPrice(product.getPrice());
                    items.add(item);
                    System.out.println("Thêm mới vào giỏ hàng thành công!");
                    break;
                }
            }
        }
    }
    public static Item findItem(int id){
        for (Item item : items) {
            if (item.getId() == id){
                return item;
            }
        }
        return null;
    }
    public static void adminManagement(){
        System.out.println("-----MENU-----");
        System.out.println("1. Danh sách danh mục");
        System.out.println("2. Danh sách sản phẩm");
        System.out.println("3. Tìm kiếm danh mục theo id");
        System.out.println("4. Tìm kiếm sản phẩm theo id");
        System.out.println("5. Tìm kiếm danh mục theo tên");
        System.out.println("6. Tìm kiếm sản phẩm theo tên");
        System.out.println("7. Thêm danh mục mới");
        System.out.println("8. Cập nhật danh mục");
        System.out.println("9. Xóa danh mục");
        System.out.println("10. Thêm sản phẩm mới");
        System.out.println("11. Cập nhật sản phẩm");
        System.out.println("12. Xóa sản phẩm");
        System.out.println("13. Thoát chương trình");
    }
    public static void customerManagement(){
        System.out.println("-----MENU-----");
        System.out.println("1. Tìm kiếm sản phẩm theo danh mục");
        System.out.println("2. Tìm kiếm sản phẩm theo id");
        System.out.println("3. Tìm kiếm sản phẩm theo tên");
        System.out.println("4. Danh sách sản phẩm");
        System.out.println("5. Thêm vào giỏ hàng");
        System.out.println("6. Xem giỏ hàng");
        System.out.println("7. Thoát chương trình");
    }
}

package pojo;

public class Shop {
    private Product[] products;

    public Shop(int placeCount) {
        this.products = new Product[placeCount];
    }

    public void delete(int index) {
        products[index] = null;
        for (int i = index; i < products.length; i++) {
            if (products[i] == null) {
                for (int j = i + 1; j < products.length; j++) {
                    if (products[j] != null) {
                        Product product = products[j];
                        products[j] = products[i];
                        products[i] = product;
                        break;
                    }
                }
            }
        }
    }

    public void show() {
        for (Product product : products) {
            if (product != null) {
                System.out.println(product.getName());
            } else {
                break;
            }
        }
    }

    public static void main(String[] args) {
        Shop shop = new Shop(5);
        shop.products[0] = new Product("Milk", 10);
        shop.products[1] = new Product("Bread", 4);
        shop.products[2] = new Product("Egg", 19);
        shop.products[3] = new Product("Fish", 5);
        shop.products[4] = new Product("Meat", 8);

        System.out.println("This is the product list of a shop:");
        shop.show();

        System.out.println("\nDelete product with index 1");
        shop.delete(1);

        System.out.println("\nThis is the product list of a shop:");
        shop.show();
    }

}
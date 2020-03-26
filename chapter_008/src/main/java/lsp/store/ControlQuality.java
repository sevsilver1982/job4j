package lsp.store;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ControlQuality<E extends AbstractStoreHouse<T>, T extends AbstractProduct> {
    private List<E> storeHouseList;

    public ControlQuality(List<E> storeHouseList) {
        this.storeHouseList = storeHouseList;
    }

    /*public void transfer(E from, E to, T product) {

    }*/

    public void control() {
        List<E> acceptorStore = new ArrayList<E>(storeHouseList);
        storeHouseList.forEach(storeHouse -> {
            System.out.println(storeHouse.toString());
            Iterator<T> productList = storeHouse.getProductList().iterator();
            while (productList.hasNext()) {
                T product = productList.next();
                System.out.print(String.format("Product: %s; Expired: %s %%; Discount: %s", product.getName(), product.getExpirationValue(), product.getDiscount()));
                if (!storeHouse.getCondition().test(product)) {
                    acceptorStore.forEach(acceptor -> {
                        if (acceptor.getCondition().test(product)) {
                            System.out.print(String.format(" -> %s", acceptor.getName()));
                            //transfer(productList, acceptor, product);
                            acceptor.add(product);
                            productList.remove();
                        }
                    });
                }
                System.out.println();
            }
            System.out.println();
        });
    }

}
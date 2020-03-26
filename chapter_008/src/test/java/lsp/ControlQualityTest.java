package lsp;

import lsp.store.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class ControlQualityTest {
    private static Warehouse<AbstractProduct> warehouse = new Warehouse<>("Warehouse", product -> product.getExpirationValue() < 25);
    private static Shop<AbstractProduct> shop = new Shop<>("Shop", product -> {
        int expirationValue = product.getExpirationValue();
        if (expirationValue > 75 && expirationValue <= 100) {
            product.setDiscount(50);
        }
        return expirationValue >= 25 && expirationValue <= 100;
    });
    private static Trash<AbstractProduct> trash = new Trash<>("Trash", product -> product.getExpirationValue() > 100);
    private static ControlQuality<AbstractStoreHouse<AbstractProduct>, AbstractProduct> controlQuality = new ControlQuality<>(
            List.of(
                    warehouse,
                    shop,
                    trash
            )
    );

    private static Calendar creationDateConsignment1 = Calendar.getInstance();
    private static Calendar expirationDateConsignment1 = Calendar.getInstance();
    private static Calendar expirationDateConsignment2 = Calendar.getInstance();
    private static Calendar expirationDateConsignment3 = Calendar.getInstance();
    private static Calendar expirationDateConsignment4 = Calendar.getInstance();
    private static Calendar expirationDateConsignment5 = Calendar.getInstance();
    private static Calendar expirationDateConsignment6 = Calendar.getInstance();

    @BeforeAll
    static void init() {
        creationDateConsignment1.set(2020, Calendar.JANUARY, 1);
        expirationDateConsignment1.set(2021, Calendar.JANUARY, 1);
        expirationDateConsignment2.set(2020, Calendar.OCTOBER, 1);
        expirationDateConsignment3.set(2020, Calendar.AUGUST, 1);
        expirationDateConsignment4.set(2020, Calendar.JUNE, 1);
        expirationDateConsignment5.set(2020, Calendar.APRIL, 1);
        expirationDateConsignment6.set(2020, Calendar.FEBRUARY, 1);
    }

    @Test
    public void controlTest() {
        AbstractProduct product1 = new Product("product1", creationDateConsignment1, expirationDateConsignment1, 10);
        AbstractProduct product2 = new Product("product2", creationDateConsignment1, expirationDateConsignment2, 20);
        AbstractProduct product3 = new Product("product3", creationDateConsignment1, expirationDateConsignment3, 30);
        AbstractProduct product4 = new Product("product4", creationDateConsignment1, expirationDateConsignment4, 40);
        AbstractProduct product5 = new Product("product5", creationDateConsignment1, expirationDateConsignment5, 50);
        AbstractProduct product6 = new Product("product6", creationDateConsignment1, expirationDateConsignment6, 60);

        warehouse.add(product1);
        warehouse.add(product2);
        warehouse.add(product3);
        warehouse.add(product4);
        warehouse.add(product5);
        warehouse.add(product6);

        controlQuality.control();
        System.out.println();
        controlQuality.control();
        List<AbstractProduct> warehouseProductList = List.of(product1);
        List<AbstractProduct> shopProductList = List.of(product2, product3, product4, product5);
        List<AbstractProduct> trashProductList = List.of(product6);
        assertThat(
                warehouse.getProductList().equals(warehouseProductList)
                & warehouseProductList.equals(warehouse.getProductList()),
                is(true)
        );
        assertThat(
                shop.getProductList().equals(shopProductList)
                        & shopProductList.equals(shop.getProductList()),
                is(true)
        );
        assertThat(
                trash.getProductList().equals(trashProductList)
                        & trashProductList.equals(trash.getProductList()),
                is(true)
        );
    }

}
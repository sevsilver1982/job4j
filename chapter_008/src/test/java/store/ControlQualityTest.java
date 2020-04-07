package store;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ControlQualityTest {
    private static StoreWarehouse storeWarehouse;
    private static StoreShop storeShop;
    private static StoreTrash storeTrash;
    private static ControlQuality<IStore> controlQuality;

    private static Calendar creationDate = Calendar.getInstance();
    private static Calendar expirationDate1 = Calendar.getInstance();
    private static Calendar expirationDate2 = Calendar.getInstance();
    private static Calendar expirationDate3 = Calendar.getInstance();
    private static Calendar expirationDate4 = Calendar.getInstance();
    private static Calendar expirationDate5 = Calendar.getInstance();
    private static Calendar expirationDate6 = Calendar.getInstance();
    private static Calendar toDate = Calendar.getInstance();

    @BeforeAll
    static void init() {
        creationDate.set(2020, Calendar.JANUARY, 1);
        expirationDate1.set(2020, Calendar.FEBRUARY, 1);
        expirationDate2.set(2020, Calendar.APRIL, 1);
        expirationDate3.set(2020, Calendar.JUNE, 1);
        expirationDate4.set(2020, Calendar.AUGUST, 1);
        expirationDate5.set(2020, Calendar.OCTOBER, 1);
        expirationDate6.set(2021, Calendar.JANUARY, 1);
        toDate.set(2020, Calendar.APRIL, 1);

        storeWarehouse = new StoreWarehouse("Warehouse", new StoreWarehouseCondition());
        storeShop = new StoreShop("Shop", new StoreShopCondition());
        storeTrash = new StoreTrash("Trash", new StoreTrashCondition());

        controlQuality = new ControlQuality(
                List.of(
                        storeWarehouse,
                        storeShop,
                        storeTrash
                )
        );
    }

    @Test
    public void controlTest() {
        IProduct product1 = new Food("product1", creationDate, expirationDate1, 10);
        IProduct product2 = new Food("product2", creationDate, expirationDate2, 20);
        IProduct product3 = new HouseholdGoods("product3", creationDate, expirationDate3, 30);
        IProduct product4 = new HouseholdGoods("product4", creationDate, expirationDate4, 40);
        IProduct product5 = new Stationery("product5", creationDate, expirationDate5, 50);
        IProduct product6 = new Stationery("product6", creationDate, expirationDate6, 60);

        storeWarehouse.add(product1);
        storeWarehouse.add(product2);
        storeWarehouse.add(product3);
        storeWarehouse.add(product4);
        storeWarehouse.add(product5);
        storeWarehouse.add(product6);

        controlQuality.resort(toDate.getTime());

        List<IProduct> warehouseProductList = List.of(product6);
        List<IProduct> shopProductList = List.of(product2, product3, product4, product5);
        List<IProduct> trashProductList = List.of(product1);
        assertEquals(
                warehouseProductList,
                storeWarehouse.getProductList()
        );
        assertEquals(
                shopProductList,
                storeShop.getProductList()
        );
        assertEquals(
                trashProductList,
                storeTrash.getProductList()
        );
    }

    @Test
    void distribute() {
        IProduct product1 = new Food("product1", creationDate, expirationDate1, 10);
        IProduct product2 = new Food("product2", creationDate, expirationDate2, 20);
        IProduct product3 = new HouseholdGoods("product3", creationDate, expirationDate3, 30);
        IProduct product4 = new HouseholdGoods("product4", creationDate, expirationDate4, 40);
        IProduct product5 = new Stationery("product5", creationDate, expirationDate5, 50);
        IProduct product6 = new Stationery("product6", creationDate, expirationDate6, 60);

        controlQuality.distribute(product1, toDate.getTime());
        controlQuality.distribute(product2, toDate.getTime());
        controlQuality.distribute(product3, toDate.getTime());
        controlQuality.distribute(product4, toDate.getTime());
        controlQuality.distribute(product5, toDate.getTime());
        controlQuality.distribute(product6, toDate.getTime());

        List<IProduct> warehouseProductList = List.of(product6);
        List<IProduct> shopProductList = List.of(product2, product3, product4, product5);
        List<IProduct> trashProductList = List.of(product1);
        assertEquals(
                warehouseProductList,
                storeWarehouse.getProductList()
        );
        assertEquals(
                shopProductList,
                storeShop.getProductList()
        );
        assertEquals(
                trashProductList,
                storeTrash.getProductList()
        );
    }

}
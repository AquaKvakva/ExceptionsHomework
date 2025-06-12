import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.internal.matchers.Equals;

import static org.junit.jupiter.api.Assertions.*;

public class ShopRepositoryTest {
    @Test
    public void testRemoveExistProduct() {
        ShopRepository repo = new ShopRepository();
        Product product1 = new Product(1, "Игрушка", 10_000);
        Product product2 = new Product(2, "Конфеты", 500);
        Product product3 = new Product(3, "Планшет", 40_000);

        repo.add(product1);
        repo.add(product2);
        repo.add(product3);
        repo.remove(2);
        Product[] actual = repo.findAll();
        Product[] expected = new Product[]{product1, product3};

        Assertions.assertArrayEquals(expected, actual);

    }

    @Test
    public void testRemoveNotExistProduct() {

        ShopRepository repo = new ShopRepository();
        Product product1 = new Product(1, "Игрушка", 10_000);
        Product product2 = new Product(2, "Конфеты", 500);
        Product product3 = new Product(3, "Планшет", 40_000);

        repo.add(product1);
        repo.add(product2);
        repo.add(product3);

        Assertions.assertThrows(NotFoundException.class, () -> repo.remove(14));

    }

    @Test
    public void shouldAddProduct() {
        ShopRepository repo = new ShopRepository();
        Product product = new Product(1, "Самокат", 10_000);

        Assertions.assertDoesNotThrow(() -> repo.add(product));

        Product[] result = repo.findAll();
        Assertions.assertArrayEquals(new Product[]{product}, result);
    }

    @Test
    public void shouldAddProductWithExistId() {
        ShopRepository repo = new ShopRepository();
        Product product1 = new Product(1, "Самокат", 10_000);
        Product product2 = new Product(1, "Лестница", 5_000);

        repo.add(product1);

        AlreadyExistsException exception = Assertions.assertThrows(
                AlreadyExistsException.class,
                () -> repo.add(product2));

        Assertions.assertEquals("Element with id" + product2.getId() + "already exists", exception.getMessage());
        Product[] result = repo.findAll();

        Assertions.assertEquals(1, result.length);
        Assertions.assertEquals(product1, result[0]);
    }


    @Test
    public void shouldReturnTrueForSameValuesEquals() {
        Product product1 = new Product(1, "Самокат", 10_000);
        Product product2 = new Product(1, "Самокат", 10_000);

        boolean result1 = product1.equals(product2);

        Assertions.assertTrue(result1);
        Assertions.assertEquals(product1.hashCode(), product2.hashCode());

    }

    @Test
    public void shouldReturnFalseWhenDifferEquals() {
        Product product1 = new Product(1, "Самокат", 10_000);
        Product product2 = new Product(2, "Самокат", 10_000);
        Product product3 = new Product(1, "Часы", 10_000);
        Product product4 = new Product(1, "Самокат", 15_000);
        String string = new String("текст");

        boolean result1 = product1.equals(product2);
        boolean result2 = product1.equals(product3);
        boolean result3 = product1.equals(product4);
        boolean result4 = product1.equals(string);
        boolean result5 = product1.equals(null);

        Assertions.assertFalse(result1);
        Assertions.assertFalse(result2);
        Assertions.assertFalse(result3);
        Assertions.assertFalse(result4);
        Assertions.assertFalse(result5);


    }


}

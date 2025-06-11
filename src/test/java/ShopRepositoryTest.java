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

}

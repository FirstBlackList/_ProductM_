package ru.netology.manager;

import ru.netology.repository.ProductRepository;
import ru.netology.domain.Book;
import ru.netology.domain.Product;
import ru.netology.domain.Smartphone;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

public class ProductManagerTest {
    ProductRepository repository = new ProductRepository();
    ProductManager manager = new ProductManager(repository);
    Product book = new Book(1, "Harry Potter", 760, "J.K. Rowling");
    Product smartphone = new Smartphone(2, "SONY Xperia", 125_000, "Sony Mobile");
    Product product = new Product(3, "Watch", 44000);

    @Test
    void shouldAdd() {
        manager.addProduct(smartphone);
        Assertions.assertArrayEquals(new Product[]{smartphone}, repository.findAll());
    }

    @Test
    void shouldAddAll() {
        manager.addProduct(book);
        manager.addProduct(smartphone);
        manager.addProduct(product);
        Assertions.assertArrayEquals(new Product[]{book, smartphone, product}, repository.findAll());
    }

    @Test
    void shouldSearchBy() {
        manager.addProduct(book);
        manager.addProduct(smartphone);
        manager.addProduct(product);
        Assertions.assertArrayEquals(new Product[]{book}, manager.searchByName(book.getName()));
    }

    @Test
    void shouldSearchWhenFewProductsSuit() {
        manager.addProduct(book);
        manager.addProduct(smartphone);
        manager.addProduct(product);
        Assertions.assertArrayEquals(new Product[]{smartphone}, manager.searchByName(smartphone.getName()));
    }

    @Test
    void shouldSearchWhenFewProducts() {
        manager.addProduct(book);
        manager.addProduct(smartphone);
        manager.addProduct(product);
        Assertions.assertArrayEquals(new Product[]{product}, manager.searchByName(product.getName()));
    }

    @Test
    void shouldSearchWhenProductsNotSuit() {
        manager.addProduct(book);
        manager.addProduct(smartphone);
        manager.addProduct(product);
        Assertions.assertArrayEquals(new Product[]{}, manager.searchByName("Sony Playstation"));
    }
}
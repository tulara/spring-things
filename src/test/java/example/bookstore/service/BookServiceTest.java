package example.bookstore.service;

import example.bookstore.model.Book;
import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.util.MatcherAssertionErrors.assertThat;


public class BookServiceTest {

    private BookService bookService = new BookService();

    @Test
    public void shouldCreateBook() {
        Book book = new Book("Emma", "Jane Austen", 3);
        bookService.createBook(book);
        assertThat(bookService.getBookWithId(3), is(book));
    }
}
package example.bookstore.service;

import example.bookstore.model.Book;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class BookService {

    private List<Book> books = Arrays.asList(
            new Book("Jane Eyre", "Charlotte Brontë", 1),
            new Book("Great Expectations", "Charles Dickens", 2));

    public BookService(){}

    public Book getBookWithId(int id) {
        for(Book book: books){
            if(book.getId() == id){
                return book;
            }
        }
        return null;
    }

    public List<Book> getBooks() {
        return books;
    }
}

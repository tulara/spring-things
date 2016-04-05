package example.bookstore.web;

import example.bookstore.model.Book;
import example.bookstore.service.BookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BookStoreController {

    private final Logger logger = LoggerFactory.getLogger(BookStoreController.class);
    private BookService bookService;

    @Autowired
    public BookStoreController(BookService bookService) {
        this.bookService = bookService;
    }

    @RequestMapping(value="/book/{id}", method= RequestMethod.GET)
    public ResponseEntity<Book> getBook(@PathVariable int id){
        Book book = bookService.getBookWithId(id);
        return book == null ? new ResponseEntity<Book>(HttpStatus.NOT_FOUND) :
                new ResponseEntity<Book>(book, HttpStatus.OK);
    }

    @RequestMapping(value="/book", method= RequestMethod.GET)
    public ResponseEntity<List<Book>> getBooks(){
        List<Book> books = bookService.getBooks();
        return books.isEmpty() ? new ResponseEntity<List<Book>>(HttpStatus.NOT_FOUND) :
                new ResponseEntity<>(books, HttpStatus.OK);
    }

    @RequestMapping(value="/book", method= RequestMethod.POST, consumes="application/json")
    public ResponseEntity<Book> createBook(@RequestBody Book book){
        logger.debug("created book with title: " + book.getTitle());
        bookService.createBook(book);
        return new ResponseEntity<Book>(book, HttpStatus.CREATED);
    }
}

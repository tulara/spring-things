package example.bookstore.web;

import example.bookstore.model.Book;
import example.bookstore.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.ws.Response;
import java.util.List;

@RestController
public class BookStoreController {

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

    @RequestMapping(value="/book", method= RequestMethod.POST)
    public ResponseEntity<Book> createBook(Book book){
        bookService.createBook(book);
        return new ResponseEntity<Book>(book, HttpStatus.CREATED);
    }


}

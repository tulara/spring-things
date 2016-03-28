package example.bookstore.web;

import example.bookstore.model.Book;
import example.bookstore.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BookStoreController {

    private BookService bookService;

    @Autowired
    public BookStoreController(BookService bookService) {
        this.bookService = bookService;
    }

    @RequestMapping(value="/book/{id}", method= RequestMethod.GET)
    public Book getBook(@PathVariable int id){
        return bookService.getBookWithId(id);
    }

    @RequestMapping(value="/books", method= RequestMethod.GET)
    public List<Book> getBooks(){
        return bookService.getBooks();
    }


}

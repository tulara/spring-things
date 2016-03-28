package example.bookstore.model;

import lombok.Getter;
import org.springframework.stereotype.Component;

@Component
public class Book {
    @Getter
    private String title;

    @Getter
    private String author;

    @Getter
    private int id;

    public Book(){};

    public Book(String title, String author, int id) {
        this.title = title;
        this.author = author;
        this.id = id;
    }


}

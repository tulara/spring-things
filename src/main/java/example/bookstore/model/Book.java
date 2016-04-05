package example.bookstore.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
public class Book {
    @Getter
    @Setter
    private String title;

    @Getter
    @Setter
    private String author;

    @Getter
    @Setter
    private int id;

    public Book(){};

    public Book(String title, String author, int id) {
        this.title = title;
        this.author = author;
        this.id = id;
    }


}

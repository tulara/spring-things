package example.bookstore.web;

import example.bookstore.service.BookService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

public class BookStoreControllerTest {
    //integration test

    MockMvc mockMvc;
    BookService bookService = new BookService();

    @Before
    public void setup() {
        mockMvc = standaloneSetup(new BookStoreController(bookService)).build();
    }

    @Test
    public void shouldHappilyReturnBookWithMatchingId() throws Exception {
        mockMvc.perform(get("/book/1"))
                .andExpect(jsonPath("$.title", is("Jane Eyre")))
                .andExpect(jsonPath("$.author", is("Charlotte Brontë")));
    }
}

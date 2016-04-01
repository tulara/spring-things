package example.bookstore.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import example.bookstore.model.Book;
import example.bookstore.service.BookService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

public class BookStoreControllerTest {

    MockMvc mockMvc;

    @Mock
    BookService bookService;

    private static List<Book> sampleServiceResponse = Arrays.asList(
            new Book("Jane Eyre", "Charlotte Brontë", 1),
            new Book("Great Expectations", "Charles Dickens", 2));

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        mockMvc = standaloneSetup(new BookStoreController(bookService)).build();
    }

    @Test
    public void shouldHappilyReturnBookWithMatchingId() throws Exception {
        Mockito.when(bookService.getBookWithId(1)).thenReturn(new Book("Jane Eyre", "Charlotte Brontë", 1));
        mockMvc.perform(get("/book/1"))
                .andExpect(jsonPath("$.title", is("Jane Eyre")))
                .andExpect(jsonPath("$.author", is("Charlotte Brontë")));
    }

    @Test
    public void bookWithNoMatchingIdShouldReturnNotFound() throws Exception {
        Mockito.when(bookService.getBookWithId(-1)).thenReturn(null);
        mockMvc.perform(get("/book/-1"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void shouldReturn400OnInvalidId() throws Exception {
        mockMvc.perform(get("/book/NaN"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void shouldShowAllBooks() throws Exception {
        Mockito.when(bookService.getBooks()).thenReturn(sampleServiceResponse);
        mockMvc.perform(get("/book"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)));
    }

    @Test
    public void shouldCreateNewBook() throws Exception {
        Book newBook = new Book("Emma", "Jane Austen", 3);
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(newBook);


        mockMvc.perform(post("/book")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isCreated());
        verify(bookService).createBook(any(Book.class));
    }

}

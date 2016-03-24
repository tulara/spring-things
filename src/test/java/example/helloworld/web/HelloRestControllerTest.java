package example.helloworld.web;

import example.helloworld.model.Greeting;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.core.Is.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

public class HelloRestControllerTest {
    MockMvc mockMvc;
    Greeting greeting = new Greeting();

    @Before
    public void setUp() {
        mockMvc = standaloneSetup(new HelloRestController(greeting)).build();
    }

    @Test
    public void sayHi() throws Exception {
        mockMvc.perform(get("/hi"))
                .andExpect(content().string("hi"));
    }

    @Test
    public void shouldSendDefaultGreeting() throws Exception {
        mockMvc.perform(get("/greeting"))
                .andExpect(jsonPath("$.greeting", is("Hello")))
                .andExpect(jsonPath("$.name", is("World")));

    }
}

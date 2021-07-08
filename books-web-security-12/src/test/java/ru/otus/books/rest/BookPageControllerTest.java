package ru.otus.books.rest;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import ru.otus.books.controller.BookPageController;
import ru.otus.books.repository.AuthorRepository;
import ru.otus.books.repository.BookRepository;
import ru.otus.books.services.MongoUserDetailsService;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(BookPageController.class)
public class BookPageControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    BookRepository bookRepository;
    @MockBean
    AuthorRepository authorRepository;
    @MockBean
    MongoUserDetailsService mongoUserDetailsService;


    private static final String USERNAME = "TEST";
    private static final String CORRECT_PASSWORD = "$2y$10$w5e3WDexlsWg2ypu93Wsqe06gVlTTPZqiPmTrvWJL36kB9qAh2Tqa";


    @Test
    public void canSeeBooksPage() throws Exception {
        mockMvc.perform(get("/books")
                .param("username", USERNAME)
                .param("password", CORRECT_PASSWORD))
                .andDo(print())
                .andExpect(status().isOk());
    }

}

package ru.otus.books.rest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import ru.otus.books.controller.AuthorPageController;
import ru.otus.books.repository.AuthorRepository;
import ru.otus.books.repository.BookRepository;
import ru.otus.books.services.MongoUserDetailsService;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AuthorPageController.class)
public class AuthorPageControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    BookRepository bookRepository;
    @MockBean
    AuthorRepository authorRepository;
    @MockBean
    MongoUserDetailsService mongoUserDetailsService;

    @WithMockUser(
            username = "TEST",
            password = "$2y$10$w5e3WDexlsWg2ypu93Wsqe06gVlTTPZqiPmTrvWJL36kB9qAh2Tqa"
    )
    @Test
    public void testUserCanSeeAuthorsPage() throws Exception {
        mockMvc.perform(get("/authors"))
                .andDo(print())
                .andExpect(status().isOk());
    }
}

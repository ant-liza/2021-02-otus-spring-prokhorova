package ru.otus.books.rest;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import ru.otus.books.repository.AuthorRepository;
import ru.otus.books.repository.BookRepository;
import ru.otus.books.services.MongoUserDetailsService;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest
public class LoginTest {
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
    private static final String INCORRECT_PASSWORD = "1";
    @Test
    public void ifLoginHasIncorrectUserAndPasswordReturnErrorPage() throws Exception {
        RequestBuilder requestBuilder = formLogin().user(USERNAME).password(INCORRECT_PASSWORD);
        mockMvc.perform(requestBuilder)
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(forwardedUrl("/error"));
    }
    @Test
    public void ifLoginHasCorrectUserAndPasswordReturnOk() throws Exception {
        RequestBuilder requestBuilder = formLogin().user(USERNAME).password(CORRECT_PASSWORD);
        mockMvc.perform(requestBuilder)
                .andDo(print())
                .andExpect(status().isOk());
    }
}

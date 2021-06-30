package ru.otus.books.rest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.MockBeans;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.otus.books.repository.AuthorRepository;
import ru.otus.books.repository.BookRepository;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@MockBeans({@MockBean(BookRepository.class), @MockBean(AuthorRepository.class)})
@WebMvcTest(controllers = BookRestController.class)
public class BookRestControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void getAllBooksAPI() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .get("/api/books")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void getBokByIdAPI() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .get("/api/books/{id}", 1)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    public void deleteBookAPI() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.delete("/api/book/{id}", 1))
                .andExpect(status().isOk());
    }
}


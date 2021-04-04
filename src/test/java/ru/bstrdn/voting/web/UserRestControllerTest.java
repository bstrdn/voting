package ru.bstrdn.voting.web;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.authentication;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest()
@AutoConfigureMockMvc
class UserRestControllerTest {

    private static final String REST_URL = UserRestController.REST_URL + '/';
    private static final String RESTAURANT_ID = "1007";
    private static final UsernamePasswordAuthenticationToken user = new UsernamePasswordAuthenticationToken("user0@ya.ru", "p");

    @Autowired
    private MockMvc mvc;

    @Test
    void getRestaurant() throws Exception {
        mvc.perform(get(REST_URL + RESTAURANT_ID)
                .with(authentication(user)))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
    }

    @Test
    void getAll() throws Exception {
        mvc.perform(get(REST_URL)
                .with(authentication(user)))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
    }

    @Test
    void vote() throws Exception {
        mvc.perform(post(REST_URL + RESTAURANT_ID)
                .with(authentication(user)))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
    }
}
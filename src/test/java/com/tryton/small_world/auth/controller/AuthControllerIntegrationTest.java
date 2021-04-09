package com.tryton.small_world.auth.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.tryton.small_world.auth.AuthServerApplication;
import com.tryton.small_world.auth.controller.model.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.blankOrNullString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = AuthServerApplication.class)
@AutoConfigureMockMvc
class AuthControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void shouldReturnBadRequestWhenThereAreNoRequiredParametersForSignUp() throws Exception {
        performPost("/signup")
                .andExpect(status().isBadRequest());
    }

    @Test
    void shouldReturnStatus200WhenUserCreatedDuringSigningUp() throws Exception {
        String email = "fake@gmail.com";
        User user = User.builder()
                .email(email)
                .password("pass123")
                .build();

        performPostWithParameters("/signup", user)
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.email", is(email)))
                .andExpect(jsonPath("$.password", blankOrNullString()));
    }

    @Test
    void shouldReturnBadRequestWhenThereAreNoRequiredParametersForSigIn() throws Exception {
        performPost("/signin")
                .andExpect(status().isBadRequest());
    }

    @Test
    void shouldReturnStatus200WhenUserCreatedDuringSigningIn() throws Exception {
        String email = "fake@gmail.com";
        User user = User.builder()
                .email(email)
                .password("pass123")
                .build();

        performPostWithParameters("/signin", user)
                .andExpect(status().isOk())
                .andExpect(content().string("abs123"));
    }

    private ResultActions performPost(String url) throws Exception {
        return mockMvc.perform(post(url));
    }

    private ResultActions performPostWithParameters(String url, User user) throws Exception {
        return mockMvc.perform(post(url)
                .contentType(MediaType.APPLICATION_JSON)
                .content(toJson(user)));
    }

    private static String toJson(Object request) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        return ow.writeValueAsString(request);
    }
}

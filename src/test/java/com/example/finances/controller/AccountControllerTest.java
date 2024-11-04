package com.example.finances.controller;

import com.example.finances.util.JsonUtil;

import jakarta.transaction.Transactional;
import lombok.SneakyThrows;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Transactional
@SpringBootTest
@AutoConfigureMockMvc
@Sql("classpath:presentation/rest/account/data.sql")
class AccountControllerTest {
  @Autowired
  private MockMvc mvc;

  @Test
  @SneakyThrows
  @DisplayName("GET /account/user/1 returns user accounts")
  void testCase01() {
    var expectedResponse = JsonUtil.readJsonFromFile("classpath:presentation/rest/account/find_by_userId_response.json");

    mvc.perform(get("/account/user/1")
            .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(content().json(expectedResponse));
  }
}
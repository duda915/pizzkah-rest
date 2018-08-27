package com.mdud.pizzkahrest.datamodel.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mdud.pizzkahrest.datamodel.entity.Pizza;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.math.BigDecimal;
import java.nio.charset.Charset;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class PizzaRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private MediaType jsonMediaType = new MediaType("application", "json",
            Charset.forName("utf8"));

    private ObjectMapper objectMapper = new ObjectMapper();

    @Test
    public void getPizzaList() throws Exception {
        //default get
        mockMvc.perform(get("/api/pizza"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(jsonMediaType))
                .andExpect(jsonPath("$[0].id", is(notNullValue())))
                .andExpect(jsonPath("$[0].name", is(notNullValue())))
                .andExpect(jsonPath("$[0].ingredients", is(notNullValue())))
                .andExpect(jsonPath("$[0].price", is(notNullValue())))
                .andExpect(jsonPath("$[0].available", is(notNullValue())));
    }

    @Test
    public void addPizza() throws Exception {
        Pizza pizza = new Pizza("Test pizza", "Test ham", new BigDecimal(10.11), false);
        mockMvc.perform(post("/api/pizza")
                    .contentType(jsonMediaType)
                    .content(objectMapper.writeValueAsBytes(pizza)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(jsonMediaType));


        //false available get
        mockMvc.perform(get("/api/pizza?available=false"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(jsonMediaType))
                .andExpect(jsonPath("$[-1].id", is(notNullValue())))
                .andExpect(jsonPath("$[-1].name", is("Test pizza")))
                .andExpect(jsonPath("$[-1].ingredients", is("Test ham")))
                .andExpect(jsonPath("$[-1].price", is(10.11)))
                .andExpect(jsonPath("$[-1].available", is(false)));

    }

    @Test
    public void updatePizza() throws Exception{
        MvcResult mvcResult = mockMvc.perform(get("/api/pizza?available=false"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(jsonMediaType))
                .andReturn();

        String json = mvcResult.getResponse().getContentAsString();
        Pizza[] pizzas = objectMapper.readValue(json, Pizza[].class);
        Assert.assertNotNull(pizzas);

        int updateId = pizzas[0].getId().intValue();

        Pizza pizza = new Pizza("Test pizza", "Test ham", new BigDecimal(10.11), false);
        mockMvc.perform(put("/api/pizza?id="+updateId)
                    .contentType(jsonMediaType)
                    .content(objectMapper.writeValueAsBytes(pizza)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(jsonMediaType));

        mockMvc.perform(get("/api/pizza?available=false"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(jsonMediaType))
                .andExpect(jsonPath("$[0].id", is(updateId)))
                .andExpect(jsonPath("$[0].name", is("Test pizza")))
                .andExpect(jsonPath("$[0].ingredients", is("Test ham")))
                .andExpect(jsonPath("$[0].price", is(10.11)))
                .andExpect(jsonPath("$[0].available", is(false)));
    }
}
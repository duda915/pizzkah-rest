package com.mdud.pizzkahrest.datamodel.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mdud.pizzkahrest.datamodel.entity.OrderData;
import com.mdud.pizzkahrest.datamodel.entity.Pizza;
import com.mdud.pizzkahrest.datamodel.entity.PizzaOrder;
import com.mdud.pizzkahrest.datamodel.repository.PizzaOrderRepository;
import com.mdud.pizzkahrest.datamodel.repository.PizzaRepository;
import org.json.JSONArray;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Date;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.isA;
import static org.hamcrest.Matchers.notNullValue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class PizzaOrderRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private PizzaRepository pizzaRepository;

    @Autowired
    private PizzaOrderRepository pizzaOrderRepository;

    private MediaType jsonMediaType = new MediaType("application", "json",
            Charset.forName("utf8"));

    private ObjectMapper objectMapper = new ObjectMapper();

    @Test
    public void getPendingOrders() throws Exception {
        mockMvc.perform(get("/api/order"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(jsonMediaType))
                .andExpect(jsonPath("$[0].id", is(notNullValue())))
                .andExpect(jsonPath("$[0].date", is(notNullValue())))
                .andExpect(jsonPath("$[0].customerFirstName", is(notNullValue())))
                .andExpect(jsonPath("$[0].customerLastName", is(notNullValue())))
                .andExpect(jsonPath("$[0].phoneNumber", is(notNullValue())))
                .andExpect(jsonPath("$[0].address", is(notNullValue())))
                .andExpect(jsonPath("$[0].done", is(false)))
                .andExpect(jsonPath("$[0].orderDataList",isA(ArrayList.class)))
                .andExpect(jsonPath("$[0].totalPrice", is(notNullValue())));
    }

    @Test
    public void getCompletedOrders() throws Exception{
        mockMvc.perform(get("/api/order/completed"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(jsonMediaType))
                .andExpect(jsonPath("$[0].id", is(notNullValue())))
                .andExpect(jsonPath("$[0].date", is(notNullValue())))
                .andExpect(jsonPath("$[0].customerFirstName", is(notNullValue())))
                .andExpect(jsonPath("$[0].customerLastName", is(notNullValue())))
                .andExpect(jsonPath("$[0].phoneNumber", is(notNullValue())))
                .andExpect(jsonPath("$[0].address", is(notNullValue())))
                .andExpect(jsonPath("$[0].done", is(true)))
                .andExpect(jsonPath("$[0].orderDataList",isA(ArrayList.class)))
                .andExpect(jsonPath("$[0].totalPrice", is(notNullValue())));
    }

    @Test
    public void addOrder() throws Exception{
        Pizza pizza = new Pizza("TestPizza", "TestHam", new BigDecimal(25.55), true);
        pizza = pizzaRepository.save(pizza);

        PizzaOrder pizzaOrder = new PizzaOrder(new Date(), "TesterFirst",
                "TesterLast", "555555555",
                "Test 123", false);

        pizzaOrder.getOrderDataList().add(new OrderData(pizzaOrder, pizza));

        mockMvc.perform(post("/api/order")
                    .contentType(jsonMediaType)
                    .content(objectMapper.writeValueAsBytes(pizzaOrder)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(jsonMediaType));

        mockMvc.perform(get("/api/order"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(jsonMediaType))
                .andExpect(jsonPath("$[-1].id", is(notNullValue())))
                .andExpect(jsonPath("$[-1].date", is(notNullValue())))
                .andExpect(jsonPath("$[-1].customerFirstName", is("TesterFirst")))
                .andExpect(jsonPath("$[-1].customerLastName", is("TesterLast")))
                .andExpect(jsonPath("$[-1].phoneNumber", is("555555555")))
                .andExpect(jsonPath("$[-1].address", is("Test 123")))
                .andExpect(jsonPath("$[-1].done", is(false)))
                .andExpect(jsonPath("$[-1].orderDataList[-1].pizza.name",is("TestPizza")))
                .andExpect(jsonPath("$[-1].totalPrice", is(25.55)));
    }

    @Test
    public void markOrderAsDone() throws Exception{
        Pizza pizza = new Pizza("TestPizza", "TestHam", new BigDecimal(25.55), true);
        pizza = pizzaRepository.save(pizza);
        PizzaOrder pizzaOrder = new PizzaOrder(new Date(), "TesterFirst",
                "TesterLast", "444444444",
                "Test 123", false);
        pizzaOrder.getOrderDataList().add(new OrderData(pizzaOrder, pizza));

        pizzaOrder = pizzaOrderRepository.save(pizzaOrder);

        mockMvc.perform(put("/api/order")
                    .contentType(jsonMediaType)
                    .content(objectMapper.writeValueAsBytes(pizzaOrder.getId())))
                .andExpect(status().isOk())
                .andExpect(content().contentType(jsonMediaType));

        mockMvc.perform(get("/api/order/completed"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(jsonMediaType))
                .andExpect(jsonPath("$[-1].id", is(notNullValue())))
                .andExpect(jsonPath("$[-1].date", is(notNullValue())))
                .andExpect(jsonPath("$[-1].customerFirstName", is(notNullValue())))
                .andExpect(jsonPath("$[-1].customerLastName", is(notNullValue())))
                .andExpect(jsonPath("$[-1].phoneNumber", is("444444444")))
                .andExpect(jsonPath("$[-1].address", is(notNullValue())))
                .andExpect(jsonPath("$[-1].done", is(true)))
                .andExpect(jsonPath("$[-1].orderDataList",isA(ArrayList.class)))
                .andExpect(jsonPath("$[-1].totalPrice", is(notNullValue())));
    }
}
package com.mdud.pizzkahrest.datamodel;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "pizza_order")
public class PizzaOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Date date;

    @Column(name = "customer_firstname")
    private String customerFirstName;

    @Column(name = "customer_lastname")
    private String customerLastName;

    @Column(name = "phone_number")
    private String phoneNumber;
    private String address;

    @Column(name = "done")
    private Boolean isDone;

    @OneToMany(mappedBy = "pizzaOrder", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderData> orderDataList = new ArrayList<>();


    public PizzaOrder() {}

    public PizzaOrder(Date date, String customerFirstName, String customerLastName,
                      String phoneNumber, String address, Boolean isDone) {
        this.date = date;
        this.customerFirstName = customerFirstName;
        this.customerLastName = customerLastName;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.isDone = isDone;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getCustomerFirstName() {
        return customerFirstName;
    }

    public void setCustomerFirstName(String customerFirstName) {
        this.customerFirstName = customerFirstName;
    }

    public String getCustomerLastName() {
        return customerLastName;
    }

    public void setCustomerLastName(String customerLastName) {
        this.customerLastName = customerLastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Boolean getDone() {
        return isDone;
    }

    public void setDone(Boolean done) {
        isDone = done;
    }

    public List<OrderData> getOrderDataList() {
        return orderDataList;
    }

    public void setOrderDataList(List<OrderData> orderDataList) {
        this.orderDataList = orderDataList;
    }
}

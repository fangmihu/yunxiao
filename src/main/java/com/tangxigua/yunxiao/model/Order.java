/*******************************************************
 * Copyright (C) 2018 fangmihu - All Rights Reserved
 *
 * Created by fangmihu <github.com/fangmihu>
 * in 2018/7/7
 *******************************************************/
package com.tangxigua.yunxiao.model;

import javax.validation.constraints.NotNull;
import java.util.Date;

public class Order {

    private Long id;

    @NotNull
    private Long customerId;

    @NotNull
    private Integer phoneNumber;

    @NotNull
    private Address Address;

    private Double totalPrice;

    //支付状态 0：已经支付 1：未支付
    private Integer status;

    private Date createDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public Integer getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(Integer phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public com.tangxigua.yunxiao.model.Address getAddress() {
        return Address;
    }

    public void setAddress(com.tangxigua.yunxiao.model.Address address) {
        Address = address;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}

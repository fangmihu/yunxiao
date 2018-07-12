
package com.tangxigua.yunxiao.model;

public enum CustomerCategoryEnum {

    ADVERTISER(0,"广告主"),
    PRODUCT_LINE(1,"产品线"),
    AGENT(2,"代理商");

    private int id;
    private String name;

    CustomerCategoryEnum(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

}

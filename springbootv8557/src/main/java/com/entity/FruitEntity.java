package com.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.math.BigInteger;

@TableName("Fruit")
public class FruitEntity {

    @TableId
    private BigInteger id;

    @TableField("fruit_name")
    private String fruit_name;

    @TableField("image")
    private String image;

    @TableField("fruit_type")
    private String fruit_type;

    @TableField("introduce")
    private String introduce;

    @TableField("efficacy")
    private String efficacy;


    private Integer calories;

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public String getFruit_name() {
        return fruit_name;
    }

    public void setFruit_name(String fruit_name) {
        this.fruit_name = fruit_name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getFruit_type() {
        return fruit_type;
    }

    public void setFruit_type(String fruit_type) {
        this.fruit_type = fruit_type;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public String getEfficacy() {
        return efficacy;
    }

    public void setEfficacy(String efficacy) {
        this.efficacy = efficacy;
    }

    public Integer getCalories() {
        return calories;
    }

    public void setCalories(Integer calories) {
        this.calories = calories;
    }
}

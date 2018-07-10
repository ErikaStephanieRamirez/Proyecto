package com.ramirez.proyecto.RoomArchitecture.Entities;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import java.io.Serializable;

@Entity(tableName = "Pollos_table")
public class PolloEntity implements Serializable{

    @NonNull
    @PrimaryKey
    @ColumnInfo(name = "id")
    private String id;

    @ColumnInfo(name = "name")
    private String name;

    @ColumnInfo(name = "price")
    private Float price;

    @ColumnInfo(name = "priceCombo")
    private Float priceCombo;

    @ColumnInfo(name = "productImage")
    private String productImage;

    public PolloEntity(@NonNull String id, String name, Float price, Float priceCombo, String productImage) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.priceCombo = priceCombo;
        this.productImage = productImage;
    }

    @NonNull
    public String getId() {
        return id;
    }

    public void setId(@NonNull String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public Float getPriceCombo() {
        return priceCombo;
    }

    public void setPriceCombo(Float priceCombo) {
        this.priceCombo = priceCombo;
    }

    public String getProductImage() {
        return productImage;
    }

    public void setProductImage(String productImage) {
        this.productImage = productImage;
    }
}

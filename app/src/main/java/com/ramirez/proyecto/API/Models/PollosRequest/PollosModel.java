package com.ramirez.proyecto.API.Models.PollosRequest;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PollosModel {

    @SerializedName("_id")
    @Expose
    private String id;

    @SerializedName("name")
    @Expose
    private String nombre;

    @SerializedName("price")
    @Expose
    private Float price;

    @SerializedName("priceCombo")
    @Expose
    private Float priceCombo;

    @SerializedName("productImage")
    @Expose
    private String productImage;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
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

package com.ramirez.proyecto.API.Models.PupusasRequest;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PupusasModel {

    @SerializedName("_id")
    @Expose
    private String id;

    @SerializedName("name")
    @Expose
    private String nombre;

    @SerializedName("price")
    @Expose
    private Float price;

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

    public String getProductImage() {
        return productImage;
    }

    public void setProductImage(String productImage) {
        this.productImage = productImage;
    }
}

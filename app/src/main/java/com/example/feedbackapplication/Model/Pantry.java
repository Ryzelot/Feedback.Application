package com.example.feedbackapplication.Model;

public class Pantry {

    Integer idpantry;
    String itempantry;
    String itembrandpantry;
    String itemqtypantry;

    public Pantry() {
    }

    public Pantry(Integer idpantry, String itempantry, String itembrandpantry, String itemqtypantry) {
        this.idpantry = idpantry;
        this.itempantry = itempantry;
        this.itembrandpantry = itembrandpantry;
        this.itemqtypantry = itemqtypantry;
    }

    public Integer getIdpantry() {
        return idpantry;
    }

    public void setIdpantry(Integer idpantry) {
        this.idpantry = idpantry;
    }

    public String getItempantry() {
        return itempantry;
    }

    public void setItempantry(String itempantry) {
        this.itempantry = itempantry;
    }

    public String getItembrandpantry() {
        return itembrandpantry;
    }

    public void setItembrandpantry(String itembrandpantry) {
        this.itembrandpantry = itembrandpantry;
    }

    public String getItemqtypantry() {
        return itemqtypantry;
    }

    public void setItemqtypantry(String itemqtypantry) {
        this.itemqtypantry = itemqtypantry;
    }
}

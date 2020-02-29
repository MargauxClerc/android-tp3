package fr.clerc.myapplication.model;

import com.google.gson.annotations.SerializedName;

public class Properties {
    private int id;
    @SerializedName("NOM")
    private String name;
    @SerializedName("RUE")
    private String street;
    @SerializedName("CODEPOSTAL")
    private int postalCode;
    @SerializedName("COMMUNE")
    private String city;
    @SerializedName("TYPE")
    private String type;
    @SerializedName("CODE")
    private int code;
    @SerializedName("LaMetro")
    private Boolean isMetro;
    @SerializedName("LeGresivaudan")
    private Boolean isGresivaudan;
    @SerializedName("PaysVoironnais")
    private Boolean isPaysVoironnais;

    public Properties(int id, String name, String street, int postalCode, String city, String type, int code, Boolean isMetro, Boolean isGresivaudan, Boolean isPaysVoironnais) {
        this.id = id;
        this.name = name;
        this.street = street;
        this.postalCode = postalCode;
        this.city = city;
        this.type = type;
        this.code = code;
        this.isMetro = isMetro;
        this.isGresivaudan = isGresivaudan;
        this.isPaysVoironnais = isPaysVoironnais;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public int getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(int postalCode) {
        this.postalCode = postalCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Boolean getMetro() {
        return isMetro;
    }

    public void setMetro(Boolean metro) {
        isMetro = metro;
    }

    public Boolean getGresivaudan() {
        return isGresivaudan;
    }

    public void setGresivaudan(Boolean gresivaudan) {
        isGresivaudan = gresivaudan;
    }

    public Boolean getPaysVoironnais() {
        return isPaysVoironnais;
    }

    public void setPaysVoironnais(Boolean paysVoironnais) {
        isPaysVoironnais = paysVoironnais;
    }
}

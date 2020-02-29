package fr.clerc.myapplication.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class FeatureCollection {
    private String type;
    @SerializedName("features")
    private ArrayList<Feature> featureList;

    public FeatureCollection(String type, ArrayList<Feature> featureList) {
        this.type = type;
        this.featureList = featureList;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public ArrayList<Feature> getFeatureList() {
        return featureList;
    }

    public void setFeatureList(ArrayList<Feature> featureList) {
        this.featureList = featureList;
    }
}


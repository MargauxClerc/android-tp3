package fr.clerc.myapplication.model;

import java.util.ArrayList;

public class Geometry {
    private String type;
    private ArrayList<Float> coordinates;

    public Geometry(String type, ArrayList<Float> coordinates) {
        this.type = type;
        this.coordinates = coordinates;
    }
}

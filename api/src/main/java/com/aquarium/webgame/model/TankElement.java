package com.aquarium.webgame.model;

import java.awt.geom.Point2D;

public class TankElement {
    private int id;
    private String name;
    private Point2D.Double position;


    public TankElement() {
    }

    public TankElement(int id, String name) {
        this.id = id;
        this.name = name;
        this.position = new Point2D.Double(0, 0); // Default position
    }

    public TankElement(int id, String name, Point2D.Double position) {
        this.id = id;
        this.name = name;
        this.position = position;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }

}
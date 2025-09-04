package com.aquarium.webgame.model;

import java.awt.geom.Point2D;

public class TankEntity extends TankElement {
    
    private double speed;
    private Point2D.Double targetPoint;

    public TankEntity(int id, String name, double speed) {
        super(id, name);
        this.speed = speed;
    }

    private void stepToPoint(Point2D.Double targetPoint) {
        Point2D.Double currentPosition = super.getPosition();
        double deltaX = targetPoint.x - currentPosition.x;
        double deltaY = targetPoint.y - currentPosition.y;
        double distance = Math.sqrt(deltaX * deltaX + deltaY * deltaY);
        if (distance <= speed) {
            super.setPosition(targetPoint);
        } else {
            double newX = currentPosition.x + deltaX * (speed / distance);
            double newY = currentPosition.y + deltaY * (speed / distance);
            super.setPosition(new Point2D.Double(newX, newY));
        }
    }

    public void heartBeat() {
        if (targetPoint != null) {
            stepToPoint(targetPoint);
        }

        //stop moving once target is reached
        if (targetPoint.equals(super.getPosition())) {
            targetPoint = null;
        }
    }

    public void moveTo(Point2D.Double targetPoint) {
        this.targetPoint = targetPoint;
    }
}
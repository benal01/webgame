package com.aquarium.webgame.service;

import java.util.HashSet;
import java.util.Set;

import com.aquarium.webgame.model.Tank;

public class TankService {
    private Set <Tank> tanks = new HashSet<>();

    public TankService() {
    }

    public Set<Tank> getTankElements() {
        return tanks;
    }
    public void addTankElement(Tank tank) {
        this.tanks.add(tank);
    }
}

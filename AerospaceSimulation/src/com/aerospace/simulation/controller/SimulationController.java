package com.aerospace.simulation.controller;

import com.aerospace.simulation.Simulation;
import com.aerospace.simulation.model.AerospaceVehicle;

import java.util.ArrayList;
import java.util.List;

public class SimulationController implements Simulation {
    private List<AerospaceVehicle> vehicles;

    public SimulationController() {
        this.vehicles = new ArrayList<>();
    }

    @Override
    public void initialize() {
        // Initialize the simulation with default parameters or vehicles
        System.out.println("Simulation initialized.");
    }

    @Override
    public void start() {
        // Start the simulation
        System.out.println("Simulation started.");
        runSimulation(100, 0.1);
    }

    @Override
    public void stop() {
        // Stop the simulation
        System.out.println("Simulation stopped.");
    }

    @Override
    public void reset() {
        // Reset the simulation to initial state
        vehicles.clear();
        System.out.println("Simulation reset.");
    }

    public void addVehicle(AerospaceVehicle vehicle) {
        vehicles.add(vehicle);
    }

    public List<AerospaceVehicle> getVehicles() {
        return vehicles;
    }

    private void runSimulation(int steps, double timeStep) {
        for (int i = 0; i < steps; i++) {
            for (AerospaceVehicle vehicle : vehicles) {
                vehicle.updateState(timeStep);
                System.out.printf("Step %d: Vehicle %s - Velocity: %.2f m/s, Altitude: %.2f m%n",
                        i, vehicle.getName(), vehicle.getVelocity(), vehicle.getAltitude());
            }
        }
    }
}
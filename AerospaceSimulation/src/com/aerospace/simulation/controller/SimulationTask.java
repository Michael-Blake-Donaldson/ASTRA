package com.aerospace.simulation.controller;

import com.aerospace.simulation.model.AerospaceVehicle;
import javafx.concurrent.Task;
import javafx.application.Platform;

import java.util.List;

public class SimulationTask extends Task<Void> {
    private final List<AerospaceVehicle> vehicles;
    private final SimulationUIController uiController;
    private final double timeStep;
    private final int steps;

    public SimulationTask(List<AerospaceVehicle> vehicles, SimulationUIController uiController, double timeStep, int steps) {
        this.vehicles = vehicles;
        this.uiController = uiController;
        this.timeStep = timeStep;
        this.steps = steps;
    }

    @Override
    protected Void call() {
        for (int i = 0; i < steps; i++) {
            for (AerospaceVehicle vehicle : vehicles) {
                vehicle.updateState(timeStep);
                updateMessage(vehicle.getName() + " - Velocity: " + vehicle.getVelocity() + " m/s, Altitude: " + vehicle.getAltitude() + " m");
            }

            try {
                Thread.sleep((long) (timeStep * 1000));
            } catch (InterruptedException e) {
                if (isCancelled()) {
                    break;
                }
            }

            Platform.runLater(uiController::updateCanvas);
        }
        return null;
    }
}

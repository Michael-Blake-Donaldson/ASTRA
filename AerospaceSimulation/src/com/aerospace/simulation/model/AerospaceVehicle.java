package com.aerospace.simulation.model;

public class AerospaceVehicle {
    private String name;
    private double mass; // in kilograms
    private double thrust; // in newtons
    private double dragCoefficient; // dimensionless
    private double liftCoefficient; // dimensionless
    private double velocity; // in meters per second
    private double altitude; // in meters

    public AerospaceVehicle(String name, double mass, double thrust, double dragCoefficient, double liftCoefficient) {
        this.name = name;
        this.mass = mass;
        this.thrust = thrust;
        this.dragCoefficient = dragCoefficient;
        this.liftCoefficient = liftCoefficient;
        this.velocity = 0;
        this.altitude = 0;
    }

    public String getName() {
        return name;
    }

    public double getMass() {
        return mass;
    }

    public double getThrust() {
        return thrust;
    }

    public double getDragCoefficient() {
        return dragCoefficient;
    }

    public double getLiftCoefficient() {
        return liftCoefficient;
    }

    public double getVelocity() {
        return velocity;
    }

    public double getAltitude() {
        return altitude;
    }

    public void updateState(double timeStep) {
        double gravity = 9.81; // m/s^2
        double airDensity = 1.225; // kg/m^3, at sea level
        double wingArea = 10; // m^2, assumed constant for simplicity

        double drag = 0.5 * airDensity * velocity * velocity * dragCoefficient * wingArea;
        double lift = 0.5 * airDensity * velocity * velocity * liftCoefficient * wingArea;
        double netForce = thrust - drag - (mass * gravity - lift);

        double acceleration = netForce / mass;
        velocity += acceleration * timeStep;
        altitude += velocity * timeStep;
    }
}
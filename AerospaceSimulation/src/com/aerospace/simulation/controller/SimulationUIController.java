package com.aerospace.simulation.controller;

import com.aerospace.simulation.model.AerospaceVehicle;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

public class SimulationUIController {
    @FXML
    private ListView<String> vehicleListView;
    @FXML
    private Label simulationStatusLabel;
    @FXML
    private TextField nameField;
    @FXML
    private TextField massField;
    @FXML
    private TextField thrustField;
    @FXML
    private TextField dragField;
    @FXML
    private TextField liftField;
    @FXML
    private Canvas simulationCanvas;

    private ObservableList<String> vehicleList = FXCollections.observableArrayList();
    private SimulationController simulationController;
    private SimulationTask simulationTask;
    private double timeStep = 0.1;
    private int steps = 100;

    public void initialize() {
        simulationController = new SimulationController();
        vehicleListView.setItems(vehicleList);
    }

    public void addVehicleToList(AerospaceVehicle vehicle) {
        vehicleList.add(vehicle.getName() + " - Velocity: " + vehicle.getVelocity() + " m/s, Altitude: " + vehicle.getAltitude() + " m");
    }

    public void clearVehicleList() {
        vehicleList.clear();
    }

    @FXML
    private void addVehicle() {
        String name = nameField.getText();
        double mass = Double.parseDouble(massField.getText());
        double thrust = Double.parseDouble(thrustField.getText());
        double drag = Double.parseDouble(dragField.getText());
        double lift = Double.parseDouble(liftField.getText());

        AerospaceVehicle vehicle = new AerospaceVehicle(name, mass, thrust, drag, lift);
        simulationController.addVehicle(vehicle);
        addVehicleToList(vehicle);

        nameField.clear();
        massField.clear();
        thrustField.clear();
        dragField.clear();
        liftField.clear();
    }

    @FXML
    private void startSimulation() {
        if (simulationTask != null && simulationTask.isRunning()) {
            return; // Prevent starting a new task while another is running
        }

        simulationTask = new SimulationTask(simulationController.getVehicles(), this, timeStep, steps);

        simulationTask.messageProperty().addListener((obs, oldMessage, newMessage) -> Platform.runLater(() -> {
            vehicleList.clear();
            for (AerospaceVehicle vehicle : simulationController.getVehicles()) {
                addVehicleToList(vehicle);
            }
            updateCanvas();
        }));

        simulationTask.setOnRunning(event -> updateStatusLabel("Running"));
        simulationTask.setOnSucceeded(event -> updateStatusLabel("Completed"));
        simulationTask.setOnCancelled(event -> updateStatusLabel("Cancelled"));
        simulationTask.setOnFailed(event -> updateStatusLabel("Failed"));

        Thread simulationThread = new Thread(simulationTask);
        simulationThread.setDaemon(true);
        simulationThread.start();
    }

    @FXML
    private void stopSimulation() {
        if (simulationTask != null && simulationTask.isRunning()) {
            simulationTask.cancel();
        }
    }

    @FXML
    private void resetSimulation() {
        stopSimulation();
        simulationController.reset();
        clearVehicleList();
        updateCanvas();
        updateStatusLabel("Idle");
    }

    @FXML
    private void speedUpSimulation() {
        timeStep = Math.max(0.01, timeStep / 2); // Ensure timeStep doesn't go below 0.01
    }

    @FXML
    private void slowDownSimulation() {
        timeStep = Math.min(1.0, timeStep * 2); // Ensure timeStep doesn't go above 1.0
    }

    private void updateStatusLabel(String status) {
        simulationStatusLabel.setText("Simulation Status: " + status);
    }

    public void updateCanvas() {
        GraphicsContext gc = simulationCanvas.getGraphicsContext2D();
        gc.clearRect(0, 0, simulationCanvas.getWidth(), simulationCanvas.getHeight());

        gc.setStroke(Color.BLACK);
        gc.strokeLine(0, simulationCanvas.getHeight() / 2, simulationCanvas.getWidth(), simulationCanvas.getHeight() / 2);

        double canvasWidth = simulationCanvas.getWidth();
        double canvasHeight = simulationCanvas.getHeight();
        double scaleFactorX = canvasWidth / (steps * timeStep * 10); // Adjust scaling factor for better view
        double scaleFactorY = canvasHeight / 100; // Assuming a max altitude of 100 meters for better view

        for (AerospaceVehicle vehicle : simulationController.getVehicles()) {
            double x = vehicle.getVelocity() * scaleFactorX;
            double y = canvasHeight / 2 - (vehicle.getAltitude() * scaleFactorY);

            gc.setFill(Color.BLUE);
            gc.fillOval(x, y, 10, 10);
            gc.fillText(vehicle.getName(), x, y - 10);
        }
    }
}
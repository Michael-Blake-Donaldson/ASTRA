# ASTRA
Aerospace Simulation and Training Resource Application

## Overview
The Aerospace Simulation Software is a Java-based application that simulates the dynamics of aerospace vehicles. It provides a graphical user interface (GUI) using JavaFX to visualize the movement and parameters of the vehicles in real-time.

## Features
- Add multiple aerospace vehicles with custom parameters.
- Simulate the dynamics of the vehicles including velocity and altitude changes.
- Real-time graphical visualization of the vehicle's movements.
- Controls to start, stop, reset, speed up, and slow down the simulation.

## Technologies Used
- Java
- JavaFX

## Project Structure
AerospaceSimulation/
│
├── src/
│ ├── com/
│ │ ├── aerospace/
│ │ │ ├── simulation/
│ │ │ │ ├── Main.java
│ │ │ │ ├── Simulation.java
│ │ │ │ ├── controller/
│ │ │ │ │ ├── SimulationController.java
│ │ │ │ │ ├── SimulationTask.java
│ │ │ │ │ ├── SimulationUIController.java
│ │ │ │ ├── model/
│ │ │ │ │ ├── AerospaceVehicle.java
│ │ │ │ ├── view/
│ │ │ │ │ ├── MainApp.java
│ │ │ │ │ ├── SimulationUI.fxml

### Prerequisites
- Java Development Kit (JDK) 11 or higher
- JavaFX SDK

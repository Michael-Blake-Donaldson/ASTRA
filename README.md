
# ASTRA 🚀

### Aerospace Simulation and Training Resource Application

**A Java-based aerospace simulation application for modeling vehicle dynamics, visualizing movement, and exploring physics-driven software development.**

ASTRA is a desktop application developed using Java and JavaFX that allows users to create aerospace vehicles, configure physical properties, and observe simulated changes in velocity and altitude.

The project combines object-oriented programming, basic physics modeling, graphical user interface development, and asynchronous task execution to demonstrate the integration of computational logic with interactive software.

**Project Status:** Functional Prototype / Educational Simulation

---

## Overview

Aerospace simulations provide an opportunity to explore the relationship between physical forces, mathematical calculations, and software systems.

ASTRA was developed to demonstrate how aerospace vehicle behavior can be represented through computational models and visualized using a desktop application.

Users can define vehicles using configurable physical parameters, execute simulation steps, and observe how the simulated vehicles respond over time.

The application is designed as an educational software project rather than a professional aerospace engineering tool.

### Project Objectives

- Model simplified aerospace vehicle dynamics
- Apply object-oriented programming principles
- Implement physics-based computational logic
- Develop an interactive JavaFX desktop interface
- Visualize changes in vehicle velocity and altitude
- Support multiple vehicles within a simulation
- Explore background task execution and UI updates
- Organize application components into maintainable classes

---

## Key Features

### Aerospace Vehicle Configuration

Users can create multiple aerospace vehicles with configurable physical characteristics.

Each vehicle includes:

- **Name:** Unique display identifier entered by the user
- **Mass:** Vehicle mass measured in kilograms
- **Thrust:** Applied force measured in newtons
- **Drag Coefficient:** Parameter representing aerodynamic resistance
- **Lift Coefficient:** Parameter representing aerodynamic lift
- **Velocity:** Dynamically calculated during simulation
- **Altitude:** Updated as the simulation progresses

Each vehicle is represented by an instance of the `AerospaceVehicle` class.

### Physics-Based Simulation

ASTRA implements a simplified mathematical model that calculates acceleration based on forces acting on each vehicle.

The model incorporates:

- Gravitational force
- Engine thrust
- Aerodynamic drag
- Aerodynamic lift
- Vehicle mass
- Numerical time-step integration

Vehicle velocity and altitude are updated during each simulation step.

### Real-Time Visualization

The JavaFX interface includes a graphical canvas used to represent vehicle movement.

During simulation execution, the application updates the canvas based on calculated velocity and altitude values.

Visual elements include:

- Vehicle position indicators
- Vehicle name labels
- A reference line
- Coordinate scaling
- Repeated graphical updates

The visualization uses a simplified two-dimensional representation rather than a full 3D flight environment.

### Multiple Vehicle Support

ASTRA supports multiple vehicles within a single simulation.

Each vehicle maintains its own physical parameters and calculated state.

The simulation iterates through the vehicle collection and updates each vehicle individually.

### Simulation Controls

The graphical interface provides controls for managing simulation execution.

| Control | Purpose |
|---|---|
| Add Vehicle | Creates a vehicle using the entered parameters |
| Start Simulation | Starts the simulation task |
| Stop Simulation | Requests cancellation of the active task |
| Reset Simulation | Clears vehicles and resets the interface |
| Speed Up | Reduces the configured time step |
| Slow Down | Increases the configured time step |

The speed controls modify the time step used when creating subsequent simulation tasks.

### Background Processing

ASTRA uses JavaFX's `Task` class to execute simulation calculations outside the main interface thread.

This helps prevent long-running simulation operations from blocking the graphical interface.

The implementation uses:

- Background worker threads
- JavaFX task lifecycle management
- Task cancellation
- UI status updates
- `Platform.runLater()` for scheduling interface updates

---

## Technology Stack

| Technology | Purpose |
|---|---|
| Java | Primary programming language |
| JavaFX | Desktop graphical user interface |
| FXML | Declarative interface layout |
| Java Collections | Vehicle management |
| Java Concurrency | Background simulation execution |
| Object-Oriented Programming | Application architecture |
| Numerical Integration | Simulation state calculations |

### Development Concepts

The project demonstrates practical experience with:

- Classes and objects
- Encapsulation
- Interfaces
- Collections
- Event-driven programming
- Multithreading
- Mathematical modeling
- GUI development
- Separation of concerns

---

## Application Architecture

ASTRA uses a modular package structure that separates vehicle data, simulation logic, interface controls, and graphical presentation.

### Project Structure

```text
ASTRA/
├── AerospaceSimulation/
│   ├── bin/
│   └── src/
│       └── com/
│           └── aerospace/
│               └── simulation/
│                   ├── Main.java
│                   ├── Simulation.java
│                   │
│                   ├── controller/
│                   │   ├── SimulationController.java
│                   │   ├── SimulationTask.java
│                   │   └── SimulationUIController.java
│                   │
│                   ├── model/
│                   │   └── AerospaceVehicle.java
│                   │
│                   └── view/
│                       ├── MainApp.java
│                       └── SimulationUI.fxml
│
├── LICENSE
└── README.md
```

### Core Components

**AerospaceVehicle.java**

Represents an individual aerospace vehicle.

Stores physical properties and provides the `updateState()` method for calculating velocity and altitude changes.

**Simulation.java**

Defines an interface for basic simulation lifecycle operations:

- Initialize
- Start
- Stop
- Reset

**SimulationController.java**

Manages the vehicle collection and provides simulation lifecycle methods.

It also includes a synchronous simulation loop for updating and displaying vehicle state information.

**SimulationTask.java**

Executes simulation calculations through a JavaFX background task.

Schedules graphical updates and supports cancellation of the active task.

**SimulationUIController.java**

Connects the graphical interface with application functionality.

Handles user input, vehicle creation, simulation controls, status updates, and canvas rendering.

**MainApp.java**

Initializes the JavaFX application and loads the FXML interface.

**SimulationUI.fxml**

Defines the application's graphical layout, including input fields, buttons, vehicle listings, and the simulation canvas.

---

## Physics and Simulation Model

ASTRA uses a simplified force-based model to calculate changes in vehicle motion.

### Gravitational Force

The gravitational force acting on a vehicle is calculated as:

```text
F_gravity = mass × gravity
```

Where:

- `mass` is measured in kilograms
- `gravity` is 9.81 m/s²

### Aerodynamic Drag

Drag is calculated using:

```text
F_drag = 0.5 × airDensity × velocity²
         × dragCoefficient × wingArea
```

### Aerodynamic Lift

Lift is calculated using:

```text
F_lift = 0.5 × airDensity × velocity²
         × liftCoefficient × wingArea
```

### Net Force

The current simulation combines the calculated forces:

```text
F_net = thrust - drag - (mass × gravity - lift)
```

### Acceleration

Acceleration is calculated using Newton's second law:

```text
acceleration = netForce / mass
```

### Velocity Update

```text
velocity += acceleration × timeStep
```

### Altitude Update

```text
altitude += velocity × timeStep
```

These calculations are performed repeatedly as the simulation advances.

### Model Assumptions and Limitations

The current implementation makes several simplifying assumptions:

- Gravity remains constant at 9.81 m/s²
- Air density remains constant at 1.225 kg/m³
- Wing area is fixed at 10 m²
- Vehicle motion uses a simplified scalar model
- Forces are combined into one net-force calculation
- The model does not simulate full six-degree-of-freedom flight dynamics
- Atmospheric conditions do not change with altitude
- Numerical integration uses a basic time-step approach

Because of these assumptions, ASTRA is intended for educational experimentation rather than accurate aircraft performance prediction.

---

## Getting Started

### Prerequisites

Before running ASTRA, install:

- Java Development Kit (JDK) 11 or newer
- JavaFX SDK compatible with your JDK
- Git
- A Java IDE such as Eclipse, IntelliJ IDEA, or Visual Studio Code with Java extensions

### 1. Clone the Repository

```bash
git clone https://github.com/Michael-Blake-Donaldson/ASTRA.git
```

Navigate into the project:

```bash
cd ASTRA/AerospaceSimulation
```

### 2. Configure JavaFX

Download the JavaFX SDK from:

https://openjfx.io/

Configure the SDK as a library in your preferred Java IDE.

Depending on your development environment, you may need to specify the JavaFX module path and required modules.

Example VM arguments:

```text
--module-path "/path/to/javafx-sdk/lib"
--add-modules javafx.controls,javafx.fxml
```

Replace the example path with the actual location of your JavaFX installation.

### 3. Run the Application

Open the project in your Java IDE.

Locate the entry point:

```text
src/com/aerospace/simulation/Main.java
```

Run the `Main` class with the JavaFX dependencies configured.

The application launches a JavaFX window containing the simulation controls and visualization canvas.

### 4. Create a Vehicle

Enter the following parameters:

- Vehicle name
- Mass
- Thrust
- Drag coefficient
- Lift coefficient

Select **Add Vehicle**.

The vehicle will appear in the vehicle list.

### 5. Start the Simulation

Select **Start Simulation** to begin calculating vehicle movement.

Observe the vehicle's updated velocity and altitude in the interface.

Use the available controls to stop, reset, or modify subsequent simulation runs.

---

## Example Simulation

The following values demonstrate an example vehicle configuration.

| Parameter | Example Value |
|---|---|
| Vehicle Name | Test Vehicle 01 |
| Mass | 1,000 kg |
| Thrust | 15,000 N |
| Drag Coefficient | 0.30 |
| Lift Coefficient | 0.50 |
| Initial Velocity | 0 m/s |
| Initial Altitude | 0 m |
| Time Step | 0.1 seconds |

The simulation calculates acceleration using the combined forces and updates the vehicle state during each iteration.

Actual behavior depends on the provided vehicle parameters and simplified force calculations.

---

## Software Engineering Concepts

### Object-Oriented Design

ASTRA uses separate classes to represent vehicles, simulation operations, and graphical interface behavior.

This organization helps keep the application's responsibilities understandable and maintainable.

### Interface-Based Programming

The `Simulation` interface establishes a contract for basic simulation lifecycle operations.

This demonstrates the use of abstraction to define expected behavior.

### Concurrent Programming

JavaFX background tasks allow simulation operations to execute separately from the primary interface thread.

The project demonstrates task lifecycle handling, cancellation, and UI thread coordination.

### Numerical Computing

The vehicle model uses mathematical formulas and repeated time-step calculations to approximate changes in motion.

This demonstrates the translation of mathematical concepts into executable software.

### Event-Driven Development

JavaFX controls trigger application behaviors through event handlers.

User actions initiate vehicle creation, simulation execution, cancellation, and interface updates.

---

## Current Limitations

ASTRA is an educational prototype and has several areas that require further development.

Known limitations include:

- Limited validation of numerical vehicle inputs
- Simplified aerodynamic calculations
- Constant atmospheric assumptions
- Basic two-dimensional visualization
- No persistent vehicle or simulation storage
- No automated test suite included in the repository
- No packaged installer or automated build configuration
- Limited error handling for invalid simulation parameters
- Simulation speed adjustments apply to newly created tasks rather than dynamically updating an active task

These limitations provide opportunities for improving both the simulation model and application architecture.

---

## Future Improvements

Potential development priorities include:

### Advanced Physics

- More realistic atmospheric modeling
- Altitude-dependent air density
- Improved aerodynamic force calculations
- Separate horizontal and vertical motion
- Alternative numerical integration methods

### Visualization

- Enhanced vehicle movement tracking
- Graphs for velocity and altitude over time
- Interactive flight paths
- Additional interface controls
- Improved graphical scaling

### Application Functionality

- Save and load vehicle configurations
- Export simulation results
- Compare multiple simulation runs
- Add predefined aerospace vehicles
- Support configurable initial conditions

### Engineering Improvements

- Introduce automated unit tests
- Improve numerical input validation
- Add structured exception handling
- Integrate a Java build system
- Refine simulation cancellation and lifecycle management
- Improve class documentation
- Expand architecture modularity

---

## Project Takeaways

ASTRA provided an opportunity to apply software engineering concepts to a simulation-oriented application.

The project brings together several areas of development:

- Modeling real-world concepts through software
- Designing object-oriented systems
- Building interactive desktop applications
- Working with mathematical calculations
- Managing asynchronous tasks
- Connecting application logic with visual output
- Structuring a Java codebase into logical components

The project also established a foundation for exploring more advanced simulation, visualization, and numerical computing techniques.

---

## Author

**Michael Blake Donaldson**

Computer Science Student  
Software Engineering Concentration

**GitHub:**  
https://github.com/Michael-Blake-Donaldson

**Portfolio:**  
https://michael-blake-donaldson.github.io/MichaelDonaldson_Portfolio/

---

## License

This project is licensed under the **MIT License**.

See the [LICENSE](LICENSE) file for details.

---

**ASTRA — Exploring aerospace dynamics through software.**

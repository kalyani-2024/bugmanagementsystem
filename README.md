# Bug Management System

A robust and intuitive Bug Tracking System built with Java Swing. This application provides a streamlined interface for developers and teams to report, track, and manage software bugs efficiently.

## Features

- **User Authentication**: Secure login interface to access the system.
- **Dashboard View**: Centralized table view displaying all reported bugs with key details like ID, Title, Description, Priority, and Status.
- **Bug Reporting**: Simple form to log new bugs with priority levels (Low, Medium, High).
- **Context Actions**: Right-click context menu support for quick actions:
    - **Change Status**: Update bug status (e.g., Open, In Progress, Closed).
    - **Delete Bug**: Remove resolved or invalid bug reports.
- **Modern UI**: Custom gradient background and styled Swing components for a pleasant user experience.

## Getting Started

### Prerequisites

- Java Development Kit (JDK) 8 or higher.
- An IDE (like VS Code, IntelliJ IDEA, or Eclipse) or a command-line environment.

### Running the Application

1. Compile the Java source files.
2. Run the `Main` class to launch the application.
3. The application will start with the Login screen.

## Project Structure

- `src/Main.java`: Entry point of the application.
- `src/LoginGUI.java`: Handles user authentication.
- `src/BugTrackerGUI.java`: Main dashboard interface.
- `src/BugManager.java`: Logic for managing bug data.
- `src/Bug.java`: Data model representing a bug.

## Technologies Used

- **Java Core**: Application logic.
- **Java Swing**: Graphical User Interface (GUI).

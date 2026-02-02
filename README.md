# Bird Conservatory Management System

**Author:** Gbadamassi Hanane  
**Class:** CS5010 Lab 1

## Overview

The Bird Conservatory Management System is a Java-based application designed to manage a bird sanctuary. It tracks different types of birds, manages their dietary needs, and assigns them to appropriate aviaries based on strict compatibility rules (e.g., separating predators from prey).

## Features

-   **Bird Rescue**: Register new birds into the system.
-   **Intelligent Housing**: Automatically assigns birds to aviaries, creating new ones as needed.
-   **Safety Enforcement**: Prevents incompatible birds (e.g., Hawks and Doves) from sharing an aviary.
-   **Food Management**: Calculates total food requirements for the entire conservatory.
-   **Search & Mapping**: Look up bird locations and print a full map of the conservatory.

## Project Structure

```
lab-5010/
├── src/                # Source code
├── test/               # JUnit tests
├── res/                # Resources (Design doc, JAR file)
├── scripts/            # Helper scripts (PDF generation)
└── pom.xml             # Maven configuration
```

## How to Run

### Using the Pre-built JAR
An executable JAR file is provided in the `res` folder. You can run it directly:

```bash
java -jar res/bird-conservatory.jar
```

### Building from Source
If you want to build the project yourself, you will need **Java 11+** and **Maven**.

1.  **Compile and Test**:
    ```bash
    mvn clean test
    ```

2.  **Package JAR**:
    ```bash
    mvn package
    ```
    This will generate a new JAR in the `target/` directory.

## Documentation

A full design document, including a Mermaid class diagram and testing plan, is available in the `res` folder:
-   **Markdown**: [res/design_document.md](res/design_document.md)
-   **PDF**: [res/design_document.pdf](res/design_document.pdf)

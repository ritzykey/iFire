# Project Setup

This project consists of two main components:
1. **Frontend** (React with Vite)
2. **Backend** (Spring Boot)

## Prerequisites

Ensure you have the following installed on your machine:
- **Node.js** (for running the Vite frontend)
- **Java JDK** (for running the Spring Boot backend)
- **Maven** (depending on your Spring Boot setup)
- **MongoDB** (for database operations)

## Running the Frontend (React with Vite)

Navigate to the `frontend` folder and follow these steps:

1. Install dependencies:

    ```bash
    cd frontend
    npm install
    ```

2. Start the Vite development server:

    ```bash
    npm run dev
    ```

The frontend will be running at `http://localhost:5173/` (the default Vite port).

## Running the Backend (Spring Boot)

Navigate to the `webservice` folder and follow these steps:

1. If you're using Maven:

    ```bash
    cd webservice
    mvn spring-boot:run
    ```

The backend will be running at `http://localhost:8080/`.

## Notes
- Ensure both servers are running simultaneously to enable full functionality.
- You can customize the ports in the configuration files if needed.
- If you encounter any issues with CORS while connecting the frontend to the backend, ensure you have the appropriate CORS configuration in your Spring Boot application.


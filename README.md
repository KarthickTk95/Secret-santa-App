# Secret Santa App

Welcome to the **Secret Santa App!** This is a full-stack application built with **React (frontend)** and **Spring Boot (backend)** to help users organize and manage Secret Santa events effortlessly.

## Features

- **User-friendly Interface:** Built with React for a smooth and responsive experience.
- **REST API:** Powered by Spring Boot for managing participants, assignments, and events.
- **CSV Upload & Download:** Easily upload employee lists and generate Secret Santa pairings.
- **Dynamic Assignment:** Ensures fair and randomized Secret Santa pairings.
- **Modern UI:** Styled with Bootstrap and Tailwind CSS for a sleek design.

---

## Prerequisites

Before running the app, ensure you have the following installed:

- **Node.js** (for React frontend) - [Download](https://nodejs.org/)
- **Java JDK** (for Spring Boot backend) - [Download](https://www.oracle.com/java/technologies/javase-downloads.html)
- **Maven** (for building the Spring Boot project) - [Download](https://maven.apache.org/download.cgi)
- **Git** (for cloning the repository) - [Download](https://git-scm.com/downloads)

---

## How to Run the App

### 1️⃣ Clone the Repository

```sh
# Clone the repository
git clone https://github.com/KarthickTk95/Secret-santa-App.git
cd secret-santa-app
```

### 2️⃣ Run the Backend (Spring Boot)

Navigate to the backend directory and start the Spring Boot application:

```sh
cd backend
mvn spring-boot:run
```

The backend will start on: **[http://localhost:8080](http://localhost:8080)**

### 3️⃣ Build and Deploy Frontend (React)

#### Option 1: Run in Development Mode (Hot Reload)

Navigate to the frontend directory:

```sh
cd ../frontend
npm install  # Install dependencies
npm start    # Start React development server
```

The frontend will open in your browser at **[http://localhost:3000](http://localhost:3000)** and communicate with the backend at **[http://localhost:8080](http://localhost:8080)**.

#### Option 2: Build and Serve with Spring Boot

1. **Build the React App:**
   ```sh
   npm run build
   ```
2. **Copy Build Files:**
   ```sh
   cp -r build/* ../backend/src/main/resources/static/
   ```
3. **Restart the Spring Boot server.**

Now, the React app is served directly from **Spring Boot** and accessible at **[http://localhost:8080/](http://localhost:8080/)**.

---

## API Endpoints

| Method | Endpoint                    | Description                       |
| ------ | --------------------------- | --------------------------------- |
| GET    | `/api/employees`            | Retrieve all employees            |
| POST   | `/api/employees/upload`     | Upload employees CSV              |
| POST   | `/api/secretsanta/generate` | Generate Secret Santa assignments |
| GET    | `/api/secretsanta/download` | Download assignments CSV          |

---

## Built With

- **Frontend:** React.js, Axios, Bootstrap, Tailwind CSS
- **Backend:** Spring Boot, Spring Data JPA, OpenCSV
- **Database:** MySql, PostgreSQL (optional for production)

---

## Contributing

Contributions are welcome! Feel free to fork the repository and submit a pull request with improvements.

---

## License

This project is licensed under the **MIT License** - see the [LICENSE](LICENSE) file for details.

---

## Contact

For any queries or issues, please reach out to [KarthickTk95](https://github.com/KarthickTk95).

Happy Coding!


# CoderHack Leaderboard API

This project is a RESTful API service built with Spring Boot and MongoDB to manage the leaderboard for a coding platform. The platform hosts a single contest with a single leaderboard and awards virtual badges to users based on their scores.

## Features

- **CRUD Operations**: Register, update, retrieve, and delete users.
- **Score Management**: Users' scores can be updated, and badges are awarded based on their scores.
- **Sorting**: Users can be retrieved sorted by their scores.
- **Validation and Error Handling**: Basic validation for fields and appropriate HTTP status codes for errors.
- **JUnit Tests**: Basic unit tests to verify the operations.

## Getting Started

### Prerequisites

- Java 11 or higher
- Maven
- MongoDB
- Git

### Installation

1. **Clone the repository:**

   ```sh
   git clone https://github.com/your-username/CoderHack.git
   cd CoderHack

2.***MongoDb configuration**
Ensure MongoDB is running on your local machine. By default, the application expects MongoDB to be available at mongodb://localhost:27017.

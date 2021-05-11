# API Service - Jalgaon CoHelp

The REST API backend server for the [Jalgaon CoHelp](https://jalgaoncohelp.in) application.

[![CI](https://github.com/Jalgaon-CoHelp/api-service/actions/workflows/ci.yml/badge.svg)](https://github.com/Jalgaon-CoHelp/api-service/actions/workflows/ci.yml)

## 🛠 Technology / Tools used 

- Kotlin: Programming language for development
- Ktor: Backend development framework
- PostgreSQL: For data storage
- Kodein: Dependency Injection Framework
- Liquibase: Migrations and DB change version control
- SQLDelight: Generates typesafe Kotlin APIs from SQL
- Spotless: Lint checker

## 📙 Overview of Codebase 

It is a multi-module gradle project and includes below modules.

- `core`: Core Business Logic
- `port:db` Single source of truth (data)
- `api`: Exposes public APIs
- `migration`: Handles database migrations and control

There's a main application module:

- `application`: This is a main module from where application is executed. It includes app configuration, management and Dependency Injection Bindings.


## 🖥 Development Setup 

### 🗄️ Database Setup

- Download and install the latest [PostgreSQL package](https://www.postgresql.org/download/) as per your system need.
- After successful installation, create database for this project.
For e.g. create a database named `jalgaoncohelp_dev`.

### ⚙️ Project Setup

- You will require latest stable version of JetBrains IntelliJ Idea IDE to build and run the server application. You can install the latest version from [here](https://www.jetbrains.com/idea/).
- Import project in IntelliJ IDE.
- Use existing Gradle wrapper for syncing project.
- Build 🔨 the project.

### ✈️ Running the Application 

- Set up environment variables for database credentials as following with valid values as per your setup.

```bash
export SECRET_KEY=ANY_RANDOM_SECRET

export DATABASE_NAME=jalgaoncohelp_dev
export DATABASE_HOST=localhost
export DATABASE_PORT=5432
export DATABASE_USER=postgres
export DATABASE_PASSWORD=postgres

export JWT_AUDIENCE=JWT_AUDIENCE
export JWT_ISSUER=JWT_ISSUER
```

- Finally, run the Gradle command:

```bash
./gradlew :application:run
```

***OR***

Use IntelliJ’s run configuration to run the API server application.

- After that, API server will be live on `http://localhost:8080`.

## 🙋‍♂️ Want to Contribute ?
Awesome! If you want to contribute to this project, you're always welcome! See [Contributing Guidelines](CONTRIBUTING.md).

## 💬 Want to discuss? 
Have any questions, doubts or want to present your opinions, views? You're always welcome. You can [start discussions](https://github.com/Jalgaon-CoHelp/api-service/discussions).

## 👓 REST API Specification
You can navigate to [/http](/http) and try API calls in IntelliJ Idea IDE itself after API is running.

## License

```
MIT License

Copyright (c) 2021 Jalgaon CoHelp

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
```


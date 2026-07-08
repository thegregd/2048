# Getting Started

## Requirements

* JDK 25

## Local Setup

### Running the application in Intellij IDEA

1. Clone this repository
2. Import the project as a new project in IntelliJ
3. Navigate to `Application.java` and run the main method by clicking the top green arrow on the left side of the editor.
4. Set the environment variable `ANTHROPIC_API_KEY` with the value of your Anthropic Api Key.
    1. Go to `Run/Debug Configuration`.
    2. Select `Spring Boot` -> `Application`.
    3. Add the variable `ANTHROPIC_API_KEY` with your value to the `Environment variables:` section.

### Running the Application using Gradle

1. Clone this repository
2. Set the environment variable `ANTHROPIC_API_KEY` with the value of your Anthropic Api Key.
3. Navigate to the project directory in your terminal
4. Run the following command: `./mvnw spring-boot:run`

### Running GUI

1. Navigate to the `gui` folder in the project directory in your terminal.
2. Run the development server:

```bash
npm run dev
# or
yarn dev
# or
pnpm dev
# or
bun dev
```

3. Open [http://localhost:3000](http://localhost:3000) with your browser to see the result.

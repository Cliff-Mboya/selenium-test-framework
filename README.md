# Selenium Test Framework (Java + TestNG + Maven)

A Selenium automation framework built with **Java**, **TestNG**, and **Maven**, following the **Page Object Model (POM)** design pattern.

It supports **cross-browser execution (Chrome + Firefox)** using TestNG parameters and is structured for clean maintenance and future CI integration.

---

## ✅ Tech Stack
- Java 17+
- Maven
- Selenium 4
- TestNG
- WebDriverManager
- Logback (logging)

---

## ✅ Framework Architecture

This framework separates reusable automation components from test execution logic:

- **src/main/java** → reusable framework code (pages, driver setup, utils)
- **src/test/java** → test execution layer (tests, BaseTest, listeners)
- **src/test/resources** → test configs and suite definitions

### 📁 Project Structure


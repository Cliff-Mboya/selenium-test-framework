# Selenium Test Framework (Java + TestNG + Maven)

A Selenium automation framework built with **Java**, **TestNG**, and **Maven**, following the **Page Object Model (POM)** design pattern.

Supports:
- Cross-browser execution (**Chrome + Firefox**) via command line
- Parallel execution via **TestNG**
- Failure screenshots + test reporting (Surefire + ExtentReports)
- Logging via **Logback**

---

## ✅ Tech Stack
- Java 21 (works with 17+)
- Maven
- Selenium 4
- TestNG
- WebDriverManager
- Logback (logging)
- ExtentReports (HTML reporting)

---

## ✅ Framework Architecture

This framework separates reusable automation components from test execution logic:

- **src/main/java** → reusable framework code (**pages, driver setup, utils**)
- **src/test/java** → test execution layer (**tests, BaseTest, listeners**)
- **src/test/resources** → configs + suite definitions (`testng.xml`)

---

## 📁 Project Structure


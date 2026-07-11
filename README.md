# TutorialsNinja Hybrid Automation Framework

![CI](https://github.com/Manyatha123/TutorialsNinjaHybridFramework/actions/workflows/ci.yml/badge.svg)
![Java](https://img.shields.io/badge/Java-17-orange?logo=openjdk)
![Selenium](https://img.shields.io/badge/Selenium-4.27-brightgreen?logo=selenium)
![TestNG](https://img.shields.io/badge/TestNG-7.10-red)
![Maven](https://img.shields.io/badge/Maven-3.9-blue?logo=apachemaven)
![License](https://img.shields.io/badge/License-MIT-lightgrey)

An end-to-end **Data-Driven + Page Object Model (POM) Hybrid** UI automation framework built on Selenium WebDriver 4 and TestNG, validating the [TutorialsNinja](https://tutorialsninja.com/demo/) e-commerce demo site.

---

## 🎯 Overview

This framework demonstrates a production-style hybrid automation approach — combining the reusability of the **Page Object Model** with the flexibility of **data-driven testing** through Excel. Built to showcase real-world SDET practices: clean architecture, external test data, reusable utilities, HTML reporting, and CI-ready execution.

---

## 🛠 Tech Stack

| Layer | Technology |
|---|---|
| Language | Java 17 |
| UI Automation | Selenium WebDriver 4.27 |
| Test Runner | TestNG 7.10 |
| Reporting | ExtentReports 5.1 |
| Data-Driven | Apache POI 5.3 (Excel `.xlsx`) |
| Boilerplate | Lombok |
| Build Tool | Maven 3.9+ |
| CI/CD | GitHub Actions |

---

## 🏗 Framework Architecture

**Hybrid = Data-Driven + Page Object Model.**

- **Page Object Model (POM)** — every application page has a dedicated Java class encapsulating locators and page actions, so tests stay clean and locators change in exactly one place.
- **Data-Driven** — test inputs (usernames, passwords, product data, expected outputs) live in Excel sheets under `src/test/resources/testdata/`, read via Apache POI. Tests are parameterized so adding a new scenario means adding a new row, not new code.
- **Reusable Utilities** — WebDriver factory, Excel reader, config reader, wait helpers, screenshot capture, and ExtentReports listener are shared across the suite.
- **TestNG Listeners** — hook into test lifecycle to log to ExtentReports, capture screenshots on failure, and manage suite-level setup/teardown.

---

## 📂 Project Structure

​```
TutorialsNinjaHybridFramework/
├── src/
│   ├── main/java/
│   │   ├── pages/               # Page Object classes
│   │   ├── base/                # BaseTest, DriverFactory
│   │   ├── utils/               # ExcelUtils, ConfigReader, WaitUtils
│   │   └── listeners/           # TestNG + Extent listeners
│   └── test/
│       ├── java/tests/          # TestNG test classes
│       └── resources/
│           ├── testdata/        # Excel test data
│           ├── config.properties
│           └── testng.xml       # Suite configuration
├── .github/workflows/ci.yml     # GitHub Actions pipeline
├── pom.xml
└── README.md
​```

---

## ✨ Key Features

- **Cross-browser support** via config-driven browser selection (Chrome / Firefox / Edge)
- **Headless mode** toggle for CI execution via `-Dheadless=true` system property
- **Data-driven test execution** via Excel using `@DataProvider`
- **ExtentReports** with test-level pass/fail dashboards and screenshots on failure
- **Automatic screenshot capture** on test failure, attached to the report
- **Config-externalized** — URLs, timeouts, browser choice all in `config.properties`
- **Parallel execution** configurable via `testng.xml`
- **CI integration** — every push triggers a headless run on GitHub Actions with artifacts uploaded

---

## 🧪 Test Scenarios Covered

| # | Module | Scenario | Type |
|---|---|---|---|
| 1 | Login | Valid credentials → redirected to My Account | Positive |
| 2 | Login | Invalid password → error message shown | Negative |
| 3 | Login | Empty fields → validation error | Boundary |
| 4 | Registration | New user signup with all mandatory fields | Positive |
| 5 | Product Search | Search existing product → results displayed | Positive |
| 6 | Product Search | Search non-existent product → "No product" message | Negative |
| 7 | Add to Cart | Add product → cart count increments | Positive |
| 8 | Checkout | Complete checkout as guest user | End-to-End |

*(Replace the rows above with the actual test cases in your `src/test/java/tests/` folder.)*

---

## 🚀 Getting Started

### Prerequisites

- Java 17+
- Maven 3.9+
- Chrome / Firefox / Edge browser
- Git

### Setup

​```bash
# Clone the repo
git clone https://github.com/Manyatha123/TutorialsNinjaHybridFramework.git
cd TutorialsNinjaHybridFramework

# Install dependencies
mvn clean install -DskipTests
​```

### Run the tests

​```bash
# Run the full suite
mvn test

# Run headless (recommended for CI)
mvn test -Dheadless=true

# Run a specific TestNG suite file
mvn test -DsuiteXmlFile=src/test/resources/testng.xml
​```

---

## 📊 Reports

After a test run, HTML reports are generated at:

​```
test-output/ExtentReports/ExtentReport.html
​```

Surefire reports are also produced at `target/surefire-reports/` and uploaded as CI artifacts on every push.


---

## 🔮 Roadmap

- [ ] Add Cucumber BDD layer on top of existing POM (in progress)
- [ ] Docker-based Selenium Grid for parallel cross-browser execution
- [ ] Allure Reports integration
- [ ] Visual regression testing (Applitools or Percy)
- [ ] API-layer helpers for setting up test data via backend

---

## 👤 Author

**Manyatha Chippa**  
SDET | QA Automation Engineer  
[LinkedIn](https://linkedin.com/in/manyatha-chippa-45873a216) · [GitHub](https://github.com/Manyatha123)

---

## 📄 License

MIT — free to use, learn from, and adapt.

# Trello API Automation

This project is designed to automate test scenarios using the Trello API. The tests are implemented using Rest-Assured and TestNG.

---

## Requirements

- **Java Development Kit (JDK)**: 17 or later
- **Apache Maven**: For managing project dependencies
- **An IDE or Text Editor**: IntelliJ IDEA is recommended
- **Trello API Key and Token**: Obtain from [Trello Developer Key](https://trello.com/app-key).

---

## Setup

### 1. Clone the Repository
```bash
git clone https://github.com/username/trello-api-automation.git
cd trello-api-automation
```
### 2. Install Dependencies
```bash
mvn clean install
```
### 3. Configure API Key and Token
In the com.example.trello.config.Config class, replace the placeholders with your API Key and Token:
```
public static final String API_KEY = "YOUR_API_KEY";
public static final String TOKEN = "YOUR_API_TOKEN";
```
### Running the Tests
```bash
mvn test
```
# 🧱 Page Object Model & DriverFactory — Building a Scalable Test Automation Framework

A modern Java test automation template demonstrating a clean, maintainable Page Object Model (POM)   
and a centralized WebDriver management approach (DriverFactory)   
— built on top of [the-internet.herokuapp.com/login](https://the-internet.herokuapp.com/login).

---

## 🚀 What’s Inside?

A thoughtfully layered framework designed for clarity, reusability, and scalability:

🔹 **BasePage** – abstract class with shared browser utilities (e.g., navigation, screenshots)  
🔹 **Page Classes** – represent each UI screen with chainable, readable actions  
🔹 **ElementActions** – centralized utility for robust interaction and explicit waits  
🔹 **DriverFactory** – thread-safe WebDriver manager supporting multiple browsers and parallel execution  
🔹 **BrowserConfig** – resolves browser type, headless/incognito flags from multiple sources (method args → system properties → config file → defaults)  
🔹 **Sample Tests – showcase** login flows, cross-browser execution, and parallel testing  

---

## 💡 Why This POM?

Built from experience — this project shows how to:

- ✅ Avoid flaky tests using explicit waits
- ✅ Keep code clean with chainable methods and constructor injection
- ✅ Maintain separation of concerns across pages, drivers, and tests
- ✅ Scale easily with centralized driver management & configuration resolution
- ✅ Enable browser-agnostic execution (Chrome, Firefox, Edge) with minimal changes
- ✅ Safely run parallel tests using ThreadLocal WebDriver instances

---

## 🛠 Getting Started

**✅ Prerequisites**

- Java 17+
- Maven
- IntelliJ IDEA (recommended)
- Chrome browser (and optionally Firefox / Edge)

**🛠️ Project Setup**

Clone the repo:
```bash
git clone git@github.com:VoElenaS/theInternetLoginPage.git
```
1. Open the project in IntelliJ IDEA or Eclipse.
2. Explore core classes:
- `BasePage`
- `LoginPage`
- `SecureAreaPage`
- `ElementalSeleniumPage`
- `ElementActions`
- `DriverFactory`
- `BrowserConfig`
3. Run the example tests under /src/test/java.
4. Extend with your own pages, flows, or cross-browser tests.
 
🧰 **Technologies Used**

- **Java 17+**
- **Selenium WebDriver** – UI browser automation
- **TestNG** – test framework
- **Maven** – project build & dependency management
- **WebDriverManager** – automatic driver management
- **Lombok** – boilerplate reduction (e.g., constructors, getters)
- **SLF4J + Logback** – structured logging

🧭 **Design Highlights**  

✅ By locators over `@FindBy` for flexibility and control  
✅ No test logic in page objects — all validations live in test classes  
✅ Explicit, chainable methods for clear user flows  
✅ Reusable `ElementActions` for clicks, inputs, waits, and alerts  
✅ DriverFactory with ThreadLocal for safe parallel execution  
✅ BrowserConfig with layered parameter resolution (method args → system properties → config file → defaults)

📚 **Learn More**  
This project is a companion to the article:

* **"Crafting the Perfect Page Object Model: An Expert Tester's Blueprint for Long-Lasting Automation Success**
[Read the article →](https://medium.com/@VElanaS/b818c6ef6a90?source=friends_link&sk=32d065ebd7806736428197ac9d3fe4fe)
* **"DriverFactory — The Backbone of Selenium Test Automation" [Read the article →](https://medium.com/@VElanaS/1a5c807a3c2f?source=friends_link&sk=d30f14fc0f0e4a85bcbbf2078d6fdd5b)**

🤝 **Contribute**  
Ideas, improvements, and pull requests are welcome! Let’s refine the POM experience together.

📄 **License**  
MIT License — open to use, fork, and improve.
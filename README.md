# 🧱 Page Object Model Mastery for Test Automation Success

A modern Java test automation template demonstrating a clean, maintainable Page Object Model (POM) using best practices — built on top of [the-internet.herokuapp.com/login](https://the-internet.herokuapp.com/login).

---

## 🚀 What’s Inside?

A thoughtfully layered framework designed for clarity, reusability, and scalability:

- 🔹 **BasePage** – abstract class with shared browser utilities (e.g., navigation, screenshots)
- 🔹 **Page Classes** – represent each UI screen with chainable, readable actions
- 🔹 **ElementActions** – centralized utility for robust interaction and explicit waits
- 🔹 **Sample Tests** – show real-world usage with login flows

---

## 💡 Why This POM?

Built from experience — this project shows how to:

- ✅ Avoid flaky tests using explicit waits
- ✅ Keep code clean with chainable methods and constructor injection
- ✅ Maintain separation of concerns across layers
- ✅ Extend easily by centralizing repetitive logic

---

## 🛠 Getting Started

**✅ Prerequisites**

- Java 17+
- Maven
- IntelliJ IDEA (recommended)
- Chrome browser & ChromeDriver

**🛠️ Project Setup**

1. Clone the repo:
```bash
git clone git@github.com:VoElenaS/theInternetLoginPage.git
```
2. Open the project in IntelliJ IDEA or Eclipse.
3. Explore core classes:
- `BasePage`
- `LoginPage`
- `SecureAreaPage`
- `ElementalSeleniumPage`
- `ElementActions`
4. Run the test examples, then extend with your own pages & flows.
 
🧰 **Technologies Used**

- **Java 17+**
- **Selenium WebDriver** – UI browser automation
- **JUnit 5 (junit-jupiter)** – test framework
- **Maven** – project build & dependency management
- **WebDriverManager** – automatic driver management
- **Lombok** – boilerplate reduction (e.g., constructors, getters)
- **SLF4J + Logback** – structured logging

🧭 **Design Highlights**  

✅ By locators over `@FindBy` for flexibility and control  
✅ No test logic in page objects — all validations live in test classes  
✅ Explicit, chainable methods for clear user flows  
✅ Reusable `ElementActions` for clicks, inputs, waits, and alerts

📚 **Learn More**  
This project is a companion to the article:

**"Crafting the Perfect Page Object Model: An Expert Tester's Blueprint for Long-Lasting Automation Success**
[Read the article →](https://medium.com/@VElanaS/b818c6ef6a90?source=friends_link&sk=32d065ebd7806736428197ac9d3fe4fe)

🤝 **Contribute**  
Ideas, improvements, and pull requests are welcome! Let’s refine the POM experience together.

📄 **License**  
MIT License — open to use, fork, and improve.
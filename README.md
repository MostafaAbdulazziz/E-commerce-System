# E-commerce System

A simple Java-based e-commerce system that simulates a shopping cart, checkout process, and shipping functionality. This project is built using Maven and demonstrates object-oriented programming principles, including inheritance, interfaces, and exception handling.

## Table of Contents
- [Overview](#overview)
- [Features](#features)
- [Prerequisites](#prerequisites)
- [Setup](#setup)
- [Usage](#usage)
- [Project Structure](#project-structure)
- [Contributing](#contributing)
- [License](#license)

## Overview
The E-commerce System is a console-based application designed to model a basic online shopping experience. It allows customers to add products to a cart, check out with payment validation, and process shipping for eligible items. The system supports different product types (e.g., perishable goods like Cheese and Biscuits, and non-perishable items like TVs and Scratch Cards) and includes features like stock management, expiry checks, and shipping calculations.

## Features
- **Shopping Cart**: Add products with quantity validation against available stock.
- **Product Types**: Supports perishable (Biscuits, Cheese) and non-perishable (TV, Scratch Card) products with appropriate interfaces (`Expirable`, `Shippable`).
- **Checkout Process**: Validates customer balance, checks for expired products, and calculates subtotal and shipping fees.
- **Shipping Service**: Computes total package weight and generates a shipment notice for shippable items.
- **Exception Handling**: Robust error handling for insufficient stock, expired products, and insufficient customer balance.
- **Console Output**: Displays detailed receipts and shipment notices.

## Prerequisites
- **Java**: JDK 11 or higher
- **Maven**: Version 3.6.0 or higher
- **IDE**: IntelliJ IDEA, Eclipse, or any Java-compatible IDE (optional)

## Setup
1. **Clone the Repository**:
   ```bash
   git clone https://github.com/MostafaAbdulazziz/E-commerce-System.git
   cd E-commerce-System
   ```

2. **Build the Project**:
   Use Maven to compile and package the project:
   ```bash
   mvn clean install
   ```

3. **Run the Application**:
   Execute the main class to run the application:
   ```bash
   mvn exec:java -Dexec.mainClass="com.ecommerce.Main"
   ```

## Usage
The `Main` class provides an example of how to use the system. Below is a sample workflow:

1. Create a customer with a name and balance.
2. Initialize a cart and add products (e.g., Cheese, Biscuits, Scratch Card).
3. Use the `CheckoutService` to process the cart, validate the purchase, and generate a receipt and shipment notice.

### Example Code
```java
Customer customer = new Customer("Ali", 500);
Cart cart = new Cart();

Product cheese = new Cheese("Cheese", 100, 10, LocalDate.of(2025, 12, 1), 0.4);
Product biscuits = new Biscuits("Biscuits", 150, 5, LocalDate.of(2025, 11, 1), 0.7);
Product scratchCard = new ScratchCard("Scratch Card", 50, 20);

cart.add(cheese, 2);
cart.add(biscuits, 1);
cart.add(scratchCard, 1);

CheckoutService checkoutService = new CheckoutService(new ShippingService());
checkoutService.checkout(customer, cart);
```

### Sample Output
```
** Checkout receipt **
2x Cheese  200.0
1x Biscuits  150.0
1x Scratch Card  50.0
----------------------
Subtotal         400.0
Shipping         30.0
Amount           430.0
Balance left     70.0
** Shipment notice **
2x Cheese
1x Biscuits
400g
400g
700g
Total package weight kg  1.5
```

## Project Structure
```
E-commerce-System/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   ├── com.ecommerce/
│   │   │   │   ├── cart/
│   │   │   │   │   ├── Cart.java
│   │   │   │   │   ├── CartItem.java
│   │   │   │   ├── customer/
│   │   │   │   │   ├── Customer.java
│   │   │   │   ├── model/
│   │   │   │   │   ├── Biscuits.java
│   │   │   │   │   ├── Cheese.java
│   │   │   │   │   ├── Expirable.java
│   │   │   │   │   ├── Product.java
│   │   │   │   │   ├── ScratchCard.java
│   │   │   │   │   ├── Shippable.java
│   │   │   │   │   ├── TV.java
│   │   │   │   ├── service/
│   │   │   │   │   ├── CheckoutService.java
│   │   │   │   │   ├── ShippingService.java
│   │   │   │   ├── Main.java
│   │   ├── resources/
├── pom.xml
├── README.md
```

## Contributing
Contributions are welcome! To contribute:
1. Fork the repository.
2. Create a new branch (`git checkout -b feature/your-feature`).
3. Make your changes and commit (`git commit -m "Add your feature"`).
4. Push to the branch (`git push origin feature/your-feature`).
5. Open a pull request.

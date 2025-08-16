# 🚗 Parking Lot Management System

A scalable **Parking Lot Management System** built with **Java 8, JDBC, MySQL, Collections, Stream API, and Razorpay integration**.  
This project is designed with clean architecture principles to simulate **real-world parking lot operations** (Entry, Exit, Admin management).

---

## 📌 Features

### 🎟️ Entry Flow
- Vehicle arrives → Slot assigned (based on type)
- Ticket generated & slot marked as occupied
- Entry response returned

### 🚪 Exit Flow
- Ticket presented
- Parking fee calculated (Flat / Hourly / Hybrid rules)
- Payment processed (via Razorpay / Adapter)
- Receipt generated, slot released, ticket closed

### 🛠️ Admin Flow
- Add / Edit / Delete **floors** and **slots**
- Define / Update **pricing rules**
- View **parking lot status**

---

## 🛠️ Tech Stack

- **Java 8** (Collections, Stream API, OOP)
- **JDBC** (Database Connectivity)
- **MySQL** (Persistence Layer)
- **Razorpay** (Payment Gateway Integration via Adapter Pattern)
- **JUnit & Mockito** (Unit Testing)
- **Maven** (Build Tool)

---

## 🧱 System Architecture


Entry Flow
<img width="2205" height="1182" alt="image" src="https://github.com/user-attachments/assets/ad6ae75b-d02b-4163-9f6a-d981fb3d89bc" />
Exit Flow
<img width="4096" height="1208" alt="image" src="https://github.com/user-attachments/assets/4b744f4d-c4b8-4f51-955b-e7e4a510632d" />
Admin Flow
<img width="2126" height="1537" alt="image" src="https://github.com/user-attachments/assets/0e973e53-1fd3-4016-9411-04af21220e8b" />

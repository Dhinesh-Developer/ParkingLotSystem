# 🚗 Parking Lot System

A scalable **Parking Lot Management System** built with **Java 8, JDBC, MySQL, Collections, Stream API, and Razorpay integration**.  
This project is designed with clean architecture principles to simulate **real-world parking lot operations** (Entry, Exit, Admin management).

# 👨‍💻 Author: DhineshKumar M  

## 🧱 System Architecture


Entry Flow
<img width="2205" height="1182" alt="image" src="https://github.com/user-attachments/assets/ad6ae75b-d02b-4163-9f6a-d981fb3d89bc" />
Exit Flow
<img width="4096" height="1208" alt="image" src="https://github.com/user-attachments/assets/4b744f4d-c4b8-4f51-955b-e7e4a510632d" />
Admin Flow
<img width="2126" height="1537" alt="image" src="https://github.com/user-attachments/assets/0e973e53-1fd3-4016-9411-04af21220e8b" />

---

# 🚗 Parking Lot Management System

A scalable **Parking Lot Management System** built with **Java 8, JDBC, MySQL, Collections, Stream API, and Razorpay integration**.  
This project simulates real-world parking lot operations such as **vehicle entry, exit, and admin management**, while following clean architecture and solid OOP design principles.

---

## 📌 Step 1: Clarify Requirements

### ✅ Functional Requirements

#### Entry Flow
- Vehicle arrives at gate
- Assign slot based on vehicle type
- Generate ticket
- Mark slot as occupied
- Return entry response

#### Exit Flow
- Present ticket
- Calculate fee using pricing rules (flat vs hourly)
- Process payment
- Release slot
- Return exit response with receipt

#### Admin Flow
- Add/Edit/Delete floors and slots
- Define/update pricing rules
- View parking lot status

💡 **Interview Tip**: Always confirm requirements with the interviewer before jumping into code.

---

### ⚙️ Non-Functional Requirements
- **Scalability** → Multiple parking lots, thousands of slots
- **Availability** → Entry/Exit must work even if payment fails
- **Consistency** → Accurate slot status at all times
- **Extensibility** → Easy addition of new vehicle types or gateways
- **Security** → Role-based access for admin operations
- **Latency** → < 500ms for key flows

---

### 🧩 Edge Cases
- Payment failure at exit
- Lost ticket
- System clock mismatch
- Slot wrongly marked as occupied

---

## 📌 Step 2: Identify Core Entities

| Entity       | Key Attributes                                             |
|--------------|-----------------------------------------------------------|
| **Vehicle**  | id, licensePlate, vehicleType                             |
| **Slot**     | id, slotType, isOccupied, floorNumber                     |
| **Floor**    | id, floorNumber, slots                                    |
| **Ticket**   | id, vehicleId, slotId, entryTime, isActive                |
| **Receipt**  | id, ticketId, exitTime, totalFee, paymentStatus           |
| **Pricing**  | vehicleType, ratePerHour, flatRate, ruleType              |
| **Payment**  | ticketId, amount, gateway, status                         |
| **Result**   | success, data, message (EntryResult / ExitResult)         |

💡 **Interview Tip**: Use enums (e.g., VehicleType, SlotType, RuleType, PaymentStatus) for clarity and control.

---

## 📌 Step 3: Visual Interaction Flows

### Entry Flow
1. Vehicle arrives
2. Slot allocated
3. Ticket generated
4. Slot marked occupied

### Exit Flow
1. Ticket scanned
2. Fee calculated
3. Payment processed (with retries)
4. Receipt generated
5. Slot released
6. Ticket deactivated

### Admin Flow
1. Add floor
2. Add slot
3. Update pricing rules

💡 

---

## 📌 Step 4: Architecture & Layers


- **Client Layer** → User/API interaction  
- **Controller Layer** → Request handling (`EntryController`, `ExitController`, `AdminController`)  
- **Service Layer** → Business logic (`TicketService`, `SlotService`, `PricingService`, `PaymentService`)  
- **Repository Layer** → Database access (`TicketRepository`, `SlotRepository`, etc.)  
- **Domain Layer** → Core entities (`Vehicle`, `Ticket`, `Receipt`, etc.)  

### Controllers
- `EntryController.enterVehicle()`
- `ExitController.exitVehicle()`
- `AdminController.addFloor(), addSlot(), updatePricing()`

### Services
- `TicketService` → Ticket lifecycle  
- `SlotService` → Allocation & release  
- `PricingService` → Fee calculation  
- `PaymentService` → Gateway integration (with retries)  
- `ReceiptService` → Receipt generation  
- `AdminService` → Floor & slot mgmt, pricing rules  

### Repositories
- `TicketRepository`, `SlotRepository`, `FloorRepository`, `PricingRuleRepository`, `PaymentRepository`  

### Adapters
- `PaymentGatewayAdapter` (interface)  
- `RazorpayAdapter`, `StripeAdapter` (implementations)  

💡 **Interview Tip**: Highlight layering and interfaces to show **separation of concerns**.

---

## 📌 Step 5: Core Use Cases

### Entry Use Case

enterVehicle() → SlotService.allocateSlot() → TicketService.generateTicket() 
→ TicketRepository.save() → Return EntryResult

exitVehicle() → Get Ticket → Calculate Fee → Process Payment (retry logic) 
→ Release Slot → Generate Receipt → Return ExitResult
---

## 🛠️ Tech Stack

- **Java 8** (Collections, Stream API, OOP)
- **JDBC** (Database Connectivity)
- **MySQL** (Persistence Layer)
- **Razorpay** (Payment Gateway Integration via Adapter Pattern)
- **JUnit & Mockito** (Unit Testing)
- **Maven** (Build Tool)
# 🚗 Parking Lot Management System  

A robust and extensible **Parking Lot Management System** built using **Java, OOP principles, and design patterns**.  
The project focuses on scalability, modularity, and real-world problem-solving — designed as a **system design + coding interview ready project**.  

---

## 📌 Features
- Multi-floor, multi-slot parking management  
- Role-based actions (Admin, User)  
- Pricing rules with flexible strategy updates  
- Vehicle entry/exit handling  
- Payment gateway integration (via Adapter pattern)  
- Edge-case handling for real-world scenarios  

---

## 🏗️ System Architecture
- **Controller Layer** → Handles API/requests  
- **Service Layer** → Business logic centralization  
- **Repository Layer** → Data persistence (DB isolation)  
- **Entity Layer** → Core models (Vehicle, Slot, Floor, Ticket, Payment, etc.)  

---

## 👨‍💻 Admin Use Cases
| Use Case       | Functionality |
|----------------|--------------|
| `addFloor()`   | Save a new floor |
| `addSlot()`    | Save a new slot in a floor |
| `updatePricing()` | Update pricing rules dynamically |

💡 **Interview Tip:** Always **map each use case → service → repository** to show workflow clarity.  

---

## 📌 OOP & Design Patterns  

### 🎯 Design Patterns  
- **Adapter Pattern** → Payment gateways (PayPal, Stripe, etc.)  
- **Repository Pattern** → DB isolation for testability & flexibility  
- **Service Layer Pattern** → Centralizes business logic  

### 🧑‍💻 OOP Principles  
- **SRP (Single Responsibility Principle):** Each class has one responsibility  
- **ISP (Interface Segregation Principle):** Role-specific interfaces  
- **DIP (Dependency Inversion Principle):** Services depend on abstractions, not implementations  
- **Open/Closed Principle:** Extend without modifying existing classes  
- **Encapsulation:** Entities encapsulate both data & behavior  

💡 **Interview Tip:** Explain how these patterns make the system **flexible, extensible, and maintainable**.  

---

## ⚡ Handling Edge Cases  

| Edge Case              | Strategy |
|-------------------------|----------|
| Lost Ticket             | Admin override for manual validation |
| Payment Failure         | Retry logic via `PaymentGatewayAdapter` |
| Mismatched Vehicle Type | Validate at entry/exit; reject invalid slot assignment |
| Clock Issues            | Use centralized `TimeProvider` for consistency |
| Slot State Mismatch     | Reconciliation service to correct discrepancies |

---

## 📂 Project Structure
/src
┣ 📂 controller → Handles entry/exit APIs
┣ 📂 service → Business logic (pricing, slot allocation)
┣ 📂 repository → DB interactions (slotRepo, floorRepo, ticketRepo)
┣ 📂 model → Core entities (Vehicle, Slot, Floor, Ticket, Payment)
┗ 📂 utils → Helpers (TimeProvider, PaymentGatewayAdapter)
---


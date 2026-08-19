# ระบบจัดการซุปเปอร์มาร์เก็ต (Java Console Application)

โปรเจกต์ระบบจัดการซุปเปอร์มาร์เก็ตแบบ console เขียนด้วยภาษา Java รองรับผู้ใช้งาน
3 บทบาท ได้แก่ Customer, Employee และ Admin พร้อมระบบจัดการสินค้า การออกบิล
และการจัดการบัญชีผู้ใช้ ข้อมูลถูกเก็บผ่านไฟล์ CSV (ผู้ใช้) และไฟล์ JSON (สินค้า)
โดยใช้ไลบรารี [json-simple](https://github.com/fangyidong/json-simple)

> โปรเจกต์นี้เป็นงานที่ทำระหว่างเรียน (mini project ในวิชา Java) เพื่อฝึกฝน
> แนวคิด OOP และการเขียนโปรแกรมแบบ console-based application  

## ฟีเจอร์

**Customer**
- สมัครสมาชิก / เข้าสู่ระบบ
- ดูรายการสินค้า (Food, Water)
- เพิ่มสินค้าลงตะกร้าและชำระเงิน (ออกบิล)
- ดูและแก้ไขข้อมูลบัญชี (ชื่อ, ที่อยู่, เบอร์โทร)

**Employee**
- เข้าสู่ระบบ
- จัดการสินค้า (Food, Water)
- ดูและแก้ไขข้อมูลบัญชีตัวเอง (ชื่อ, เบอร์โทร, รหัสผ่าน)

**Admin**
- เข้าสู่ระบบ
- จัดการสินค้า (Food, Water)
- ดูรายชื่อพนักงาน
- ลงทะเบียนพนักงานใหม่

## Tech Stack

- Java (แนะนำ JDK 21 ขึ้นไป ใช้ได้ตั้งแต่ JDK 8)
- [json-simple 1.1.1](https://github.com/fangyidong/json-simple) — สำหรับอ่าน/เขียนข้อมูลสินค้าแบบ JSON
- ไฟล์ CSV — เก็บข้อมูลผู้ใช้ (`adminData.csv`, `employeeData.csv`, `customerData.csv`)
- ไฟล์ JSON — เก็บข้อมูลสินค้า (`dataWater.json`, `menuData.json`)

## โครงสร้างโปรเจกต์

```
mini_project_java/
├── lib/
│   └── json-simple-1.1.1.jar     # dependency ที่จำเป็น แนบมาใน repo แล้ว
├── Calling_Store.java             # จุดเริ่มต้นโปรแกรม (เมนูหลัก)
├── Admin.java
├── Employee.java
├── Customer.java
├── Bill.java
├── Products.java
├── product_food.java
├── product_water.java
├── User.java
├── adminData.csv
├── employeeData.csv
├── customerData.csv
├── dataWater.json
├── menuData.json
└── README.md
```

## วิธีติดตั้งและรันโปรแกรม

### สิ่งที่ต้องมี
- ติดตั้ง JDK 8 ขึ้นไป (เช็คด้วยคำสั่ง `java -version` และ `javac -version`)

### 1. Clone repository
```bash
git clone https://github.com/yahalofew/mini_project_java.git
cd mini_project_java
```

### 2. Compile
```bash
javac -cp .:lib/json-simple-1.1.1.jar *.java
```
> บน Windows (cmd/PowerShell) ให้เปลี่ยน `:` เป็น `;` ในการระบุ classpath:
> ```
> javac -cp .;lib/json-simple-1.1.1.jar *.java
> ```

### 3. Run
```bash
java -cp .:lib/json-simple-1.1.1.jar Calling_Store
```
> Windows:
> ```
> java -cp .;lib/json-simple-1.1.1.jar Calling_Store
> ```

### 4. วิธีใช้งาน
เมื่อเปิดโปรแกรมจะเจอเมนูหลักดังนี้:
```
----- Welcome to Supermarket -----
---- Login to continue -----
1. login customer
2. SignUp customer
3. Employee Login
4. Admin Login
0. Exit
```

**บัญชีตัวอย่างสำหรับทดสอบ** (มาจากข้อมูลใน CSV ที่แนบมาในโปรเจกต์):

| บทบาท    | Username | Password      |
|----------|----------|---------------|
| Admin    | admin01  | 1234567zaZA   |
| Employee | emp1     | 123456emp1    |
| Employee | emp2     | 1234567emp2   |

ลูกค้าใหม่สามารถสมัครสมาชิกได้ทันทีจากเมนูหลัก (เลือกข้อ 2)

## หมายเหตุ

- โปรเจกต์นี้เป็นงานฝึกฝน/ผลงานสำหรับ portfolio เน้นแนวคิด OOP หลักของ Java
  ได้แก่ การสืบทอดคลาส (บทบาท `Employee`/`Admin`), การอ่าน-เขียนไฟล์, การ parse
  CSV/JSON และการจัดการเมนูแบบ console
- คู่มือการใช้งาน (ภาษาไทย) มีให้ดูเพิ่มเติมได้ตามคำขอ — เอาออกจาก repo เพื่อให้
  ขนาดไฟล์เบาลง ติดต่อเจ้าของโปรเจกต์หากต้องการเอกสารฉบับเต็ม

## ผู้พัฒนา
yahalofew

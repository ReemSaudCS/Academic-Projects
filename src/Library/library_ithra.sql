
CREATE TABLE customer_table (
	Cust_id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    Fname VARCHAR(45) NOT NULL,
    Lname VARCHAR(45) NOT NULL,
    Email VARCHAR(100) NOT NULL,
    Phone VARCHAR(45) NOT NULL,
    Username VARCHAR(45) NOT NULL,
    Cust_pass VARCHAR(45) NOT NULL,
    City VARCHAR(45) NOT NULL, 
    Country VARCHAR(45) NOT NULL) AUTO_INCREMENT = 1000;
    
INSERT INTO customer_table (Fname,Lname,Email,Phone,Username,Cust_pass,City,Country)
VALUES
('Ali','Khalid','AliKhalid@gmail.com','0583617238','AliKhalid12','Ali123','Khobar','Saudi Arabia'),
('Nada','Turki','NadaTurki@gmail.com','0547215144','NadaTurki12','Nada123','Jubail','Saudi Arabia'),
('Sara','Sultan','SaraSultan@gmail.com','0581213128','SaraSultan12','Sara123','Dammam','Saudi Arabia'),
('Waad','Ahmad','WaadAhmad@gmail.com','0572142627','WaadAhmad12','Waad123','Riyadh','Saudi Arabia'),
('Fahad','Khalid','FahadKhalid@gmail.com','0552323454','FahadKhalid13','Fahad123','Riyadh','Saudi Arabia'),
('Jood','Raed','JoodRaed@gmail.com','0562728136','JoodRaed23','Jood12','Dammam','Saudi Arabia');

CREATE TABLE admin_table (
	Admin_id INT NOT NULL PRIMARY KEY,
    Fname VARCHAR(45) NOT NULL,
    Lname VARCHAR(45) NOT NULL,
    Email VARCHAR(100) NOT NULL,
    Phone VARCHAR(45) NOT NULL,
    Username VARCHAR(45) NOT NULL,
    Admin_pass VARCHAR(45) NOT NULL,
    City VARCHAR(45) NOT NULL, 
    Country VARCHAR(45) NOT NULL);

INSERT INTO admin_table (Admin_id,Fname,Lname,Email,Phone,Username,Admin_Pass,City,Country)
VALUES
('2476','Renad','Alharbi','Renad123@gmail.com','0551798693','Renad12','Ra1234','Khobar','Saudi Arabia '),
('2674','Reem','Alhazzani','Reem123@gmail.com','0532542536','Reem12','Re1234','khobar','Saudi Arabia'),
('3386','Nora','Alharbi','Nora123@gmail.com','0501327720','Nora12','No1234','Dammam','Saudi Arabia'),
('2424','Maryam','Almajed','Maryam123@gmail.com','0540057051','Maryam12','My1234','Riyadh','Saudi Arabia'),
('3687','Maab','Almohsin','Maab123@gmail.com','0538745105','Maab12','Ma1234','Riyadh','Saudi Arabia'),
('3178','Shahad','Alsadah','Shahad123@gmail.com','0559719200','Shahad12','Sh1234','Jeddah','Saudi Arabia');

CREATE TABLE book_table (
	Book_id INT NOT NULL PRIMARY KEY,
    Title VARCHAR(100) NOT NULL,
    Author VARCHAR(100) NOT NULL,
    Price DECIMAL(5,2) NOT NULL,
    Type_of_book ENUM('GENERAL','STUDY'),
    Section VARCHAR(45) NOT NULL,
    Rating DECIMAL(5,2)NOT NULL ,
    Admin_id INT NOT NULL,
    FOREIGN KEY(Admin_id) REFERENCES admin_table(admin_id));
   
INSERT INTO book_table(book_id,Title,Author,Price,Type_of_book,Section,Rating,Admin_id)
VALUES
('12857','Cards on the Table','Agatha Christie','67.0','GENERAL','POETRY',3.75,'2476'),
('11088','Before the coffee','Toshikazu Kawaguchi','55.0','GENERAL','STORY','3.50','2674'),
('97812','Happy Camper','shamini flint','35.0','GENERAL','STORY','3.0','3386'),
('03673','Day of Accident','Nuala Ellwood','139.0','GENERAL','CRIME','4.0','2424'),
('01983','There were none','Agatha Christie','46.0','GENERAL','CRIME','4.75','3687'),
('99532','Mind hunter','John E. Douglas','80.0','GENERAL','CRIME','4.50','2476'),
('97820','My sisters bones','Nuala Ellwood','98.0','GENERAL','CRIME','3.50','2476'),
('27354','The house on the lake','Nuala Ellwood','71.0','GENERAL','CRIME','3.75','2674'),
('58271','Computer course','Sarah Lawrey','88.0','STUDY','CS','4.50','3386'),
('80262','Computer fundamentals','Sarah Lawrey','71.0','STUDY','CS','3.75','3178'),
('73518','Computer Science','Sarah Lawery','91.0','STUDY','CS','3.50','3178');

CREATE TABLE bill_table (
	Bill_no INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    Cust_id INT NOT NULL,
    FOREIGN KEY(Cust_id) REFERENCES customer_table(Cust_id),
    Book_id INT NOT NULL,
    FOREIGN KEY(Book_id) REFERENCES book_table(Book_id),
    Item_no INT NOT NULL,
    Amount INT NOT NULL,
	Bill_date DATE NOT NULL,
    Addressline VARCHAR(100) NOT NULL,
    Bill_type ENUM('borrow','purchase')) AUTO_INCREMENT=2000;
   
    INSERT INTO bill_table(Cust_id,Book_id,Item_no,Amount,Bill_date,Addressline,Bill_type)
    VALUES
    ('1000','99532','4','115','2022-12-23','Dammam- Alrakah','borrow'),
    ('1001','58271','3','112','2022-05-04','Riyadh- Alnafal','purchase'),
    ('1002','73518','1','110','2022-09-10','Khobar- Albandar','borrow'),
    ('1003','11088','2','109','2022-08-23','Dammam- Alnuaim','purchase'),
    ('1004','27354','3','110','2022-05-09','Riyadh-Alhasseb','borrow'),
    ('1005','73518','5','116','2022-08-06','Khobar-Alandalus ','purchase');
    
CREATE TABLE buy_table (
	Book_id INT NOT NULL,
    FOREIGN KEY(Book_id) REFERENCES book_table(Book_id),
    Cust_id INT NOT NULL,
    FOREIGN KEY(Cust_id) REFERENCES customer_table(Cust_id),
    Payment_method ENUM('Cash','Credit card')
);

INSERT INTO buy_table(Book_id,Cust_id,Payment_method)
VALUES
('99532','1000','Cash'),
('73518','1001','Credit card'),
('27354','1002','Cash'),
('80262','1003','Credit card'),
('03673','1004','Cash'),
('11088','1005','Credit card');
    
CREATE TABLE borrow_table (
	Book_id INT NOT NULL,
    FOREIGN KEY(Book_id) REFERENCES book_table(Book_id),
    Cust_id INT NOT NULL,
    FOREIGN KEY(Cust_id) REFERENCES customer_table(Cust_id),
    Return_date DATE,
    Borrow_date DATE
);

INSERT INTO borrow_table(Book_id,Cust_id,Return_date,Borrow_date)
VALUES
('99532','1000','2022-10-12','2022-09-09'),
('27354','1001','2022-12-12','2022-08-08'),
('03673','1002','2022-12-10','2022-10-10'),
('11088','1003','2022-12-11','2022-11-11'),
('03673','1004','2023-01-12','2022-12-12'),
('73518','1005','2022-12-12','2022-01-01');

-- insert into serviceCenter (scId, address, city, state, zip, mngrId, minWage, maxWage) values ('30001', '3921 Western Blvd', 'Raleigh', 'NC', '27606', '123456789', '10', '20');
-- insert into employee (eId, scId, name) values ('123456789','30001','John Doe');
-- insert into manager values ('123456789','30001','44');
-- insert into users values ('john','doe','Manager');
-- insert into userEmployee values ('john','123456789','30001');

-- insert into employee (eId, scId, name, phone, email, address) values ('132457689','30001','James Williams','4576312882','jwilliams@gmail.com','1400 Gorman St, Raleigh, NC 27606-2972');
-- insert into mechanic (eId, scId, hrRate, hrsWorked) values ('132457689','30001','35','0');
-- insert into users values ('JamesW','Williams','Mechanic');
-- insert into userEmployee values ('JamesW', '132457689', '30001');

-- ALL INSERT QUESRIES START FROM HERE

-- slots table
INSERT INTO slots VALUES ('1','8','9');
INSERT INTO slots VALUES ('2','9','10');
INSERT INTO slots VALUES ('3','10','11');
INSERT INTO slots VALUES ('4','11','12');
INSERT INTO slots VALUES ('5','13','14');
INSERT INTO slots VALUES ('6','14','15');
INSERT INTO slots VALUES ('7','15','16');
INSERT INTO slots VALUES ('8','16','17');
INSERT INTO slots VALUES ('9','17','18');
INSERT INTO slots VALUES ('10','18','19');
INSERT INTO slots VALUES ('11','19','20');

-- users table
INSERT INTO users (username,password,role) VALUES ('admin','password','Admin');
INSERT INTO users (username,password,role) VALUES ('john','doe','Manager');
INSERT INTO users (username,password,role) VALUES ('rachel','brooks','Manager');
INSERT INTO users (username,password,role) VALUES ('caleb','smith','Manager');
INSERT INTO users (username,password,role) VALUES ('JamesW','Williams','Mechanic');
INSERT INTO users (username,password,role) VALUES ('DavidJ','Johnson','Mechanic');
INSERT INTO users (username,password,role) VALUES ('MariaG','Garcia','Mechanic');
INSERT INTO users (username,password,role) VALUES ('EllieC','Clark','Mechanic');
INSERT INTO users (username,password,role) VALUES ('RobertM','Martinez','Mechanic');
INSERT INTO users (username,password,role) VALUES ('CharlesR','Rodriguez','Mechanic');
INSERT INTO users (username,password,role) VALUES ('JoseH','Hernandez','Mechanic');
INSERT INTO users (username,password,role) VALUES ('CharlieB','Brown','Mechanic');
INSERT INTO users (username,password,role) VALUES ('JeffG','Gibson','Mechanic');
INSERT INTO users (username,password,role) VALUES ('IsabelleW','Wilder','Mechanic');
INSERT INTO users (username,password,role) VALUES ('PeterT','Titus','Mechanic');
INSERT INTO users (username,password,role) VALUES ('MarkM','Mendez','Mechanic');
INSERT INTO users (username,password,role) VALUES ('LisaA','Alberti','Mechanic');
INSERT INTO users (username,password,role) VALUES ('PeterP','Parker','Customer');
INSERT INTO users (username,password,role) VALUES ('DianaP','Prince','Customer');
INSERT INTO users (username,password,role) VALUES ('BillyB','Batson','Customer');
INSERT INTO users (username,password,role) VALUES ('BruceW','Wayne','Customer');
INSERT INTO users (username,password,role) VALUES ('SteveR','Rogers','Customer');
INSERT INTO users (username,password,role) VALUES ('HappyH','Hogan','Customer');
INSERT INTO users (username,password,role) VALUES ('TonyS','Stark','Customer');
INSERT INTO users (username,password,role) VALUES ('NatashaR','Romanoff','Customer');
INSERT INTO users (username,password,role) VALUES ('HarveyB','Bullock','Customer');
INSERT INTO users (username,password,role) VALUES ('SamW','Wilson','Customer');
INSERT INTO users (username,password,role) VALUES ('WandaM','Maximoff','Customer');
INSERT INTO users (username,password,role) VALUES ('VirginiaP','Potts','Customer');

-- serviceCenter table
INSERT INTO serviceCenter (scId,address,city,state,zip,phoneNo,status,mngrId,minWage,maxWage,openOnSat) VALUES ('30001','3921 Western Blvd','Raleigh','NC','27606','3392601234','Pending','123456789','30','40','Y');
INSERT INTO serviceCenter (scId,address,city,state,zip,phoneNo,status,mngrId,minWage,maxWage,openOnSat) VALUES ('30002','4500 Preslyn Dr Suite 103','Raleigh','NC','27616','8576890280','Pending','987654321','25','35','Y');
INSERT INTO serviceCenter (scId,address,city,state,zip,phoneNo,status,mngrId,minWage,maxWage,openOnSat) VALUES ('30003','9515 Chapel Hill Rd','Morrisville','NC','27560','7798182200','Pending','987612345','20','25','N');

-- employee table
INSERT INTO employee (eId,scId,name,phone,email,address) VALUES ('123456789','30001','John Doe','8636368778','jdoe@gmail.com','1378 University Woods, Raleigh, NC 27612');
INSERT INTO employee (eId,scId,name,phone,email,address) VALUES ('987654321','30002','Rachel Brooks','8972468552','rbrooks@ymail.com','2201 Gorman Parkwood, Raleigh, NC 27618');
INSERT INTO employee (eId,scId,name,phone,email,address) VALUES ('987612345','30003','Caleb Smith','8547963210','csmith@yahoo.com','1538 Red Bud Lane, Morrisville, NC 27560');
INSERT INTO employee (eId,scId,name,phone,email,address) VALUES ('132457689','30001','James Williams','4576312882','jwilliams@gmail.com','1400 Gorman St, Raleigh, NC 27606-2972');
INSERT INTO employee (eId,scId,name,phone,email,address) VALUES ('314275869','30001','David Johnson','9892321100','djohnson@ymail.com','686 Stratford Court, Raleigh, NC 27606');
INSERT INTO employee (eId,scId,name,phone,email,address) VALUES ('241368579','30001','Maria Garcia','4573459090','mgarcia@yahoo.com','1521 Graduate Lane, Raleigh, NC 27606');
INSERT INTO employee (eId,scId,name,phone,email,address) VALUES ('423186759','30002','Ellie Clark','9892180921','eclark@gmail.com','3125 Avent Ferry Road, Raleigh, NC 27605');
INSERT INTO employee (eId,scId,name,phone,email,address) VALUES ('123789456','30002','Robert Martinez','7652304282','rmartinez@ymail.com','1232 Tartan Circle, Raleigh, NC 27607');
INSERT INTO employee (eId,scId,name,phone,email,address) VALUES ('789123456','30002','Charles Rodriguez','9092234281','crodriguez@yahoo.com','218 Patton Lane, Raleigh, NC 27603');
INSERT INTO employee (eId,scId,name,phone,email,address) VALUES ('125689347','30002','Jose Hernandez','7653241714','jhernandez@gmail.com','4747 Dola Mine Road, Raleigh, NC 27609');
INSERT INTO employee (eId,scId,name,phone,email,address) VALUES ('347812569','30003','Charlie Brown','9091237568','cbrown@ymail.com','1 Rockford Mountain Lane, Morrisville, NC 27560');
INSERT INTO employee (eId,scId,name,phone,email,address) VALUES ('123456780','30003','Jeff Gibson','3390108899','jgibson@yahoo.com','900 Development Drive, Morrisville, NC 27560');
INSERT INTO employee (eId,scId,name,phone,email,address) VALUES ('123456708','30003','Isabelle Wilder','3394561231','iwilder@gmail.com','6601 Koppers Road, Morrisville, NC 27560');
INSERT INTO employee (eId,scId,name,phone,email,address) VALUES ('123456078','30003','Peter Titus','3396723418','ptitus@ymail.com','2860 Slater Road, Morrisville, NC 27560');
INSERT INTO employee (eId,scId,name,phone,email,address) VALUES ('123450678','30003','Mark Mendez','3395792080','mmendez@yahoo.com','140 Southport Drive, Morrisville, NC 27560');
INSERT INTO employee (eId,scId,name,phone,email,address) VALUES ('123405678','30003','Lisa Alberti','3391126787','lalberti@yahoo.com','100 Valley Glen Drive, Morrisville, NC 27560');

-- userEmployee table
INSERT INTO userEmployee (username,eId,scId) VALUES ('john','123456789','30001');
INSERT INTO userEmployee (username,eId,scId) VALUES ('rachel','987654321','30002');
INSERT INTO userEmployee (username,eId,scId) VALUES ('caleb','987612345','30003');
INSERT INTO userEmployee (username,eId,scId) VALUES ('JamesW','132457689','30001');
INSERT INTO userEmployee (username,eId,scId) VALUES ('DavidJ','314275869','30001');
INSERT INTO userEmployee (username,eId,scId) VALUES ('MariaG','241368579','30001');
INSERT INTO userEmployee (username,eId,scId) VALUES ('EllieC','423186759','30002');
INSERT INTO userEmployee (username,eId,scId) VALUES ('RobertM','123789456','30002');
INSERT INTO userEmployee (username,eId,scId) VALUES ('CharlesR','789123456','30002');
INSERT INTO userEmployee (username,eId,scId) VALUES ('JoseH','125689347','30002');
INSERT INTO userEmployee (username,eId,scId) VALUES ('CharlieB','347812569','30003');
INSERT INTO userEmployee (username,eId,scId) VALUES ('JeffG','123456780','30003');
INSERT INTO userEmployee (username,eId,scId) VALUES ('IsabelleW','123456708','30003');
INSERT INTO userEmployee (username,eId,scId) VALUES ('PeterT','123456078','30003');
INSERT INTO userEmployee (username,eId,scId) VALUES ('MarkM','123450678','30003');
INSERT INTO userEmployee (username,eId,scId) VALUES ('LisaA','123405678','30003');

-- model table
INSERT INTO model VALUES ('1','Honda');
INSERT INTO model VALUES ('2','Toyota');
INSERT INTO model VALUES ('3','Nissan');

-- cars table

INSERT INTO cars (vinNo,mId,year,currMileage) VALUES ('4Y1BL658','2','2007','53000');
INSERT INTO cars (vinNo,mId,year,currMileage) VALUES ('7A1ST264','1','1999','117000');
INSERT INTO cars (vinNo,mId,year,currMileage) VALUES ('5TR3K914','3','2015','111000');
INSERT INTO cars (vinNo,mId,year,currMileage) VALUES ('15DC9A87','2','2020','21000');
INSERT INTO cars (vinNo,mId,year,currMileage) VALUES ('18S5R2D8','3','2019','195500');
INSERT INTO cars (vinNo,mId,year,currMileage) VALUES ('9R2UHP54','1','2013','67900');
INSERT INTO cars (vinNo,mId,year,currMileage) VALUES ('88TSM888','1','2000','10000');
INSERT INTO cars (vinNo,mId,year,currMileage) VALUES ('71HK2D89','2','2004','195550');
INSERT INTO cars (vinNo,mId,year,currMileage) VALUES ('34KLE19D','2','2010','123800');
INSERT INTO cars (vinNo,mId,year,currMileage) VALUES ('29T56WC3','3','2011','51300');
INSERT INTO cars (vinNo,mId,year,currMileage) VALUES ('P39VN198','3','2013','39500');
INSERT INTO cars (vinNo,mId,year,currMileage) VALUES ('39YVS415','1','2021','49900');

-- manager table
INSERT INTO manager (eId,scId,annualSal) VALUES ('123456789','30001','44');
INSERT INTO manager (eId,scId,annualSal) VALUES ('987654321','30002','35');
INSERT INTO manager (eId,scId,annualSal) VALUES ('987612345','30003','25');

-- receptionist table

-- mechanic table
INSERT INTO mechanic (eId,scId,hrRate) VALUES ('132457689','30001','35');
INSERT INTO mechanic (eId,scId,hrRate) VALUES ('314275869','30001','35');
INSERT INTO mechanic (eId,scId,hrRate) VALUES ('241368579','30001','35');
INSERT INTO mechanic (eId,scId,hrRate) VALUES ('423186759','30002','25');
INSERT INTO mechanic (eId,scId,hrRate) VALUES ('123789456','30002','25');
INSERT INTO mechanic (eId,scId,hrRate) VALUES ('789123456','30002','25');
INSERT INTO mechanic (eId,scId,hrRate) VALUES ('125689347','30002','25');
INSERT INTO mechanic (eId,scId,hrRate) VALUES ('347812569','30003','22');
INSERT INTO mechanic (eId,scId,hrRate) VALUES ('123456780','30003','22');
INSERT INTO mechanic (eId,scId,hrRate) VALUES ('123456708','30003','22');
INSERT INTO mechanic (eId,scId,hrRate) VALUES ('123456078','30003','22');
INSERT INTO mechanic (eId,scId,hrRate) VALUES ('123450678','30003','22');
INSERT INTO mechanic (eId,scId,hrRate) VALUES ('123405678','30003','22');


-- services table
INSERT INTO services (sNo,sName,duration) VALUES ('101','Belt Replacement','1');
INSERT INTO services (sNo,sName,duration) VALUES ('102','Engine Repair','5');
INSERT INTO services (sNo,sName,duration) VALUES ('103','Exhaust Repair','4');
INSERT INTO services (sNo,sName,duration) VALUES ('104','Muffler Repair','2');
INSERT INTO services (sNo,sName,duration) VALUES ('105','Alternator Repair','4');
INSERT INTO services (sNo,sName,duration) VALUES ('106','Power Lock Repair','5');
INSERT INTO services (sNo,sName,duration) VALUES ('107','Axle Repair','7');
INSERT INTO services (sNo,sName,duration) VALUES ('108','Brake Repair','3');
INSERT INTO services (sNo,sName,duration) VALUES ('109','Tire Balancing','2');
INSERT INTO services (sNo,sName,duration) VALUES ('110','Wheel Alignment','1');
INSERT INTO services (sNo,sName,duration) VALUES ('111','Compressor Repair','3');
INSERT INTO services (sNo,sName,duration) VALUES ('112','Evaporator Repair','4');
INSERT INTO services (sNo,sName,duration) VALUES ('113','A','3');
INSERT INTO services (sNo,sName,duration) VALUES ('114','B','6');
INSERT INTO services (sNo,sName,duration) VALUES ('115','C','9');

-- repair table
INSERT INTO repair (sNo,repairCat) VALUES ('101','Engine Services');
INSERT INTO repair (sNo,repairCat) VALUES ('102','Engine Services');
INSERT INTO repair (sNo,repairCat) VALUES ('103','Exhaust Services');
INSERT INTO repair (sNo,repairCat) VALUES ('104','Exhaust Services');
INSERT INTO repair (sNo,repairCat) VALUES ('105','Electrical Services');
INSERT INTO repair (sNo,repairCat) VALUES ('106','Electrical Services');
INSERT INTO repair (sNo,repairCat) VALUES ('107','Transmission Services');
INSERT INTO repair (sNo,repairCat) VALUES ('108','Transmission Services');
INSERT INTO repair (sNo,repairCat) VALUES ('109','Tire Services');
INSERT INTO repair (sNo,repairCat) VALUES ('110','Tire Services');
INSERT INTO repair (sNo,repairCat) VALUES ('111','Heating and A/C Services');
INSERT INTO repair (sNo,repairCat) VALUES ('112','Heating and A/C Services');

-- maintenance table
INSERT INTO maintenance (sNo,sName) VALUES ('113','Oil Changes');
INSERT INTO maintenance (sNo,sName) VALUES ('113','Filter Replacements');
INSERT INTO maintenance (sNo,sName) VALUES ('114','Oil Changes');
INSERT INTO maintenance (sNo,sName) VALUES ('114','Filter Replacements');
INSERT INTO maintenance (sNo,sName) VALUES ('114','Brake Repair');
INSERT INTO maintenance (sNo,sName) VALUES ('114','Check Engine Light Diagnostics');
INSERT INTO maintenance (sNo,sName) VALUES ('115','Oil Changes');
INSERT INTO maintenance (sNo,sName) VALUES ('115','Filter Replacements');
INSERT INTO maintenance (sNo,sName) VALUES ('115','Brake Repair');
INSERT INTO maintenance (sNo,sName) VALUES ('115','Check Engine Light Diagnostics');
INSERT INTO maintenance (sNo,sName) VALUES ('115','Suspension Repair');
INSERT INTO maintenance (sNo,sName) VALUES ('115','Evaporator Repair');

-- serviceProvided table
INSERT INTO serviceProvided (scId,sNo,mId,price) VALUES ('30001','101','1','140');
INSERT INTO serviceProvided (scId,sNo,mId,price) VALUES ('30001','102','1','400');
INSERT INTO serviceProvided (scId,sNo,mId,price) VALUES ('30001','103','1','590');
INSERT INTO serviceProvided (scId,sNo,mId,price) VALUES ('30001','104','1','140');
INSERT INTO serviceProvided (scId,sNo,mId,price) VALUES ('30001','105','1','400');
INSERT INTO serviceProvided (scId,sNo,mId,price) VALUES ('30001','106','1','400');
INSERT INTO serviceProvided (scId,sNo,mId,price) VALUES ('30001','107','1','1000');
INSERT INTO serviceProvided (scId,sNo,mId,price) VALUES ('30001','108','1','400');
INSERT INTO serviceProvided (scId,sNo,mId,price) VALUES ('30001','109','1','50');
INSERT INTO serviceProvided (scId,sNo,mId,price) VALUES ('30001','110','1','140');
INSERT INTO serviceProvided (scId,sNo,mId,price) VALUES ('30001','111','1','590');
INSERT INTO serviceProvided (scId,sNo,mId,price) VALUES ('30001','112','1','400');
INSERT INTO serviceProvided (scId,sNo,mId,price) VALUES ('30001','113','1','120');
INSERT INTO serviceProvided (scId,sNo,mId,price) VALUES ('30001','114','1','195');
INSERT INTO serviceProvided (scId,sNo,mId,price) VALUES ('30001','115','1','300');
INSERT INTO serviceProvided (scId,sNo,mId,price) VALUES ('30001','101','2','175');
INSERT INTO serviceProvided (scId,sNo,mId,price) VALUES ('30001','102','2','500');
INSERT INTO serviceProvided (scId,sNo,mId,price) VALUES ('30001','103','2','700');
INSERT INTO serviceProvided (scId,sNo,mId,price) VALUES ('30001','104','2','175');
INSERT INTO serviceProvided (scId,sNo,mId,price) VALUES ('30001','105','2','500');
INSERT INTO serviceProvided (scId,sNo,mId,price) VALUES ('30001','106','2','500');
INSERT INTO serviceProvided (scId,sNo,mId,price) VALUES ('30001','107','2','1200');
INSERT INTO serviceProvided (scId,sNo,mId,price) VALUES ('30001','108','2','500');
INSERT INTO serviceProvided (scId,sNo,mId,price) VALUES ('30001','109','2','70');
INSERT INTO serviceProvided (scId,sNo,mId,price) VALUES ('30001','110','2','175');
INSERT INTO serviceProvided (scId,sNo,mId,price) VALUES ('30001','111','2','700');
INSERT INTO serviceProvided (scId,sNo,mId,price) VALUES ('30001','112','2','500');
INSERT INTO serviceProvided (scId,sNo,mId,price) VALUES ('30001','113','2','190');
INSERT INTO serviceProvided (scId,sNo,mId,price) VALUES ('30001','114','2','210');
INSERT INTO serviceProvided (scId,sNo,mId,price) VALUES ('30001','115','2','310');
INSERT INTO serviceProvided (scId,sNo,mId,price) VALUES ('30001','101','3','160');
INSERT INTO serviceProvided (scId,sNo,mId,price) VALUES ('30001','102','3','450');
INSERT INTO serviceProvided (scId,sNo,mId,price) VALUES ('30001','103','3','680');
INSERT INTO serviceProvided (scId,sNo,mId,price) VALUES ('30001','104','3','160');
INSERT INTO serviceProvided (scId,sNo,mId,price) VALUES ('30001','105','3','450');
INSERT INTO serviceProvided (scId,sNo,mId,price) VALUES ('30001','106','3','450');
INSERT INTO serviceProvided (scId,sNo,mId,price) VALUES ('30001','107','3','1100');
INSERT INTO serviceProvided (scId,sNo,mId,price) VALUES ('30001','108','3','450');
INSERT INTO serviceProvided (scId,sNo,mId,price) VALUES ('30001','109','3','60');
INSERT INTO serviceProvided (scId,sNo,mId,price) VALUES ('30001','110','3','160');
INSERT INTO serviceProvided (scId,sNo,mId,price) VALUES ('30001','111','3','680');
INSERT INTO serviceProvided (scId,sNo,mId,price) VALUES ('30001','112','3','450');
INSERT INTO serviceProvided (scId,sNo,mId,price) VALUES ('30001','113','3','200');
INSERT INTO serviceProvided (scId,sNo,mId,price) VALUES ('30001','114','3','215');
INSERT INTO serviceProvided (scId,sNo,mId,price) VALUES ('30001','115','3','305');
INSERT INTO serviceProvided (scId,sNo,mId,price) VALUES ('30002','101','1','160');
INSERT INTO serviceProvided (scId,sNo,mId,price) VALUES ('30002','102','1','420');
INSERT INTO serviceProvided (scId,sNo,mId,price) VALUES ('30002','103','1','610');
INSERT INTO serviceProvided (scId,sNo,mId,price) VALUES ('30002','104','1','160');
INSERT INTO serviceProvided (scId,sNo,mId,price) VALUES ('30002','105','1','420');
INSERT INTO serviceProvided (scId,sNo,mId,price) VALUES ('30002','106','1','420');
INSERT INTO serviceProvided (scId,sNo,mId,price) VALUES ('30002','107','1','1020');
INSERT INTO serviceProvided (scId,sNo,mId,price) VALUES ('30002','108','1','420');
INSERT INTO serviceProvided (scId,sNo,mId,price) VALUES ('30002','109','1','70');
INSERT INTO serviceProvided (scId,sNo,mId,price) VALUES ('30002','110','1','160');
INSERT INTO serviceProvided (scId,sNo,mId,price) VALUES ('30002','111','1','610');
INSERT INTO serviceProvided (scId,sNo,mId,price) VALUES ('30002','112','1','420');
INSERT INTO serviceProvided (scId,sNo,mId,price) VALUES ('30002','113','1','125');
INSERT INTO serviceProvided (scId,sNo,mId,price) VALUES ('30002','114','1','200');
INSERT INTO serviceProvided (scId,sNo,mId,price) VALUES ('30002','115','1','305');
INSERT INTO serviceProvided (scId,sNo,mId,price) VALUES ('30002','101','2','195');
INSERT INTO serviceProvided (scId,sNo,mId,price) VALUES ('30002','102','2','520');
INSERT INTO serviceProvided (scId,sNo,mId,price) VALUES ('30002','103','2','720');
INSERT INTO serviceProvided (scId,sNo,mId,price) VALUES ('30002','104','2','195');
INSERT INTO serviceProvided (scId,sNo,mId,price) VALUES ('30002','105','2','520');
INSERT INTO serviceProvided (scId,sNo,mId,price) VALUES ('30002','106','2','520');
INSERT INTO serviceProvided (scId,sNo,mId,price) VALUES ('30002','107','2','1220');
INSERT INTO serviceProvided (scId,sNo,mId,price) VALUES ('30002','108','2','520');
INSERT INTO serviceProvided (scId,sNo,mId,price) VALUES ('30002','109','2','90');
INSERT INTO serviceProvided (scId,sNo,mId,price) VALUES ('30002','110','2','195');
INSERT INTO serviceProvided (scId,sNo,mId,price) VALUES ('30002','111','2','720');
INSERT INTO serviceProvided (scId,sNo,mId,price) VALUES ('30002','112','2','520');
INSERT INTO serviceProvided (scId,sNo,mId,price) VALUES ('30002','113','2','195');
INSERT INTO serviceProvided (scId,sNo,mId,price) VALUES ('30002','114','2','215');
INSERT INTO serviceProvided (scId,sNo,mId,price) VALUES ('30002','115','2','315');
INSERT INTO serviceProvided (scId,sNo,mId,price) VALUES ('30002','101','3','180');
INSERT INTO serviceProvided (scId,sNo,mId,price) VALUES ('30002','102','3','470');
INSERT INTO serviceProvided (scId,sNo,mId,price) VALUES ('30002','103','3','700');
INSERT INTO serviceProvided (scId,sNo,mId,price) VALUES ('30002','104','3','180');
INSERT INTO serviceProvided (scId,sNo,mId,price) VALUES ('30002','105','3','470');
INSERT INTO serviceProvided (scId,sNo,mId,price) VALUES ('30002','106','3','470');
INSERT INTO serviceProvided (scId,sNo,mId,price) VALUES ('30002','107','3','1120');
INSERT INTO serviceProvided (scId,sNo,mId,price) VALUES ('30002','108','3','470');
INSERT INTO serviceProvided (scId,sNo,mId,price) VALUES ('30002','109','3','80');
INSERT INTO serviceProvided (scId,sNo,mId,price) VALUES ('30002','110','3','180');
INSERT INTO serviceProvided (scId,sNo,mId,price) VALUES ('30002','111','3','700');
INSERT INTO serviceProvided (scId,sNo,mId,price) VALUES ('30002','112','3','470');
INSERT INTO serviceProvided (scId,sNo,mId,price) VALUES ('30002','113','3','205');
INSERT INTO serviceProvided (scId,sNo,mId,price) VALUES ('30002','114','3','220');
INSERT INTO serviceProvided (scId,sNo,mId,price) VALUES ('30002','115','3','310');
INSERT INTO serviceProvided (scId,sNo,mId,price) VALUES ('30003','101','1','175');
INSERT INTO serviceProvided (scId,sNo,mId,price) VALUES ('30003','102','1','435');
INSERT INTO serviceProvided (scId,sNo,mId,price) VALUES ('30003','103','1','625');
INSERT INTO serviceProvided (scId,sNo,mId,price) VALUES ('30003','104','1','175');
INSERT INTO serviceProvided (scId,sNo,mId,price) VALUES ('30003','105','1','435');
INSERT INTO serviceProvided (scId,sNo,mId,price) VALUES ('30003','106','1','435');
INSERT INTO serviceProvided (scId,sNo,mId,price) VALUES ('30003','107','1','1035');
INSERT INTO serviceProvided (scId,sNo,mId,price) VALUES ('30003','108','1','435');
INSERT INTO serviceProvided (scId,sNo,mId,price) VALUES ('30003','109','1','85');
INSERT INTO serviceProvided (scId,sNo,mId,price) VALUES ('30003','110','1','175');
INSERT INTO serviceProvided (scId,sNo,mId,price) VALUES ('30003','111','1','625');
INSERT INTO serviceProvided (scId,sNo,mId,price) VALUES ('30003','112','1','435');
INSERT INTO serviceProvided (scId,sNo,mId,price) VALUES ('30003','113','1','140');
INSERT INTO serviceProvided (scId,sNo,mId,price) VALUES ('30003','114','1','210');
INSERT INTO serviceProvided (scId,sNo,mId,price) VALUES ('30003','115','1','310');
INSERT INTO serviceProvided (scId,sNo,mId,price) VALUES ('30003','101','2','210');
INSERT INTO serviceProvided (scId,sNo,mId,price) VALUES ('30003','102','2','535');
INSERT INTO serviceProvided (scId,sNo,mId,price) VALUES ('30003','103','2','735');
INSERT INTO serviceProvided (scId,sNo,mId,price) VALUES ('30003','104','2','210');
INSERT INTO serviceProvided (scId,sNo,mId,price) VALUES ('30003','105','2','535');
INSERT INTO serviceProvided (scId,sNo,mId,price) VALUES ('30003','106','2','535');
INSERT INTO serviceProvided (scId,sNo,mId,price) VALUES ('30003','107','2','1235');
INSERT INTO serviceProvided (scId,sNo,mId,price) VALUES ('30003','108','2','535');
INSERT INTO serviceProvided (scId,sNo,mId,price) VALUES ('30003','109','2','105');
INSERT INTO serviceProvided (scId,sNo,mId,price) VALUES ('30003','110','2','210');
INSERT INTO serviceProvided (scId,sNo,mId,price) VALUES ('30003','111','2','735');
INSERT INTO serviceProvided (scId,sNo,mId,price) VALUES ('30003','112','2','535');
INSERT INTO serviceProvided (scId,sNo,mId,price) VALUES ('30003','113','2','180');
INSERT INTO serviceProvided (scId,sNo,mId,price) VALUES ('30003','114','2','220');
INSERT INTO serviceProvided (scId,sNo,mId,price) VALUES ('30003','115','2','305');
INSERT INTO serviceProvided (scId,sNo,mId,price) VALUES ('30003','101','3','195');
INSERT INTO serviceProvided (scId,sNo,mId,price) VALUES ('30003','102','3','485');
INSERT INTO serviceProvided (scId,sNo,mId,price) VALUES ('30003','103','3','715');
INSERT INTO serviceProvided (scId,sNo,mId,price) VALUES ('30003','104','3','195');
INSERT INTO serviceProvided (scId,sNo,mId,price) VALUES ('30003','105','3','485');
INSERT INTO serviceProvided (scId,sNo,mId,price) VALUES ('30003','106','3','485');
INSERT INTO serviceProvided (scId,sNo,mId,price) VALUES ('30003','107','3','1135');
INSERT INTO serviceProvided (scId,sNo,mId,price) VALUES ('30003','108','3','485');
INSERT INTO serviceProvided (scId,sNo,mId,price) VALUES ('30003','109','3','95');
INSERT INTO serviceProvided (scId,sNo,mId,price) VALUES ('30003','110','3','195');
INSERT INTO serviceProvided (scId,sNo,mId,price) VALUES ('30003','111','3','715');
INSERT INTO serviceProvided (scId,sNo,mId,price) VALUES ('30003','112','3','485');
INSERT INTO serviceProvided (scId,sNo,mId,price) VALUES ('30003','113','3','195');
INSERT INTO serviceProvided (scId,sNo,mId,price) VALUES ('30003','114','3','215');
INSERT INTO serviceProvided (scId,sNo,mId,price) VALUES ('30003','115','3','310');

-- customer table
INSERT INTO customer (scId,cId,firstName,lastName,email,phoneNo,address,status) VALUES ('30001','10001','Peter','Parker','','','','F');
INSERT INTO customer (scId,cId,firstName,lastName,email,phoneNo,address,status) VALUES ('30001','10002','Diana','Prince','','','','F');
INSERT INTO customer (scId,cId,firstName,lastName,email,phoneNo,address,status) VALUES ('30002','10053','Billy','Batson','','','','F');
INSERT INTO customer (scId,cId,firstName,lastName,email,phoneNo,address,status) VALUES ('30002','10010','Bruce','Wayne','','','','F');
INSERT INTO customer (scId,cId,firstName,lastName,email,phoneNo,address,status) VALUES ('30002','10001','Steve','Rogers','','','','F');
INSERT INTO customer (scId,cId,firstName,lastName,email,phoneNo,address,status) VALUES ('30002','10035','Happy','Hogan','','','','F');
INSERT INTO customer (scId,cId,firstName,lastName,email,phoneNo,address,status) VALUES ('30003','10002','Tony','Stark','','','','F');
INSERT INTO customer (scId,cId,firstName,lastName,email,phoneNo,address,status) VALUES ('30003','10003','Natasha','Romanoff','','','','F');
INSERT INTO customer (scId,cId,firstName,lastName,email,phoneNo,address,status) VALUES ('30003','10011','Harvey','Bullock','','','','F');
INSERT INTO customer (scId,cId,firstName,lastName,email,phoneNo,address,status) VALUES ('30003','10062','Sam','Wilson','','','','F');
INSERT INTO customer (scId,cId,firstName,lastName,email,phoneNo,address,status) VALUES ('30003','10501','Wanda','Maximoff','','','','F');
INSERT INTO customer (scId,cId,firstName,lastName,email,phoneNo,address,status) VALUES ('30003','10010','Virginia','Potts','','','','F');

-- userCustomer table
INSERT INTO userCustomer (username,cId,scId) VALUES ('PeterP','10001','30001');
INSERT INTO userCustomer (username,cId,scId) VALUES ('DianaP','10002','30001');
INSERT INTO userCustomer (username,cId,scId) VALUES ('BillyB','10053','30002');
INSERT INTO userCustomer (username,cId,scId) VALUES ('BruceW','10010','30002');
INSERT INTO userCustomer (username,cId,scId) VALUES ('SteveR','10001','30002');
INSERT INTO userCustomer (username,cId,scId) VALUES ('HappyH','10035','30002');
INSERT INTO userCustomer (username,cId,scId) VALUES ('TonyS','10002','30003');
INSERT INTO userCustomer (username,cId,scId) VALUES ('NatashaR','10003','30003');
INSERT INTO userCustomer (username,cId,scId) VALUES ('HarveyB','10011','30003');
INSERT INTO userCustomer (username,cId,scId) VALUES ('SamW','10062','30003');
INSERT INTO userCustomer (username,cId,scId) VALUES ('WandaM','10501','30003');
INSERT INTO userCustomer (username,cId,scId) VALUES ('VirginiaP','10010','30003');

-- carOwner table
INSERT INTO carOwner (scId,cId,vinNo,nextMaintenance) VALUES ('30001','10001','4Y1BL658','A');
INSERT INTO carOwner (scId,cId,vinNo,nextMaintenance) VALUES ('30001','10002','7A1ST264','A');
INSERT INTO carOwner (scId,cId,vinNo,nextMaintenance) VALUES ('30002','10053','5TR3K914','A');
INSERT INTO carOwner (scId,cId,vinNo,nextMaintenance) VALUES ('30002','10010','15DC9A87','A');
INSERT INTO carOwner (scId,cId,vinNo,nextMaintenance) VALUES ('30002','10001','18S5R2D8','A');
INSERT INTO carOwner (scId,cId,vinNo,nextMaintenance) VALUES ('30002','10035','9R2UHP54','A');
INSERT INTO carOwner (scId,cId,vinNo,nextMaintenance) VALUES ('30003','10002','88TSM888','A');
INSERT INTO carOwner (scId,cId,vinNo,nextMaintenance) VALUES ('30003','10003','71HK2D89','A');
INSERT INTO carOwner (scId,cId,vinNo,nextMaintenance) VALUES ('30003','10011','34KLE19D','A');
INSERT INTO carOwner (scId,cId,vinNo,nextMaintenance) VALUES ('30003','10062','29T56WC3','A');
INSERT INTO carOwner (scId,cId,vinNo,nextMaintenance) VALUES ('30003','10501','P39VN198','A');
INSERT INTO carOwner (scId,cId,vinNo,nextMaintenance) VALUES ('30003','10010','39YVS415','A');

-- serviceRequest table
INSERT INTO serviceRequest (invoiceId,totalAmount,status,serviceDate) VALUES ('135791','195','U','2');
INSERT INTO serviceRequest (invoiceId,totalAmount,status,serviceDate) VALUES ('246802','175','U','2');
INSERT INTO serviceRequest (invoiceId,totalAmount,status,serviceDate) VALUES ('123456','95','P','1');
INSERT INTO serviceRequest (invoiceId,totalAmount,status,serviceDate) VALUES ('654321','420','P','11');
INSERT INTO serviceRequest (invoiceId,totalAmount,status,serviceDate) VALUES ('101010','700','P','8');
INSERT INTO serviceRequest (invoiceId,totalAmount,status,serviceDate) VALUES ('112233','215','P','13');
INSERT INTO serviceRequest (invoiceId,totalAmount,status,serviceDate) VALUES ('113355','195','P','19');

-- customerRequest table
INSERT INTO customerRequest (scId,cId,vinNo,invoiceId) VALUES ('30003','10501','P39VN198','135791');
INSERT INTO customerRequest (scId,cId,vinNo,invoiceId) VALUES ('30003','10010','39YVS415','246802');
INSERT INTO customerRequest (scId,cId,vinNo,invoiceId) VALUES ('30003','10062','29T56WC3','123456');
INSERT INTO customerRequest (scId,cId,vinNo,invoiceId) VALUES ('30002','10035','9R2UHP54','654321');
INSERT INTO customerRequest (scId,cId,vinNo,invoiceId) VALUES ('30002','10053','5TR3K914','101010');
INSERT INTO customerRequest (scId,cId,vinNo,invoiceId) VALUES ('30002','10010','15DC9A87','112233');
INSERT INTO customerRequest (scId,cId,vinNo,invoiceId) VALUES ('30002','10001','18S5R2D8','113355');

-- requestedServices table
INSERT INTO requestedServices (invoiceId,scId,sNo,mId) VALUES ('135791','30003','110','3');
INSERT INTO requestedServices (invoiceId,scId,sNo,mId) VALUES ('246802','30003','101','1');
INSERT INTO requestedServices (invoiceId,scId,sNo,mId) VALUES ('123456','30003','109','3');
INSERT INTO requestedServices (invoiceId,scId,sNo,mId) VALUES ('654321','30002','105','1');
INSERT INTO requestedServices (invoiceId,scId,sNo,mId) VALUES ('101010','30002','111','3');
INSERT INTO requestedServices (invoiceId,scId,sNo,mId) VALUES ('112233','30002','114','2');
INSERT INTO requestedServices (invoiceId,scId,sNo,mId) VALUES ('113355','30002','113','3');

-- mechanicAvailability table

-- serveRequest table
INSERT INTO serveRequest (eId,scId,invoiceId,startTime,endTime) VALUES ('123405678','30003','135791','8:00','9:00');
INSERT INTO serveRequest (eId,scId,invoiceId,startTime,endTime) VALUES ('123450678','30003','246802','8:00','9:00');
INSERT INTO serveRequest (eId,scId,invoiceId,startTime,endTime) VALUES ('123456780','30003','123456','8:00','9:00');
INSERT INTO serveRequest (eId,scId,invoiceId,startTime,endTime) VALUES ('423186759','30002','654321','9:00','14:00');
INSERT INTO serveRequest (eId,scId,invoiceId,startTime,endTime) VALUES ('125689347','30002','101010','10:00','14:00');
INSERT INTO serveRequest (eId,scId,invoiceId,startTime,endTime) VALUES ('789123456','30002','112233','9:00','16:00');
INSERT INTO serveRequest (eId,scId,invoiceId,startTime,endTime) VALUES ('125689347','30002','113355','13:00','16:00');


-- Set dummy mechanic availability

update mechanicAvailability me
set me.available = 'N'
where me.eid = '123789456' and me.scId = '30002'
and me.weekId = 1 and dayId = 3 and slotId >= 4 and slotId <=6;
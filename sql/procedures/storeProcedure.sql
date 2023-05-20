CREATE OR REPLACE PROCEDURE storeprocedure (scId IN VARCHAR2, address IN VARCHAR2,  city IN VARCHAR2, 
state IN VARCHAR2, zip IN VARCHAR2, minWage IN VARCHAR2, maxWage IN VARCHAR2, 
mngrId IN VARCHAR2,  managerUsername IN VARCHAR2, managerPassword IN VARCHAR2, mngrName IN VARCHAR2, managerSalary IN VARCHAR2)
AS
  c1 SYS_REFCURSOR;  
BEGIN
INSERT INTO serviceCenter (scId, address, city, state, zip, minWage, maxWage, mngrId) values (scId, address, city, state, zip, minWage, maxWage, mngrId);
insert into employee (eId, scId, name) values (mngrId, scId, mngrName);
insert into manager values (mngrId, scId, managerSalary);
insert into users values (managerUsername, managerPassword, 'Manager');
insert into userEmployee values (managerUsername, mngrId, scId);
END;
/
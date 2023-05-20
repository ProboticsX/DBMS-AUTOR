CREATE OR REPLACE PROCEDURE serviceProcedure (sNum IN VARCHAR2, sName IN VARCHAR2, 
duration IN VARCHAR2, category IN VARCHAR2, serviceType IN VARCHAR2)
AS
 exist number;
BEGIN
  IF serviceType = 'R' THEN
    INSERT INTO services VALUES (sNum, sName, duration);
    INSERT INTO repair VALUES (sNum, category);
  ELSE
    select count(*) into exist from services s where s.sNo=sNum;
    IF exist > 0 THEN
      INSERT INTO maintenance VALUES (sNum, sName);
    ELSE
      INSERT INTO services VALUES (sNum, category, duration);
      INSERT INTO maintenance VALUES (sNum, sName);
    END IF;
    
  END IF;
END;
/
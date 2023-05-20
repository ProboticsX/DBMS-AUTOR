CREATE OR REPLACE TRIGGER customerStatusUpdate
    BEFORE INSERT ON carOwner
    FOR EACH ROW
DECLARE
    tot number;
BEGIN
    Select count(c.vinNo) into tot from carOwner c where c.scId = :new.scId and c.cId = :new.cId;

    IF tot>=0 THEN
        update customer c set status =  'T' where c.scId = :new.scId and c.cId = :new.cId;
    ELSE
        update customer c set status =  'F' where c.scId = :new.scId and c.cId = :new.cId;
    END IF;
END;
/

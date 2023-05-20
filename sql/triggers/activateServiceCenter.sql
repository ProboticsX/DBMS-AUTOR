CREATE OR REPLACE TRIGGER activateServiceCenter
    BEFORE INSERT ON mechanic
    FOR EACH ROW
DECLARE
    tot number;
BEGIN
    Select count(m.eId) into tot from mechanic m where m.scId = :new.scId;

    IF tot>=2 THEN
        update serviceCenter set status =  'Available' where scId = :new.scId;
    END IF;
END;
/
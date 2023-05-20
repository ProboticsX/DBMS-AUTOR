CREATE OR REPLACE TRIGGER checkMechanicRate
    BEFORE INSERT OR UPDATE OF hrRate ON mechanic
    FOR EACH ROW
DECLARE
    minWage number;
    maxWage number;
BEGIN
    Select sc.minWage into minWage from serviceCenter sc where sc.scId = :new.scId;
    Select sc.maxWage into maxWage from serviceCenter sc where sc.scId = :new.scId;

    IF :new.hrRate < minWage THEN
        raise_application_error(-20000, 'Hourly rate less than minWage');
    ELSIF :new.hrRate > maxWage THEN
        raise_application_error(-20001, 'Hourly rate more than maxWage');
    END IF;
END;
/
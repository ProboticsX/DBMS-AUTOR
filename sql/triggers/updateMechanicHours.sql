CREATE OR REPLACE TRIGGER updateMechanicHours
    AFTER INSERT ON serveRequest
    FOR EACH ROW
DECLARE
    tot number;
    sSlot number;
    eSlot number;
    worked number;
BEGIN

    select startSlot, endSlot into sSlot, eSlot from serveRequest where eId =:new.eId and scId = :new.scId and weekId = :new.weekId and dayId = :new.dayId;
    
    tot := eSlot - sSlot + 1;
     
    select hrsWorked into worked from mechanicWorkingHours where eId = :new.eId and scId = :new.scId and weekId = :new.weekId;

    worked := worked + tot;

    update mechanicWorkingHours set hrsWorked = worked where eId = :new.eId and scId = :new.scId and weekId = :new.weekId;
END;
/
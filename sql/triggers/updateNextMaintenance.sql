CREATE OR REPLACE TRIGGER updateNextMaintenance
    AFTER INSERT ON requestedServices
    FOR EACH ROW
DECLARE
    tot number;
    curr varchar(30);
    currScId varchar(10);
    currCId varchar(10);
    currVinNo varchar(8);
BEGIN

    select count(*) into tot from services where sNo = :new.sNo;

    IF tot>0 THEN
        Select sName into curr from services where sNo = :new.sNo;
        select scId, cId, vinNo into currScId, currCId, currVinNo from customerRequest where invoiceId = :new.invoiceId;

        IF curr='A' THEN
            update carOwner set nextMaintenance = 'B' where scId = currScId and cId = currCId and vinNo = currVinNo;
        ELSIF curr='B' THEN
            update carOwner set nextMaintenance = 'C' where scId = currScId and cId = currCId and vinNo = currVinNo;
        ELSIF curr='C' THEN
            update carOwner set nextMaintenance = 'A' where scId = currScId and cId = currCId and vinNo = currVinNo;
        END IF;
    END IF;
END;
/
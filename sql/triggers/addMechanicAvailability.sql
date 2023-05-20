CREATE OR REPLACE TRIGGER addMechanicAvailability
    AFTER INSERT ON mechanic
    FOR EACH ROW
DECLARE
    week number;
    day number;
    slots number;
BEGIN
    FOR week in 1..4
    LOOP
        INSERT into mechanicWorkingHours values (:new.eId, :new.scId, week, 0);
        FOR day in 1..6
        LOOP
            IF week = 4 AND day=3 THEN
                EXIT;
            END IF;
            IF day = 6 THEN
                FOR slot in 2..4
                LOOP
                    INSERT into mechanicAvailability values (:new.eId, :new.scId, week, day, slot, 'Y');
                END LOOP;
            ELSE
                FOR slot in 1..11
                LOOP
                    INSERT into mechanicAvailability values (:new.eId, :new.scId, week, day, slot, 'Y');
                END LOOP;
            END IF;
        END LOOP;
    END LOOP;
END;
/
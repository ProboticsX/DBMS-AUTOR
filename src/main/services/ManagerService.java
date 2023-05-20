package main.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;

import main.helpers.DatabaseHelper;
import main.helpers.QueryFunctions;
import main.models.Role;
import main.models.Service;

public class ManagerService {

    static DatabaseHelper dbHelper;
    static Connection conn;

    public static boolean addNewEmployee(String username, String employeeName, String employeeAddress,
            String employeeEmail, String employeePhone, String employeeRole, String employeeStartDate,
            String employeeCompensation) {
        dbHelper = new DatabaseHelper();
        conn = dbHelper.getConnection();

        PreparedStatement stmt;

        String managerId = null;
        String serviceCenterId = null;
        String employeeId = null;

        String[] splittedName = employeeName.split(" ");
        String firstName = splittedName[0].trim();
        String lastName = splittedName[1].trim();

        String employeeUsername = firstName + lastName.substring(0, 1);
        String employeePassword = lastName.toLowerCase();

        try {

            managerId = QueryFunctions.getManagerIdFromUsername(username);
            serviceCenterId = QueryFunctions.getServiceCenterIdFromManagerId(managerId);
            employeeId = QueryFunctions.getMaxEmployeeIdFromRole(employeeRole);

            stmt = conn.prepareStatement(
                    "insert into employee (eId, scId, name, phone, email, address) values (?,?,?,?,?,?)");
            stmt.setString(1, employeeId);
            stmt.setString(2, serviceCenterId);
            stmt.setString(3, employeeName);
            stmt.setString(4, employeePhone);
            stmt.setString(5, employeeEmail);
            stmt.setString(6, employeeAddress);
            stmt.executeUpdate();

            if (employeeRole == Role.RECEPTIONIST) {
                stmt = conn.prepareStatement("insert into receptionist (eId, scId, annualSal) values (?,?,?)");
                stmt.setString(1, employeeId);
                stmt.setString(2, serviceCenterId);
                stmt.setString(3, employeeCompensation);
                stmt.executeUpdate();
            }

            if (employeeRole.equals(Role.MECHANIC)) {
                stmt = conn.prepareStatement("insert into mechanic (eId, scId, hrRate, hrsWorked) values (?,?,?,?)");
                stmt.setString(1, employeeId);
                stmt.setString(2, serviceCenterId);
                stmt.setString(3, employeeCompensation);
                stmt.setString(4, "0");
                stmt.executeUpdate();
            }

            stmt = conn.prepareStatement("insert into users values (?,?,?)");
            stmt.setString(1, employeeUsername);
            stmt.setString(2, employeePassword);
            stmt.setString(3, employeeRole);
            stmt.executeUpdate();

            stmt = conn.prepareStatement("insert into userEmployee (username, eId, scId) values (?,?,?)");
            stmt.setString(1, employeeUsername);
            stmt.setString(2, employeeId);
            stmt.setString(3, serviceCenterId);
            stmt.executeUpdate();

            System.out.println("Employee added successfully");
            System.out.println("Username: " + employeeUsername);
            System.out.println("ID Number: " + employeeId);

        } catch (Exception e) {
            System.out.println("ERROR:" + e);
            e.printStackTrace();
            return false;
        } finally {
            dbHelper.closeConnection(conn);
        }

        return true;
    }

    public static boolean setupOperationalHours(String username, String operationalOnSaturdays) {

        dbHelper = new DatabaseHelper();
        conn = dbHelper.getConnection();

        PreparedStatement stmt;

        String managerId = null;
        String serviceCenterId = null;

        try {
            managerId = QueryFunctions.getManagerIdFromUsername(username);
            serviceCenterId = QueryFunctions.getServiceCenterIdFromManagerId(managerId);

            if (operationalOnSaturdays.equals("Y")) {
                stmt = conn.prepareStatement("update serviceCenter set openOnSat = 'Y' where scId = ?");
                stmt.setString(1, serviceCenterId);
                stmt.executeUpdate();
            }

            System.out.println("Operational hours set successfully");

        } catch (Exception e) {
            System.out.println("ERROR:" + e);
            e.printStackTrace();
            return false;
        } finally {
            dbHelper.closeConnection(conn);
        }
        return true;
    }

    public static boolean setupMaintenanceServicePrices(String username,
            ArrayList<Service> maintenanceServicePrices) {

        dbHelper = new DatabaseHelper();
        conn = dbHelper.getConnection();

        PreparedStatement stmt;

        try {
            for (Service maintenanceService : maintenanceServicePrices) {
                stmt = conn.prepareStatement("insert into serviceProvided (scId, sNo, mId, price) values (?,?,?,?)");
                stmt.setString(1, maintenanceService.getScId());
                stmt.setString(2, maintenanceService.getsNo());
                stmt.setString(3, maintenanceService.getmId());
                stmt.setString(4, maintenanceService.getPrice());
                stmt.executeUpdate();
            }
            System.out.println("Maintenance service prices set successfully");
        } catch (Exception e) {
            System.out.println("ERROR:" + e);
            e.printStackTrace();
            return false;
        } finally {
            dbHelper.closeConnection(conn);
        }
        return true;
    }

    public static boolean setupRepairServicePrices(String username, ArrayList<Service> repairServicePrices) {

        dbHelper = new DatabaseHelper();
        conn = dbHelper.getConnection();

        PreparedStatement stmt;

        try {
            for (Service repairService : repairServicePrices) {
                stmt = conn.prepareStatement("insert into serviceProvided (scId, sNo, mId, price) values (?,?,?,?)");
                stmt.setString(1, repairService.getScId());
                stmt.setString(2, repairService.getsNo());
                stmt.setString(3, repairService.getmId());
                stmt.setString(4, repairService.getPrice());
                stmt.executeUpdate();
            }
            System.out.println("Repair service prices set successfully");
        } catch (Exception e) {
            System.out.println("ERROR:" + e);
            e.printStackTrace();
            return false;
        } finally {
            dbHelper.closeConnection(conn);
        }
        return true;
    }
}

package main.helpers;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.HashMap;

import main.models.Role;

public class QueryFunctions {

    static DatabaseHelper dbHelper;
    static Connection conn;

    public static HashMap<String, String> getServices(String service) {
        dbHelper = new DatabaseHelper();
        conn = dbHelper.getConnection();

        ResultSet servicesResultSet = dbHelper.runQuery(
                "select distinct S.sNo as rsNO, S.sName as rsName from services S inner join repair R on S.sNo = R.sNo");

        if (service.toUpperCase().equals("REPAIR")) {
            servicesResultSet = dbHelper.runQuery(
                    "select distinct S.sNo as rsNO, S.sName as rsName from services S inner join repair R on S.sNo = R.sNo");
        } else if (service.toUpperCase().equals("MAINTENANCE")) {
            servicesResultSet = dbHelper.runQuery(
                    "select distinct S.sNo as rsNO, S.sName as rsName from services S inner join maintenance M on S.sNo = M.sNo");
        } else {
            System.out.println("Invalid Service");
            return null;
        }

        HashMap<String, String> services = new HashMap<String, String>();

        try {
            while (servicesResultSet.next()) {
                services.put(servicesResultSet.getString("rsNO"),
                servicesResultSet.getString("rsName"));
            }
        } catch (Exception e) {
            System.out.println("ERROR:" + e);
            e.printStackTrace();
            return null;
        } finally {
            dbHelper.closeConnection(conn);
        }

        return services;
    }

    public static String getScheduleSNo(String schedule) {
        dbHelper = new DatabaseHelper();
        conn = dbHelper.getConnection();

        ResultSet scheduleSNoSet = dbHelper
                .runQuery("select distinct(sNo) as dsNo from services where schedule = '" + schedule + "'");

        String scheduleSNo = null;

        try {
            if (scheduleSNoSet.next()) {
                scheduleSNo = scheduleSNoSet.getString("dsNo");
            }
        } catch (Exception e) {
            System.out.println("ERROR:" + e);
            e.printStackTrace();
        }

        return scheduleSNo == null ? null : scheduleSNo;
    }

    public static HashMap<String, String> getCarsDetail() {
        dbHelper = new DatabaseHelper();
        conn = dbHelper.getConnection();

        ResultSet carsDetailResultSet = dbHelper.runQuery("select * from model");

        HashMap<String, String> cars = new HashMap<String, String>();

        try {
            while (carsDetailResultSet.next()) {
                cars.put(carsDetailResultSet.getString("mId"), carsDetailResultSet.getString("make"));
            }
        } catch (Exception e) {
            System.out.println("ERROR:" + e);
            e.printStackTrace();
            return null;
        } finally {
            dbHelper.closeConnection(conn);
        }
        return cars;
    }

    public static String getManagerIdFromUsername(String username) {
        dbHelper = new DatabaseHelper();
        conn = dbHelper.getConnection();

        ResultSet managerEIdResultSet = dbHelper
                .runQuery("SELECT eId from userEmployee where username = '" + username + "'");

        String managerId = null;

        try {
            if (managerEIdResultSet.next()) {
                managerId = managerEIdResultSet.getString("eId");
            }
        } catch (Exception e) {
            System.out.println("ERROR:" + e);
            e.printStackTrace();
        }

        return managerId == null ? null : managerId;

    }

    public static String getServiceCenterIdFromManagerId(String managerId) {
        dbHelper = new DatabaseHelper();
        conn = dbHelper.getConnection();

        ResultSet serviceCenterIdResultSet = dbHelper
                .runQuery("SELECT distinct scId as scId from serviceCenter where mngrId = '" + managerId + "'");

        String serviceCenterId = null;

        try {
            if (serviceCenterIdResultSet.next()) {
                serviceCenterId = serviceCenterIdResultSet.getString("scId");
            }
        } catch (Exception e) {
            System.out.println("ERROR:" + e);
            e.printStackTrace();
        }

        return serviceCenterId == null ? null : serviceCenterId;
    }

    public static String getServiceCenterIdFromUsername(String username) {
        dbHelper = new DatabaseHelper();
        conn = dbHelper.getConnection();

        ResultSet serviceCenterIdResultSet = dbHelper
                .runQuery(" select r.scid from userEmployee ue, receptionist r where ue.scid = r.scid and username = '"
                        + username + "'");

        String serviceCenterId = null;

        try {
            if (serviceCenterIdResultSet.next()) {
                serviceCenterId = serviceCenterIdResultSet.getString("scId");
            }
        } catch (Exception e) {
            System.out.println("ERROR:" + e);
            e.printStackTrace();
        }

        return serviceCenterId == null ? null : serviceCenterId;
    }

    public static String getMaxEmployeeIdFromRole(String employeeRole) {

        dbHelper = new DatabaseHelper();
        conn = dbHelper.getConnection();

        String getMaxQuery = null;
        Long initialID = 0L;
        String employeeId = null;

        if (employeeRole.equals(Role.CUSTOMER)) {
            getMaxQuery = "select max(cId) as maxId from customer";
            initialID = 10000L;
        } else if (employeeRole.equals(Role.MANAGER)) {
            getMaxQuery = "select max(eId) as maxId from manager";
            initialID = 123456789L;
        } else if (employeeRole.equals(Role.RECEPTIONIST)) {
            getMaxQuery = "select max(eId) as maxId from receptionist";
            initialID = 10000000L;
        } else if (employeeRole.equals(Role.MECHANIC)) {
            getMaxQuery = "select max(eId) as maxId from mechanic";
            initialID = 132457689L;
        } else {
            System.out.println("Invalid Role");
            return null;
        }

        ResultSet employeeIdResultSet = dbHelper.runQuery(getMaxQuery);

        try {
            if (employeeIdResultSet.next()) {
                String getMaxQueryResult = employeeIdResultSet.getString("maxId");

                if (getMaxQueryResult == null) {
                    employeeId = Long.toString(initialID - 1);
                } else {
                    employeeId = getMaxQueryResult;
                }
            }
        } catch (Exception e) {
            System.out.println("ERROR:" + e);
            e.printStackTrace();
        }

        employeeId = Long.toString(Long.parseLong(employeeId) + 1);

        return employeeId;
    }

    public static String getMaxRequestId(String eId, String scId) {

        dbHelper = new DatabaseHelper();
        conn = dbHelper.getConnection();

        String getMaxQuery = "select max(requestId) as requestId from pendingRequest pe where pe.eId = '"+
                                eId+"' and pe.scId = '"+scId+"'";
        Long initialRequestID = 7000L;
        String requestId = null;

        ResultSet requestIdResultSet = dbHelper.runQuery(getMaxQuery);

        try {
            if (requestIdResultSet.next()) {
                String getMaxQueryResult = requestIdResultSet.getString("requestId");

                if (getMaxQueryResult == null) {
                    requestId = Long.toString(initialRequestID - 1);
                } else {
                    requestId = getMaxQueryResult;
                }
            }
        } catch (Exception e) {
            System.out.println("ERROR:" + e);
            e.printStackTrace();
        }

        requestId = Long.toString(Long.parseLong(requestId) + 1);

        return requestId;
    }
}
package main.services;

import main.helpers.DatabaseHelper;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import java.util.*;
import java.util.HashSet;

public class AdminService {

    public static boolean addStore(String username, String storeId, String address, String managerFirstname,
            String managerLastname, String managerUsername, String managerPassword, String managerSalary,
            String managerEmpId, String minWage, String maxWage) {
        // check if already exist
        DatabaseHelper dbHelper = new DatabaseHelper();
        Connection conn = dbHelper.getConnection();

        String[] sub_address = address.split(",");
        address = sub_address[0];
        String city = sub_address[1].trim();
        sub_address[2] = sub_address[2].trim();
        String[] sub_state = sub_address[2].split(" ");
        String state = sub_state[0];
        String zip = sub_state[1];

        try {

            PreparedStatement stmt = conn.prepareStatement(
                    "insert into serviceCenter (scId, address, city, state, zip, mngrId, minWage, maxWage) values (?,?,?,?,?,?,?,?)");
            stmt.setString(1, storeId);
            stmt.setString(2, address);
            stmt.setString(3, city);
            stmt.setString(4, state);
            stmt.setString(5, zip);
            stmt.setString(6, managerEmpId);
            stmt.setString(7, minWage);
            stmt.setString(8, maxWage);
            stmt.executeUpdate();

            stmt = conn.prepareStatement("insert into employee (eId, scId, name) values (?,?,?)");
            stmt.setString(1, managerEmpId);
            stmt.setString(2, storeId);
            stmt.setString(3, managerFirstname + " " + managerLastname);
            stmt.executeUpdate();

            stmt = conn.prepareStatement("insert into manager values (?,?,?)");
            stmt.setString(1, managerEmpId);
            stmt.setString(2, storeId);
            stmt.setString(3, managerSalary);
            stmt.executeUpdate();

            stmt = conn.prepareStatement("insert into users values (?,?,'Manager')");
            stmt.setString(1, managerUsername);
            stmt.setString(2, managerPassword);
            stmt.executeUpdate();

            stmt = conn.prepareStatement("insert into userEmployee values (?,?,?)");
            stmt.setString(1, managerUsername);
            stmt.setString(2, managerEmpId);
            stmt.setString(3, storeId);
            stmt.executeUpdate();

        } catch (Exception e) {
            System.out.println("ERROR:" + e);
            e.printStackTrace();
            return false;
        } finally {
            dbHelper.closeConnection(conn);
        }

        dbHelper.closeConnection(conn);
        return true;
    }

    public static boolean addService(String username, String serviceCategory, String serviceName,
            String serviceDuration) {
        DatabaseHelper dbHelper = new DatabaseHelper();
        Connection conn = dbHelper.getConnection();
        ResultSet rs = null;

        try {
            PreparedStatement stmt = conn.prepareStatement("select max(sNo) as serviceNo from services");
            rs = stmt.executeQuery();

            rs.next();
            int serviceId = Integer.parseInt(rs.getString("serviceNo"));
            serviceId += 1;

            stmt = conn.prepareStatement("insert into services values (?,?,?)");
            stmt.setInt(1, serviceId);
            stmt.setString(2, serviceName);
            stmt.setString(3, serviceDuration);
            stmt.executeUpdate();

            stmt = conn.prepareStatement("insert into repair values (?,?)");
            stmt.setInt(1, serviceId);
            stmt.setString(2, serviceCategory);
            stmt.executeUpdate();

        } catch (Exception e) {
            System.out.println("ERROR:" + e);
            e.printStackTrace();
            return false;
        } finally {
            dbHelper.closeConnection(conn);
        }

        dbHelper.closeConnection(conn);
        return true;
    }

    public static boolean uploadService(String username, String inputServiceFile) {
        // check if already exist
        String line = "";
        String splitBy = ",";
        int numServiceAttributes = 5;
        String[] attributes = new String[numServiceAttributes];
        DatabaseHelper dbHelper = new DatabaseHelper();
        Connection conn = dbHelper.getConnection();
        try {
            BufferedReader br = new BufferedReader(new FileReader("csvs/" + inputServiceFile + ".csv"));
            int row = 0;
            while ((line = br.readLine()) != null) {
                String[] services = line.split(splitBy);
                if (row == 0) {
                    for (int i = 0; i < numServiceAttributes; i++) {
                        attributes[i] = services[i];
                    }
                } else {
                    System.out.println(Arrays.toString(services));
                    String sNo = services[0];
                    String sName = services[1];
                    String duration = services[2];
                    String category = services[3];
                    String serviceType = services[4];
                    try {
                        String query = "{call serviceProcedure(?,?,?,?,?)}";
                        CallableStatement statement = conn.prepareCall(query);
                        statement.setString(1, sNo);
                        statement.setString(2, sName);
                        statement.setString(3, duration);
                        statement.setString(4, category);
                        statement.setString(5, serviceType);
                        statement.execute();
                    } catch (Exception e) {
                        System.out.println("ERROR:" + e);
                        e.printStackTrace();
                        return false;
                    }
                }
                row++;
            }
            System.out.println("services updated!");
        } catch (IOException e) {
            System.out.println("ERROR:" + e);
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public static boolean uploadStore(String username, String inputStoreFile) {
        // check if already exist
        String line = "";
        String splitBy = ",";
        int numServiceCenterAttributes = 13;
        String[] attributes = new String[numServiceCenterAttributes];
        DatabaseHelper dbHelper = new DatabaseHelper();
        Connection conn = dbHelper.getConnection();
        try {
            BufferedReader br = new BufferedReader(new FileReader("csvs/" + inputStoreFile + ".csv"));
            int row = 0;
            while ((line = br.readLine()) != null) {
                String[] serviceCenter = line.split(splitBy);
                if (row == 0) {
                    for (int i = 0; i < numServiceCenterAttributes; i++) {
                        attributes[i] = serviceCenter[i];
                    }
                } else {
                    System.out.println(Arrays.toString(serviceCenter));
                    String scId = serviceCenter[0];
                    String address = serviceCenter[1];
                    String city = serviceCenter[2];
                    String state = serviceCenter[3];
                    String zip = serviceCenter[4];
                    String minWage = serviceCenter[5];
                    String maxWage = serviceCenter[6];
                    String mngrId = serviceCenter[7];
                    String mngrUsername = serviceCenter[8];
                    String mngrPassowrd = serviceCenter[9];
                    String mngrFirstName = serviceCenter[10];
                    String mngrLastName = serviceCenter[11];
                    String mngrSalary = serviceCenter[12];
                    try {
                        String query = "{call storeprocedure(?,?,?,?,?,?,?,?,?,?,?,?)}";
                        CallableStatement statement = conn.prepareCall(query);
                        statement.setString(1, scId);
                        statement.setString(2, address);
                        statement.setString(3, city);
                        statement.setString(4, state);
                        statement.setString(5, zip);
                        statement.setString(6, minWage);
                        statement.setString(7, maxWage);
                        statement.setString(8, mngrId);
                        statement.setString(9, mngrUsername);
                        statement.setString(10, mngrPassowrd);
                        statement.setString(11, mngrFirstName + " " + mngrLastName);
                        statement.setString(12, mngrSalary);
                        statement.execute();
                    } catch (Exception e) {
                        System.out.println("ERROR:" + e);
                        e.printStackTrace();
                        return false;
                    }
                }
                row++;
            }
            System.out.println("serviceCenter updated!");
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public static HashSet<String> getServiceCategories() {

        HashSet<String> services = new HashSet<>();
        DatabaseHelper dbHelper = new DatabaseHelper();
        Connection conn = dbHelper.getConnection();
        ResultSet rs = null;

        try {

            PreparedStatement stmt = conn.prepareStatement("select distinct repairCat from repair");
            rs = stmt.executeQuery();

            while (rs.next()) {
                services.add(rs.getString("repairCat"));
            }
        } catch (Exception e) {
            System.out.println("ERROR:" + e);
            e.printStackTrace();
        } finally {
            dbHelper.closeConnection(conn);
        }

        dbHelper.closeConnection(conn);
        return services;
    }

}

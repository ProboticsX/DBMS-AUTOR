package main.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import main.helpers.QueryFunctions;

import main.helpers.DatabaseHelper;

public class ReceptionistService {

    static DatabaseHelper dbHelper = new DatabaseHelper();
    static Connection conn = dbHelper.getConnection();

    public static void saveReceptionistDetailstoDB(String username, String customerName, String address,
            String emailAddress, String phoneNumber, String customerUsername, String vinNumber, String carManufacturer,
            String currentMileage, String year) {

        String[] cust_name = customerName.split(" ");
        String firstName = cust_name[0];
        String lastName = cust_name[1];
        String scid = null;
        String cid = null;
        String mid = null;
        String status = "F";

        PreparedStatement stmt;

        try {
            ResultSet cidResultSet = dbHelper
                    .runQuery("select max(C.cid) as maxCId from customer C, receptionist R where C.scid = R.scid and R.scid = '"
                            + scid + "'");
    

            try {
                if (cidResultSet.next()) {
                    String getMaxQueryResult = cidResultSet.getString("maxCId");
                    if(getMaxQueryResult == null){
                        cid = String.valueOf(1000000-1);
                    }else{
                        cid = getMaxQueryResult;
                    }
                }
            } catch (Exception e) {
                System.out.println("ERROR:" + e);
                e.printStackTrace();
            }

            cid = String.valueOf(Long.parseLong(cid) + 1);

            stmt = conn.prepareStatement("insert into customer VALUES(?,?,?,?,?,?,?,?)");
            stmt.setString(1, scid);
            stmt.setString(2, cid);
            stmt.setString(3, firstName);
            stmt.setString(4, lastName);
            stmt.setString(5, emailAddress);
            stmt.setString(6, phoneNumber);
            stmt.setString(7, address);
            stmt.setString(8, status);

            stmt.executeUpdate();

            if (carManufacturer.equals("Honda"))
                mid = "1";
            else if (carManufacturer.equals("Toyota"))
                mid = "2";
            else if (carManufacturer.equals("Nissan"))
                mid = "3";

            stmt = conn.prepareStatement("insert into cars VALUES(?,?,?,?)");
            stmt.setString(1, vinNumber);
            stmt.setString(2, mid);
            stmt.setString(3, year);
            stmt.setString(4, currentMileage);

            System.out.println(vinNumber+mid+year+currentMileage);

            stmt.executeUpdate();

            stmt = conn.prepareStatement("insert into userCustomer VALUES(?,?,?)");
            stmt.setString(1, username);
            stmt.setString(2, cid);
            stmt.setString(3, scid);

            stmt.executeUpdate();

            dbHelper.closeConnection(conn);

        } catch (Exception e) {
            System.out.println("ERROR:" + e);
            e.printStackTrace();
        }

        System.out.println("Saved to Database");
    }

}

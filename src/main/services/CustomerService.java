package main.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

import main.helpers.DatabaseHelper;

public class CustomerService {

    public static void showProfile(String username) {

        DatabaseHelper dbHelper = new DatabaseHelper();
        Connection conn = dbHelper.getConnection();
        ResultSet rs = null;

        try{

            PreparedStatement stmt = conn.prepareStatement("select * from userCustomer where username = (?) ");
            stmt.setString(1, username);
            rs = stmt.executeQuery();

            rs.next();
            String cId = rs.getString("cId");
            String scId = rs.getString("scId");

            stmt = conn.prepareStatement("select * from customer where cId = (?) and scId = (?)");
            stmt.setString(1, cId);
            stmt.setString(2,scId);
            rs = stmt.executeQuery();

            rs.next();

            System.out.println("Customer Id: "+rs.getString("cId")+"\nFull Name: "+rs.getString("firstName")+" "+rs.getString("lastName")+"\nAddress: "+rs.getString("address") +"\nEmail Address: "+ rs.getString("email") +"\nPhone Number: "+rs.getString("phoneNo")+"\nCar details: \n");

            getAvilableCars(username);

        }
        catch(Exception e){
            System.out.println("ERROR:" + e);
            e.printStackTrace();
        }finally{
            dbHelper.closeConnection(conn);
        }
        
        dbHelper.closeConnection(conn);
    }

    public static HashSet<String> getAvilableCars(String username) {
        HashSet<String> vinNoList = new HashSet<String>();

        DatabaseHelper dbHelper = new DatabaseHelper();
        Connection conn = dbHelper.getConnection();
        ResultSet rs = null;
        ResultSet rs_sub = null;

        try{

            PreparedStatement stmt = conn.prepareStatement("select * from userCustomer where username = (?) ");
            stmt.setString(1, username);
            rs = stmt.executeQuery();

            rs.next();
            String cId = rs.getString("cId");
            String scId = rs.getString("scId");

            stmt = conn.prepareStatement("select * from carOwner where cId = (?) and scId = (?)");
            stmt.setString(1, cId);
            stmt.setString(2,scId);
            rs = stmt.executeQuery();

            while(rs.next()){
                String vinNo = rs.getString("vinNo");
                vinNoList.add(vinNo);

                

                stmt = conn.prepareStatement("select * from cars where vinNo = (?)");
                stmt.setString(1, vinNo);
                rs_sub = stmt.executeQuery();
                
                rs_sub.next();

                stmt = conn.prepareStatement("select * from model where mId = (?)");
                stmt.setString(1, rs_sub.getString("mId"));
                ResultSet rs_make = stmt.executeQuery();

                rs_make.next();

                System.out.println("VIn number: "+ rs_sub.getString("vinNo")+"\nCar Manufacturer: "+rs_make.getString("make")+"\nCurrent Mileage: "+ rs_sub.getString("currMileage")+"\nYear: "+rs_sub.getString("year"));
                System.out.println();

            }

        }
        catch(Exception e){
            System.out.println("ERROR:" + e);
            e.printStackTrace();
        }finally{
            dbHelper.closeConnection(conn);
        }
        
        dbHelper.closeConnection(conn);
        return vinNoList;
    }

    public static boolean saveCarInfo(String username, String vinNo, String manufacturerName, String currMileage,
            String year) {
            
            DatabaseHelper dbHelper = new DatabaseHelper();
            Connection conn = dbHelper.getConnection();
            ResultSet rs = null;
    
            try{
    
                PreparedStatement stmt = conn.prepareStatement("select * from userCustomer where username = (?) ");
                stmt.setString(1, username);
                rs = stmt.executeQuery();
    
                rs.next();
                String cId = rs.getString("cId");
                String scId = rs.getString("scId");

                stmt = conn.prepareStatement("select * from model where make = (?) ");
                stmt.setString(1, manufacturerName);
                rs = stmt.executeQuery();
    
                rs.next();
                String mId = rs.getString("mId");
                
                stmt = conn.prepareStatement("select count(*) from cars where vinNo = (?)");
                stmt.setString(1, vinNo);
                rs = stmt.executeQuery();

                rs.next();
                String exist = rs.getString(1);

                if(exist.equals("0")){
                    stmt = conn.prepareStatement("insert into cars values (?,?,?,?)");
                    stmt.setString(1, vinNo);
                    stmt.setString(2, mId);
                    stmt.setString(3, year);
                    stmt.setString(4, currMileage);
                    stmt.executeUpdate();
                }

                stmt = conn.prepareStatement("insert into carOwner values (?,?,?,'A')");
                stmt.setString(1, scId);
                stmt.setString(2, cId);
                stmt.setString(3, vinNo);
                stmt.executeUpdate();

            }
            catch(Exception e){
                System.out.println("ERROR:" + e);
                e.printStackTrace();
                return false;
            }finally{
                dbHelper.closeConnection(conn);
            }
            
            dbHelper.closeConnection(conn);
        
        return true;
    }

    public static boolean deleteCarInfo(String vinNo) {

        DatabaseHelper dbHelper = new DatabaseHelper();
        Connection conn = dbHelper.getConnection();

        try{

            PreparedStatement stmt = conn.prepareStatement("delete from carOwner where vinNo = (?)");
            stmt.setString(1, vinNo);
            stmt.executeUpdate();

        }
        catch(Exception e){
            System.out.println("ERROR:" + e);
            e.printStackTrace();
            return false;
        }finally{
            dbHelper.closeConnection(conn);
        }
        
        dbHelper.closeConnection(conn);
        
        return true;
    }

    public static void getServiceHistory(String username, String vinNo){

        DatabaseHelper dbHelper = new DatabaseHelper();
        Connection conn = dbHelper.getConnection();
        ResultSet rs = null;
        ResultSet rs_sub = null;

        try{
            
            PreparedStatement stmt = conn.prepareStatement("select * from userCustomer where username = (?) ");
            stmt.setString(1, username);
            rs = stmt.executeQuery();

            rs.next();
            String cId = rs.getString("cId");
            String scId = rs.getString("scId");

            stmt = conn.prepareStatement("select invoiceId from customerRequest where scId = (?) and cId = (?) and vinNo = (?)");
            stmt.setString(1, scId);
            stmt.setString(2, cId);
            stmt.setString(3, vinNo);
            rs = stmt.executeQuery();

            while(rs.next()){
                String invoiceId = rs.getString("invoiceId");
                stmt = conn.prepareStatement("select * from serviceRequest where invoiceId = (?)");
                stmt.setString(1, invoiceId);
                rs_sub = stmt.executeQuery();
                
                rs_sub.next();
                int totalAmount = rs_sub.getInt("totalAmount");
                String serviceDate = rs_sub.getString("serviceDate");

                System.out.println("Invoice Id: "+invoiceId+"\nVin No: "+vinNo+"\nService Cost: "+ totalAmount+"\nService Date: "+ serviceDate+"\nMechanics: ");

                stmt = conn.prepareStatement("select e.name from employee e, serveRequest sr where e.eId = sr.eId and e.scId = sr.scId and sr.invoiceId = (?)");
                stmt.setString(1, invoiceId);
                rs_sub = stmt.executeQuery();

                while(rs_sub.next()){
                    System.out.println(rs_sub.getString("name"));
                }
                
            }

        }
        catch(Exception e){
            System.out.println("ERROR:" + e);
            e.printStackTrace();
        }finally{
            dbHelper.closeConnection(conn);
        }
        
        dbHelper.closeConnection(conn);       
    } 

    public static String[] getNextMaintenanceService(String username, String vinNo){
        String[] serviceDetails = new String[2];

        DatabaseHelper dbHelper = new DatabaseHelper();
        Connection conn = dbHelper.getConnection();
        ResultSet rs = null;

        try{

            PreparedStatement stmt = conn.prepareStatement("select * from userCustomer where username = (?) ");
            stmt.setString(1, username);
            rs = stmt.executeQuery();

            rs.next();
            String cId = rs.getString("cId");
            String scId = rs.getString("scId");

            stmt = conn.prepareStatement("select * from carOwner where cId = (?) and scId = (?)");
            stmt.setString(1, cId);
            stmt.setString(2, scId);
            rs = stmt.executeQuery();

            rs.next();
            serviceDetails[0] = rs.getString("nextMaintenance");

            stmt = conn.prepareStatement("select sp.price from serviceProvided sp, services s where sp.sNo = s.sNo and sp.scId = (?) and s.sName = (?) and mId = (select c.mId from cars c where c.vinNo = (?))");
            stmt.setString(1, scId);
            stmt.setString(2, serviceDetails[0]);
            stmt.setString(3, vinNo);
            rs = stmt.executeQuery();

            rs.next();
            serviceDetails[1] = rs.getString("price");

        }
        catch(Exception e){
            System.out.println("ERROR:" + e);
            e.printStackTrace();
        }finally{
            dbHelper.closeConnection(conn);
        }
        
        dbHelper.closeConnection(conn);
        return serviceDetails;
    }

    public static ArrayList<String> getRepairServices( String category){
        
        ArrayList<String> services = new ArrayList<>();

        DatabaseHelper dbHelper = new DatabaseHelper();
        Connection conn = dbHelper.getConnection();
        ResultSet rs = null;
        ResultSet rs_sub = null;

        try{

            PreparedStatement stmt = conn.prepareStatement("select * from repair where repairCat = (?)");
            stmt.setString(1, category);
            rs = stmt.executeQuery();

            while(rs.next()){
                stmt = conn.prepareStatement("select * from services where sNo = (?)");
                stmt.setString(1, rs.getString("sNo"));
                rs_sub = stmt.executeQuery();

                rs_sub.next();
                services.add(rs_sub.getString("sName"));
            }

        }
        catch(Exception e){
            System.out.println("ERROR:" + e);
            e.printStackTrace();
        }finally{
            dbHelper.closeConnection(conn);
        }
        
        dbHelper.closeConnection(conn);
        return services;
    }

    public static HashSet<String> getAvailableTimeSlots(String username, HashSet<String> Cart, String vinNo, String currMileage){
        
        DatabaseHelper dbHelper = new DatabaseHelper();
        Connection conn = dbHelper.getConnection();
        ResultSet rs = null;
        ResultSet rs_sub = null;

        HashSet<String> timeSlots = new HashSet<>();

        try{

            PreparedStatement stmt = conn.prepareStatement("select * from userCustomer where username = (?) ");
            stmt.setString(1, username);
            rs = stmt.executeQuery();

            rs.next();
            String cId = rs.getString("cId");
            String scId = rs.getString("scId");

            int totalDuration = 0;

            for(String service:Cart){
                stmt = conn.prepareStatement("select duration from services where sName = (?)");
                stmt.setString(1, service);
                rs_sub = stmt.executeQuery();
                rs_sub.next();

                totalDuration+= rs_sub.getInt("duration");
            }
            
            for(int week=1; week<=4; week+=1){
                
                stmt = conn.prepareStatement("select m.eId, mh.hrsWorked from mechanic m, mechanicWorkingHours mh where m.eId = mh.eId and m.scId = mh.scId and m.scId = (?) and mh.weekId = (?)");
                stmt.setString(1, scId);
                stmt.setInt(2, week);

                for(int day=1;day<=7;day+=1){
                    if(week==4 && day==3){
                        break;
                    }

                    for(int slots=1;slots+totalDuration-1<=11;slots+=1){
                        int start = slots;
                        int end = slots+totalDuration-1;
                        if(end>11){
                            break;
                        }
                        int flag = 0;
                        
                        rs = stmt.executeQuery();

                        while(rs.next()){
                            String mechanicEId = rs.getString("eId");
                            if(rs.getInt("hrsWorked")+totalDuration<=50){
                                for(int i=start;i<=end;i+=1){
                                    PreparedStatement stmt2 = conn.prepareStatement("select available from mechanicAvailability where eId = (?) and scId = (?) and weekId = (?) and dayId = (?) and slotId = (?)");
                                    stmt2.setString(1, mechanicEId);
                                    stmt2.setString(2, scId);
                                    stmt2.setInt(3, week);
                                    stmt2.setInt(4, day);
                                    stmt2.setInt(5, i);
                                    rs_sub = stmt2.executeQuery();
                                    rs_sub.next();

                                    if(rs_sub.getString("available").equals("N") || rs_sub.getString("available").equals("O")){
                                        flag=1;
                                        break;
                                    }
                                }
                            }

                            if(flag==0){
                                timeSlots.add(week+" "+day+" "+start+" "+end+","+mechanicEId);
                                break;
                            }
                        }

                    }
                }
            }
        }
        catch(Exception e){
            System.out.println("ERROR:" + e);
            e.printStackTrace();
        }finally{
            dbHelper.closeConnection(conn);
        }
        
        dbHelper.closeConnection(conn);
        
        return timeSlots;
    }

    public static void scheduleServices(String username, HashSet<String> Cart, String vinNo, String currMileage, String timeSlot){

        String[] slotInfo = timeSlot.split(",");
        String eId = slotInfo[1];
        String[] schedule = slotInfo[0].split(" ");

        int weekId = Integer.parseInt(schedule[0]);
        int dayId = Integer.parseInt(schedule[1]);
        int startSlot = Integer.parseInt(schedule[2]);
        int endSlot = Integer.parseInt(schedule[3]);
        int date = (weekId-1)*7 + dayId;

        DatabaseHelper dbHelper = new DatabaseHelper();
        Connection conn = dbHelper.getConnection();
        ResultSet rs = null;

        try{

            PreparedStatement stmt = conn.prepareStatement("select * from userCustomer where username = (?) ");
            stmt.setString(1, username);
            rs = stmt.executeQuery();

            rs.next();
            String cId = rs.getString("cId");
            String scId = rs.getString("scId");

            stmt = conn.prepareStatement("select max(invoiceId) as invoiceId from serviceRequest");
            rs = stmt.executeQuery();

            rs.next();
            String invoiceId = String.valueOf(Integer.parseInt(rs.getString("invoiceId"))+1);
            
            stmt = conn.prepareStatement("select mId from cars where vinNo = (?)");
            stmt.setString(1,vinNo);
            rs = stmt.executeQuery();

            rs.next();
            int mId = rs.getInt("mId");

            int totalAmount = 0;
            int totalDuration = 0;

            for(String service:Cart){
                stmt = conn.prepareStatement("select sp.price, s.sNo from serviceProvided sp, services s where sp.sNo= s.sNo and sp.scId = (?) and sp.mId = (?) and s.sName = (?)");
                stmt.setString(1, scId);
                stmt.setInt(2, mId);
                stmt.setString(3, service);
                rs= stmt.executeQuery();
                rs.next();

                totalAmount+= Integer.parseInt(rs.getString("price"));

                stmt = conn.prepareStatement("select duration from services where sName = (?)");
                stmt.setString(1, service);
                rs = stmt.executeQuery();
                rs.next();

                totalDuration+= rs.getInt("duration");
            }

            stmt = conn.prepareStatement("insert into serviceRequest values (?,?,'U',?)");
            stmt.setString(1, invoiceId);
            stmt.setInt(2, totalAmount);
            stmt.setString(3, String.valueOf(date));
            stmt.executeUpdate();

            stmt = conn.prepareStatement("insert into customerRequest values (?,?,?,?)");
            stmt.setString(1, scId);
            stmt.setString(2, cId);
            stmt.setString(3, vinNo);
            stmt.setString(4, invoiceId);
            stmt.executeUpdate();


            for(String service:Cart){
                stmt = conn.prepareStatement("select sNo from services where sName = (?)");
                stmt.setString(1, service);
                rs= stmt.executeQuery();
                rs.next();

                String sNo = rs.getString("sNo");
                stmt = conn.prepareStatement("insert into requestedServices values (?,?,?,?)");
                stmt.setString(1, invoiceId);
                stmt.setString(2, scId);
                stmt.setString(3, sNo);
                stmt.setInt(4, mId);
                stmt.executeUpdate();
            }

            for(int slot=startSlot; slot<=endSlot; slot+=1){
                stmt = conn.prepareStatement("update mechanicAvailability set available='N' where eId = (?) and scId = (?) and weekId = (?) and dayId = (?) and slotId =(?)");
                stmt.setString(1, eId);
                stmt.setString(2, scId);
                stmt.setInt(3, weekId);
                stmt.setInt(4, dayId);
                stmt.setInt(5, slot);
                stmt.executeUpdate();
            }

            stmt = conn.prepareStatement("select startTime from slots where slotId = (?)");
            stmt.setInt(1, startSlot);
            rs= stmt.executeQuery();
            rs.next();
            int startTime = rs.getInt("startTime");
            
            stmt = conn.prepareStatement("select endTime from slots where slotId = (?)");
            stmt.setInt(1, endSlot);
            rs= stmt.executeQuery();
            rs.next();
            int endTime = rs.getInt("endTime");

            stmt = conn.prepareStatement("insert into serveRequest values (?,?,?,?,?)");
            stmt.setString(1, eId);
            stmt.setString(2, scId);
            stmt.setString(3, invoiceId);
            stmt.setInt(4,startTime);
            stmt.setInt(5,endTime);
            stmt.executeUpdate();
            
        }
        catch(Exception e){
            System.out.println("ERROR:" + e);
            e.printStackTrace();
        }finally{
            dbHelper.closeConnection(conn);
        }
        
        dbHelper.closeConnection(conn);

    }

    public static HashMap<String,String> getInvoices(String username){
        
        DatabaseHelper dbHelper = new DatabaseHelper();
        Connection conn = dbHelper.getConnection();
        ResultSet rs = null;
        ResultSet rs_sub = null;
        HashMap<String,String> invoices = new HashMap<>();

        try{

            PreparedStatement stmt = conn.prepareStatement("select * from userCustomer where username = (?) ");
            stmt.setString(1, username);
            rs = stmt.executeQuery();

            rs.next();
            String cId = rs.getString("cId");
            String scId = rs.getString("scId");

            stmt = conn.prepareStatement("select * from customerRequest where cId = (?) and scId = (?)");
            stmt.setString(1, cId);
            stmt.setString(2, scId);
            rs = stmt.executeQuery();

            while(rs.next()){
                String invoiceId = rs.getString("invoiceId");
                stmt = conn.prepareStatement("select * from serviceRequest where invoiceId = (?)");
                stmt.setString(1, invoiceId);
                rs_sub = stmt.executeQuery();

                rs_sub.next();
                invoices.put(invoiceId, rs_sub.getString("status"));
            }
            
        }
        catch(Exception e){
            System.out.println("ERROR:" + e);
            e.printStackTrace();
        }finally{
            dbHelper.closeConnection(conn);
        }
        
        dbHelper.closeConnection(conn);
        return invoices;
    }

    public static void viewInvoice(String username, String invoiceId){
        
        DatabaseHelper dbHelper = new DatabaseHelper();
        Connection conn = dbHelper.getConnection();
        ResultSet rs = null;
        ResultSet rs_sub = null;

        try{

            PreparedStatement stmt = conn.prepareStatement("select * from userCustomer where username = (?) ");
            stmt.setString(1, username);
            rs = stmt.executeQuery();

            rs.next();
            String cId = rs.getString("cId");
            String scId = rs.getString("scId");

            stmt = conn.prepareStatement("select * from serviceRequest where invoiceId = (?)");
            stmt.setString(1, invoiceId);
            rs = stmt.executeQuery();

            rs.next();
            System.out.println("Invoice Id: "+ invoiceId +"\nCustomer Id: "+ cId + "\nInvoice Status: "+ rs.getString("status") + "\nTotal cost: "+ rs.getString("totalAmount") + "\nService Date: " + rs.getString("serviceDate")+"\nServices:");

            stmt = conn.prepareStatement("select scId,sNo,mId  from requestedServices where invoiceId = (?)");
            stmt.setString(1, invoiceId);
            rs = stmt.executeQuery();
            
            while(rs.next()){
                stmt = conn.prepareStatement("select s.sName,sp.price from serviceProvided sp, services s where sp.sNo=s.sNo and sp.scId = (?) and sp.sNo = (?) and sp.mId = (?)");
                stmt.setString(1, rs.getString("scId"));
                stmt.setString(2, rs.getString("sNo"));
                stmt.setString(3, rs.getString("mId"));
                rs_sub = stmt.executeQuery();

                rs_sub.next();
                System.out.println("Service name: "+ rs_sub.getString("sName") +"   Price: "+ rs_sub.getString("price"));
            }

            stmt = conn.prepareStatement("select e.name from employee e, serveRequest sr where e.eId = sr.eId and e.scId = sr.scId and sr.invoiceId = (?)");
            stmt.setString(1, invoiceId);
            rs_sub = stmt.executeQuery();

            System.out.println("Mechanics:");
            while(rs_sub.next()){
                System.out.println(rs_sub.getString("name"));
            }

        }
        catch(Exception e){
            System.out.println("ERROR:" + e);
            e.printStackTrace();
        }finally{
            dbHelper.closeConnection(conn);
        }
        
        dbHelper.closeConnection(conn);
    }

    public static void payInvoice(String username, String invoiceId){
        
        DatabaseHelper dbHelper = new DatabaseHelper();
        Connection conn = dbHelper.getConnection();

        try{

            PreparedStatement stmt = conn.prepareStatement("update serviceRequest set status = 'P' where invoiceId = (?) ");
            stmt.setString(1, invoiceId);
            stmt.executeUpdate();

            System.out.println("Invoice payment successfull.");

        }
        catch(Exception e){
            System.out.println("ERROR:" + e);
            e.printStackTrace();
        }finally{
            dbHelper.closeConnection(conn);
        }
        
        dbHelper.closeConnection(conn);
    }

}

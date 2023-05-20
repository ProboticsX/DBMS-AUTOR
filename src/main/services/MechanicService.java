package main.services;
import main.helpers.DatabaseHelper;
import main.helpers.QueryFunctions;
import main.models.Constants;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import java.util.*;
import java.util.HashSet;

public class MechanicService {
    static DatabaseHelper dbHelper = new DatabaseHelper();
    static Connection conn = dbHelper.getConnection();

    public static void viewSchedule(String username){
        try{
            ResultSet idSet = dbHelper
            .runQuery("select eId, scId from userEmployee where username = '"
                    + username + "'");
            if (idSet.next()) {
                String eId = idSet.getString("eId");
                String scId = idSet.getString("scId");
                ResultSet scheduleSet = dbHelper
                .runQuery("select me.weekId as weekId, me.dayId as dayId, me.slotId as slotId, s.startTime as startTime, s.endTime as endTime from mechanicAvailability me, slots s where me.eId = '"
                        + eId + "' and me.scId = '"+scId+"' and me.available ='N' and me.slotId = s.slotId");
                while(scheduleSet.next()){
                    String weekId = scheduleSet.getString("weekId");
                    String dayId = scheduleSet.getString("dayId");
                    String slotId = scheduleSet.getString("slotId");
                    String startTime = scheduleSet.getString("startTime");
                    String endTime = scheduleSet.getString("endTime");
                    System.out.println(scId+","+weekId+","+dayId+","+slotId+","+startTime+","+endTime);
                }
            }
    
        }
        catch (Exception e) {
            System.out.println("ERROR:" + e);
            e.printStackTrace();
        }
        
    }
    
    public static boolean sendTheRequest(String username, String week, String day, String startSlot, String endSlot){
        try{
            ResultSet idSet = dbHelper
            .runQuery("select eId, scId from userEmployee where username = '"
                    + username + "'");
            if (idSet.next()) {
                String eId = idSet.getString("eId");
                String scId = idSet.getString("scId");
                ResultSet scheduleSet = dbHelper
                .runQuery("select * from mechanicAvailability me where me.eId = '"
                        + eId + "' and me.scId = '"+scId+
                        "' and weekId = '"+week+"' and dayId = '"+day+
                        "' and me.slotId >='"+startSlot+"' and me.slotId <= '"+endSlot+"'");

                // select * from mechanicAvailability me where me.eId = '132457689' and me.scId = '30001' and weekId = '3' and dayid = '1' and me.slotid>='1' and  me.slotId<=3;
                //check if there's any booked slot for mechanic or not
                while(scheduleSet.next()){
                    if(scheduleSet.getString("available").equals("N"))
                        return false;
                }
                
                // select count(*) from mechanicAvailability me where me.eId <> '132457689' and me.scId = '30001' and weekId = '3' and dayId = '1' and me.slotId >='1' and me.slotId <= '3' and me.available = 'Y' group by (me.scId, me.weekId, me.dayId, me.slotid);

                scheduleSet = dbHelper
                .runQuery("select count(*) as count from mechanicAvailability me where me.eId <> '"
                    + eId + "' and me.scId = '"+scId+
                    "' and weekId = '"+week+"' and dayId = '"+day+
                        "' and me.slotId >='"+startSlot+"' and me.slotId <= '"+endSlot+
                        "' and me.available = 'Y' group by (me.scId, me.weekId, me.dayId, me.slotId)");
                //check if there are atleast 3 mechanics during a particular slot if request time off is approved
                while(scheduleSet.next()){
                    Integer queryResult = Integer.parseInt(scheduleSet.getString("count"));
                    if (queryResult == null || queryResult<Constants.minMechanicsThreshold) {
                        return false;
                    }
                }
                //update avaialability of mechanic to Off Day
                scheduleSet = dbHelper
                        .runQuery("update mechanicAvailability me"+
                        " set me.available = '"+Constants.offDay+"' where me.eId = '"
                        + eId + "' and me.scId = '"+scId+
                        "' and weekId = '"+week+"' and dayId = '"+day+
                            "' and me.slotId >='"+startSlot+"' and me.slotId <= '"+endSlot+"'");
            }
    
        }
        catch (Exception e) {
            System.out.println("ERROR:" + e);
            e.printStackTrace();
            return false;
        }
        
        return true;
    }

    public static int validityCheck(String username, String week, String day, String startSlot, String endSlot, String requestedEid, String requestedWeek, String requestedDay, String requestedStartSlot, String requestedEndSlot){
        try{
            ResultSet idSet = dbHelper
            .runQuery("select eId, scId from userEmployee where username = '"
                    + username + "'");
            if (idSet.next()) {
                String eId = idSet.getString("eId");
                String scId = idSet.getString("scId");
                ResultSet scheduleSetOfUser = dbHelper
                                        .runQuery(" select me.available as available from mechanicAvailability me"+
                                        " where me.eId = '"+ eId + "' and me.scId = '"+scId+
                                        "' and weekId = '"+week+"' and dayId = '"+day+
                                            "' and me.slotId >='"+startSlot+"' and me.slotId <= '"+endSlot+"'");
                
                //check if user request range is not 'O': Off day
                while(scheduleSetOfUser.next()){
                    if(scheduleSetOfUser.getString("available").equals(Constants.offDay))
                        return Constants.reject;
                }

                ResultSet scheduleSetOfRequest = dbHelper
                                        .runQuery(" select me.available as available from mechanicAvailability me"+
                                        " where me.eId = '"+ requestedEid + "' and me.scId = '"+scId+
                                        "' and weekId = '"+requestedWeek+"' and dayId = '"+requestedDay+
                                            "' and me.slotId >='"+requestedStartSlot+"' and me.slotId <= '"+requestedEndSlot+"'");
                
                //check if requested user range is not 'O': Off day
                while(scheduleSetOfRequest.next()){
                    if(scheduleSetOfRequest.getString("available").equals(Constants.offDay))
                        return Constants.reject;
                }

                ResultSet tempScheduleSet = dbHelper
                                        .runQuery(" select me.available as available from mechanicAvailability me"+
                                        " where me.eId = '"+ eId + "' and me.scId = '"+scId+
                                        "' and me.weekId = '"+requestedWeek+"' and me.dayId = '"+requestedDay+
                                            "' and me.slotId >='"+requestedStartSlot+"' and me.slotId <= '"+requestedEndSlot+"'");
                
                //robert's slots under ellie
                //check if requested user range is 'Y': available
                // while(tempScheduleSet.next()){
                //     if(tempScheduleSet.getString("available").equals(Constants.offDay) || 
                //         tempScheduleSet.getString("available").equals(Constants.busy))
                //         return Constants.reject;
                // }
                //check for double booking
                
                //check if the user is overbooked or not
                // ------YASH WORK--------
            }
    
        }
        catch (Exception e) {
            System.out.println("ERROR:" + e);
            e.printStackTrace();
        }
        return Constants.pending;
    }

    public static void populatePendingRequestTable(String username, String userWeek, String userDay, String userStartSlot, String userEndSlot, String requestedEid, String requestedWeek, String requestedDay, String requestedStartSlot, String requestedEndSlot){
        try{
            ResultSet idSet = dbHelper
            .runQuery("select eId, scId from userEmployee where username = '"
                    + username + "'");
            if (idSet.next()) {
                String usereId = idSet.getString("eId");
                String userscId = idSet.getString("scId");
                String requestId = QueryFunctions.getMaxRequestId(requestedEid, userscId);
                ResultSet employeeSet = dbHelper
                .runQuery("select e.name as requestingMechanicName from employee e where e.eId = '"
                        + usereId + "' and e.scId = '"+userscId+"'");
                while(employeeSet.next()){
                    String requestingMechanicName = employeeSet.getString("requestingMechanicName");
                    PreparedStatement stmt = conn.prepareStatement("insert into pendingRequest (eId, scId, requestId, requestingMechanicName, requestedWeek, requestedDay, requestedStartSlot, requestedEndSlot, usereId, userWeek, userDay, userStartSlot, userEndSlot) values (?,?,?,?,?,?,?,?,?,?,?,?,?)");
                    stmt.setString(1, requestedEid);
                    stmt.setString(2, userscId);
                    stmt.setString(3, requestId);
                    stmt.setString(4, requestingMechanicName);
                    stmt.setString(5, requestedWeek);
                    stmt.setString(6, requestedDay);
                    stmt.setString(7, requestedStartSlot);
                    stmt.setString(8, requestedEndSlot);
                    stmt.setString(9, usereId);
                    stmt.setString(10, userWeek);
                    stmt.setString(11, userDay);
                    stmt.setString(12, userStartSlot);
                    stmt.setString(13, userEndSlot);
                    stmt.executeUpdate();
                }
            }
    
        }
        catch (Exception e) {
            System.out.println("ERROR:" + e);
            e.printStackTrace();
        }
       
    }

    public static void displayPendingRequests(String username){
        try{
            ResultSet idSet = dbHelper
            .runQuery("select eId, scId from userEmployee where username = '"
                    + username + "'");
                    //select eId, scId from userEmployee where username = 'RobertM'
            if (idSet.next()) {
                String usereId = idSet.getString("eId");
                String userscId = idSet.getString("scId");

                ResultSet pendingResultSet = dbHelper
                        .runQuery("select * from pendingRequest pe where pe.eId = '"
                                + usereId + "' and pe.scId = '"+userscId+"'");
                                // select * from pendingRequest pe where pe.eId = '123789456' and pe.scId = '30002';
                while(pendingResultSet.next()){
                    String rId = pendingResultSet.getString("requestId");
                    String rName = pendingResultSet.getString("requestingMechanicName");
                    String wId = pendingResultSet.getString("requestedWeek");
                    String dId = pendingResultSet.getString("requestedDay");
                    String sSlot = pendingResultSet.getString("requestedStartSlot");
                    String eSlot = pendingResultSet.getString("requestedEndSlot");

                    System.out.println(rId+","+rName+","+wId+","+dId+","+sSlot+","+eSlot);
                    
                }
            }
        }
        catch (Exception e) {
            System.out.println("ERROR:" + e);
            e.printStackTrace();
        }
    }

    public static void swapSlots(String username, String requestId){
        //Start-----------
        try{
            ResultSet idSet = dbHelper
            .runQuery("select eId, scId from userEmployee where username = '"
                    + username + "'");
            if (idSet.next()) {
                String usereId = idSet.getString("eId");
                String userscId = idSet.getString("scId");

                ResultSet pendingResultSet = dbHelper
                        .runQuery("select * from pendingRequest pe where pe.eId = '"
                                + usereId + "' and pe.scId = '"+userscId+"' and pe.requestId = '"
                                + requestId+ "'");

                                // select * from pendingRequest pe where pe.eId = '123789456' and pe.scId = '30002';
                if(pendingResultSet.next()){

                    String requestingMechanicName = pendingResultSet.getString("requestingMechanicName");
                    String requestedWeek = pendingResultSet.getString("requestedWeek");
                    String requestedDay = pendingResultSet.getString("requestedDay");
                    String requestedStartSlot = pendingResultSet.getString("requestedStartSlot");
                    String requestedEndSlot = pendingResultSet.getString("requestedEndSlot");
                    String personWhoMadeTheRequestUsereID = pendingResultSet.getString("usereId");
                    String personWhoMadeTheRequestUserWeek = pendingResultSet.getString("userWeek");
                    String personWhoMadeTheRequestUserDay = pendingResultSet.getString("userDay");
                    String personWhoMadeTheRequestUserStartSlot = pendingResultSet.getString("userStartSlot");
                    String personWhoMadeTheRequestUserEndSlot = pendingResultSet.getString("userEndSlot");

                    //storing ellie's slots
                    ResultSet senderOriginalAvailabilitiesSet = dbHelper.runQuery(
                        "select me2.available as senderAvailable from  mechanicAvailability me2"+ 
                        " where me2.eid = '"+personWhoMadeTheRequestUsereID+
                        "' and me2.scId = '"+userscId+"' and me2.weekId = '"+personWhoMadeTheRequestUserWeek
                        +"' and me2.dayId = '"+personWhoMadeTheRequestUserDay+
                        "' and me2.slotId >='"+personWhoMadeTheRequestUserStartSlot+"' and me2.slotId <= '"+personWhoMadeTheRequestUserEndSlot+"'"
                    );

                    //freeing ellie's slots
                    ResultSet senderTempSet = dbHelper
                        .runQuery("update mechanicAvailability me"+
                        " set me.available = '"+Constants.available
                        +"' where me.eId = '"
                        + personWhoMadeTheRequestUsereID + "' and me.scId = '"+userscId+
                        "' and weekId = '"+personWhoMadeTheRequestUserWeek+"' and dayId = '"+personWhoMadeTheRequestUserDay+
                            "' and me.slotId >='"+personWhoMadeTheRequestUserStartSlot+"' and me.slotId <= '"+personWhoMadeTheRequestUserEndSlot+"'");             

                    //storing robert's slots
                    ResultSet receiverOriginalAvailabilitiesSet = dbHelper.runQuery(
                        "select me2.available as receiverAvailable from  mechanicAvailability me2"+ 
                        " where me2.eid = '"+usereId+
                        "' and me2.scId = '"+userscId+"' and me2.weekId = '"+requestedWeek
                        +"' and me2.dayId = '"+requestedDay+
                        "' and me2.slotId >='"+requestedStartSlot+"' and me2.slotId <= '"+requestedEndSlot+"'"
                    );

                    //freeing robert's slots
                    ResultSet receiverTempSet = dbHelper
                        .runQuery("update mechanicAvailability me"+
                        " set me.available = '"+Constants.available
                        +"' where me.eId = '"
                        + usereId + "' and me.scId = '"+userscId+
                        "' and weekId = '"+requestedWeek+"' and dayId = '"+requestedDay+
                            "' and me.slotId >='"+requestedStartSlot+"' and me.slotId <= '"+requestedEndSlot+"'");

                    //updating robert's slots in ellie
                    while(senderOriginalAvailabilitiesSet.next()){
                        ResultSet updateSenderOriginalAvailabilitiesSet = dbHelper.runQuery
                        ("update mechanicAvailability me"+
                        " set me.available = '"+senderOriginalAvailabilitiesSet.getString("senderAvailable")
                        +"' where me.eId = '"
                        + personWhoMadeTheRequestUsereID + "' and me.scId = '"+userscId+
                        "' and weekId = '"+requestedWeek+"' and dayId = '"+requestedDay+
                            "' and me.slotId >='"+requestedStartSlot+"' and me.slotId <= '"+requestedEndSlot+"'");
                    }

                    //updating ellie's slots in robert
                    while(receiverOriginalAvailabilitiesSet.next()){
                        ResultSet updateSenderOriginalAvailabilitiesSet = dbHelper.runQuery
                        ("update mechanicAvailability me"+
                        " set me.available = '"+receiverOriginalAvailabilitiesSet.getString("receiverAvailable")
                        +"' where me.eId = '"
                        + usereId + "' and me.scId = '"+userscId+
                        "' and weekId = '"+personWhoMadeTheRequestUserWeek+"' and dayId = '"+personWhoMadeTheRequestUserDay+
                            "' and me.slotId >='"+personWhoMadeTheRequestUserStartSlot+"' and me.slotId <= '"+personWhoMadeTheRequestUserEndSlot+"'");
                    }
                }
            }
        }
        catch (Exception e) {
            System.out.println("ERROR:" + e);
            e.printStackTrace();
        }
    }

}



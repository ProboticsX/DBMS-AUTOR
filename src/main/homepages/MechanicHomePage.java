package main.homepages;

import java.util.Scanner;

import main.AUTOR;
import main.models.Constants;
import main.services.MechanicService;

public class MechanicHomePage {
    
    static Scanner scan = new Scanner(System.in);

    public static void loadHomePage(String username){
        System.out.println("\nWelcome "+ username);
        System.out.println("\n1. View Schedule\n2. Request TimeOff\n3. Request Swap\n 4. Accept/Reject Swap\n 5. Logout");

        String input = scan.nextLine();

        switch(input){
            case "1":
                viewSchedule(username);
                break;
            case "2":
                requestTimeOff(username);
                break;
            case "3":
                requestSwap(username);
                break;
            case "4":
                acceptRejectSwap(username);
                break;
            case "5":
                System.out.println("Logged out successfully.");
                AUTOR.homePage();
                break;
        }
        scan.close();        
    }

    private static void viewSchedule(String username){
        System.out.println("The list of time slots (scId, weekId, dayId, slotId, startTime, endTime) when the mechanic is booked for the service is as follows:");
        MechanicService.viewSchedule(username);
        System.out.println("\n1.Go Back");
        String input = scan.nextLine();
        
        switch(input){
            case "1":
                loadHomePage(username);
                break;
        }
    }

    private static void requestTimeOff(String username){
        System.out.println("The time slots mechanic wants to be off (indicated by week, day, time slot start and end slot ids):\n Please enter the following tables:");
        System.out.println("Week:");
        String week = scan.nextLine();
        System.out.println("Day:");
        String day = scan.nextLine();
        System.out.println("Start Time Slot ID:");
        String startSlot = scan.nextLine();
        System.out.println("End Time Slot ID:");
        String endSlot = scan.nextLine();

        System.out.println("\n1.Send the request\n 2.Go Back");
        String input = scan.nextLine();

        switch(input){
            case "1":
                sendTheRequest(username, week, day, startSlot, endSlot);
                System.out.println("Continue? (Y/N)");
                String choice = scan.nextLine();
                if(choice.equals("Y"))
                    requestTimeOff(username);
                else
                    loadHomePage(username);
                break;
            case "2":
                loadHomePage(username);
                break;
        }
    }

    private static void requestSwap(String username){

        System.out.println("Status of Swap Requests are as follows:");


        System.out.println("A. Timeslot range to swap (identified by day id, weekid, begin slot id, end slot id):");
        System.out.println("Week:");
        String week = scan.nextLine();

        System.out.println("Day:");
        String day = scan.nextLine();

        System.out.println("Start Time Slot ID:");
        String startSlot = scan.nextLine();

        System.out.println("End Time Slot ID:");
        String endSlot = scan.nextLine();

        System.out.println("B. Employee ID of a mechanic that is being requested for swap:");
        String requestedEid = scan.nextLine();

        System.out.println("C. Timeslot range of the requested mechanic that is of interest:");
        
        System.out.println("Requested Week:");
        String requestedWeek = scan.nextLine();
        
        System.out.println("Requested Day:");
        String requestedDay = scan.nextLine();

        System.out.println("Requested Start Time Slot ID:");
        String requestedStartSlot = scan.nextLine();

        System.out.println("Requested End Time Slot ID:");
        String requestedEndSlot = scan.nextLine();


        System.out.println("\n1.Send the request\n 2.Go Back");
        String input = scan.nextLine();

        switch(input){
            case "1":
                requestSendSwap(username, week, day, startSlot, endSlot, requestedEid, requestedWeek, requestedDay, requestedStartSlot, requestedEndSlot);
                loadHomePage(username);
                break;
            case "2":
                loadHomePage(username);
                break;
        }
    
    }

    private static void acceptRejectSwap(String username){

        System.out.println("Your Pending Requests (RequestID, Requesting Mechanic Name, Requested Time Slot Range (Week, Day, Start Slot Id, End Slot Id)) to be Accepted/Rejected are as follows:");
        MechanicService.displayPendingRequests(username);
        
        System.out.println("\n1.Manage Swap Request\n 2.Go Back");
        String input = scan.nextLine();


        switch(input){
            case "1":
                manageSwapRequest(username);
                break;
            case "2":
                loadHomePage(username);
                break;
        }
    }

    private static void manageSwapRequest(String username){
        System.out.println("RequestID:");
        String requestId = scan.nextLine();

        System.out.println("\n1.Accept Swapt\n 2.Reject Swap\n 3.Go Back");
        String input = scan.nextLine();

        switch(input){
            case "1":
                MechanicService.swapSlots(username, requestId);
                System.out.println("Swap Completed!");
                acceptRejectSwap(username);
                break;
            case "2":
                acceptRejectSwap(username);
                break;
            case "3":
                acceptRejectSwap(username);
                break;

        }
    }

    private static void requestSendSwap(String username, String week, String day, String startSlot, String endSlot, String requestedEid, String requestedWeek, String requestedDay, String requestedStartSlot, String requestedEndSlot){
        int userRequestStatus = MechanicService.validityCheck(username, week, day, startSlot, endSlot, requestedEid, requestedWeek, requestedDay, requestedStartSlot, requestedEndSlot);
        if(userRequestStatus == Constants.pending){
            System.out.println("Request State: Pending");
            MechanicService.populatePendingRequestTable(username, week, day, startSlot, endSlot, requestedEid, requestedWeek, requestedDay, requestedStartSlot, requestedEndSlot);
        }
        else if(userRequestStatus == Constants.reject){
            System.out.println("Request State: Rejected");
        }
    }

    private static void sendTheRequest(String username, String week, String day, String startSlot, String endSlot){
        System.out.print("Status of the Request: ");
        boolean status  = MechanicService.sendTheRequest(username, week, day, startSlot, endSlot);
        if(status)
            System.out.println("Request Approved!");
        else
            System.out.println("Request Not Approved!");
    }
}

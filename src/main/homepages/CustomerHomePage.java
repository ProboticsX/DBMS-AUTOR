package main.homepages;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;

import main.AUTOR;
import main.services.CustomerService;

public class CustomerHomePage {

    static Scanner scan = new Scanner(System.in);
    private static HashSet<String> Cart = new HashSet<>();
    
    public static void loadHomePage(String username) {

        System.out.println("\nWelcome " + username);
        System.out.println("\n1. View and Update Profile\n2. View and Schedule Service\n3. Invoices\n4. Logout");

        String input = scan.nextLine();

        switch (input) {
            case "1":
                profileMenu(username);
                break;
            case "2":
                viewScheduleServiceMenu(username);
                break;
            case "3":
                invoicesMenu(username);
                break;
            case "4":
                System.out.println("Logged out successfully.");
                AUTOR.homePage();
                break;
            default:
                System.out.println("Improper input. Try again.");
                loadHomePage(username);
                break;
        }

        scan.close();
    }

    private static void profileMenu(String username) {
        System.out.println("\n1. View Profile\n2. Add Car\n3. Delete Car\n4. Go Back");
        String input = scan.nextLine();

        switch (input) {
            case "1":
                CustomerService.showProfile(username);
                viewProfileMenu(username);
                break;
            case "2":
                addCar(username);
                break;
            case "3":
                deleteCar(username);
                break;
            case "4":
                loadHomePage(username);
                break;
            default:
                System.out.println("Improper input. Try again.");
                profileMenu(username);
                break;
        }
    }

    private static void viewProfileMenu(String username) {
        System.out.println("\n1. Go Back");
        String input = scan.nextLine();

        switch (input) {
            case "1":
                profileMenu(username);
                break;
            default:
                System.out.println("Improper input. Try again.");
                viewProfileMenu(username);
                break;
        }
    }

    private static void addCar(String username) {
        System.out.println("VIN number: ");
        String vinNo = scan.nextLine();

        System.out.println("Car Manufacturer name: ");
        String manufacturerName = scan.nextLine();

        System.out.println("Current mileage:");
        String currMileage = scan.nextLine();

        System.out.println("Year:");
        String year = scan.nextLine();

        System.out.println("\n1. Save Information\n2. Cancel");
        String input = scan.nextLine();

        switch (input) {
            case "1":
                boolean status = CustomerService.saveCarInfo(username, vinNo, manufacturerName, currMileage, year);
                if (status) {
                    System.out.println("Vehicle record was saved successfully.");
                }
                loadHomePage(username);
                break;
            case "2":
                loadHomePage(username);
                break;
            default:
                System.out.println("Improper input. Try again.");
                addCar(username);
                break;
        }
    }

    private static void deleteCar(String username) {

        HashSet<String> availableCars = CustomerService.getAvilableCars(username);

        System.out.println("\n1. Select the car to delete\n2. Go Back");
        String input = scan.nextLine();

        switch (input) {
            case "1":
                System.out.println("VIN number: ");
                String vinNo = scan.nextLine();

                if (availableCars.contains(vinNo)) {
                    boolean status = CustomerService.deleteCarInfo(vinNo);
                    if (status) {
                        System.out.println("Vehicle record was deleted successfully.");
                    }
                } else {
                    System.out.println("Invalid VIN No.");
                    deleteCar(username);
                }

                loadHomePage(username);
                break;
            case "2":
                loadHomePage(username);
                break;
            default:
                System.out.println("Improper input. Try again.");
                deleteCar(username);
                break;
        }
    }

    private static void viewScheduleServiceMenu(String username) {
        System.out.println("\n1. View Service History\n2. Schedule Service\n3. Go Back");
        String input = scan.nextLine();

        switch (input) {
            case "1":
                serviceHistroyMenu(username);
                break;
            case "2":
                scheduleServiceMenu(username);
                break;
            case "3":
                loadHomePage(username);
                break;
            default:
                System.out.println("Improper input. Try again.");
                viewScheduleServiceMenu(username);
                break;
        }
    }

    private static void serviceHistroyMenu(String username) {

        System.out.println("VIN number: ");
        String vinNo = scan.nextLine();

        System.out.println("\n1. Show History\n2. Go Back");
        String input = scan.nextLine();

        switch (input) {
            case "1":
                CustomerService.getServiceHistory(username, vinNo);
                loadHomePage(username);
                break;
            case "2":
                viewScheduleServiceMenu(username);
                break;
            default:
                System.out.println("Improper input. Try again.");
                serviceHistroyMenu(username);
                break;
        }
    }

    private static void scheduleServiceMenu(String username) {
        System.out.println("VIN number: ");
        String vinNo = scan.nextLine();

        System.out.println("Current Mileage: ");
        String currMileage = scan.nextLine();

        // check vinNo of same username
        scheduleServicMenuOptions(username, vinNo, currMileage);
    }

    private static void scheduleServicMenuOptions(String username, String vinNo, String currMileage){
        System.out.println(
                "\n1. Add Schedule Maintenance\n2. Add Schedule Repair\n3. View cart and select schedule time\n4. Go Back");
        String input = scan.nextLine();

        switch (input) {
            case "1":
                addMaintenanceService(username, vinNo, currMileage);
                break;
            case "2":
                repairServiceMenu(username, vinNo, currMileage);
                break;
            case "3":
                viewAndSchedule(username, vinNo, currMileage);
                break;
            case "4":
                viewScheduleServiceMenu(username);
                break;
            default:
                System.out.println("Improper input. Try again.");
                scheduleServicMenuOptions(username, vinNo, currMileage);
                break;
        }
    }

    private static void addMaintenanceService(String username, String vinNo, String currMileage) {
        String[] service = CustomerService.getNextMaintenanceService(username, vinNo);
        System.out.println("Next elligible maintenance schedule: "+ service[0] + " Price: "+service[1]);

        System.out.println("\n1. Accept and add to cart\n2. Decline and go back to previous page");
        String input = scan.nextLine();

        switch (input) {
            case "1":
                Cart.add(service[0]);
                scheduleServicMenuOptions(username, vinNo, currMileage);
                break;
            case "2":
                scheduleServicMenuOptions(username, vinNo, currMileage);
                break;
            default:
                System.out.println("Improper input. Try again.");
                addMaintenanceService(username, vinNo, currMileage);
                break;
        }
    }

    private static void repairServiceMenu(String username, String vinNo, String currMileage) {

        System.out.println(
                "\n1. Engine Services\n2. Exhaust Services\n3. Electrical Services\n4. Transmission Services\n5. Tire Services\n6. Heating and AC Services\n7. Go Back");
        String input = scan.nextLine();

        switch (input) {
            case "1":
                chooseRepairService("Engine Services", username, vinNo, currMileage);
                break;
            case "2":
                chooseRepairService("Exhaust Services", username, vinNo, currMileage);
                break;
            case "3":
                chooseRepairService("Electrical Services", username, vinNo, currMileage);
                break;
            case "4":
                chooseRepairService("Transmission Services", username, vinNo, currMileage);
                break;
            case "5":
                chooseRepairService("Tire Services", username, vinNo, currMileage);
                break;
            case "6":
                chooseRepairService("Heating and AC Services", username, vinNo, currMileage);
                break;
            case "7":
                scheduleServicMenuOptions(username, vinNo, currMileage);
                break;
            default:
                System.out.println("Improper input. Try again.");
                repairServiceMenu(username, vinNo, currMileage);
                break;
        }
    }

    private static void chooseRepairService(String category, String username, String vinNo, String currMileage) {
        
        ArrayList<String> services = CustomerService.getRepairServices(category);
        int i = 1;
        System.out.println(services);
        for(String service: services){
            System.out.print(i+". "+service+"\n");
            i+=1;
        }
        System.out.println(i+". Go Back");

        int input = Integer.parseInt(scan.nextLine());
        if(input == i){
            scheduleServicMenuOptions(username, vinNo, currMileage);
            return;
        }

        Cart.add(services.get(input-1));
        scheduleServicMenuOptions(username, vinNo, currMileage);
    }

    private static void viewAndSchedule(String username, String vinNo, String currMileage) {
        
        System.out.println("Services in Cart: ");
        for(String service:Cart){
            System.out.println(service);
        }

        System.out.println("\n1. Proceed with scheduling\n2. Go Back");
        String input = scan.nextLine();

        switch (input) {
            case "1":
                scheduleServicesInCart(username, Cart, vinNo, currMileage);
                break;
            case "2":
                scheduleServicMenuOptions(username, vinNo, currMileage);
                break;
            default:
                System.out.println("Improper input. Try again.");
                viewAndSchedule(username, vinNo, currMileage);
                break;
        }
    }

    private static void scheduleServicesInCart(String username, HashSet<String> services, String vinNo, String currMileage) {
        HashSet<String> timeSlots = CustomerService.getAvailableTimeSlots(username, services, vinNo, currMileage);
        HashMap<Integer, String> slots = new HashMap<>();

        System.out.println("Available Time Slots: ");
        int i= 1;
        for(String slot:timeSlots){
            String[] slotInfo = slot.split(",");
            String[] schedule = slotInfo[0].split(" ");
            int week = Integer.parseInt(schedule[0]);
            int day = Integer.parseInt(schedule[1]);
            int startSlot = Integer.parseInt(schedule[2]);
            int endSlot = Integer.parseInt(schedule[3]);

            System.out.println(i+". Week: "+week+" Day: "+day+" Start Slot: "+startSlot+" End Slot: "+endSlot);
            slots.put(i,slot);
            i+=1;
        }
        
        if(i==1){
            System.out.println("No time slots available currently. Please try again later");
            viewAndSchedule(username, vinNo, currMileage);
            return;
        }

        System.out.println("Select an available time slot: ");
        int input = Integer.parseInt(scan.nextLine());
        if(input<=1 && input>i){
            System.out.println("Improper selection. Try again.");
            scheduleServicesInCart(username, services, vinNo, currMileage);
            return;
        }

        CustomerService.scheduleServices(username, services, vinNo, currMileage, slots.get(input));
        Cart = new HashSet<>();
        loadHomePage(username);
    }

    private static void invoicesMenu(String username) {
        
        HashMap<String, String> invoices = CustomerService.getInvoices(username);

        System.out.println("Invoices List: ");

        for(String invoice : invoices.keySet()){
            System.out.println(invoice +" status: "+ invoices.get(invoice));
        }

        System.out.println("\n1. View Invoice details\n2. Pay Invoice\n3. Go Back");
        String input = scan.nextLine();

        switch (input) {
            case "1":
                viewInvoiceDetails(username, invoices);
                break;
            case "2":
                payInvoice(username, invoices);
                break;
            case "3":
                loadHomePage(username);
                break;
            default:
                System.out.println("Improper input. Try again.");
                invoicesMenu(username);
                break;
        }
    }

    private static void viewInvoiceDetails(String username, HashMap<String,String> invoices) {

        System.out.print("Invoice Id: ");
        String invoiceId = scan.nextLine();

        if(!invoices.containsKey(invoiceId)){
            System.out.println("Improper Invoice Id input.");
            invoicesMenu(username);
        }

        System.out.println("\n1. View Invoice\n2. Go Back");
        String input = scan.nextLine();

        switch (input) {
            case "1":
                CustomerService.viewInvoice(username, invoiceId);
                viewInvoiceDetails(username, invoices);
                break;
            case "2":
                invoicesMenu(username);
                break;
            default:
                System.out.println("Improper input. Try again.");
                viewInvoiceDetails(username, invoices);
                break;
        }
    }

    private static void payInvoice(String username, HashMap<String,String> invoices) {
        System.out.print("Invoice Id: ");
        String invoiceId = scan.nextLine();

        if(!invoices.containsKey(invoiceId)){
            System.out.println("Improper Invoice Id input.");
            invoicesMenu(username);
        }

        if(invoices.get(invoiceId).equals("P")){
            System.out.println("Invoice is alreay paid");
            invoicesMenu(username);
        }
        
        System.out.println("\n1. Pay Invoice\n2. Go Back");
        String input = scan.nextLine();

        switch (input) {
            case "1":
                CustomerService.payInvoice(username,invoiceId);
                payInvoice(username, invoices);
                break;
            case "2":
                invoicesMenu(username);
                break;
            default:
                System.out.println("Improper input. Try again.");
                payInvoice(username, invoices);
                break;
        }

    }

}

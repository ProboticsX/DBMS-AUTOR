package main.homepages;

import java.util.Scanner;

import main.AUTOR;
import main.services.ReceptionistService;

public class ReceptionistHomePage {
    static Scanner scan = new Scanner(System.in);

    public static void loadHomePage(String username){
        
        System.out.println("\nWelcome "+ username);
        System.out.println("\n1. Add new Customer Profile\n2. Find Customers with Pending Invoices\n3.Logout");

        String input = scan.nextLine();

        switch(input){
            case "1":
                addNewCustomerProfilePage(username);
                break;
            case "2":
                findCustomerWithPendingInvoices(username);
                break;
            case "3":
                System.out.println("Logged out successfully.");
                AUTOR.homePage();
                break;
        }
        scan.close();
    }

    private static void findCustomerWithPendingInvoices(String username){

        System.out.println("Customer ID: ");
        String customerId = scan.nextLine();

        System.out.println("Customer Name: ");
        String customerName = scan.nextLine();

        System.out.println("Invoice ID:");
        String invoiceId = scan.nextLine();

        System.out.println("Invoice Date: ");
        String invoiceDate = scan.nextLine();

        System.out.println("Amount: ");
        int amount = scan.nextInt();

        System.out.println("\n1.Go Back");
        String input = scan.nextLine();

        switch(input){
            case "1":
                loadHomePage(username);
                break;
        }

    }

    private static void addNewCustomerProfilePage(String username){
        
        System.out.println("Customer Name: ");
        String customerName = scan.nextLine();

        System.out.println("Address: ");
        String address = scan.nextLine();

        System.out.println("Email Address:");
        String emailAddress = scan.nextLine();

        System.out.println("Phone Number: ");
        String phoneNumber = scan.nextLine();

        System.out.println("Username: ");
        String customerUsername = scan.nextLine();

        System.out.println("VIN Number: ");
        String vinNumber = scan.nextLine();

        System.out.println("Car Manufacturer: ");
        String carManufacturer = scan.nextLine();

        System.out.println("Current Mileage: ");
        String currentMileage = scan.nextLine();
        
        System.out.println("Year: ");
        String year = scan.nextLine();

        ReceptionistService.saveReceptionistDetailstoDB(username, customerName, address, emailAddress, phoneNumber, customerUsername, vinNumber, carManufacturer, currentMileage, year);

        System.out.println("\n1.Go Back");
        String input = scan.nextLine();

        switch(input){
            case "1":
                loadHomePage(username);
                break;
        }

        
    }

    

    
}

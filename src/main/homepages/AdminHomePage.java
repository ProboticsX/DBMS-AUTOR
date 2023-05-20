package main.homepages;

import java.util.HashSet;
import java.util.Scanner;

import main.AUTOR;
import main.services.AdminService;

public class AdminHomePage {
    static Scanner scan = new Scanner(System.in);

    public static void loadHomePage(String username) {

        System.out.println("\nWelcome " + username);
        System.out.println("\n1. System Set Up\n2. Add New Store\n3. Add New Service\n4. Logout");

        String input = scan.nextLine();

        switch (input) {
            case "1":
                systemSetup(username);
                break;
            case "2":
                addNewStore(username);
                break;
            case "3":
                addNewService(username);
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

    private static void systemSetup(String username) {
        System.out.print("Input file that contains the service general information: ");
        String inputServiceFile = scan.nextLine();

        System.out.print("Input file that contains the store general information: ");
        String inputStoreFile = scan.nextLine();

        systemSetupMenu(username, inputServiceFile, inputStoreFile);

    }

    private static void systemSetupMenu(String username, String inputServiceFile, String inputStoreFile) {

        System.out.println("\n1. Upload service general information\n2. Upload store general information\n3. Go Back");
        String input = scan.nextLine();
        boolean status = false;

        switch (input) {
            case "1":
                status = AdminService.uploadService(username, inputServiceFile);
                if (status) {
                    System.out.println("Success");
                } else {
                    System.out.println("Fail");
                }
                systemSetupMenu(username, inputServiceFile, inputStoreFile);
                break;
            case "2":
                status = AdminService.uploadStore(username, inputStoreFile);
                if (status) {
                    System.out.println("Success");
                } else {
                    System.out.println("Fail");
                }
                systemSetupMenu(username, inputServiceFile, inputStoreFile);
                break;
            case "3":
                loadHomePage(username);
                break;
            default:
                System.out.println("Improper input. Try again.");
                systemSetupMenu(username, inputServiceFile, inputStoreFile);
                break;
        }
    }

    private static void addNewStore(String username) {

        System.out.println("Store ID: ");
        String storeId = scan.nextLine();

        System.out.println("Address: ");
        String address = scan.nextLine();

        System.out.println("Managers Info:\nFirst Name:");
        String managerFirstname = scan.nextLine();

        System.out.println("Last Name: ");
        String managerLastname = scan.nextLine();

        System.out.println("Username: ");
        String managerUsername = scan.nextLine();

        System.out.println("Password: ");
        String managerPassword = scan.nextLine();

        System.out.println("Salary: ");
        String managerSalary = scan.nextLine();

        System.out.println("Employee ID: ");
        String managerEmpId = scan.nextLine();

        System.out.println("Min wage for mechanics: ");
        String minWage = scan.nextLine();

        System.out.println("Max wage for mechanics: ");
        String maxWage = scan.nextLine();

        addNewStoreMenu(username, storeId, address, managerFirstname, managerLastname, managerUsername, managerPassword,
                managerSalary, managerEmpId, minWage, maxWage);

    }

    private static void addNewStoreMenu(String username, String storeId, String address, String managerFirstname,
            String managerLastname, String managerUsername, String managerPassword, String managerSalary,
            String managerEmpId, String minWage, String maxWage) {
        System.out.println("\n1. Add Store\n2. Go Back");
        String input = scan.nextLine();

        switch (input) {
            case "1":
                boolean status = AdminService.addStore(username, storeId, address, managerFirstname, managerLastname,
                        managerUsername, managerPassword, managerSalary, managerEmpId, minWage, maxWage);
                if (status) {
                    System.out.println("Success");
                } else {
                    System.out.println("Fail");
                }
                addNewStoreMenu(username, storeId, address, managerFirstname, managerLastname, managerUsername,
                        managerPassword, managerSalary, managerEmpId, minWage, maxWage);
                break;
            case "2":
                loadHomePage(username);
                break;
            default:
                System.out.println("Improper input. Try again.");
                addNewStoreMenu(username, storeId, address, managerFirstname, managerLastname, managerUsername,
                        managerPassword, managerSalary, managerEmpId, minWage, maxWage);
                break;
        }
    }

    private static void addNewService(String username) {

        System.out.println("Enter existing service category: ");
        String serviceCategory = scan.nextLine();
        
        HashSet<String> serviceCategories = AdminService.getServiceCategories();

        if(!serviceCategories.contains(serviceCategory)){
            System.out.println("Category does not exist");
            addNewService(username);
        }

        System.out.println("Service Name: ");
        String serviceName = scan.nextLine();

        System.out.println("Duration of a service:");
        String serviceDuration = scan.nextLine();

        addNewServiceMenu(username, serviceCategory, serviceName, serviceDuration);

    }

    private static void addNewServiceMenu(String username, String serviceCategory, String serviceName,
            String serviceDuration) {
        System.out.println("\n1. Add Service\n2. Go Back");

        String input = scan.nextLine();

        switch (input) {
            case "1":
                boolean status = AdminService.addService(username, serviceCategory, serviceName, serviceDuration);
                if (status) {
                    System.out.println("Success");
                } else {
                    System.out.println("Fail");
                }
                addNewServiceMenu(username, serviceCategory, serviceName, serviceDuration);
                break;
            case "2":
                loadHomePage(username);
                break;
            default:
                System.out.println("Improper input. Try again.");
                addNewServiceMenu(username, serviceCategory, serviceName, serviceDuration);
                break;
        }
    }
}

package main.homepages;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import main.AUTOR;
import main.helpers.HelperFunctions;
import main.helpers.QueryFunctions;
import main.models.Role;
import main.models.Service;
import main.services.ManagerService;

public class ManagerHomePage {

    static Scanner scan = new Scanner(System.in);

    public static void loadHomePage(String username) {
        /*
         * Display the menu
         */

        System.out.println("\nWelcome " + username);
        System.out.println("\n1. Setup Store\n2. Add New Employee\n3. Logout");

        String input = scan.nextLine();

        switch (input) {
            case "1":
                // Setup Store
                setupStore(username);
                break;
            case "2":
                // Add New Employee
                addNewEmployee(username);
                break;
            case "3":
                // Logout
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

    private static void setupStore(String username) {
        /*
         * Display the menu
         */

        System.out.println("\n1. Add Employees\n2. Setup Operational Hours\n3. Setup Service prices\n4. Go Back");
        String input = scan.nextLine();

        switch (input) {
            case "1":
                // Add Employees
                addEmployees(username);
                break;
            case "2":
                // Setup Operational Hours
                setupOperationalHours(username);
                break;
            case "3":
                // Setup Service prices
                setupServicePrices(username);
                break;
            case "4":
                // Go Back
                loadHomePage(username);
                break;
            default:
                System.out.println("Improper input. Try again.");
                setupStore(username);
                break;
        }

        scan.close();
    }

    private static void addEmployees(String username) {
        /*
         * Ask user to input the following details in the order shown below, followed by
         * the menu.
         * A. Name
         * B. Address
         * C. Email Address
         * D. Phone Number
         * E. Role
         * F. Start Date
         * G. Compensation ($)
         */

        System.out.println("Name: ");
        String employeeName = scan.nextLine();

        System.out.println("Address: ");
        String employeeAddress = scan.nextLine();

        System.out.println("Email Address: ");
        String employeeEmail = scan.nextLine();

        System.out.println("Phone Number: ");
        String employeePhone = scan.nextLine();

        String employeeRole = null;
        Boolean isRoleCorrect = false;
        while (!isRoleCorrect) {
            System.out.println("Role: ");
            System.out.println("\t 1. Receptionist");
            System.out.println("\t 2. Mechanic");
            String employeeRoleNumber = scan.nextLine();

            switch (employeeRoleNumber) {
                case "1":
                    employeeRole = Role.RECEPTIONIST;
                    isRoleCorrect = true;
                    break;
                case "2":
                    employeeRole = Role.MECHANIC;
                    isRoleCorrect = true;
                    break;
                default:
                    System.out.println("Improper input. Try again.");
                    break;
            }
        }

        System.out.println("Start Date: ");
        String employeeStartDate = scan.nextLine();

        System.out.println("Compensation ($): ");
        String employeeCompensation = scan.nextLine();

        System.out.print("\n1. Add\n2. Go Back");
        String input = scan.nextLine();

        switch (input) {
            case "1":
                /*
                 * create a new employee record for this service center and display the new
                 * employee ID number. Set the default password for this employee.
                 * 
                 * They should be allowed to create accounts for only receptionists and
                 * mechanics. Also, each employee can only be associated with one service center
                 * After creating the account, show a confirmation message and go back to
                 * Manager: Setup Store page
                 */
                Boolean addEmployeesStatus = ManagerService.addNewEmployee(username,
                        employeeName, employeeAddress, employeeEmail, employeePhone, employeeRole,
                        employeeStartDate, employeeCompensation);

                HelperFunctions.printStatus(addEmployeesStatus);
                setupStore(username);
                break;
            case "2":
                /*
                 * If the user chooses 2, go back to the Manager: Setup Store page
                 */
                setupStore(username);
                break;
            default:
                System.out.println("Improper input. Try again.");
                addNewEmployee(username);
                break;
        }

        scan.close();

    }

    private static void setupOperationalHours(String username) {
        /*
         * Ask user to input the following details in the order shown below, followed by
         * the menu.
         * A. Operational on Saturdays?
         */

        String operationalOnSaturdays = null;
        Boolean isOperationalCorrect = false;

        while (!isOperationalCorrect) {
            System.out.println("Operational on Saturdays? (Y/N): ");
            operationalOnSaturdays = scan.nextLine();

            if (operationalOnSaturdays.equals("Y") || operationalOnSaturdays.equals("N")) {
                isOperationalCorrect = true;
            } else {
                System.out.println("Improper input. Try again.");
            }
        }

        System.out.print("\n1. Setup operational hours\n2. Go Back");
        String input = scan.nextLine();

        switch (input) {
            case "1":
                /*
                 * If the user chooses 1, take a single input from the user indicating whether
                 * the store will be open on Saturdays or not. Then setup the operational hours
                 * of the store as described in the description and go back to the Manager:
                 * Setup Store page
                 */
                Boolean setupOperationalHoursStatus = ManagerService.setupOperationalHours(username,
                        operationalOnSaturdays);

                HelperFunctions.printStatus(setupOperationalHoursStatus);

                setupStore(username);
                break;
            case "2":
                /*
                 * If the user chooses 2, go back to the Manager: Setup Store page
                 */
                setupStore(username);
                break;
            default:
                System.out.println("Improper input. Try again.");
                setupOperationalHours(username);
                break;
        }

        scan.close();

    }

    private static void setupServicePrices(String username) {
        /*
         * Display the menu
         */

        System.out.print("\n1. Setup maintenance service prices\n2. Setup repair service prices\n3. Go Back");
        String input = scan.nextLine();

        switch (input) {
            case "1":
                // Setup maintenance service prices
                setupMaintenanceServicePrices(username);
                break;
            case "2":
                // Setup repair service prices
                setupRepairServicePrices(username);
                break;
            case "3":
                // Go Back
                setupStore(username);
                break;
            default:
                System.out.println("Improper input. Try again.");
                setupServicePrices(username);
                break;
        }

        scan.close();

    }

    private static void setupMaintenanceServicePrices(String username) {
        /*
         * Ask user to input the following details in the order shown below, followed by
         * the menu.
         * A. Schedule A price
         * B. Schedule B price
         * C. Schedule C price
         */

        ArrayList<Service> maintenanceServicePrices = getServicePrice(username, "Maintenance");

        System.out.print("\n1. Setup prices\n2. Go Back");
        String input = scan.nextLine();

        switch (input) {
            case "1":
                /*
                 * If the user chooses 1, record all the information of the schedule pricesin
                 * the database. Then, go back to Manager: Setup Service Prices page
                 */
                Boolean setupMaintenanceServicePricesStatus = ManagerService.setupMaintenanceServicePrices(username,
                        maintenanceServicePrices);

                HelperFunctions.printStatus(setupMaintenanceServicePricesStatus);

                setupServicePrices(username);
                break;
            case "2":
                /*
                 * If the user chooses 2, go to the Manager: Setup Service Prices page
                 */
                setupServicePrices(username);
                break;
            default:
                System.out.println("Improper input. Try again.");
                setupMaintenanceServicePrices(username);
                break;
        }

        scan.close();

    }

    private static void setupRepairServicePrices(String username) {
        /*
         * Load all the individual repair services available (a sample list based on the
         * services mentioned in the description is given below) and ask the user to
         * input the prices for each, followed by the menu.
         * A. Belt Replacement
         * B. Engine Repair
         * C. Catalytic Converter Repair
         * D. Muffler Repair
         * E. Alternator Repair
         * F. Power Lock Repair
         * G. Axle Repair
         * H. Transmission Flush
         * I. Tire Balancing
         * J. Wheel Alignment
         * K. Compressor Repair
         */

        ArrayList<Service> repairServicePrices = getServicePrice(username, "Repair");

        System.out.print("\n1. Setup prices\n2. Go Back");
        String input = scan.nextLine();

        switch (input) {
            case "1":
                /*
                 * record all the information of car in the database. Then, go back to Manager:
                 * Setup Service Prices page
                 */
                Boolean setupRepairServicePricesStatus = ManagerService.setupRepairServicePrices(username,
                        repairServicePrices);

                HelperFunctions.printStatus(setupRepairServicePricesStatus);

                setupServicePrices(username);
                break;
            case "2":
                /*
                 * If the user chooses 2, go to the Manager: Setup Service Prices page
                 */
                setupServicePrices(username);
                break;
            default:
                System.out.println("Improper input. Try again.");
                setupRepairServicePrices(username);
                break;
        }

        scan.close();

    }

    private static void addNewEmployee(String username) {
        /*
         * Ask user to input the following details in the order shown below, followed by
         * the menu.
         * A. Name
         * B. Address
         * C. Email Address
         * D. Phone Number
         * E. Role
         * F. Start Date
         * G. Compensation ($)
         */

        System.out.println("Name: ");
        String employeeName = scan.nextLine();

        System.out.println("Address: ");
        String employeeAddress = scan.nextLine();

        System.out.println("Email Address: ");
        String employeeEmail = scan.nextLine();

        System.out.println("Phone Number: ");
        String employeePhone = scan.nextLine();

        String employeeRole = null;
        Boolean isRoleCorrect = false;
        while (!isRoleCorrect) {
            System.out.println("Role: ");
            System.out.println("\t 1. Receptionist");
            System.out.println("\t 2. Mechanic");
            String employeeRoleNumber = scan.nextLine();

            switch (employeeRoleNumber) {
                case "1":
                    employeeRole = Role.RECEPTIONIST;
                    isRoleCorrect = true;
                    break;
                case "2":
                    employeeRole = Role.MECHANIC;
                    isRoleCorrect = true;
                    break;
                default:
                    System.out.println("Improper input. Try again.");
                    break;
            }
        }

        System.out.println("Start Date: ");
        String employeeStartDate = scan.nextLine();

        System.out.println("Compensation ($): ");
        String employeeCompensation = scan.nextLine();

        System.out.print("\n1. Add\n2. Go Back");
        String input = scan.nextLine();

        switch (input) {
            case "1":
                /*
                 * create a new employee record for this service center and display the new
                 * employee ID number. Set the default password for this employee.
                 * 
                 * They should be allowed to create accounts for only receptionists and
                 * mechanics. Also, each employee can only be associated with one service
                 * center.
                 * 
                 * After creating the account, show a confirmation message and go back to
                 * Manager: Setup Store page
                 */
                Boolean addNewEmployeeStatus = ManagerService.addNewEmployee(username,
                        employeeName, employeeAddress, employeeEmail, employeePhone, employeeRole,
                        employeeStartDate, employeeCompensation);

                HelperFunctions.printStatus(addNewEmployeeStatus);
                setupStore(username);
                break;
            case "2":
                /*
                 * If the user chooses 2, go back to the Manager: Setup Store page
                 */
                setupStore(username);
                break;
            default:
                System.out.println("Improper input. Try again.");
                addNewEmployee(username);
                break;
        }
        scan.close();
    }

    private static ArrayList<Service> getServicePrice(String username, String ServiceType) {

        HashMap<String, String> services = new HashMap<>();
        String printService = null;

        if (ServiceType.equals("Repair")) {
            services = QueryFunctions.getServices("Repair");
            printService = "";
        } else if (ServiceType.equals("Maintenance")) {
            services = QueryFunctions.getServices("Maintenance");
            printService = "Schedule";
        } else {
            System.out.println("Invalid Service Type");
            return null;
        }

        String managerId = QueryFunctions.getManagerIdFromUsername(username);
        String serviceCenterId = QueryFunctions.getServiceCenterIdFromManagerId(managerId);
        HashMap<String, String> cars = QueryFunctions.getCarsDetail();

        ArrayList<Service> servicePrices = new ArrayList<Service>();

        for (String car : cars.keySet()) {

            String servicePrice = null;
            String make = cars.get(car);

            System.out.println("\nPlease enter the Repair Service Prices for the make: " + make);
            System.out.println("Do this service center provides servie for this make? (Y/N)");
            
            String isThisMakeServiced = scan.nextLine();

            while(!isThisMakeServiced.equals("Y") && !isThisMakeServiced.equals("N")){
                System.out.println("Improper Input, Please Try Again");
                isThisMakeServiced = scan.nextLine();
            }

            for (String service : services.keySet()) {

                String serviceName = services.get(service);

                if (isThisMakeServiced.equals("N")) {
                    servicePrice = null;
                } else if (isThisMakeServiced.equals("Y")) {
                    System.out.println("\nRepair Service Price for the make " + cars.get(car)
                            + " and repair service: " + printService + serviceName + ": ");
                    servicePrice = scan.nextLine();
                }

                Service serviceObject = new Service();
                serviceObject.setScId(serviceCenterId);
                serviceObject.setmId(car);
                serviceObject.setMake(make);
                serviceObject.setsNo(service);
                serviceObject.setsName(serviceName);
                serviceObject.setPrice(servicePrice == null ? "NULL" : servicePrice);

                servicePrices.add(serviceObject);
            }
        }
        return servicePrices;
    }
}
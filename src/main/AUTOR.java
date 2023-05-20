package main;

import java.sql.ResultSet;
import java.util.Scanner;

import main.helpers.DatabaseHelper;
import main.homepages.AdminHomePage;
import main.homepages.CustomerHomePage;
import main.homepages.ManagerHomePage;
import main.homepages.MechanicHomePage;
import main.homepages.ReceptionistHomePage;

public class AUTOR {
    static Scanner scan = new Scanner(System.in);

    public static void main(String[] args) {

        System.out.println("\n__Welcome to AUTOR__");
        homePage();

    }

    public static void homePage() {
        /*
         * Display the menu
         */

        System.out.println("\n1. Login\n2. Exit");

        String input = scan.nextLine();

        switch (input) {
            case "1":
                loginPage();
                break;
            case "2":
                System.exit(0);
                break;
            default:
                System.out.println("Improper input. Try again.");
                homePage();
                break;
        }
    }

    private static void loginPage() {
        /*
         * Ask user to input the following details in the order shown below, followed by
         * the menu
         * A. User ID
         * B. Password
         */
        System.out.println("\n__Login Page__\n");
        System.out.print("User ID:");
        String username = scan.nextLine();
        System.out.print("Password:");
        String password = scan.nextLine();

        System.out.println("\n1. Sign-In\n2. Go Back");

        String input = scan.nextLine();

        switch (input) {
            case "1":
                /*
                 * validate credentials and recognize if user is an Admin, Manager,
                 * Receptionist, Mechanic or Customer and go to the correct Landing page. Print
                 * “Login Incorrect” for invalid credentials and ask to enter again.
                 */

                String user = checkUserCredentials(username, password);

                if (user.equals("Invalid")) {
                    System.out.println("Login Incorrect");
                    loginPage();
                }

                switch (user) {
                    case "Admin":
                        AdminHomePage.loadHomePage(username);
                        break;
                    case "Manager":
                        ManagerHomePage.loadHomePage(username);
                        break;
                    case "Customer":
                        CustomerHomePage.loadHomePage(username);
                        break;
                    case "Receptionist":
                        ReceptionistHomePage.loadHomePage(username);
                        break;
                    case "Mechanic":
                        MechanicHomePage.loadHomePage(username);
                        break;
                }

                break;

            case "2":
                /*
                 * discard the input and go back to the home page
                 */
                homePage();
                break;

            default:
                System.out.println("Improper input. Try again.");
                loginPage();
                break;
        }
    }

    private static String checkUserCredentials(String username, String password) {
        /*
         * check if the user credentials in db are valid and return the user type
         */
        DatabaseHelper dbHelper = new DatabaseHelper();
        ResultSet rs = dbHelper
                .runQuery("Select * from users where username = '" + username + "' and password = '" + password + "'");
        try {
            if (rs.next()) {
                return rs.getString("ROLE");
            }
        } catch (Exception e) {
            System.out.println("ERROR:" + e);
            e.printStackTrace();
        }

        return "Invalid";
    }

}
import java.io.*;
import java.util.*;


public class Admin extends User {
    public static Admin loginAdmin;
    public product_food productsfood = new product_food();
    public product_water productswater = new product_water();
    Employee employeeKeep;

    Admin() {
    }

    //private String Dataempolyee = "E:\\mini_project\\employeeData.csv";
    private String Dataadmin = "E:\\mini_project\\adminData.csv";

    public Admin(String name, String username, String password, long id, long phone) {
        this.setName(username);
        this.setUsername(username);
        this.setPassword(password);
        this.setId(id);
        this.setPhone(phone);
    }

    public void adminLogin() {
        try {
            File file = new File(Dataadmin);
            Login(file);
            if (logged == 1) {
                loginAdmin = new Admin(Details[2], Details[4], Details[7], Long.parseLong(Details[1]),
                        Long.parseLong(Details[3]));
                productsfood.readfilemenu();
                productswater.readfilemenu();
            } else {
                while (logged != 1) {
                    System.out.println(" Wrong!! \n -- The username or password is incorrect.--");
                    System.out.println("1. Try Again");
                    System.out.println("0. Exit");
                    int ch = keyboard.nextInt();

                    switch (ch) {
                        case 1:
                            adminLogin();
                            break;
                        case 2:
                            employeeSignUp();
                            break;
                        case 0:
                            // exit
                            break;
                        default:
                            System.out.println("Please enter a valid option");
                    }
                }
            }
            // sc.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void employeeSignUp() {
        try {
            Scanner inputsignup = new Scanner(System.in);

            System.out.println("-- Sign Up from employee --");
            System.out.println("Username ID:");
            String user = inputsignup.next();
            // System.out.println("Password: ");
            // String pass = inputsignup.next();

            int signupfound = 0;
            int customerNo = 0;
            Scanner sc = new Scanner(dataEmployee);

            while (sc.hasNextLine()) {
                Details = sc.nextLine().split(",");

                if (user.equalsIgnoreCase(Details[4])) {
                    System.out.println("-- This username is already used!! --");
                    signupfound = 1;
                    break;
                }
                customerNo++;
            }
            if (signupfound == 1) {
                System.out.println("1. Create new account with different Email ID");
                System.out.println("0. EXIT");
                int ch = keyboard.nextInt();
                switch (ch) {
                    case 1:
                        employeeSignUp();
                        break;
                    case 0:
                        // exit
                        break;
                    default:
                        System.out.println("Please enter a valid option");
                }
            } else {
                long phone = 0;
                String password = "";

                System.out.println("Name:");
                String name = keyboard.nextLine();

                boolean cheak = false;
                while (!cheak) {
                    System.out.println("Phone Number: ");
                    phone = keyboard.nextLong();
                    if (phone < 1000000000) {
                        System.out.println("Invalid phone number");
                    } else {
                        cheak = true;
                    }
                }
                // cheak = false;
                System.out.println("Password: ");
                password = keyboard.nextLine();

                FileWriter fileWriter = new FileWriter(dataEmployee, true);
                fileWriter.write(Integer.toString(customerNo) + "," + Integer.toString(customerNo) + "," + name + ","
                        + phone + "," + user + "," + "/" + "," + "/" + "," + password);

            }

        } catch (IllegalStateException e) {
            System.out.println("Exception thrown: " + e);
        } catch (NoSuchElementException e) {
            System.out.println("Exception thrown: " + e);
        } catch (Exception e) {
            System.out.println("Exception thrown: " + e);
        }
    }
    public void menuAdmin(){
        int ch;
        do {
            System.out.println("---------------- WELCOME ----------------\n");
            System.out.println("1. Manage products");
            //System.out.println("2. View to customer");
            System.out.println("3. View to employee");
            System.out.println("4. Employee registration");
            System.out.println("0. Logout");
            System.out.print("PLEASE ENTER YOUR CHOICE");
            ch = keyboard.nextInt();
            switch (ch) {
                case 1:
                    System.out.println("--------------- Product type ---------------");
                    System.out.println("1. Products Food");
                    System.out.println("2. Products Water");
                    System.out.println("0. Going Back");
                    System.out.println("---------------------------------------------");
                    System.out.print("Enter your choice: ");
                    int ch1 = keyboard.nextInt();
                    switch (ch1) {
                        case 1:
                                productsfood.productsMenu();
                            break;
                        case 2:
                                productswater.productsMenu();
                            break;
                        case 0:
                            break;
                        default:
                            break;
                    }                   
                    break;
                /*case 2:
                    ViewCustomer();
                    break;*/
                case 3:
                    ViewEmployee();
                    break;
                case 4:
                    employeeSignUp();
                    break;
                case 0:
                    System.out.println("Exiting ... ");
                    System.exit(0);
                    break;
                default:
                    System.out.println("INVALID CHOICE \n");
                    break;
            }
        } while (ch != 0);
    }
    
    /*public void ViewCustomer(){
        try {
            ArrayList<Customer> customerList = new ArrayList<Customer>();
            File file = new File(DataCustomer);
            Scanner scanner = new Scanner(file);

            while (scanner.hasNextLine()) {
                edDetails = scanner.nextLine().split(",");

                if (edDetails[4].equalsIgnoreCase("Username")) {
                    continue;
                } else {
                    Customer customerKeep = new Customer(Details[2], Details[4], Details[7], Long.parseLong(Details[1]),
                    Long.parseLong(Details[3]), Details[5]);
                    customerList.add(customerKeep);
                }
            }
            System.out.println("-------- customer information --------");
            System.out.println("-- ID ---- Username ---- Name ---- Phone ---- Address ----");
            for (Customer addToCustomer : customerList) {
                System.out.println("--"+ addToCustomer.getId()+" ---- "+addToCustomer.getUsername()+" ---- "+addToCustomer.getName()+" ---- "+addToCustomer.getPhone()+" ---- "+addToCustomer.getAddress()+" ----");
            }
            System.out.println("0. Back");
            System.out.print("Enter 0 to back: ");
            int ch = keyboard.nextInt();
            if(ch != 0){
                System.out.println("Invalid Specifier!! : Going Back");
                menuAdmin();
            }else{
                menuAdmin();
            }
            customerList.clear();

        } catch (IOException e) {
            System.out.println(e);
        } catch (Exception e) {
            System.out.println(e);
        }
    }*/

    public void ViewEmployee(){
        try {
            ArrayList<Employee> employeeList = new ArrayList<Employee>();
            File file = new File(dataEmployee);
            Scanner scanner = new Scanner(file);

            while (scanner.hasNextLine()) {
                edDetails = scanner.nextLine().split(",");

                if (edDetails[4].equalsIgnoreCase("Username")) {
                    continue;
                } else {
                    employeeKeep = new Employee(edDetails[2], edDetails[4], edDetails[7],
                            Long.parseLong(edDetails[1]), Long.parseLong(edDetails[3]));
                    employeeList.add(employeeKeep);
                }
            }
            
            System.out.println("-------- customer information --------");
            System.out.println("-- ID ---- Username ---- Name ---- Phone ----");
            for (Employee addToEmployee : employeeList) {
                System.out.println("--"+ addToEmployee.getId()+" ---- "+addToEmployee.getUsername()+" ---- "+addToEmployee.getName()+" ---- "+addToEmployee.getPhone()+" ---- ");
            }
            
            System.out.println("0. Back");
            System.out.print("Enter 0 to back: ");
            int ch = keyboard.nextInt();
            if(ch != 0){
                System.out.println("Invalid Specifier!! : Going Back");
                menuAdmin();
            }else{
                menuAdmin();
            }
            employeeList.clear();

        } catch (IOException e) {
            System.out.println(e);
        } catch (Exception e) {
            System.out.println(e);
        }
    }    

    public void deleteQu(){

    }
}

import java.util.*;
import java.io.*;

public class Employee extends Admin {

    //private String dataemployee = "E:\\mini_project\\employeeData.csv";
    private static Employee loginEmployee;
    //public product_food productsfood = new product_food();
    //public product_water productswater = new product_water();
    Employee() {
    }

    public Employee(String name, String username, String password, long id, long phone) {
        this.setName(name);
        this.setUsername(username);
        this.setPassword(password);
        this.setId(id);
        this.setPhone(phone);
    }

    public void employeeLogin() {
        try {
            File file = new File(dataEmployee);
            Login(file);
            if (logged == 1) {
                loginEmployee = new Employee(Details[2], Details[4], Details[7], Long.parseLong(Details[1]),
                        Long.parseLong(Details[3]));
                productsfood.readfilemenu();
                productswater.readfilemenu();
            } else {
                while (logged != 1) {
                    System.out.println(" \t-- Wrong!! -- \n -- The username or password is incorrect --");
                    System.out.println("1. Try Again");
                    System.out.println("2. Back");
                    System.out.println("0. Exit");
                    int ch = keyboard.nextInt();

                    switch (ch) {
                        case 1:
                            employeeLogin();
                            break;
                        // case 2:
                        // break;
                        case 0:
                            System.out.println("Exiting ... ");
                            System.exit(0);
                            break;
                        default:
                            System.out.println("Please enter a valid option");
                            keyboard.next();
                    }
                }
            }
            
            // sc.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void menuEmployee() {
        int ch;
        do {
            System.out.println("---------------- WELCOME ----------------\n");
            System.out.println("1. Manage products");
            System.out.println("2. Account Settings");
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
                case 2:
                    accSetting();
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

    public void accSetting() {
        int ch;
        do {
            System.out.println("----------- Account Settings -----------");
            System.out.println("1. DisPlay details");
            System.out.println("2. Edit Details");
            System.out.println("0. Back");

            ch = keyboard.nextInt();
            switch (ch) {
                case 1:
                    System.out.println("Name: " + loginEmployee.getName());
                    System.out.println("Username : " + loginEmployee.getUsername());
                    System.out.println("Phone Number: " + loginEmployee.getPhone());
                    accSetting();
                    break;
                case 2:
                    edit();
                    accSetting();
                    break;
                case 0:
                    menuEmployee();
                    break;
                default:
                    System.out.println("INVALID CHOICE \n");
                    break;
            }
        } while (ch != 0);
    }

    public void edit() {
        boolean validity = false;
        long phone = 0;
        String oldPass = "";
        String newPass = "";
        int ch;
        
        do{
            System.out.println(" ----- EDIT Employee -----");
            System.out.println("1. Edit Name");
            System.out.println("2. EDIT Phone Number");
            System.out.println("3. Edit Password ");
            System.out.println("0. Back");
            System.out.println(" ----------------");
            System.out.println("Enter ch:");
            ch = keyboard.nextInt();
            switch (ch) {
                case 1:
                    keyboard.nextLine();
                    System.out.println("Name Old: " + loginEmployee.getName());
                    System.out.println("Enter a new name: ");
                    String name = keyboard.nextLine();
                    loginEmployee.setName(name);
                    System.out.println("edit successfully");
                    break;
                case 2:
                    keyboard.nextLine();
                    System.out.println("Phone Number Old: " + loginEmployee.getPhone());
                    while (!validity) {
                        System.out.println("Enter a new phone number: ");
                        phone = keyboard.nextLong();
                        if (phone <= 100000000 || phone >= 1000000000) {
                            System.out.println("Invalid phone number!(ex:+66:95-234-5678(9 digits)");
                        } else {
                            validity = true;
                        }
                    }
                    loginEmployee.setPhone(phone);
                    System.out.println("edit successfully");
                    break;
                case 3:
                    System.out.println("Enter your old password: ");
                    oldPass = keyboard.next();
                    if (loginEmployee.getPassword().equals(oldPass)) {
                        System.out.println("Enter a new password: ");
                        newPass = keyboard.next();
                        loginEmployee.setPassword(newPass);
                    } else {
                        System.out.println("Wrong password !!!");
                        keyboard.next();
                        edit();
                    }
                    System.out.println("edit successfully");
                    break;
                case 0:
                    // back
                    break;
                default:
                    System.out.println("Invalid choice !!!");
                    edit();
                    break;
            }
            fileWriter();
        }while(ch != 0);
        
    }

    public void fileWriter() {
        File file = new File(dataEmployee);
        try {
            ArrayList<Employee> employeeList = new ArrayList<Employee>();
            Scanner scanner = new Scanner(file);

            while (scanner.hasNextLine()) {
                edDetails = scanner.nextLine().split(",");

                if (edDetails[4].equalsIgnoreCase("Username")) {
                    continue;
                } else if (Integer.parseInt(edDetails[1]) == loginEmployee.getId()) {
                    employeeList.add(loginEmployee);
                } else {
                    Employee employeeKeep = new Employee(edDetails[2], edDetails[4], edDetails[7],
                            Long.parseLong(edDetails[1]), Long.parseLong(edDetails[3]));
                    employeeList.add(employeeKeep);
                }
            }
            FileWriter filewriter = new FileWriter(file);
            filewriter.write("SrNo,ID,Name,Phone No,Username,/,/,Password,\n");
            int x = 1;
            for (Employee addToEmployee : employeeList) {
                filewriter.write(Integer.toString(x) + "," + Long.toString(addToEmployee.getId()) + "," +
                        addToEmployee.getName() + "," + Long.toString(addToEmployee.getPhone()) + ","
                        + addToEmployee.getUsername() +
                        "," + "/" + "," + "/" + "," + addToEmployee.getPassword() + ",\n");
                x++;
            }
            filewriter.close();
            employeeList.clear();

        } catch (IOException e) {
            System.out.println(e);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}

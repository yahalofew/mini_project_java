import java.util.Scanner;

public class Calling_Store {
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        int ch;
        do {
            System.out.println(" ----- Welcome to Supermarket ----- ");
            System.out.println(" ---- Login to continue ----- ");
            System.out.println("1. login customer");
            System.out.println("2. SignUp customer");
            System.out.println("3. Employee Login");
            System.out.println("4. Admin Login");
            System.out.println("0. Exit");
            ch = input.nextInt();

            switch (ch) {
                case 1:
                    Customer loginCus = new Customer();
                    loginCus.customerLogin();
                    loginCus.menuCustomer();
                    break;
                case 2:
                    Customer signcus = new Customer();
                    signcus.signUp();
                    signcus.menuCustomer();
                    break;
                case 3:
                    Employee emp = new Employee();
                    emp.employeeLogin();
                    emp.menuEmployee();
                    break;
                case 4:
                    Admin loginAdmin = new Admin();
                    loginAdmin.adminLogin();
                    loginAdmin.menuAdmin();
                    break;
                case 0:
                    System.out.println("Exitng ...");
                    System.exit(0);
                    break;
                default:
                    break;
            }
        } while (ch != 0);
    }

}

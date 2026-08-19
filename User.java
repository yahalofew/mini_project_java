import java.util.*;
import java.io.*;

public class User {

    public String dataEmployee = "E:\\mini_project\\employeeData.csv";
    public String DataCustomer = "E:\\mini_project\\customerData.csv";
    private long id;
    private long phone;
    private String address;
    private String username;
    private String password;
    private String name;
    public String Data;
    public int logged = 0;

    // protected ArrayList<String> customers = new ArrayList<String>(8);
    public String Details[] = new String[10];
    public Scanner keyboard = new Scanner(System.in);

    public String edDetails[] = new String[8];

    User() {
    }

    public void Login(File data) {
        System.out.println("Enter the following details");
        System.out.println("Username ID: ");
        String user = keyboard.next();
        System.out.println("Password: ");
        String pass = keyboard.next();

        try {
            Scanner sc = new Scanner(data);

            while (sc.hasNextLine()) {
                Details = sc.nextLine().split(",");
                // check data in details
                /*
                 * System.out.println(Details.length+" length");
                 * for(int i=0; i < Details.length;i++)
                 * {
                 * System.out.print(Details[i]+" ");
                 * }
                 * System.out.println("");
                 */
                //
                if (user.equals(Details[4]) && pass.toString().equals(Details[7])) {
                    System.out.println("-- SUCCESSFULL LOGIN --");
                    logged = 1;
                    break;
                }
            }
            sc.close();

        } catch (IllegalStateException e) {
            System.out.println("Exception thrown: " + e);
        } catch (NoSuchElementException e) {
            System.out.println("Exception thrown: " + e);
        } catch (Exception e) {
            System.out.println("Exception thrown: " + e);
        }
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void setPhone(long phone) {
        this.phone = phone;
    }

    public long getPhone() {
        return phone;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddress() {
        return address;
    }
}
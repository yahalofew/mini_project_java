import java.util.*;

import java.io.*;

public class Customer extends User {

    //private String DataCustomer = "E:\\mini_project\\customerData.csv";
    public static Customer loginCustomer;
    public ArrayList<Products> cart = new ArrayList<Products>();
    public ArrayList<Products> Order = new ArrayList<Products>();
    public product_food viewProductsfood = new product_food();
    public product_water viewProductswater = new product_water();
    public Bill buyProducts = new Bill();
    public int totalPrice;
    String dDetails[] = new String[8];
    Customer() {
        setName("");
        setUsername("");
        setPassword("");
        setId(0);
        setPhone(0);
        setAddress("");
    }

    Customer(String name, String username, String password, long id, long phone, String address) {
        this.setName(name);
        this.setUsername(username);
        this.setPassword(password);
        this.setId(id);
        this.setPhone(phone);
        this.setAddress(address);
    }

    public void customerLogin() {
        try {
            File file = new File(DataCustomer);
            Login(file);

            if (logged == 1) {
                loginCustomer = new Customer(Details[2], Details[4], Details[7], Long.parseLong(Details[1]),
                        Long.parseLong(Details[3]), Details[5]);
                viewProductsfood.readfilemenu();
                viewProductswater.readfilemenu();
            } else {
                while (logged != 1) {
                    System.out.println(" Wrong!! \n The username or password is incorrect.");
                    System.out.println("1. Try Again");
                    System.out.println("2. Sign Up");
                    System.out.println("0. Exit");
                    int ch = keyboard.nextInt();

                    switch (ch) {
                        case 1:
                            customerLogin();
                            break;
                        case 2:
                            signUp();
                            break;
                        case 0:
                            System.out.println("Exiting ... ");
                            System.exit(0);
                            break;
                        default:
                            System.out.println("Please enter a valid option");
                            customerLogin();
                            break;
                    }
                }
            }
            // sc.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void signUp() {
        try {
            keyboard.nextLine();
            System.out.println("-- Enter the following details --");
            System.out.print("Enter Username ID:");
            String user = keyboard.next();
            // System.out.println("Password: ");
            // String pass = inputsignup.next();
            int signupfound = 0;
            int customerNo = 0;
            File file = new File(DataCustomer);
            Scanner sc = new Scanner(file);

            while (sc.hasNextLine()) {
                dDetails = sc.nextLine().split(",");

                if (dDetails.length >= 5 && user.equals(dDetails[4])) {
                    System.out.println("\n--- This username is already used!! ---\n");
                    signupfound = 1;
                    break;
                }
                customerNo++;
            }
            if (signupfound == 1) {
                System.out.println("1. Log in");
                System.out.println("2. Try Again !!(Create new account with different Email ID)");
                System.out.println("0. EXIT");
                int ch = keyboard.nextInt();
                switch (ch) {
                    case 1:
                        customerLogin();
                        break;
                    case 2:
                        signUp();
                        break;
                    case 0:
                        System.out.println("Exiting ... ");
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Please enter a valid option");
                }
            } else {
                long phone = 0;
                String password = "";
                String name = "";
                String address = "";
                keyboard.nextLine();
                System.out.println("Enter Name:");
                name = keyboard.nextLine();
                System.out.println("Enter Address:");
                address = keyboard.nextLine();

                boolean validity = false;
                while (!validity) {
                    System.out.println("Enter Phone Number(ex:+66:95 234 7567): ");
                    phone = keyboard.nextLong();
                    if (phone <= 100000000 || phone >= 1000000000) {
                        System.out.println("Invalid phone number (ex:+66:95 234 5678(9 digits))");
                    } else {
                        validity = true;
                    }
                }
                keyboard.nextLine();

                System.out.println("Enter Password: ");
                password = keyboard.nextLine();

                loginCustomer = new Customer(name, user, password, customerNo, phone, address);

                FileWriter filewriter = new FileWriter(DataCustomer, true);
                filewriter.write(Integer.toString(customerNo) + "," + Integer.toString(customerNo) + "," + name + ","
                        + phone + "," + user + "," + address + "," + 0 + "," + password + "," + "\n");
                filewriter.close();

                customerLogin();

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

    public void menuCustomer() {
        int ch;
        do {
            System.out.println("------------------------WELCOME--------------------\n");
            System.out.println("1. View products");
            System.out.println("2. View to cart");
            // System.out.println("3. View to Order");//
            System.out.println("3. Account Settings");
            System.out.println("0. Logout");
            System.out.print("Enter your choice ");
            ch = keyboard.nextInt();
            switch (ch) {
                case 1:
                    System.out.println("--------------------Product type-------------------");
                    System.out.println("1. Products Food");
                    System.out.println("2. Products Water");
                    System.out.println("0. Going Back");
                    System.out.println("---------------------------------------------------");
                    System.out.print("Enter your choice: ");
                    int ch1 = keyboard.nextInt();
                    switch (ch1) {
                        case 1:
                            viewProductsfood.addToCart(loginCustomer);
                            break;
                        case 2:
                            viewProductswater.addToCart(loginCustomer);
                            break;
                        case 0:
                            break;
                        default:
                            break;
                    }
                    break;
                case 2:
                    viewToCart();
                    break;
                // case 3:

                // break;
                case 3:
                    accSetting();
                    break;
                case 0:
                    System.out.println("Exiting ... ");
                    System.exit(0);
                    break;
                default:
                    break;
            }
        } while (ch != 0);

    }

    public void displayCustomer() {
        System.out.println("Name: " + loginCustomer.getName());
        System.out.println("Username : " + loginCustomer.getUsername());
        System.out.println("Phone Number: " + loginCustomer.getPhone());
        System.out.println("Address: " + loginCustomer.getAddress());
    }

    public void accSetting() {
        int ch;
        do {
            System.out.println("---------------Account Settings--------------------");
            System.out.println("1. DisPlay details");
            System.out.println("2. Edit Details");
            System.out.println("0. Back");

            ch = keyboard.nextInt();
            switch (ch) {
                case 1:
                    displayCustomer();
                    accSetting();
                    break;
                case 2:
                    edit();
                    accSetting();
                    break;
                case 0:
                    menuCustomer();
                    break;
                default:
                    System.out.println("INVALID CHOICE \n");
                    keyboard.next();
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
        do {
            System.out.println("-----------------------EDIT------------------------");
            System.out.println("1. Edit Name");
            System.out.println("2. Edit Address");
            System.out.println("3. EDIT Phone Number");
            System.out.println("4. Edit Password ");
            System.out.println("0. Back");
            System.out.println("---------------------------------------------------");
            System.out.println("Enter ch:");
            ch = keyboard.nextInt();
            switch (ch) {
                case 1:
                    keyboard.nextLine();
                    System.out.println("Name Old: " + loginCustomer.getName());
                    System.out.println("Enter a new name: ");
                    String name = keyboard.nextLine();
                    loginCustomer.setName(name);
                    break;
                case 2:
                    keyboard.nextLine();
                    System.out.println("Address Old: " + loginCustomer.getAddress());
                    System.out.println("Enter a new Address: ");
                    String address = keyboard.nextLine();
                    loginCustomer.setAddress(address);
                    break;
                case 3:
                    keyboard.nextLine();
                    System.out.println("Phone Number Old: " + loginCustomer.getPhone());
                    while (!validity) {
                        System.out.println("Enter a new phone number: ");
                        phone = keyboard.nextLong();
                        if (phone <= 100000000 || phone >= 1000000000) {
                            System.out.println("Invalid phone number!(ex:+66:95-234-5678(9 digits))");
                            keyboard.next();
                        } else {
                            validity = true;
                        }
                    }
                    loginCustomer.setPhone(phone);
                    break;
                case 4:
                    System.out.println("Enter your old password: ");
                    oldPass = keyboard.next();
                    if (loginCustomer.getPassword().equals(oldPass)) {
                        System.out.println("Enter a new password: ");
                        newPass = keyboard.next();
                        loginCustomer.setPassword(newPass);
                    } else {
                        System.out.println("Wrong password !!!");
                        keyboard.next();
                        edit();
                    }
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

        } while (ch != 0);
    }

    public void fileWriter() {
        try {
            ArrayList<Customer> customerList = new ArrayList<Customer>();
            File file = new File(DataCustomer);
            Scanner scanner = new Scanner(file);

            while (scanner.hasNextLine()) {
                edDetails = scanner.nextLine().split(",");

                if (edDetails[4].equalsIgnoreCase("Username")) {
                    continue;
                } else if (Integer.parseInt(edDetails[1]) == loginCustomer.getId()) {
                    customerList.add(loginCustomer);
                } else {
                    Customer customeKeep = new Customer(edDetails[2], edDetails[4], edDetails[7],
                            Long.parseLong(edDetails[1]), Long.parseLong(edDetails[3]), edDetails[5]);
                    customerList.add(customeKeep);
                }
            }
            FileWriter filewriter = new FileWriter(file);
            filewriter.write("SrNo,ID,Name,Phone No,Username,Address,Credits,Password,\n");
            int x = 1;
            for (Customer addtoCustomer : customerList) {
                filewriter.write(Integer.toString(x) + "," + Long.toString(addtoCustomer.getId()) + "," +
                        addtoCustomer.getName() + "," + Long.toString(addtoCustomer.getPhone()) + ","
                        + addtoCustomer.getUsername() +
                        "," + addtoCustomer.getAddress() + "," + 0 + "," + addtoCustomer.getPassword() + ",\n");
                x++;
            }
            filewriter.close();
            customerList.clear();

        } catch (IOException e) {
            System.out.println(e);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    /*
     * public void delete() {
     * System.out.println("Are you sure you want to delete your account?(Y->1/N->0)"
     * );
     * int ch = keyboard.nextInt();
     * if (ch == 1) {
     * try{
     * File file = new File(DataCustomer);
     * Scanner scanner = new Scanner(file);
     * while(scanner.hasNextLine()){
     * 
     * }
     * }
     * }
     * }
     */

    public void viewCart() {
        try {
            if (loginCustomer.cart.size() > 0) {
                System.out.println("-------------------Your Cart-----------------------");
                System.out.println("\tID\tName\tPrice\tQuantity");
                for (Products cartProduct : loginCustomer.cart) {
                    System.out.println("\t"+ cartProduct.productID + "\t" + cartProduct.productName + "\t"
                            + cartProduct.productPrice + "\t"
                            + cartProduct.productQuantity);
                }
            } else {
                System.out.println("\nYour cart is empty : Shop \n");
                keyboard.nextLine();
                menuCustomer();
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void viewToCart() {
        int ch;
        //viewCart();
        try {
            if(loginCustomer.cart.size()>0){
                viewCart();
                do {
                    System.out.println("---------------------------------------------------");
                    System.out.println("What do you want to do with the shopping cart?");
                    //System.out.println("1. Edit: Number of products");
                    System.out.println("1. Remove products");
                    System.out.println("2. Continue & buy");
                    System.out.println("0. Going Back");
                    System.out.println("---------------------------------------------------");
                    System.out.print("Enter your choice:");
                    ch = keyboard.nextInt();
                    switch (ch) {
                        //case 1:
                            //System.out.println("");
                            //break;
                        case 1:
                            System.out.println("Enter ID of the product from to remove it ");
                            int reID = keyboard.nextInt();
                            removeProd(reID);
                            break;
                        case 2:
                            buyProducts.confirmDetails(loginCustomer);
                            break;
                        case 0:
                            System.out.println("");
                            menuCustomer();
                            break;
                        default:
                            break;
                    }
                } while (ch != 0);
            }else{
                System.out.println("\nYour cart is empty : Shop >> \n");
                menuCustomer();
            }
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    /*
     * public void continueToBuy() {
     * if (loginCustomer.cart.size() > 0) {
     * System.out.println("Sure you want to buy");
     * } else {
     * 
     * }
     * }
     */

    public void removeProd(int Idremove) {
        for (int i = 0; i < loginCustomer.cart.size(); i++) {
            if (loginCustomer.cart.get(i).productID == Idremove) {
                System.out.println("Do you want to remove products from the basket? [ 1 - Y | 0 - N ] :");
                int ch = keyboard.nextInt();
                if (ch == 1) {
                    loginCustomer.cart.remove(i);
                } else {
                    viewCart();
                }
            }
        }
    }

    public void setDataCustomer(String dataCustomer) {
        DataCustomer = dataCustomer;
    }

    public String getDataCustomer() {
        return DataCustomer;
    }
}

import java.io.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Bill {
    public Scanner keyboard = new Scanner(System.in);
    private String numberCard;
    private String nameCard;
    private String expirationDate;
    private String CVV;
    public int payment;
    Bill() {
    }

    public void confirmDetails(Customer customer) {
        if (customer.cart.size() > 0) {// skip not elseif
            System.out.println("--------------------Your Details-------------------");
            customer.displayCustomer();
            System.out.println("---------------------------------------------------");
            System.out.print("Do You want to edit your personal details [0 -> No || 1 -> Yes]: ");
            int ch = keyboard.nextInt();

            if (ch == 0) {
                confirmOrder(customer);
            } else if (ch == 1) {
                customer.edit();
                confirmOrder(customer);
            } else {
                System.out.println("Invalid choice");
                confirmOrder(customer);
            }
        }
    }

    public void confirmOrder(Customer customer) {
        Summary(customer);      
        // if(customer.)// credit {
        // }
        System.out.println("---------------------------------------------------");
        System.out.println("How do you wish to pay?");
        System.out.println("1. Debit/Credit");
        System.out.println("2. Pay on Delivery/Cash Payment");
        System.out.println("0. Cancel");
        System.out.println("---------------------------------------------------");
        int ch = keyboard.nextInt();
        payment=0;
        switch (ch) {
            case 1:
                Credit();
                System.out.println("Select successful payment");
                Billing(customer);
                break;
            case 2:
                System.out.println("Select successful payment");
                Billing(customer);
                break;
            case 0:
                break;
            default:
                System.out.println("Invalid choice");
                confirmOrder(customer);
        }
    }

    public void Summary(Customer customer) {
        customer.viewCart();
        customer.totalPrice=0;
        for (Products totalPrice : customer.cart) {
            customer.totalPrice += (totalPrice.getProductPrice() * totalPrice.getproductQuantity());
        }
        System.out.println("---------------------------------------------------");
        System.out.println(">>>> Total Price : " + customer.totalPrice + "Baht");
    }

    public void Billing(Customer customer) {
        int ch;
        do {
            System.out.println("1. Confirm Order");
            System.out.println("0. Cancel");
            ch = keyboard.nextInt();
            switch (ch) {
                case 1:
                    System.out.println("Order Successfully placed!!");
                    Tobill(customer);
                    try {
                        for (int i = 0; i < customer.cart.size(); i++) {
                            customer.Order.add(customer.cart.get(i));
                        }
                        
                        customer.cart.clear();
                        System.out.println("");
                        keyboard.nextLine();
                        customer.menuCustomer();
                    } catch (Exception e) {
                        System.out.println(e);
                    }
                    break;
                case 0:
                    customer.viewToCart();
                    break;
                default:
                    System.out.println("Invalid choice");
                    break;
            }
        } while (ch != 0);
    }

    public void Credit(){
        keyboard.nextLine();

        System.out.println("--------------------------------------------");
        System.out.println("input your numberCard : ");
        numberCard = keyboard.nextLine();
        System.out.println("input your nameCard : ");
        nameCard = keyboard.nextLine();
        System.out.println("input your expirationDate : ");
        expirationDate = keyboard.nextLine();
        System.out.println("input your CVV : ");
        CVV = keyboard.nextLine();
        payment=1;

    }

    public void Tobill(Customer customer){
        System.out.println("==============================================");
        System.out.println("\t\t Bill Receipt\t\t");
        System.out.println("==============================================\n");
        LocalDateTime myDateObj = LocalDateTime.now();
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String formattedDate = myDateObj.format(myFormatObj);
        System.out.println("\nDate/Time: Ordered: "+ formattedDate);
        Summary(customer);     
        if(payment!=0){
            System.out.println("Payment: Debit/Credit");
        }else{
            System.out.println("Payment: Pay on Delivery/Cash Payment");
        }
        System.out.println("==============================================");
    }


}


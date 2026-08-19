import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.util.*;

public class product_water extends Products {

    private String datawater = "./dataWater.json";
    public static ArrayList<product_water> productAll = new ArrayList<product_water>();

    product_water() { 
    }

    public product_water(int id, String name, int price, int qu) { 
        super(id,name,price,qu);
    }

    public void productsMenu() {
        int ch;
        do {
            System.out.println("---------------------------------------------------");
            System.out.println("1. Add Products");
            System.out.println("2. Remove Products");
            System.out.println("3. Alter Products Info");
            System.out.println("4. View All Products");
            System.out.println("0. Back");
            System.out.println("---------------------------------------------------");
            System.out.println("Please enter your choice : ");
            ch = keyboard.nextInt();
            switch (ch) {
                case 1:
                    addProduct();
                    break;
                case 2:
                    deleteProduct();//
                    break;
                case 3:
                    editProduct();
                    break;
                case 4:
                    showProducts();
                    break;
                case 0:
                    
                    break;
                default:
                    System.out.println("Wrong choice ");
                    break;
            }
        } while (ch != 0);
    }

    public void showProducts() {
        if (productAll.size() <= 0) {
            System.out.println("NO PRODUCTS AVAIALABLE !");
            keyboard.next();

        } else {
            System.out.println("------------------------------------- WATER --------------------------------------");
            System.out.println("----------------------------------------------------------------------------------");
            System.out.println("--- Product ID ------- Products Name ------ Price ------ Quantity ------");
            for (product_water prod : productAll) {
                System.out.println("--- " + prod.productID + " ------- " + prod.productName + " ------ "
                        + prod.productPrice + " ------ " + prod.productQuantity + " ------ ");
            }
            System.out.println("---------------------------------------------------------------------------------");

        }
    }

    public void readfilemenu() {
        try {
            JSONParser parser = new JSONParser();
            Reader reader = new FileReader(datawater);
            JSONArray jsonArray = (JSONArray) parser.parse(reader);

            //Iterator<JSONArray> iterator = jsonArray.iterator();
            
            for(i=0; i<jsonArray.size();i++){
            //while (iterator.hasNext()) {
                // ทำloopใหม่
                JSONObject JSONid = (JSONObject) jsonArray.get(i);

                String id = (String) JSONid.get("ID");
                String name = (String) JSONid.get("Name");
                String price = (String) JSONid.get("Price");
                String qu = (String) JSONid.get("Quantity");

                product_water addDataProduct = new product_water(Integer.parseInt(id), name, Integer.parseInt(price),
                        Integer.parseInt(qu));

                productAll.add(addDataProduct);
                
            }
            
        } catch (ParseException e) {
            System.out.println(e);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void addProduct() {
        try {
            keyboard.nextLine();
            System.out.println("-- ADD PRODUCT --");

            System.out.print("Product name: ");
            String name = keyboard.nextLine();

            System.out.print("\nProduct price: ");
            int price = keyboard.nextInt();
            
            System.out.print("\nProduct Quantity: ");
            int qu = keyboard.nextInt();

            i = i + 1;
            JSONObject data = new JSONObject();// แก้เป็น JsONObject
            data.put("ID", Integer.toString(i));
            data.put("Name", name);
            data.put("Price",Integer.toString(price));
            data.put("Quantity", Integer.toString(qu));

            // obj.put(data);
            JSONParser parser = new JSONParser();
            Reader reader = new FileReader(datawater);
            JSONArray jsonArray = (JSONArray) parser.parse(reader);
            jsonArray.add(data);// คลุ่มด้วย Array

            FileWriter file = new FileWriter(datawater);
            file.write(jsonArray.toJSONString());

            product_water addProduct = new product_water(i, name, price, qu);
            productAll.add(addProduct);
            System.out.println("Product added successfully");
            file.close();

        } catch (IOException e) {
            System.out.println(e);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void editProduct() {
        int ch;
        do {
            System.out.println("---------------------------------------------------");
            System.out.println(" 1. Edit name ");
            System.out.println(" 2. Edit price ");
            System.out.println(" 3. Edit Quantity ");
            System.out.println("0. Back");
            System.out.println("---------------------------------------------------");
            System.out.print("Please enter your choice : ");
            ch = keyboard.nextInt();
            switch (ch) {
                case 1:
                    editName();
                    break;
                case 2:
                    editPrice();
                    break;
                case 3:
                    editQu();
                    break;
                case 0:

                    break;
                default:
                    System.out.println("Wrong choice !!");
                    break;
            }
        } while (ch != 0);
    }

    public void editName() {
        showProducts();
        System.out.println("---------------------------------------------------");
        System.out.println("Please enter id number that you want to edit the name: ");
        int id = keyboard.nextInt();

        int correct = 0;
        String edName = "";
        try {
            for (product_water printProd : productAll) {
                if (id == printProd.getProductID()) {
                    keyboard.nextLine();
                    System.out.println("Enter the name to edit: ");
                    edName = keyboard.nextLine();

                    // printProd.setProductName(edName);

                    //Products edname = new Products();
                    //edname.setProductName(edName);
                    productAll.get(id - 1).setProductName(edName);
                    correct = 1;
                }

            }
            if (correct == 1) {
                JSONParser parser = new JSONParser();
                Reader reader = new FileReader(datawater);
                JSONArray jsonArray = (JSONArray) parser.parse(reader);

                JSONObject JSONid = (JSONObject) jsonArray.get(id - 1);

                JSONid.put("Name", edName);

                FileWriter file = new FileWriter(datawater);
                file.write(jsonArray.toJSONString());

                System.out.println("Edit name complete");

                file.close();
            } else {
                System.out.println("Invalid : Product id not found !!! , please try again");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void editPrice() {
        showProducts();
        System.out.println(" ----------------------------------------------------- ");
        System.out.println("Please enter id number that you want to edit the Price: ");
        int id = keyboard.nextInt();

        int correct = 0;
        int edPrice = 0;
        try {
            for (product_water printProd : productAll) {
                if (id == printProd.getProductID()) {
                    System.out.println("Enter the Price to edit: ");
                    edPrice = keyboard.nextInt();

                    // printProd.setProductPrice(edPrice);

                    //Products edprice = new Products();
                    //edprice.setproductQuantity(edPrice);
                    
                    productAll.get(id - 1).setProductPrice(edPrice);;

                    correct = 1;
                }
            }
            if (correct == 1) {
                JSONParser parser = new JSONParser();
                Reader reader = new FileReader(datawater);
                JSONArray jsonArray = (JSONArray) parser.parse(reader);

                JSONObject JSONid = (JSONObject) jsonArray.get(id - 1);

                JSONid.put("Price", Integer.toString(edPrice));

                FileWriter file = new FileWriter(datawater);
                file.write(jsonArray.toJSONString());

                System.out.println("Edit Price complete");
                file.close();
            } else {
                System.out.println("Invalid : Product id not found !!! , please try again");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void editQu() {
        showProducts();
        System.out.println("---------------------------------------------------");
        System.out.println("Please enter id number that you want to edit the Quantity: ");
        int id = keyboard.nextInt();

        correct = 0;
        int edQu = 0;
        try {
            for (product_water printProd : productAll) {
                if (id == printProd.getProductID()) {
                    System.out.println("Enter the Quantity to edit: ");
                    edQu = keyboard.nextInt();
                    // printProd.setproductQuantity(edQu);
                    //Products edqu = new Products();
                    //edqu.setproductQuantity(edQu);

                    productAll.get(id - 1).setproductQuantity(edQu);;

                    correct = 1;
                }

            }
            if (correct == 1) {
                JSONParser parser = new JSONParser();
                Reader reader = new FileReader(datawater);
                JSONArray jsonArray = (JSONArray) parser.parse(reader);

                JSONObject JSONid = (JSONObject) jsonArray.get(id - 1);

                JSONid.put("Quantity", Integer.toString(edQu));

                FileWriter file = new FileWriter(datawater);
                file.write(jsonArray.toJSONString());
                System.out.println("Edit Quantity complete");

                file.close();
            } else {
                System.out.println("Invalid : Product id not found !!! , please try again");
                keyboard.next();
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void deleteProduct() {
        showProducts();
        System.out.println("---------------------------------------------------");
        System.out.println("Enter the ID product you want to delete: ");
        int id = keyboard.nextInt();
        correct =0;
        int ch;
        try {
            for (product_water printProd : productAll) {
                if (id == printProd.getProductID()) {
                    System.out.println("Are you sure you want to delete this product?(Y->1/N->0)");
                    ch = keyboard.nextInt();
                    if(ch == 1){
                        productAll.remove(id - 1);
                        correct = 1;
                    }else if(ch !=1){
                        productsMenu();
                        break;
                    }else{
                        System.out.println("Invalid : Product id not found !!! , please try again");
                        deleteProduct();
                        break;
                    }
                    // printProd.setproductQuantity(edQu);
                    //Products edqu = new Products();
                    //edqu.setproductQuantity(edQu);
                }

            }
            if (correct == 1) {
                JSONParser parser = new JSONParser();
                Reader reader = new FileReader(datawater);
                JSONArray jsonArray = (JSONArray) parser.parse(reader);

                //JSONObject JSONid = (JSONObject) jsonArray.get(id - 1);
                //JSONid.put("Quantity", Integer.toString(edQu));
                jsonArray.remove(id - 1);

                FileWriter file = new FileWriter(datawater);
                file.write(jsonArray.toJSONString());
                System.out.println("Edit Quantity complete");

                file.close();    
            } else {
                System.out.println("Invalid : Product id not found !!! , please try again");
                keyboard.next();
            }
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    public void addToCart(Customer customer) {
        int ch;
        try{
            do {
                showProducts();
                System.out.println("1. Add to cart");
                System.out.println("0. Going Back");
                System.out.print("Enter your choice: ");
                ch = keyboard.nextInt();
                switch (ch) {
                    case 1:
                        System.out.println("Enter the product id to add to cart");
                        int id = keyboard.nextInt();
                        for (product_water printProd : productAll) {
                            if (id == printProd.getProductID()) {
                                System.out.println("Enter the number of products to add to the cart");
                                int qu = keyboard.nextInt();
                                if (qu > printProd.getproductQuantity()) {
                                    System.out.println(
                                            "Insufficient number of products: Remaining stock"
                                                    + printProd.getproductQuantity()
                                                    + "Item");
                                    break;
                                } else {
                                    // customerCart.add(productAll.get(id - 1));
                                    // customerCart.set(id, qu);
                                    // customer.cart.add(customerCart);

                                    Products addToCusCart = new Products(productAll.get(id - 1).getProductID(),
                                    productAll.get(id - 1).getProductName(),
                                    productAll.get(id - 1).getProductPrice(),
                                    qu);

                                    customer.cart.add(addToCusCart);

                                    /*JSONParser parser = new JSONParser();
                                    Reader reader = new FileReader(datawater);
                                    JSONArray jsonArray = (JSONArray) parser.parse(reader);

                                    JSONObject JSONid = (JSONObject) jsonArray.get(id - 1);

                                    String edit = (String) JSONid.get("Quantity");
                                    Integer.parseInt(edit);
                                    edit = edit - qu;

                                    JSONid.put("Quantity", Integer.toString(edit));

                                    FileWriter file = new FileWriter(datawater);
                                    file.write(jsonArray.toJSONString());

                                    productAll.get(id - 1).setproductQuantity(edit);
                                    */

                                    System.out.println("Successfully added product");
                                    // addToCart(customer);
                                    break;
                                }
                            } else if (printProd == null) {
                                System.out.println("Invalid : Product id not found !!");
                                addToCart(customer);
                                break;
                            }
                        }
                        break;
                    case 0:
                        // back
                        break;
                    default:
                        System.out.println("Invalid Choice");
                        break;
                }
            } while (ch != 0);
        }catch(Exception e){
            System.out.println(e);
        }
    }
}

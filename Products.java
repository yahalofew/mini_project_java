
import java.util.*;

public class Products {

    Products() {
    }

    public Scanner keyboard = new Scanner(System.in);

    //private String datamenu = "./menuData.json";

    public int productID;
    public String productName;
    public int productPrice;
    public int productQuantity;

    public int correct;
    public static int i = 0;
    // JSONParser parser = new JSONParser();
    // public Reader reader = new FileReader(datamenu);
    // public static JSONObject obj = new JSONObject();
    // public JSONArray jsonObject = (JSONArray) parser.parse(reader);

    public Products(int id, String name, int price, int qu) {
        this.productID = id;
        this.productName = name;
        this.productPrice = price;
        this.productQuantity = qu;
    }
    
    public void setCorrect(int correct) {
        this.correct = correct;
    }
    public int getCorrect() {
        return correct;
    }
    public void setProductID(int productID) {
        this.productID = productID;
    }

    public int getProductID() {
        return productID;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductPrice(int productPrice) {
        this.productPrice = productPrice;
    }

    public int getProductPrice() {
        return productPrice;
    }

    public void setproductQuantity(int productQuantity) {
        this.productQuantity = productQuantity;
    }

    public int getproductQuantity() {
        return productQuantity;
    }

}
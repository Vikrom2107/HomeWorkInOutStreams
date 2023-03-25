import java.io.*;

public class Basket {
    String[] products;
    int[] prices;
    int[] count;
    int sumProducts = 0;

    public Basket(String[] products, int [] prices) {
        this.products = products;
        this.prices = prices;
        this.count = new int[products.length];
    }
    public void addToCart(int productNumber, int productCount) {
        count[productNumber - 1] += productCount;
        sumProducts += productCount * prices[productNumber - 1];

    }
    public void getList() {
        System.out.println("Список продуктов:");
        for (int i = 0; i< products.length; i++) {
            System.out.println((i+1) + ". " + products[i] + " " + prices[i] + " руб/шт");
        }
    }
    public void printCart() {
        System.out.println("Ваша корзина:");
        for (int i = 0; i < products.length; i++) {
            System.out.println((i + 1) + ". " + products[i] + " " + count[i] + " шт "
                    + prices[i] + " руб/шт Cумма " + (count[i] * prices[i]) + " руб");
        }
        System.out.println("Общая сумма продуктов " + sumProducts + " руб");
    }

    public void setProducts(String[] products) {
        this.products = products;
    }
    public String[] getProducts() {
        return products;
    }

    public void setCount(int[] count) {
        this.count = count;
    }

    public void setSumProducts(int sumProducts) {
        this.sumProducts = sumProducts;
    }



    public void saveTxt(File textFile) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(textFile))){
            for (String prod : products) {
                bw.write(prod + ";");
            }
            bw.newLine();
            for (int c : count) {
                bw.write(c + ";");
            }
            bw.newLine();
            bw.write(sumProducts + "");
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
}


import java.io.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        File file = new File("basket.bin");
        Basket basket = loadFromBinFile(file);
        Scanner scanner = new Scanner(System.in);

        while (true) {
            int productNumber = 0;
            int productCount = 0;
            System.out.println("Введите команду (1. Добавить товар 2. Показать корзину 3. end)");
            String input = scanner.nextLine();
            if ("3".equals(input) || "end".equals(input)) {
                basket.printCart();
                break;
            } else if ("1".equals(input) || "Добавить товар".equals(input)) {
                while (true) {
                    System.out.println("Выберите товар, указав необходимый порядковый номер, " +
                            "и введите количество продуктов");
                    basket.getList();
                    String addProduct = scanner.nextLine();
                    String[] numbers = addProduct.split(" ");
                    if (numbers.length != 2) {
                        System.out.println("Вы не ввели порядковый номер или количество продуктов, пожалуйста" +
                                " введите 2 значения\n");
                        continue;
                    }
                    try {
                        productNumber = Integer.parseInt(numbers[0]);
                        productCount = Integer.parseInt(numbers[1]);
                        if (productNumber > basket.getProducts().length || productNumber < 1) {
                            System.out.println("В списке нет продуктов под таким номером, пожалуйста введите " +
                                    "порядковый номер из представленных в списке\n");
                            continue;
                        }
                        if (productCount <= 0) {
                            System.out.println("Количество продуктов должно быть больше 0\n");
                            continue;
                        }
                        basket.addToCart(productNumber, productCount);
                        basket.saveBin(file, basket);
                        break;
                    } catch (NumberFormatException e) {
                        System.out.println("Вы ввели некорректные значения, пожалуйста введите " +
                                "2 целых числа через пробел\n");
                    }
                }
            } else if ("2".equals(input) || "Показать корзину".equals(input)) {
                basket.printCart();
            } else {
                System.out.println("Неверно введена команда");
            }
        }
    }
    public static Basket loadFromBinFile(File binFile) {
        if (binFile.exists()) {
            Basket basket = new Basket(new String[]{"Хлеб", "Яблоки", "Молоко", "Гречневая крупа"},
                    new int[]{50, 150, 100, 200});
            try (FileInputStream fis = new FileInputStream(binFile);
                 ObjectInputStream ois = new ObjectInputStream(fis)) {
                basket= (Basket) ois.readObject();
                return basket;
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        } else {
            try {
                binFile.createNewFile();
                Basket basket2 = new Basket(new String[]{"Хлеб", "Яблоки", "Молоко", "Гречневая крупа"},
                        new int[]{50, 150, 100, 200});
                return basket2;
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        return new Basket(new String[]{"Хлеб", "Яблоки", "Молоко", "Гречневая крупа"},
                new int[]{50, 150, 100, 200});
    }
}
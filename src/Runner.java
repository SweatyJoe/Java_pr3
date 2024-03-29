import by.gsu.pms.DiscountPurchase;
import by.gsu.pms.DiscountPurchaseWithConstant;
import by.gsu.pms.GeneralPurchase;
import by.gsu.pms.PurchasesFactory;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static by.gsu.pms.WriterInfo.Writer;

public class Runner {
    public static void main(String[] args) {
        GeneralPurchase[] purchases = {
                new GeneralPurchase(),
                new GeneralPurchase(),
                new GeneralPurchase(),
                new GeneralPurchase(),
                new GeneralPurchase(),
                new GeneralPurchase(),
        };
        Worker(purchases);
        Writer(purchases);
    }

    private static void Worker(GeneralPurchase[] purchases) {
        final String FILE = "src/files/file.txt";
        List<String> listLines = new ArrayList<>();
        try (Scanner sc = new Scanner(new FileReader(FILE))) {
            while (sc.hasNextLine()) {
                listLines.add(sc.nextLine());
            }
        } catch (NullPointerException e) {
            System.out.println("Null exception");
        } catch (FileNotFoundException e) {
            System.out.println("Can't find file!");
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        int i = 0;
        String[] lists = new String[listLines.size()];
        for (String list : listLines) {
            lists[i] = list;
            i++;
        }
        PurchasesFactory factory = new PurchasesFactory();
        int index = 0;
        for (String list : lists) {
            purchases[index] = factory.purchasesFactory(list);
            index++;
        }
        double maxCost = 0;
        for (GeneralPurchase l : purchases) {
            if (l == null) continue;
            if (maxCost < l.getCost()) maxCost = l.getCost();
        }
        System.out.println("Max cost: " + maxCost);
    }
}
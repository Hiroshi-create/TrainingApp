package input;

import instance.Stock;
import reader.ReadStock;

import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InputStock {

    public static String name(){
        String name;
        while (true){
            System.out.println("銘柄名を半角英数字で入力してください。");
            System.out.print("銘柄名>");
            name = new Scanner(System.in).nextLine();

            String correctFormat = "^[A-Za-z0-9]+$";
            Pattern pattern = Pattern.compile(correctFormat);
            Matcher matcher = pattern.matcher(name);

            if (matcher.matches()){
                break;
            } else {
                System.out.println("半角英数字で入力してください。");
            }
        }
        return name;
    }

    public static String code(){
        String code;
        while (true){
            System.out.println("銘柄コードを半角数字4桁で入力してください。");
            System.out.print("銘柄コード>");
            code = new Scanner(System.in).nextLine();

            String correctFormat = "([0-9]){4}";
            Pattern pattern = Pattern.compile(correctFormat);
            Matcher matcher = pattern.matcher(code);

            if (!matcher.matches()){
                System.out.println("半角英数字で入力してください。");
                continue;
            }
            break;
        }
        return code;
    }

    public static String market() {
        String market;
        while (true) {
            System.out.println("上場市場を以下より、番号から選んで入力してください。");
            System.out.println("1. PRIME : 2. STANDAD : 3. GROWTH >");
            String marketNumber = new Scanner(System.in).nextLine();

            if ("1".equals(marketNumber) || "１".equals(marketNumber)) {
                market = "PRIME";
                break;
            } else if ("2".equals(marketNumber) || "２".equals(marketNumber)) {
                market = "STANDARD";
                break;
            } else if ("3".equals(marketNumber) || "３".equals(marketNumber)) {
                market = "GROWTH";
                break;
            } else {
                System.out.println("番号から選び、入力してください。");
            }
        }
        return market;
    }
    public static String sharesIssued(){
        String sharesIssued;
        while (true){
            System.out.println("発行済み株式数を半角数字で入力してください。");
            System.out.print("発行済み株式数>");
            sharesIssued = new Scanner(System.in).nextLine();

            String correctFormat = "([0-9])+";
            Pattern pattern = Pattern.compile(correctFormat);
            Matcher matcher = pattern.matcher(sharesIssued);

            if (!matcher.matches()){
                System.out.println("半角数字で入力してください。");
                continue;
            }
            break;
        }
        return sharesIssued;
    }

    public static String coincide(String code){
        String coincide = null;
        List<Stock> stocks = ReadStock.fileReader();
        for (Stock codes : stocks){
            if (codes.getCode().equals(code)) {
                coincide = "登録済";
                break;
            }
        }
        return coincide;
    }
}

package input;

import editor.PositionCode;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InputMarketPrice {
    public static List<BigDecimal> marketPrice(){
        List<BigDecimal> marketPriceList = new ArrayList<>();
        LinkedHashSet<String> codeList = PositionCode.positionCode();

        for (String code:codeList){
            String marketPrice;
            while (true){
                System.out.println("次の銘柄コードの時価を入力してください。<"+code+"> (例 : 2000.50)");
                System.out.print("時価>");
                marketPrice = new Scanner(System.in).nextLine();

                //なぜか4桁も通してしまう、1200,1345,etc
                String correctFormat = "^[0-9]+\\.([0-9]{2})$";
                Pattern pattern = Pattern.compile(correctFormat);
                Matcher matcher = pattern.matcher(marketPrice);

                if (!matcher.matches()){
                    System.out.println("正しいフォーマットで入力してください。");
                    continue;
                }
                break;
            }
            BigDecimal newMarketPrice = new BigDecimal(marketPrice);
            marketPriceList.add(newMarketPrice);
        }
        return marketPriceList;
    }
}

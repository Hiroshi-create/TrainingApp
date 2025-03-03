package menu;

import editor.PositionCalculator;
import editor.PositionCode;
import indicater.IndicatePosition;
import indicater.IndicateStock;
import indicater.IndicateTrade;
import input.InputStock;
import instance.Position;
import instance.Stock;
import instance.Trade;
import reader.ReadStock;
import reader.ReadTrade;
import writer.WriteStock;

import java.math.BigDecimal;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Scanner;

public class SelectMenu {

    public static String menu(){

        System.out.println("---");
        System.out.println("操作するメニューを番号から選択してください。");
        System.out.println("\s1. 銘柄マスタ一覧表示");
        System.out.println("\s2. 銘柄マスタ新規登録");
        System.out.println("\s3. 取引入力");
        System.out.println("\s4. 取引一覧の表示");
        System.out.println("\s5. ポジションの表示");
        System.out.println("\s6. 値洗い");
        System.out.println("\s9. アプリケーションを終了する");

        System.out.print(">");

        return new Scanner(System.in).nextLine();
    }
    //銘柄マスタ一覧表示
    public static void indicateProduct(){
        System.out.println("銘柄マスタ一覧を表示します。");
        List<Stock> stocks = ReadStock.fileReader();
        IndicateStock.indicate(stocks);
    }

    //取引一覧の表示
    public static void indicateTrade(){
        System.out.println("取引一覧を表示します。");
        List<Trade> tradeList = ReadTrade.fileReader();
        IndicateTrade.indicate(tradeList);
    }

    //ポジションの表示
    public static void indicatePosition(){

    }
    public static boolean end(){
        System.out.println("アプリケーションを終了します。");
        boolean judge = false;
        return judge;
    }
}

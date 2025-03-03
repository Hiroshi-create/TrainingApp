package menu;

import editor.*;
import indicater.IndicatePosition;
import input.InputMarketPrice;
import input.InputStock;
import input.InputTrade;
import instance.Position;
import writer.WriteStock;
import writer.WriteTrade;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("株式取引管理システムを開始します。");
        boolean judge = true;

        List<BigDecimal> valuationList = Initialization.initialization();
        List<BigDecimal> valuationProfitOrLossList = Initialization.initialization();

        while (judge){
            String number = SelectMenu.menu();
            switch (number) {
                case "1", "１" -> SelectMenu.indicateProduct();
                case "2", "２" -> {
                    System.out.println("銘柄マスタを新規登録します。");
                    String name = InputStock.name();
                    String code = InputStock.code();
                    if (InputStock.coincide(code) != null) {
                        System.out.println("このコードはすでに登録されています");
                        continue;
                    }
                    String market = InputStock.market();
                    String sharesIssued = InputStock.sharesIssued();
                    WriteStock.writer(name, code, market, sharesIssued);
                }
                case "3", "３" -> {
                    System.out.println("取引を入力します。");
                    String date = InputTrade.dateTime();
                    String time = InputTrade.time(date);
                    String codeNumber = InputTrade.code();
                    if (InputStock.coincide(codeNumber) == null) {
                        System.out.println("このコードは登録されていません。");
                        continue;
                    }
                    String buyOrSell = InputTrade.buyOrSell(codeNumber);
                    if (buyOrSell == null) {
                        continue;
                    }
                    String quantity = InputTrade.quantity(codeNumber, buyOrSell);
                    BigDecimal price = InputTrade.price();
                    int year = Integer.parseInt(date.substring(0, 4));
                    int month = Integer.parseInt(date.substring(5, 7));
                    int day = Integer.parseInt(date.substring(8, 10));
                    int hour = Integer.parseInt(time.substring(0, 2));
                    int minute = Integer.parseInt(time.substring(3, 5));
                    LocalDateTime dateTime = LocalDateTime.of(year, month, day, hour, minute);
                    WriteTrade.writerTrade(dateTime, codeNumber, buyOrSell, quantity, price);
                }
                case "4", "４" -> SelectMenu.indicateTrade();
                case "5", "５" -> {
                    System.out.println("現在のポジションを表示します。");

                    LinkedHashSet<String> codeList = PositionCode.positionCode();
                    List<Integer> quantityList = PositionCalculator.quantity();
                    List<BigDecimal> averageGetPriceList = PositionCalculator.averageGetPrice();
                    List<BigDecimal> actualProfitList = PositionCalculator.actualProfit(averageGetPriceList);
                    List<Position> positionList = Position.positionList(codeList, quantityList, averageGetPriceList, actualProfitList, valuationList, valuationProfitOrLossList);
                    IndicatePosition.positionTable(positionList);

                }
                case "6", "６" -> {
                    System.out.println("値洗いを行います。");

                    List<BigDecimal> marketPriceList = InputMarketPrice.marketPrice();
                    valuationList = PositionCalculator.valuation(marketPriceList);
                    valuationProfitOrLossList = PositionCalculator.valuationProfitOrLoss(valuationList);
                }
                case "9", "９" -> judge = SelectMenu.end();
                default -> System.out.println("該当する操作はありません");
            }
        }
    }
}
package editor;

import instance.Trade;
import reader.ReadTrade;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Objects;

public class PositionCalculator{

    static List<Trade> tradeList = ReadTrade.fileReader();
    static LinkedHashSet<String> codeList = PositionCode.positionCode();

    //保有数量メソッド
    public static List<Integer> quantity() {
        List<Integer> quantityList = new ArrayList<>();
        for (String code:codeList){
            int quantity = 0;
            for (Trade trade:tradeList){

                String tradeCode = trade.getCode();
                String buyOrSell = trade.getBuyOrSell();

                if (code.equals(tradeCode) && "BUY".equals(buyOrSell)) {
                    quantity += Integer.parseInt(trade.getQuantity());
                } else if (code.equals(tradeCode) && "SELL".equals(buyOrSell)){
                    quantity -= Integer.parseInt(trade.getQuantity());
                }
            }
            quantityList.add(quantity);
        }
        return quantityList;
    }

    //平均取得単価メソッド
    public static List<BigDecimal> averageGetPrice() {
        List<BigDecimal> averageGetPriceList = new ArrayList<>();
        for (String codes:codeList){
            double sumPrice = 0;
            int buyQuantity = 0;
            for (Trade list:tradeList){
                if (Objects.equals(codes, list.getCode()) && "BUY".equals(list.getBuyOrSell())){
                    sumPrice += Double.parseDouble(list.getQuantity())*list.getPrice().doubleValue();
                    buyQuantity += Integer.parseInt(list.getQuantity());
                }
            }
            BigDecimal editPrice = new BigDecimal(sumPrice/buyQuantity).setScale(2, RoundingMode.HALF_UP);
            averageGetPriceList.add(editPrice);
        }
        return averageGetPriceList;
    }

    //実現損益メソッド
    public static List<BigDecimal> actualProfit(List<BigDecimal> averageGetPriceList) {
        List<BigDecimal> actualProfitList = new ArrayList<>();
        int i = 0;
        for (String codes:codeList){
            BigDecimal actualPrice = new BigDecimal(0);
            for (Trade list:tradeList){
                if (Objects.equals(codes, list.getCode()) && "SELL".equals(list.getBuyOrSell())){
                    actualPrice = actualPrice.add(list.getPrice().subtract(averageGetPriceList.get(i))).multiply(new BigDecimal(list.getQuantity()));
                    actualPrice = actualPrice.setScale(2, RoundingMode.HALF_UP);
                }
            }
            actualProfitList.add(actualPrice);
            i++;
        }
        return actualProfitList;
    }

    //評価額メソッド
    public static List<BigDecimal> valuation(List<BigDecimal> marketPriceList) {
        List<BigDecimal> valuationList = new ArrayList<>();
        List<Integer> quantityList = quantity();
        for (int i = 0;i<quantityList.size();i++){
            BigDecimal quantity = new BigDecimal(quantityList.get(i));
            BigDecimal valuation = marketPriceList.get(i).multiply(quantity);
            valuationList.add(valuation);
        }
        return valuationList;
    }

    //評価損益メソッド
    public static List<BigDecimal> valuationProfitOrLoss(List<BigDecimal> valuationList) {
        List<Integer> quantityList = quantity();
        List<BigDecimal> averageGetPriceList = averageGetPrice();
        List<BigDecimal> valuationProfitOrLossList = new ArrayList<>();
        for (int i = 0;i<quantityList.size();i++){
            BigDecimal quantity = new BigDecimal(quantityList.get(i));
            BigDecimal valuationProfitOrLoss = valuationList.get(i).subtract(quantity.multiply(averageGetPriceList.get(i)));
            valuationProfitOrLossList.add(valuationProfitOrLoss);
        }
        return valuationProfitOrLossList;
    }
}

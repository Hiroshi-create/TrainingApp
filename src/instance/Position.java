package instance;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;

public class Position {

    private String code;
    private int quantity;
    private BigDecimal averageGetPrice;
    private BigDecimal actualProfit;
    private BigDecimal valuation;
    private BigDecimal valuationProfitOrLoss;

    public Position(String product,int quantity,BigDecimal averageGetPrice,BigDecimal actualProfit,BigDecimal valuation,BigDecimal valuationProfitOrLoss){
        this.code = product;
        this.quantity = quantity;
        this.averageGetPrice = averageGetPrice;
        this.actualProfit = actualProfit;
        this.valuation = valuation;
        this.valuationProfitOrLoss = valuationProfitOrLoss;
    }
    //positionListに変換するメソッド
    public static List<Position> positionList(LinkedHashSet<String> codeList,List<Integer> quantityList,List<BigDecimal> averageGetPriceList,List<BigDecimal> actualProfit,List<BigDecimal> valuation,List<BigDecimal> valuationProfitOrLoss){
        List<Position> positionList = new ArrayList<>();
        int i = 0;
        for (String codes:codeList){
            Position position = new Position(codes,quantityList.get(i),averageGetPriceList.get(i),actualProfit.get(i),valuation.get(i),valuationProfitOrLoss.get(i));
            positionList.add(position);
            i++;
        }
        return positionList;
    }
    public BigDecimal getValuationProfitOrLoss() {
        return valuationProfitOrLoss;
    }

    public void setValuationProfitOrLoss(BigDecimal expectedPrice) {
        this.valuationProfitOrLoss = expectedPrice;
    }

    public BigDecimal getValuation() {
        return valuation;
    }

    public void setValuation(BigDecimal presentPrice) {
        this.valuation = presentPrice;
    }

    public BigDecimal getActualProfit() {
        return actualProfit;
    }

    public void setActualProfit(BigDecimal actualProfit) {
        this.actualProfit = actualProfit;
    }

    public BigDecimal getAverageGetPrice() {
        return averageGetPrice;
    }

    public void setAverageGetPrice(BigDecimal averageGetPrice) {
        this.averageGetPrice = averageGetPrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getProduct() {
        return code;
    }

    public void setProduct(String product) {
        this.code = product;
    }
}

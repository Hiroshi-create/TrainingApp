package editor;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Initialization {
    public static List<BigDecimal> initialization(){
        List<BigDecimal> valuationProfitOrLossList = new ArrayList<>();
        List<Integer> quantityList = PositionCalculator.quantity();

        for (int i = 0;i<quantityList.size();i++){
            valuationProfitOrLossList.add(null);
        }
        return valuationProfitOrLossList;
    }
}

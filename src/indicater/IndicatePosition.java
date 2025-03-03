package indicater;

import instance.Position;
import java.util.List;

public class IndicatePosition {

    public static void positionTable(List<Position> positionList){

        for (int i = 0;i<134;i++){
            if (i==133){
                System.out.println("=");
            } else {
                System.out.print("=");
            }
        }
        System.out.format("| %8s", "code" + "  |");
        System.out.format("%25s", "stock_quantity" + "     |");
        System.out.format("%25s", "average_get_price" + "    |");
        System.out.format("%25s", "actual_profit" + "    |");
        System.out.format("%25s", "valuation" + "       |");
        System.out.format("%25s", "Profit/Loss" + "     |" + "\n");

        for (int i = 0;i<134;i++){
            if (i==133){
                System.out.println("|");
            } else if (i==0) {
                System.out.print("|");
            } else {
                System.out.print("=");
            }
        }

        for (Position position : positionList) {
            System.out.format("| %8s", position.getProduct() + "  |");

            String editQuantity = String.format("%1$,3d", position.getQuantity());
            System.out.format("%25s", editQuantity + " |");

            double average = position.getAverageGetPrice().doubleValue();
            String editAverage = String.format("$%,.2f", average);
            System.out.format("%25s", editAverage + " |");

            double profit = position.getActualProfit().doubleValue();
            String editProfit = String.format("$%,.2f", profit);
            System.out.format("%25s", editProfit + " |");

            try {
                double valuation = position.getValuation().doubleValue();
                String editValuation = String.format("$%,.2f", valuation);
                System.out.format("%25s", editValuation + " |");

                double expectedPrice = position.getValuationProfitOrLoss().doubleValue();
                String editExpectedPrice = String.format("$%,.2f", expectedPrice);
                System.out.format("%25s", editExpectedPrice + " |" + "\n");

            } catch (NullPointerException e) {
                System.out.format("%25s", "not calculated" + " |");
                System.out.format("%25s", "not calculated" + " |" + "\n");
            }
        }

        for (int k = 0;k<134;k++){
            if (k==133){
                System.out.println("=");
            } else {
                System.out.print("=");
            }
        }
    }
}
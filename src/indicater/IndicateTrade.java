package indicater;

import editor.Sort;
import instance.Trade;

import java.util.Collections;
import java.util.List;


public class IndicateTrade {
    public static void indicate(List<Trade> tradeList){

        makeLine();

        System.out.format("| %25s", "date" + "         |");
        System.out.format("%11s", "code" + "   |");
        System.out.format("%13s", "buyOrSell" + " |");
        System.out.format("%18s", "quantity" + "     |");
        System.out.format("%16s", "price" + "    |" + "\n");

        makeLine();

        Collections.sort(tradeList, new Sort());
        tradeList.forEach(trade -> {

            System.out.format("| %25s", trade.getDateTime() + "    |");
            System.out.format("%11s", trade.getCode() + "   |");
            System.out.format("%13s", trade.getBuyOrSell() + "    |");

            int numberQuantiy = Integer.parseInt(trade.getQuantity());
            String editQuantity = String.format("%1$,3d", numberQuantiy);
            System.out.format("%18s", editQuantity + " |");

            double numberPrice = trade.getPrice().doubleValue();
            String editPrice = String.format("$%,.2f", numberPrice);
            System.out.format("%16s",  editPrice+ " |" + "\n");

        });

        makeLine();
    }

    public static void makeLine(){
        for(int i = 0;i<84;i++){
            if (i==0){
                System.out.print("|");
            } else if (i==83) {
                System.out.println("|");
            } else {
                System.out.print("=");
            }
        }
    }

}

package indicater;

import instance.Stock;
import java.util.List;
public class IndicateStock {
    public static void indicate(List<Stock> stocks) {

        for (int i = 0; i < stocks.size(); i++) {
            if (i == 0) {
                //表の上部分
                makeHorizontalLine();

                System.out.format("| %8s", stocks.get(i).getCode() + "  |");
                System.out.format("%25s", stocks.get(i).getName() + "        |");
                System.out.format("%13s", stocks.get(i).getMarket() + "   |");
                System.out.format("%20s", stocks.get(i).getSharesIssued() + "    |" + "\n");
                //2行目部分
                makeLine();
            } else {
                //code
                System.out.print("| ");
                System.out.printf("%7s", stocks.get(i).getCode()+"  ");

                //name
                System.out.print("| ");
                if (stocks.get(i).getName().length()>20){
                    String name = stocks.get(i).getName().substring(0,20)+"...";
                    System.out.printf("%-23s", name);
                } else {
                    System.out.printf("%-23s", stocks.get(i).getName());
                }

                //market
                System.out.print("| ");
                System.out.format("%-11s",stocks.get(i).getMarket()+" ");

                //shares_issued
                int numberStock = Integer.parseInt(stocks.get(i).getSharesIssued());
                String editNumber = String.format("%1$,3d", numberStock);

                System.out.print("| ");
                System.out.format("%19s", editNumber + " |" + "\n");
            }
        }
            //表の下部分
            makeHorizontalLine();
    }
    public static void makeHorizontalLine(){
        for (int j = 0; j < 67; j++) {
            if (j == 66) {
                System.out.println("=");
            } else {
                System.out.print("=");
            }
        }
    }
    public static void makeLine(){
        for (int j = 0; j < 67; j++) {
            if (j == 0) {
                System.out.print("|");
            } else if (j == 66) {
                System.out.println("|");
            } else if (j == 9 || j == 34 || j == 47) {
                System.out.print("+");
            } else {
                System.out.print("=");
            }
        }
    }
}
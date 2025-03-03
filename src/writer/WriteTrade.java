package writer;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class WriteTrade {
    public static void writerTrade(LocalDateTime date, String code, String buyOrSell, String quantity, BigDecimal price){
        try {
            // FileWriterクラスのオブジェクトを生成する
            FileWriter file = new FileWriter("C:\\Users\\koshi\\IdeaProjects\\SIMPLEX\\研修課題\\TrainingApp\\tradeData.csv",true);
            // PrintWriterクラスのオブジェクトを生成する
            PrintWriter pw = new PrintWriter(new BufferedWriter(file));

            //ファイルに書き込む
            pw.print(date+"\t"+code+"\t"+buyOrSell+"\t"+quantity+"\t"+price+"\n");

            //ファイルを閉じる
            pw.close();
            System.out.println("取引を記録しました。");
        } catch (IOException e) {
            System.out.println("予期せぬエラーが発生しました。");
        }
    }
}

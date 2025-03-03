package reader;

import instance.Trade;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ReadTrade {

        public static List<Trade> fileReader(){
            List<Trade> trades = new ArrayList<>();
            try {
                // ファイルのパスを指定する
                File file = new File("C:\\Users\\koshi\\IdeaProjects\\SIMPLEX\\研修課題\\TrainingApp\\tradeData.csv");

                // BufferedReaderクラスのreadLineメソッドを使って1行ずつ読み込み表示する
                FileReader fileReader = new FileReader(file);
                BufferedReader bufferedReader = new BufferedReader(fileReader);

                String data;
                while ((data = bufferedReader.readLine()) != null) {
                    String[] datas = data.split("\t");

                    LocalDateTime acceptedDate = LocalDateTime.parse(datas[0]);
                    BigDecimal price = new BigDecimal(datas[4]);
                    Trade trade = new Trade(acceptedDate,datas[1],datas[2],datas[3],price);
                    trades.add(trade);
                }

                // 最後にファイルを閉じてリソースを開放する
                bufferedReader.close();

            } catch (IOException e) {
                System.out.println("予期せぬエラーが発生しました。");
            }catch (DateTimeException f){
                //System.out.println("取引は行われていません。");
            }
            return trades;
        }
}

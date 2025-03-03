package reader;

import instance.Stock;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ReadStock {
    public static List<Stock> fileReader(){
        List<Stock> stocks = new ArrayList<>();
        try {
            // ファイルのパスを指定する
            File file = new File("C:\\Users\\koshi\\IdeaProjects\\SIMPLEX\\研修課題\\TrainingApp\\stock.csv");

            // BufferedReaderクラスのreadLineメソッドを使って1行ずつ読み込み表示する
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String data;
            while ((data = bufferedReader.readLine()) != null) {
                String[] datas = data.split("\t");
                Stock stock = new Stock(datas[0],datas[1],datas[2],datas[3]);
                stocks.add(stock);
            }
            // 最後にファイルを閉じてリソースを開放する
            bufferedReader.close();
        } catch (IOException e) {
            System.out.println("予期せぬエラーが発生しました。");
        }
        return stocks;
    }
}

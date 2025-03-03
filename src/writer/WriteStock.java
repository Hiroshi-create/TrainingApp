package writer;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class WriteStock {
    public static void writer(String name,String code,String market,String sharesIssued){
        try {
            // FileWriterクラスのオブジェクトを生成する
            FileWriter file = new FileWriter("C:\\Users\\koshi\\IdeaProjects\\SIMPLEX\\研修課題\\TrainingApp\\stock.csv",true);
            // PrintWriterクラスのオブジェクトを生成する
            PrintWriter pw = new PrintWriter(new BufferedWriter(file));

            //ファイルに書き込む
            pw.println(code+"\t"+name+"\t"+market+"\t"+sharesIssued);

            //ファイルを閉じる
            pw.close();
        } catch (IOException e) {
            System.out.println("予期せぬエラーが発生しました。");
        }
        System.out.println(name+"を新規銘柄として登録しました。");
    }
}

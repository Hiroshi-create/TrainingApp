package editor;

import instance.Trade;
import reader.ReadTrade;
import java.util.LinkedHashSet;
import java.util.List;

public class PositionCode {
    public static LinkedHashSet<String> positionCode(){

        List<Trade> tradeList = ReadTrade.fileReader();
        LinkedHashSet<String> codeList = new LinkedHashSet<>();

        for(Trade list:tradeList){
            codeList.add(list.getCode());
        }
        return codeList;
    }
}